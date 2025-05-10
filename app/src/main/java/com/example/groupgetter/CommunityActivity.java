package com.example.groupgetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends AppCompatActivity {

    private EditText communityMessage;
    private Button communityButton;
    private RecyclerView recyclerView;
    private CommunityAdapter adapter;
    public ArrayList<Message> messages;
    private BottomNavigationView bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        communityMessage = findViewById(R.id.communityMessage);
        communityButton = findViewById(R.id.communityButton);
        recyclerView = findViewById(R.id.recyclerView);
        bottomMenu = findViewById(R.id.navigation_gg);

        messages = new ArrayList<>();
        adapter = new CommunityAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load messages from server
        loadMessagesFromServer();

        communityButton.setOnClickListener(view -> {
            String messageText = communityMessage.getText().toString().trim();

            if (!messageText.isEmpty()) {
                String username = getSharedPreferences("MyPrefs", MODE_PRIVATE).getString("username", "User");
                Message newMessage = new Message(messageText, username);

                // Send message to server
                ClientRetrofitCommunity.INSTANCE.getApi().postMessages(newMessage).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            messages.add(newMessage);
                            adapter.notifyItemInserted(messages.size() - 1);
                            communityMessage.setText("");
                        } else {
                            Toast.makeText(CommunityActivity.this, "Failed to post message", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CommunityActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(CommunityActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });

        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(CommunityActivity.this, MainActivity.class));
                        return true;
                    case R.id.navigation_cservice:
                        startActivity(new Intent(CommunityActivity.this, CServiceActivity.class));
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void loadMessagesFromServer() {
        ClientRetrofitCommunity.INSTANCE.getApi().getMessages().enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messages.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CommunityActivity.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Toast.makeText(CommunityActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

