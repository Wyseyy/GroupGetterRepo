package com.example.groupgetter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private Button returnToRedditBtn;
    private Button returnToTumblrBtn;
    private Button connectBtn;
    private Button connectBtn2;
    private WebView redditWebView;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        bottomMenu = findViewById(R.id.navigation_gg);
        returnToRedditBtn = findViewById(R.id.returnToRedditBtn);
        returnToTumblrBtn = findViewById(R.id.returnToTumblrBtn);
        connectBtn = findViewById(R.id.connectBtn);
        connectBtn2 = findViewById(R.id.connectBtn2);
        redditWebView = findViewById(R.id.redditWebView);
        progress = findViewById(R.id.progress);

        redditWebView.getSettings().setJavaScriptEnabled(true);
        redditWebView.getSettings().setDomStorageEnabled(true);
        redditWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        redditWebView.setWebChromeClient(new WebChromeClient());
        redditWebView.setVisibility(View.GONE);
        redditWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
                return false;
            }
            @Override
            public void onPageStarted(WebView view, String url, android.graphics.Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progress.setVisibility(View.VISIBLE);
                redditWebView.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progress.setVisibility(View.GONE);
                redditWebView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                super.onReceivedError(view, request, error);
                progress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load", Toast.LENGTH_SHORT).show();
            }

        });
        connectBtn.setOnClickListener(view -> {
            redditWebView.loadUrl("https://www.reddit.com/search/");
            redditWebView.setVisibility(View.VISIBLE);
            returnToRedditBtn.setVisibility(View.VISIBLE);
            returnToTumblrBtn.setVisibility(View.GONE);
        });

        //button to allow a user to return to the reddit homepage after searching for a keyword
        returnToRedditBtn.setOnClickListener(view -> {
            redditWebView.loadUrl("https://www.reddit.com/search/");
            redditWebView.setVisibility(View.VISIBLE);
        });

        connectBtn2.setOnClickListener(view -> {
            redditWebView.loadUrl("https://www.tumblr.com/search/");
            redditWebView.setVisibility(View.VISIBLE);
            returnToTumblrBtn.setVisibility(View.VISIBLE);
            returnToRedditBtn.setVisibility(View.GONE);
        });

        returnToTumblrBtn.setOnClickListener(view -> {
            redditWebView.loadUrl("https://www.tumblr.com/search/");
            redditWebView.setVisibility(View.VISIBLE);
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