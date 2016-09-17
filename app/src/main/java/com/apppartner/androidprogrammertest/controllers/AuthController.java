package com.apppartner.androidprogrammertest.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.apppartner.androidprogrammertest.services.AuthServices;
import com.apppartner.androidprogrammertest.MainActivity;
import com.apppartner.androidprogrammertest.models.LoginResponse;

import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthController {

    Retrofit retrofit;
    AuthServices authServices;

    public AuthController() {
        retrofit = retrofitBuilder();
        authServices = retrofit.create(AuthServices.class);
    }

    public void login(final Context mContext, String username, String password) {

        Call<LoginResponse> call = authServices.getToken(username, password);

        final long startTime = System.currentTimeMillis();

        call.enqueue(new retrofit2.Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                long timeDiff = System.currentTimeMillis() - startTime;

                if (response.isSuccessful()) {

                    oneBtnAlert(mContext, response.body(), timeDiff, true);

                } else {
                    try {
                        Converter<ResponseBody, LoginResponse> errorConverter = retrofit.responseBodyConverter(LoginResponse.class, new Annotation[]{});
                        LoginResponse error = errorConverter.convert(response.errorBody());
                        oneBtnAlert(mContext, error, timeDiff, false);
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(mContext, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void oneBtnAlert(final Context mContext, LoginResponse loginResponse, long timeDiff, final Boolean isSuccessful) {

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(loginResponse.getCode())
                .setMessage(loginResponse.getMessage() + " Duration: " + String.valueOf(timeDiff) + " Milliseconds")
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isSuccessful) {
                            Intent intent = new Intent(mContext, MainActivity.class);
                            mContext.startActivity(intent);
                        }
                    }
                })
                .create();
        dialog.show();
    }


    public Retrofit retrofitBuilder() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.interceptors().add(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dev3.apppartner.com/AppPartnerDeveloperTest/scripts/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;
    }
}
