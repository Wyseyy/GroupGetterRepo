package com.example.groupgetter;

//Class with the fields for the customer service request
public class CSupportRequest {
    private String name;
    private String email;
    private String message;

    public CSupportRequest(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
}
