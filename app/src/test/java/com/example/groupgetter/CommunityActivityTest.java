package com.example.groupgetter;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommunityActivityTest {
    @Test
    public void testCreateMessage_validInput() {
        String content = "Hello community!";
        String username = "Eoin";

        Message message = TestMessage.createMessage(content, username);

        assertNotNull(message);
        assertEquals("Hello community!", message.getMessage());
        assertEquals("Eoin", message.getUsername());
    }

    @Test
    public void testCreateMessage_nullContent() {
        Message message = TestMessage.createMessage(null, "User");
        assertNull(message);
    }

    @Test
    public void testCreateMessage_emptyContent() {
        Message message = TestMessage.createMessage("   ", "User");
        assertNull(message);
    }

    @Test
    public void testCreateMessage_nullUsername() {
        Message message = TestMessage.createMessage("Hi!", null);
        assertNotNull(message);
        assertEquals("User", message.getUsername());
    }
}
