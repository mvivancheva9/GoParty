package com.example.admin.goparty.presenters;

import android.content.Context;

import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.models.LoginUserResponseModel;
import com.example.admin.goparty.models.RequestRegisterUserModel;
import com.example.admin.goparty.models.User;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class UserPresenter {

    private SqLiteDbHelper sqlDb;

    ApiInterface service = ApiInterface.retrofit.create(ApiInterface.class);

    String responseMessage = "";

    public String registerUser(final User user){

        RequestRegisterUserModel requestRegisterUserModel = new RequestRegisterUserModel(user.getEmail(), user.getPassword(), user.getPassword());

        Call<Void> call = service.addUser(requestRegisterUserModel);

        try {
            call.execute().body();
                responseMessage = "Success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }

    public String loginUser(User user, Context context){

        sqlDb = new SqLiteDbHelper(context);
        Call<LoginUserResponseModel> call = service.loginUser(user.getEmail(), user.getPassword(), "password", user.getEmail());

        try {
            LoginUserResponseModel response = call.execute().body();
            sqlDb.deleteContact();
            if (response != null) {
                String token = response.getAccessToken();
                String userName = response.getUserName();
                sqlDb.deleteContact();
                sqlDb.insertUser(token, userName);
                responseMessage = "Success";
            }
            else{
                responseMessage = "The user name or password is incorrect.";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }
}
