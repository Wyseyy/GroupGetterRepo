package com.example.groupgetter;

import java.util.List;

//class to represent the response from a reddit request,
//returning a list of reddit posts with their details
    public class RedditPostResponse {
        private List<RedditPost> data;
        public List<RedditPost> getData() {
            return data;
        }

        public class RedditPost {

            private String title;
            private String url;
            private String author;

            public RedditPost(String title, String url, String author) {
                this.title = title;
                this.url = url;
                this.author = author;
            }
        }
    }
