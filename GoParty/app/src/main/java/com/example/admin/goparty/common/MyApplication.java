package com.example.admin.goparty.common;

import android.app.Application;

import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.presenters.PartyPresenter;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class MyApplication extends Application {
    private static MyApplication ourInstance = null;
    private static String url = "http://goparty.azurewebsites.net";
    private Retrofit retrofit;
    private SqLiteDbHelper sqlDb;
    private PartyPresenter partyPresenter;

    protected MyApplication() {
    }

    public synchronized static MyApplication getInstance() {

        if (ourInstance == null) {
            ourInstance = new MyApplication();
        }
        return ourInstance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public SqLiteDbHelper getSqlDb() {
        return sqlDb;
    }

    public PartyPresenter getPartyPresenter() {
        return partyPresenter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;

        init();
    }

    private void init() {
        sqlDb = new SqLiteDbHelper(this);

        partyPresenter = new PartyPresenter();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
