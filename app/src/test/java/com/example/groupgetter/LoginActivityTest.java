package com.example.groupgetter;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test
    public void validEmailAndPassword_returnsTrue() {
        assertTrue(LoginValidationTest.isValidLogin("test@example.com", "", "password123"));
    }

    @Test
    public void validUsernameAndPassword_returnsTrue() {
        assertTrue(LoginValidationTest.isValidLogin("", "user123", "password123"));
    }

    @Test
    public void invalidEmail_returnsFalse() {
        assertFalse(LoginValidationTest.isValidLogin("invalid-email", "", "password123"));
    }

    @Test
    public void emptyFields_returnsFalse() {
        assertFalse(LoginValidationTest.isValidLogin("", "", ""));
    }

    @Test
    public void nullPassword_returnsFalse() {
        assertFalse(LoginValidationTest.isValidLogin("test@example.com", "", null));
    }

    @Test
    public void nullEmailAndUsername_returnsFalse() {
        assertFalse(LoginValidationTest.isValidLogin(null, null, "password"));
    }
}







