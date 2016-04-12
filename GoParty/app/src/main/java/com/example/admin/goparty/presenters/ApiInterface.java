package com.example.admin.goparty.presenters;

import com.example.admin.goparty.models.LoginUserResponseModel;
import com.example.admin.goparty.models.RequestRegisterUserModel;
import com.example.admin.goparty.models.ResponsePartyListModel;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface ApiInterface {
    @POST("/api/Account/Register")
    Call<HttpBinResponse> addUser(@Body RequestRegisterUserModel requestRegisterUserModel);

    @FormUrlEncoded
    @POST("/Token")
    Call<LoginUserResponseModel> loginUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("Grant_type") String grantype,
            @Field("userName") String userName);

    @GET("/api/Parties")
    Call<ResponsePartyListModel> getParties();
}
