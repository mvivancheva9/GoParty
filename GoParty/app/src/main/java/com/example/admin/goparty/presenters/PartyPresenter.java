package com.example.admin.goparty.presenters;

import android.content.Context;

import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.PartyRequestModel;
import com.example.admin.goparty.models.ResponsePartyListModel;
import com.example.admin.goparty.models.User;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PartyPresenter {

    static String url = "http://goparty.azurewebsites.net";

    private static okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
    private SqLiteDbHelper sqlDb;
    OkHttpClient client = new OkHttpClient();

    public void GetAllParties(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<List<Party>> call = service.getParties();

        call.enqueue(new Callback<List<Party>>() {
            @Override
            public void onResponse(Response<List<Party>> response) {
                int code = response.code();
                System.out.println("Response status code: " + response.code());

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void addParty(Context context){
        sqlDb = new SqLiteDbHelper(context);

        List<String> list = sqlDb.getAllUsers();

        final String token = list.get(0);

        client.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                com.squareup.okhttp.Response response = chain.proceed(chain.request());

                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization",
                                "Bearer " + token)
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();

                return response;
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        PartyRequestModel partyRequestModel = new PartyRequestModel(1.23456, 1.23456, "dsds", 2233);
        Call<Party> call = service.addParty(partyRequestModel);

        call.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Response<Party> response) {
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
