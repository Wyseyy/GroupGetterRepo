package com.example.groupgetter;


public class LoginRequest {
    String emailOrUsername;
    String password;

    public LoginRequest(String emailOrUsername, String password) {
        this.emailOrUsername = emailOrUsername;
        this.password = password;
    }
}
