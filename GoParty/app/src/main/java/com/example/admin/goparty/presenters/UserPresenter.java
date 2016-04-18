package com.example.admin.goparty.presenters;

import android.content.Context;

import com.example.admin.goparty.common.MyApplication;
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

    String responseMessage = "";

    MyApplication myApplication = MyApplication.getInstance();

    public String registerUser(final User user){

        RequestRegisterUserModel requestRegisterUserModel = new RequestRegisterUserModel(user.getEmail(), user.getPassword(), user.getPassword());

        Call<Void> call = ApiInterface.service.addUser(requestRegisterUserModel);

        try {
            Void response = call.execute().body();
            responseMessage = "Success";
        } catch (IOException e) {
            e.printStackTrace();
            responseMessage = "Error";
        }
        return responseMessage;
    }

    public String loginUser(User user, Context context){

        Call<LoginUserResponseModel> call = ApiInterface.service.loginUser(user.getEmail(), user.getPassword(), "password", user.getEmail());

        try {
            LoginUserResponseModel response = call.execute().body();
            myApplication.getSqlDb().deleteContact();
            if (response != null) {
                String token = response.getAccessToken();
                String userName = response.getUserName();
                myApplication.getSqlDb().deleteContact();
                myApplication.getSqlDb().insertUser(token, userName);
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
