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

    String url = "http://goparty.apphb.com";
    private SqLiteDbHelper sqlDb;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface service = retrofit.create(ApiInterface.class);

    public void registerUser(final User user, final Context context){

        RequestRegisterUserModel requestRegisterUserModel = new RequestRegisterUserModel(user.getEmail(), user.getPassword(), user.getPassword());

        Call<Void> call = service.addUser(requestRegisterUserModel);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {

                loginUser(user, context);
            }
            @Override
            public void onFailure(Throwable caught) {
                // Convenient way to find out which exception was thrown.
                try {
                    throw caught;
                } catch (IOException e) {
                    String message = e.getMessage();
                } catch (Throwable e) {
                    // last resort -- a very unexpected exception
                }
            }
        });
    }

    public void loginUser(User user, Context context){

        sqlDb = new SqLiteDbHelper(context);
        Call<LoginUserResponseModel> call = service.loginUser(user.getEmail(), user.getPassword(), "password", user.getEmail());

        call.enqueue(new Callback<LoginUserResponseModel>() {
            @Override
            public void onResponse(Response<LoginUserResponseModel> response) {
                String token = response.body().getAccessToken();
                String userName = response.body().getUserName();
                sqlDb.deleteContact();
                sqlDb.insertUser(token, userName);

            }
            @Override
            public void onFailure(Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.getMessage());
            }
        });
    }
}
