package com.example.todoapp.preference;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref(SharedPref.Scope.UNIQUE)
public interface UserPreference {

    long userId();

    String accessToken();

}
