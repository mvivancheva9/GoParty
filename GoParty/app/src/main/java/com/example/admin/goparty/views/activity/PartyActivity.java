package com.example.admin.goparty.views.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.data.SqLiteDbHelper;

/**
 * Created by Admin on 4/12/2016.
 */
public class PartyActivity extends AppCompatActivity {
    private SqLiteDbHelper sqlDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_activity_main);
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
        if (id == R.id.action_list_all_parties) {
            CharSequence text = "Both fields are required";
            int durationLength = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, durationLength);
            toast.show();
            return true;
        }else if (id == R.id.action_logout) {
            sqlDb = new SqLiteDbHelper(this);
            sqlDb.deleteContact();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
