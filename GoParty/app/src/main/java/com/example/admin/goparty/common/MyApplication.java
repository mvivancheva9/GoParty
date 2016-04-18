package com.example.admin.goparty.common;

import android.app.Application;

import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.presenters.ApiInterface;
import com.example.admin.goparty.presenters.PartyPresenter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Admin on 4/18/2016.
 */
public class MyApplication extends Application{
    private static MyApplication ourInstance;
    private static String url = "http://goparty.azurewebsites.net";

    public ApiInterface getService() {
        return service;
    }

    private ApiInterface service;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private Retrofit retrofit;

    public SqLiteDbHelper getSqlDb() {
        return sqlDb;
    }

    public PartyPresenter getPartyPresenter() {
        return partyPresenter;
    }

    private SqLiteDbHelper sqlDb;
    private PartyPresenter partyPresenter;

    public static MyApplication getInstance() {

        return ourInstance;
    }

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        sqlDb = new SqLiteDbHelper(this);

        partyPresenter = new PartyPresenter();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
    }
}
