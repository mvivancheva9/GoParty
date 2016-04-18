package com.example.admin.goparty.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.example.admin.goparty.R;
import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.views.fragment.LoginUserFragment;

import io.fabric.sdk.android.Fabric;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private SqLiteDbHelper sqlDb;
    private PartyPresenter partyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sqlDb = new SqLiteDbHelper(this);

        partyPresenter = new PartyPresenter();
        List<String> list = sqlDb.getAllUsers();
        super.onCreate(savedInstanceState);

        if(!list.isEmpty()){
            Intent intent = new Intent(this, PartyActivity.class);
            startActivity(intent);
        }
        else {
            Fabric.with(this, new Crashlytics());
            setContentView(R.layout.user_activity_main);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.user_content_main, new LoginUserFragment())
                    .commit();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
    }
}
