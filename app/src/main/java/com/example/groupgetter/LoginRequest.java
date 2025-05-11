package com.example.groupgetter;

//Class representing the structure of a login request with the users username, password and email
public class LoginRequest {
    String emailOrUsername;
    String password;

    public LoginRequest(String emailOrUsername, String password) {
        this.emailOrUsername = emailOrUsername;
        this.password = password;
    }
}
