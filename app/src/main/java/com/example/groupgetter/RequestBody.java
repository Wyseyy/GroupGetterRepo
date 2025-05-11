package com.example.groupgetter;

//class representing a request body with a key
public class RequestBody {
    private String key;

    // Constructor
    public RequestBody(String key) {
        this.key = key;
    }

    // Getters and Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
