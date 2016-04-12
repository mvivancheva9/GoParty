package com.example.admin.goparty.presenters;

import com.example.admin.goparty.models.RegisterData;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {
    @POST("/api/Account/Register")
    Call<HttpBinResponse> addUser(@Body RegisterData registerData);

    @GET("/api/Parties")
    Call<HttpBinResponse> getParties();
}
