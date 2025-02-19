package com.example.groupgetter;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import retrofit2.Call;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class HomePageActivity extends AppCompatActivity{
    private BottomNavigationView bottomMenu;
    private RedditApi redditApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        // Initialize the RedditApi instance
        redditApi = Service.getRedditApi();

        // Fetch subreddits related to a topic
        fetchSubreddits("Dungeons and Dragons");

        bottomMenu = findViewById(R.id.navigation_gg);
        bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Check which item in the BottomNavigationView was selected
                switch (item.getItemId()) {
                    // If the CService item was selected
                    case R.id.navigation_cservice:
                        // Create an Intent to launch the CServiceActivity
                        Intent cserviceIntent = new Intent(HomePageActivity.this, CServiceActivity.class);
                        // Start the CServiceActivity
                        startActivity(cserviceIntent);
                        return true;

                    // If the Community item was selected
                    case R.id.navigation_community:
                        // Create an Intent to launch the CommunityActivity
                        Intent communityIntent = new Intent(HomePageActivity.this, CommunityActivity.class);
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

    public void fetchSubreddits(String topic) {
        Call<SubredditSearchResponse> call = redditApi.searchSubreddits(topic);
        call.enqueue(new Callback<SubredditSearchResponse>() {
            @Override
            public void onResponse(Call<SubredditSearchResponse> call, Response<SubredditSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<SubredditSearchResponse.Subreddit> subreddits = response.body().getData();
                    for (SubredditSearchResponse.Subreddit subreddit : subreddits) {
                        fetchFrontPagePosts(subreddit.getDisplay_name());
                    }
                }
            }

            @Override
            public void onFailure(Call<SubredditSearchResponse> call, Throwable t) {
                // Log error
                t.printStackTrace();
            }
        });
    }

    public void fetchFrontPagePosts(String subreddit) {
        Call<RedditPostResponse> call = redditApi.getHotPosts(subreddit);
        call.enqueue(new Callback<RedditPostResponse>() {
            @Override
            public void onResponse(Call<RedditPostResponse> call, Response<RedditPostResponse> response) {
                if (response.isSuccessful()) {
                    List<RedditPostResponse.RedditPost> posts = response.body().getData();
                    for (RedditPostResponse.RedditPost post : posts) {
                        // Process or display posts
                    }
                }
            }

            @Override
            public void onFailure(Call<RedditPostResponse> call, Throwable t) {
                // Log error
                t.printStackTrace();
            }
        });
    }
}
