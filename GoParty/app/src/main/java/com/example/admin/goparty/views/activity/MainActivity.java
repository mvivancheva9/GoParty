package com.example.admin.goparty.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.goparty.R;
import com.example.admin.goparty.data.SqLiteDbHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SqLiteDbHelper sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sqlDb = new SqLiteDbHelper(this);

        List<String> list = sqlDb.getAllUsers();

        if(!list.isEmpty()){
            super.onCreate(savedInstanceState);
            Intent intent = new Intent(this, PartyActivity.class);
            startActivity(intent);
        }
        else {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }
    }
}
