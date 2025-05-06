package com.example.groupgetter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CServiceActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private EditText CSname;
    private EditText CSemail;
    private EditText CSmessage;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cservice); // This connects to activity_cservice.xml

        bottomMenu = findViewById(R.id.navigation_gg);

        CSname = findViewById(R.id.name_input);
        CSemail = findViewById(R.id.email_input);
        CSmessage = findViewById(R.id.message_input);
        submit = findViewById(R.id.submit_button);
        bottomMenu = findViewById(R.id.navigation_gg);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = CSname.getText().toString().trim();
                String email = CSemail.getText().toString().trim();
                String message = CSmessage.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                    Toast.makeText(CServiceActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                CServiceRequest request = new CServiceRequest(name, email, message);

                ClientRetrofit.INSTANCE.getApi().sendMessage(request).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(CServiceActivity.this, "Message sent successfully!", Toast.LENGTH_SHORT).show();
                            CSname.setText("");
                            CSemail.setText("");
                            CSmessage.setText("");
                        } else {
                            Toast.makeText(CServiceActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(CServiceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the Main item was selected
                    case R.id.navigation_main:
                        // Create an Intent to launch the MainActivity
                        Intent mainIntent = new Intent(CServiceActivity.this, MainActivity.class);
                        // Start the HomeActivity
                        startActivity(mainIntent);
                        return true;

                    // If the Community item was selected
                    case R.id.navigation_community:
                        // Create an Intent to launch the CommunityActivity
                        Intent communityIntent = new Intent(CServiceActivity.this, CommunityActivity.class);
                        // Start the CommunityActivity
                        startActivity(communityIntent);
                        return true;

                    // If no item was selected, return false
                    default:
                        return false;
                }
            }
        });

    }
}
