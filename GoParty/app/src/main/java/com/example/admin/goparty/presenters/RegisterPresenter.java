package com.example.admin.goparty.presenters;

import com.example.admin.goparty.models.RegisterData;
import com.example.admin.goparty.models.User;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RegisterPresenter {

    public void registrationProcessWithRetrofit(User user){

        String url = "http://goparty.azurewebsites.net";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        RegisterData registerData = new RegisterData(user.getUsername(), user.getPassword(), user.getPassword());
        Call<HttpBinResponse> call = service.addUser(registerData);

        // Asynchronously execute HTTP request
        call.enqueue(new Callback<HttpBinResponse>() {
            @Override
            public void onResponse(Response<HttpBinResponse> response) {
                int code = response.code();
                System.out.println("Response status code: " + response.code());

                HttpBinResponse decodedResponse = response.body();

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
