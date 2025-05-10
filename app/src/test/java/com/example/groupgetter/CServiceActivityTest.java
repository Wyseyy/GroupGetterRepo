package com.example.groupgetter;

import org.junit.Test;
import static org.junit.Assert.*;

public class CServiceActivityTest {

    private boolean isValidInput(String name, String email, String message) {
        return !(name == null || name.isEmpty() ||
                email == null || email.isEmpty() ||
                message == null || message.isEmpty());
    }

    @Test
    public void testValidInput_allFieldsFilled_returnsTrue() {
        assertTrue(isValidInput("Eoin", "Eoin@example.com", "Help me"));
    }

    @Test
    public void testInvalidInput_missingName_returnsFalse() {
        assertFalse(isValidInput("", "Eoin@example.com", "Help me"));
    }

    @Test
    public void testInvalidInput_missingEmail_returnsFalse() {
        assertFalse(isValidInput("Eoin", "", "Help me"));
    }

    @Test
    public void testInvalidInput_missingMessage_returnsFalse() {
        assertFalse(isValidInput("Eoin", "Eoin@example.com", ""));
    }
}
