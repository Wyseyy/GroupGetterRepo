package com.example.groupgetter;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private EditText searchEText;
    private Button searchBtn;
    private Button returnToRedditBtn;
    private Button connectBtn;
    private RecyclerView recyclerView;
    private String accessToken;
    private GGAdapter adapter;
    private WebView redditWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        bottomMenu = findViewById(R.id.navigation_gg);
        returnToRedditBtn = findViewById(R.id.returnToRedditBtn);
        connectBtn = findViewById(R.id.connectBtn);
        redditWebView = findViewById(R.id.redditWebView);

        redditWebView.getSettings().setJavaScriptEnabled(true);
        redditWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        connectBtn.setOnClickListener(view -> {
            redditWebView.stopLoading();
            redditWebView.clearHistory();
            redditWebView.clearCache(true);
            redditWebView.loadUrl("https://www.reddit.com/");
        });

        //button to allow a user to return to the reddit homepage after searching for a keyword
        returnToRedditBtn.setOnClickListener(view -> {
            redditWebView.setVisibility(View.VISIBLE);
            returnToRedditBtn.setVisibility(View.GONE);
            redditWebView.loadUrl("https://www.reddit.com/");
        });

        // Set the OnNavigationItemSelectedListener of the BottomNavigationView
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {

                    // If the Community item was selected
                    case R.id.navigation_community:
                        // Create an Intent to launch the CommunityActivity
                        Intent communityIntent = new Intent(MainActivity.this, CommunityActivity.class);
                        // Start the CommunityActivity
                        startActivity(communityIntent);
                        return true;

                    // If the Customer Service item was selected
                    case R.id.navigation_cservice:
                        // Create an Intent to launch the CServiceActivity
                        Intent cserviceIntent = new Intent(MainActivity.this, CServiceActivity.class);
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