package com.example.admin.goparty.presenters;

import android.content.Context;

import com.example.admin.goparty.common.MyApplication;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.models.PartyRequestModel;
import com.example.admin.goparty.models.PartyResponseModel;
import com.example.admin.goparty.models.UserSqliteModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class PartyPresenter {

    List<Party> parties = new ArrayList<Party>();
    MyApplication myApplication = MyApplication.getInstance();

    public List<Party> getAllParties() {

        Call<List<Party>> call = ApiInterface.service.getParties();
        try {
            parties = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parties;
    }

    public void getPartyById(int partyId) {

        Call<Party> call = ApiInterface.service.getParty(partyId);

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

    public void addParty(final Context context, Party party) {
        final String token;

        List<UserSqliteModel> list = myApplication.getSqlDb().getAllUsers();

//        if (list.size() != 0) {
//
//            token = list.get(0);
//        }else{
//            token = "";
//        }
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
//                com.squareup.okhttp.Response response = chain.proceed(chain.request());
//
//                Request original = chain.request();
//
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Accept", "application/json")
//                        .header("Authorization",
//                                "Bearer " + token)
//                        .method(original.method(), original.body());
//
//                Request request = requestBuilder.build();
//
//                return response;
//            }
//        });

        PartyRequestModel partyRequestModel = new PartyRequestModel(party.getLatitude(), party.getLongitude(), party.getTitle().toString(), party.getDuration());
        Call<Party> call = ApiInterface.service.addParty(partyRequestModel);

        call.enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Response<Party> response) {
                int code = response.code();
                System.out.println("Response status code: " + response.code());
                PartyResponseModel party = new PartyResponseModel(response.body().getLatitude(), response.body().getLongitude(), response.body().getTitle().toString(), response.body().getDuration());
                myApplication.getSqlDb().insertParty(party.getTitle(), party.getDuration(), party.getLatitude(), party.getLongitude());
            }

            @Override
            public void onFailure(Throwable t) {

                System.out.println(t.getMessage());
            }
        });
    }
}
