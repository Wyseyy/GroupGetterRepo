package com.example.groupgetter;
import androidx.appcompat.app.AppCompatActivity;
import com.example.groupgetter.RedditApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
public class HomePageActivity extends AppCompatActivity{
    private RedditApi redditApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the RedditApi instance
        redditApi = Service.getRedditApi();

        // Fetch subreddits related to a topic
        fetchSubreddits("Dungeons and Dragons");
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
