package com.example.admin.goparty.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.Crashlytics;
import com.example.admin.goparty.R;
import com.example.admin.goparty.common.MyApplication;
import com.example.admin.goparty.views.fragment.LoginUserFragment;

import java.util.List;

import io.fabric.sdk.android.Fabric;

public class UserActivity extends AppCompatActivity {

    MyApplication myApplication;

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myApplication = (MyApplication) getApplicationContext();

        List<String> list = myApplication.getSqlDb().getAllUsers();
        super.onCreate(savedInstanceState);

        if (!list.isEmpty()) {
            Intent intent = new Intent(this, PartyActivity.class);
            startActivity(intent);
        } else {
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
