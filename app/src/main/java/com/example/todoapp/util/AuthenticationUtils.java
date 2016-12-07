package com.example.todoapp.util;

import android.util.Log;

import org.springframework.util.LinkedMultiValueMap;

public class AuthenticationUtils {

    public static final String TAG = AuthenticationUtils.class.getSimpleName();

    public static LinkedMultiValueMap<String, String> packUserCredentials(String email, String password) {
        final LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("grant_type", "password");
        map.set("username", email);
        map.set("password", password);

        Log.i(TAG, "User credentials for " + email + " packed.");
        return map;
    }

}