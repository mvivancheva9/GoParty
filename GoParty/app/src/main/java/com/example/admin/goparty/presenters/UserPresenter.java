package com.example.admin.goparty.presenters;

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

    String url = "http://goparty.azurewebsites.net";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiInterface service = retrofit.create(ApiInterface.class);

    public void registrationProcessWithRetrofit(User user){

        RequestRegisterUserModel requestRegisterUserModel = new RequestRegisterUserModel(user.getUsername(), user.getPassword(), user.getPassword());
        // Prepare the HTTP request
        Call<HttpBinResponse> call = service.addUser(requestRegisterUserModel);

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<HttpBinResponse>() {
            /**
             * onResponse is called when any kind of response has been received.
             */
            @Override
            public void onResponse(Response<HttpBinResponse> response) {
                // http response status code + headers
                System.out.println("Response status code: " + response.code());

                // isSuccess is true if response code => 200 and <= 300
                if (!response.isSuccess()) {
                    // print response body if unsuccessful
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        // do nothing
                    }
                    return;
                }

                // if parsing the JSON body failed, `response.body()` returns null
                HttpBinResponse decodedResponse = response.body();
                if (decodedResponse == null) return;

                // at this point the JSON body has been successfully parsed
                System.out.println("Response (contains request infos):");
                System.out.println("- url:         " + decodedResponse.url);
                System.out.println("- ip:          " + decodedResponse.origin);
                System.out.println("- headers:     " + decodedResponse.headers);
                System.out.println("- args:        " + decodedResponse.args);
                System.out.println("- form params: " + decodedResponse.form);
                System.out.println("- json params: " + decodedResponse.json);
            }

            /**
             * onFailure gets called when the HTTP request didn't get through.
             * For instance if the URL is invalid / host not reachable
             */
            @Override
            public void onFailure(Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.getMessage());
            }
        });
    }

    public void loginUser(User user){
        Call<LoginUserResponseModel> call = service.loginUser("aaa@abv.bg", "123456", "password", "aaa@abv.bg");

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<LoginUserResponseModel>() {
            /**
             * onResponse is called when any kind of response has been received.
             */
            @Override
            public void onResponse(Response<LoginUserResponseModel> response) {
                String token = response.body().getAccessToken();
                String userName = response.body().getUserName();

            }

            /**
             * onFailure gets called when the HTTP request didn't get through.
             * For instance if the URL is invalid / host not reachable
             */
            @Override
            public void onFailure(Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.getMessage());
            }
        });
    }
}
