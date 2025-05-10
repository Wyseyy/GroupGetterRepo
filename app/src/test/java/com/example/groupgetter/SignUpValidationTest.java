package com.example.groupgetter;

import org.junit.Test;
import static org.junit.Assert.*;

public class SignUpValidationTest {

    @Test
    public void validEmail_returnsTrue() {
        assertTrue(ValidationTest.isValidEmail("test@example.com"));
    }

    @Test
    public void invalidEmail_returnsFalse() {
        assertFalse(ValidationTest.isValidEmail("invalid-email"));
    }

    @Test
    public void nullEmail_returnsFalse() {
        assertFalse(ValidationTest.isValidEmail(null));
    }
}

