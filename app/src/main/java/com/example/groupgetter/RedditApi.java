package com.example.groupgetter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RedditApi {
    @GET("subreddits/search.json")
    Call<SubredditSearchResponse> searchSubreddits(@Query("q") String query);
    @POST("r/{subreddit}/hot.json")
    Call<RedditPostResponse> getHotPosts(@Query("subreddit") String subreddit);
}


