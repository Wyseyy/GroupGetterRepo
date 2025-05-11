package com.example.groupgetter;

//Class representing a message structure with message content and the username of the sender
public class Message {
    private String message;
    private String username;

    public Message(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}
