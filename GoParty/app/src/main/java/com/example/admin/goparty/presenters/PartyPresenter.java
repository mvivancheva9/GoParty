package com.example.admin.goparty.presenters;
import android.content.Context;
import android.database.Observable;

import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.PartyRequestModel;
import com.example.admin.goparty.models.PartyResponseModel;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PartyPresenter {

    static String url = "http://goparty.apphb.com";

    private static okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
    private SqLiteDbHelper sqlDb;
    OkHttpClient client = new OkHttpClient();
    List<Party> parties = new ArrayList<Party>();

    public List<Party> getAllParties(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<Observable<List<Party>>> call = service.getParties();

        call.enqueue(new Callback<Observable<List<Party>>>() {

            @Override
            public void onResponse(Response<Observable<List<Party>>> response) {
                int code = response.code();

                parties = (List<Party>) response.body();
                System.out.println("Response status code: " + response.code());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return parties;
    }

    public void getPartyById(int partyId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<Party> call = service.getParty(partyId);

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

    public void addParty(final Context context, Party party){
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

        PartyRequestModel partyRequestModel = new PartyRequestModel(party.getLatitude(), party.getLongitude(), party.getTitle().toString(), party.getDuration());
        Call<Party> call = service.addParty(partyRequestModel);

        call.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Response<Party> response) {
                int code = response.code();
                System.out.println("Response status code: " + response.code());
                PartyResponseModel party = new PartyResponseModel(response.body().getLatitude(), response.body().getLongitude(), response.body().getTitle().toString(), response.body().getDuration());

                //TODO -> partyDetails
            }

            @Override
            public void onFailure(Throwable t) {

                System.out.println(t.getMessage());
            }
        });
    }
}
