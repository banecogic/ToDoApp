package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.api.RestApi;
import com.example.todoapp.model.User;
import com.example.todoapp.model.dto.RegisterDTO;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.ResponseEntity;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @ViewById
    EditText email;

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @RestService
    RestApi restApi;

    @EditorAction(R.id.password)
    @Click
    void register() {
        final String email = this.email.getText().toString();
        final String username = this.username.getText().toString();
        final String password = this.password.getText().toString();
        final User user = new User(email, username, password);

        registerUser(user);
    }

    @Background
    void registerUser(User user) {
        try {
            ResponseEntity responseEntity = restApi.register(new RegisterDTO(user));
            if(responseEntity.getStatusCode().is2xxSuccessful())
                registerSuccess();
            else
                registerFailed(new Exception("Status code of register response is " + responseEntity.getStatusCode().getReasonPhrase()));
        } catch (Exception e) {
            registerFailed(e);
        }
    }

    @UiThread
    void registerSuccess() {
        final Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @UiThread
    void registerFailed(Exception e) {
        Toast.makeText(this, "Something went wrong. Probably network error.", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Something went wrong while registering. Exception message: \n\t" + e.getMessage(), e);
    }
}
