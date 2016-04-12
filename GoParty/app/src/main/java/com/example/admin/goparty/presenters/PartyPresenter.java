package com.example.admin.goparty.presenters;

import com.example.admin.goparty.models.ResponsePartyListModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PartyPresenter {
    public void GetAllParties(){

        String url = "http://goparty.azurewebsites.net";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<ResponsePartyListModel> call = service.getParties();

        call.enqueue(new Callback<ResponsePartyListModel>() {
            @Override
            public void onResponse(Response<ResponsePartyListModel> response) {
                int code = response.code();
                System.out.println("Response status code: " + response.code());

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
