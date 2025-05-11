// SplashActivity.java
package com.example.groupgetter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

//class for the splash screen that appears prior to the app opening
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay before launching MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Launch LoginActivity after the splash screen
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close SplashActivity
            }
        }, 300);
    }
}
