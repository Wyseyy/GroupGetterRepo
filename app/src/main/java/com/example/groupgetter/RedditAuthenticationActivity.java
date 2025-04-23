package com.example.groupgetter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class RedditAuthenticationActivity extends AppCompatActivity {
    private WebView webView;
    private final String redirectURI = "http://groupgetter://redditredirect";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.redditView);
        webView.getSettings().setJavaScriptEnabled(true);

        String authUrl = "https://www.reddit.com/api/v1/authorize.compact"
                + "?client_id=ZlbcDAFm9zETA6KWcxLAoA"
                + "&response_type=code"
                + "&state=randomState123"
                + "&redirect_uri=" + redirectURI
                + "&duration=temporary"
                + "&scope=read";

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith(redirectURI)){
                     Uri uri = Uri.parse(url);
                     String code = uri.getQueryParameter("code");
                     if (code != null){
                         Intent redditIntent = new Intent();
                         redditIntent.putExtra("authCode", code);
                         setResult(RESULT_OK, redditIntent);
                         finish();
                    }
                     return true;
                }
                return false;
            }
        });
        webView.loadUrl(authUrl);
    }

}
