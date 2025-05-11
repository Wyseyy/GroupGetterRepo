package com.example.groupgetter;

//class for testing
public class TestMessage {
    public static Message createMessage(String content, String username) {
        if (content == null || content.trim().isEmpty()) {
            return null;
        }
        return new Message(content.trim(), username != null ? username : "User");
    }
}
