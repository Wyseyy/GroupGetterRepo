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

public class CommunityActivity extends AppCompatActivity {

    private EditText communityMessage;
    private Button communityButton;
    private RecyclerView recyclerView;
    private CommunityAdapter adapter;
    private ArrayList<String> messages;
    private BottomNavigationView bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community); // This connects to activity_community.xml

        communityMessage = findViewById(R.id.communityMessage);
        communityButton = findViewById(R.id.communityButton);
        recyclerView = findViewById(R.id.recyclerView);
        bottomMenu = findViewById(R.id.navigation_gg);

        messages = new ArrayList<>();
        adapter = new CommunityAdapter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        communityButton.setOnClickListener(view -> {
            String message = communityMessage.getText().toString().trim();

            if(!message.isEmpty()){
                messages.add(message);
                adapter.notifyItemInserted(messages.size() -1);
                communityMessage.setText("");
            } else {
                Toast.makeText(CommunityActivity.this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the Main item was selected
                    case R.id.navigation_home:
                        // Create an Intent to launch the MainActivity
                        Intent mainIntent = new Intent(CommunityActivity.this, MainActivity.class);
                        // Start the MainActivity
                        startActivity(mainIntent);
                        return true;

                    // If the CService item was selected
                    case R.id.navigation_cservice:
                        // Create an Intent to launch the CServiceActivity
                        Intent cserviceIntent = new Intent(CommunityActivity.this, CServiceActivity.class);
                        // Start the CServiceActivity
                        startActivity(cserviceIntent);
                        return true;

                    // If no item was selected, return false
                    default:
                        return false;
                }
            }
        });


    }
}