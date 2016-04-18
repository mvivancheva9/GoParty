package com.example.admin.goparty.presenters;

import com.example.admin.goparty.common.MyApplication;
import com.example.admin.goparty.models.LoginUserResponseModel;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.PartyRequestModel;
import com.example.admin.goparty.models.RequestRegisterUserModel;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ApiInterface {

    MyApplication myApplication = MyApplication.getInstance();

    ApiInterface service = myApplication.getRetrofit().create(ApiInterface.class);

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
