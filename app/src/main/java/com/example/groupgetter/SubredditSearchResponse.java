package com.example.groupgetter;

import java.util.List;

public class SubredditSearchResponse {

    private List<SubredditSearchResponse.Subreddit> data;
    public List<SubredditSearchResponse.Subreddit> getData() {
        return data;
    }

    public class Subreddit{
        private String display_name;
        public String getDisplay_name() {
            return display_name;
        }
    }
}

