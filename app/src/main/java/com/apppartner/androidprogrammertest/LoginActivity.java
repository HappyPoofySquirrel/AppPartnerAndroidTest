package com.apppartner.androidprogrammertest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apppartner.androidprogrammertest.controllers.AuthController;

public class LoginActivity extends BaseActivity {

    public Context mContext;

    TextView usernameV;

    TextView passwordV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setSupportActionBar(toolbar);
        mContext = this;

        usernameV = (TextView) findViewById(R.id.et_login_username);
        passwordV = (TextView) findViewById(R.id.et_login_password);

        String fontPath = "fonts/Jelloween - Machinato.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        usernameV.setTypeface(tf);
        passwordV.setTypeface(tf);
    }

    public void onLoginClicked(View view) {
        AuthController authController = new AuthController();
        authController.login(mContext, "AppPartner", "qwerty");

//        String username = usernameV.getText().toString();
//        String password = passwordV.getText().toString();
//
//        if (username.isEmpty()) {
//            usernameV.setError("Username cannot be blank");
//        } else if (password.isEmpty()) {
//            passwordV.setError("Password cannot be blank");
//        } else {
//            authController.login(mContext, username, password);
//        }

    }



}
