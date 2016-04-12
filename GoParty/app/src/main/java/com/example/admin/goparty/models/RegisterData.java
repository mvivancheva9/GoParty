package com.example.admin.goparty.models;

public class RegisterData {
    final String email;
    final String password;
    final String confirmPassword;

    public RegisterData(String email, String password, String confirmPassword) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
