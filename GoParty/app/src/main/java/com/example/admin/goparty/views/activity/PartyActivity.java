package com.example.admin.goparty.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.goparty.R;
import com.example.admin.goparty.common.MyApplication;
import com.example.admin.goparty.data.SqLiteDbHelper;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.views.fragment.AddPartyFragment;
import com.example.admin.goparty.views.fragment.PartyListFragment;

/**
 * Created by Admin on 4/12/2016.
 */
public class PartyActivity extends AppCompatActivity {

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myApplication = (MyApplication) getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.party_content_main, new PartyListFragment())
                .commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_party) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                    .replace(R.id.party_content_main, new AddPartyFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (id == R.id.action_list_all_parties) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                    .replace(R.id.party_content_main, new PartyListFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        } else if (id == R.id.action_logout) {
            myApplication.getSqlDb().deleteContact();

            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
