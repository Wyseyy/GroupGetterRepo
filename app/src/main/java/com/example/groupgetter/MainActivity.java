package com.example.groupgetter;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        bottomMenu = findViewById(R.id.navigation_gg);
        //Create an Intent to launch the LoginActivity
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);

        //Start the LoginActivity
        startActivity(loginIntent);
        finish();


// Set the OnNavigationItemSelectedListener of the BottomNavigationView
        bottomMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        // Check which item in the BottomNavigationView was selected
                        switch (item.getItemId()) {
                            // If the Home item was selected
                            case R.id.navigation_home:
                                // Create an Intent to launch the HomePageActivity
                                Intent homeIntent = new Intent(MainActivity.this, HomePageActivity.class);
                                // Start the HomePageActivity
                                startActivity(homeIntent);
                                return true;
                            // If the Customer Service item was selected
                            case R.id.navigation_cservice:
                                // Create an Intent to launch the CServiceActivity
                                Intent CServiceIntent = new Intent(MainActivity.this, CServiceActivity.class);
                                // Start the CServiceActivity
                                startActivity(CServiceIntent);
                                return true;
                            // If the Recipes item was selected
                            case R.id.navigation_community:
                                //Create an Intent to launch the CommunityActivity
                                Intent CommunityIntent = new Intent(MainActivity.this, CommunityActivity.class);
                                //Start the CommunityActivity
                                startActivity(CommunityIntent);
                                return true;
                            //If no item was selected, return false
                            default:
                                return false;
                        }
                    }
                });
    }
}
