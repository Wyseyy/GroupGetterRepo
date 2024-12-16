package com.example.groupgetter;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
public class CommunityActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community); // This connects to activity_community.xml

        bottomMenu = findViewById(R.id.navigation_gg);
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the Home item was selected
                    case R.id.navigation_home:
                        // Create an Intent to launch the HomeActivity
                        Intent homeIntent = new Intent(CommunityActivity.this, HomePageActivity.class);
                        // Start the HomeActivity
                        startActivity(homeIntent);
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