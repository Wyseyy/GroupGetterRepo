package com.example.groupgetter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static final String BASE_URL = "https://www.reddit.com/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static RedditApi getRedditApi() {
        return getRetrofit().create(RedditApi.class);
    }
}
