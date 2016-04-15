package com.example.admin.goparty.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.views.Helpers.getItemsLists;
import com.example.admin.goparty.views.adapter.ListDemoAdapter;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PartyListFragment extends ListFragment {
    private List<Party> partyArrayList;

    private ListDemoAdapter listDemoAdapter;
    Context context;

    public PartyListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        partyArrayList = new ArrayList<Party>();

        getItemsLists gfl = new getItemsLists();
        gfl.execute();

        try {
            partyArrayList = gfl.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        listDemoAdapter = new ListDemoAdapter(context, partyArrayList);
        setListAdapter(listDemoAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        Party item = partyArrayList.get(position);
        Location myAddress = new Location("location");
        myAddress.setLatitude(item.getLatitude());
        myAddress.setLongitude(item.getLongitude());

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", false);
        intent.putExtra("eventLocation", myAddress.getLatitude() + " " + myAddress.getLongitude());
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+item.getDuration());
        intent.putExtra("title", item.getTitle().toString());
        startActivity(intent);
        // do something
        Toast.makeText(getActivity(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
    }
}