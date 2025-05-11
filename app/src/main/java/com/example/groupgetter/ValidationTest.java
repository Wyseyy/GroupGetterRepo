package com.example.groupgetter;

import java.util.regex.Pattern;

//class used for testing
public class ValidationTest {

    private static final Pattern emailPattern =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public static boolean isValidEmail(String email) {
        return email != null && emailPattern.matcher(email).matches();
    }
}
