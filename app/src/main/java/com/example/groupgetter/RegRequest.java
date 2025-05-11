package com.example.groupgetter;

//class that represents a request to register the user
public class RegRequest {
    private String email;
    private String username;
    private String password;

    public RegRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
