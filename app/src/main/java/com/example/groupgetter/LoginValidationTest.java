package com.example.groupgetter;

import java.util.regex.Pattern;

//class used for login testing
public class LoginValidationTest {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean isValidLogin(String email, String username, String password) {
        String emailOrUsername = (email != null && !email.trim().isEmpty())
                ? email.trim()
                : (username != null ? username.trim() : "");

        if (emailOrUsername.isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }

        // If logging in with email, check email format
        if (!username.trim().isEmpty() && email.trim().isEmpty()) {
            return true; // Valid username login
        } else {
            return EMAIL_PATTERN.matcher(emailOrUsername).matches(); // Email format check
        }
    }
}
