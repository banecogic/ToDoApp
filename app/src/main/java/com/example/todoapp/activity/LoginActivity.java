package com.example.todoapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.api.RestApi;
import com.example.todoapp.model.dto.Token;
import com.example.todoapp.util.AuthenticationUtils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @RestService
    RestApi restApi;

    @EditorAction(R.id.password)
    @Click
    void login() {
        final String email = this.email.getText().toString();
        final String password = this.password.getText().toString();

        tryLogin(email, password);
    }

    @Click
    void register() {
        RegisterActivity_.intent(this).startForResult(1);
    }

    @Background
    void tryLogin(String email, String password) {
        try {
            Log.i(TAG, "Trying to log in.");
            final Token token = restApi.login(AuthenticationUtils.packUserCredentials(email, password));
            loginSuccess(token.getAccessToken());
        } catch(Exception e) {
            loginFailed(e);
        }
    }

    @UiThread
    void loginFailed(Exception e){
        Toast.makeText(this, "Something went wrong. Probably wrong credentials.", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Failed to log in. Exception message: \n\t" + e.getMessage(), e);
    }

    @UiThread
    void loginSuccess(String accessToken) {
        Log.i(TAG, "Successfully logged in.");

        final Intent intent = new Intent();
        intent.putExtra("token", accessToken);

        setResult(RESULT_OK, intent);
        finish();
    }
}
