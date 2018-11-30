package com.adeliosys.sample;

import java.util.Date;

@SuppressWarnings("all")
public class MethodReferenceBeforeSample {

    class User {
        private String email;
        private Date birthDate;
        private int age;

        // Other attributes and getters/setters...

        public String getEmail() {
            return email;
        }
    }

    String getCurrentUserEmail() {
        User user = getCurrentUser();
        if (user != null) {
            // The only interesting part
            return user.getEmail();
        }
        else {
            return null;
        }
    }

    // Same for birth date, age, etc...

    private User getCurrentUser() {
        return null;
    }
}
