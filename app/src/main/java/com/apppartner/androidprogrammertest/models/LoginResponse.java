
package com.apppartner.androidprogrammertest.models;

import com.google.gson.annotations.SerializedName;


import com.google.gson.annotations.Expose;

public class LoginResponse {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private String message;


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

}
