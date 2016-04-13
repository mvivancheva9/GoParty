package com.example.admin.goparty.presenters;

import android.database.Observable;

import com.example.admin.goparty.models.LoginUserResponseModel;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.PartyRequestModel;
import com.example.admin.goparty.models.RequestRegisterUserModel;
import com.example.admin.goparty.models.ResponsePartyListModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiInterface {

    @POST("/api/Account/Register")
    Call<Void> addUser(@Body RequestRegisterUserModel userToRegister);

    @FormUrlEncoded
    @POST("/Token")
    Call<LoginUserResponseModel> loginUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("Grant_type") String grantype,
            @Field("userName") String userName);

    @GET("/api/Parties")
    Call<List<Party>> getParties();

    @POST("/api/Parties")
    Call<Party> addParty(@Body PartyRequestModel partyRequestModel);

    @GET("/api/Parties/Party/{id}")
    Call<Party> getParty(@Path("id") int id);
}
