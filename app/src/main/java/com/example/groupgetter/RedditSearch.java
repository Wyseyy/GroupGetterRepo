package com.example.groupgetter;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RedditSearch extends AppCompatActivity {
    private static final String BASE_URL = "https://www.reddit.com/";

    public RedditApi getRedditApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RedditApi.class);
    }
}
