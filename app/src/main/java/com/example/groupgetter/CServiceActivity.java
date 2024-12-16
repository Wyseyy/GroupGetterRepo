package com.example.groupgetter;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class CServiceActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cservice); // This connects to activity_cservice.xml

        bottomMenu = findViewById(R.id.navigation_gg);

        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the Home item was selected
                    case R.id.navigation_home:
                        // Create an Intent to launch the HomeActivity
                        Intent homeIntent = new Intent(CServiceActivity.this, HomePageActivity.class);
                        // Start the HomeActivity
                        startActivity(homeIntent);
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
