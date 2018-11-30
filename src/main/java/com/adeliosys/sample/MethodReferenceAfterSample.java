package com.adeliosys.sample;

import java.util.Date;
import java.util.function.Function;

@SuppressWarnings("all")
public class MethodReferenceAfterSample {

    class User {
        public String getEmail() {
            return null;
        }

        public Date getBirthDate() {
            return null;
        }

        public int getAge() {
            return 0;
        }
    }

    String getCurrentUserEmail() {
        return getValue(User::getEmail);
    }

    private <T> T getValue(Function<User, T> getter) {
        User user = getCurrentUser();
        if (user != null) {
            return getter.apply(user);
        } else {
            return null;
        }
    }

    int getCurrentUserAge() {
        return getValue(User::getAge);
    }

    private User getCurrentUser() {
        return null;
    }
}
