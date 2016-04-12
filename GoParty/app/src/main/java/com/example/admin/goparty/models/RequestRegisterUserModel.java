package com.example.admin.goparty.models;

public class RequestRegisterUserModel {
   String email;
   String password;
   String confirmPassword;

    public RequestRegisterUserModel(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
