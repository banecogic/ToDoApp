package com.example.todoapp.api.interceptor;

import android.util.Log;

import com.example.todoapp.preference.UserPreference;
import com.example.todoapp.preference.UserPreference_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@EBean
public class AuthenticationInterceptor implements ClientHttpRequestInterceptor{

    public static final String TAG = AuthenticationInterceptor.class.getSimpleName();

    @Pref
    UserPreference_ userPreference;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        Log.i(TAG, "Request on " + request.getURI().toString() + " is intercepted for authentication.");
        String authorization = "bearer " + userPreference.accessToken().get();
        Log.i(TAG, "The token is \n\t" + authorization);
        request.getHeaders().add("Authorization", authorization);
        return execution.execute(request, body);
    }
}
