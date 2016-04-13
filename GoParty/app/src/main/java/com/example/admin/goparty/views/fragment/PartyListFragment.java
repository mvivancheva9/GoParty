package com.example.admin.goparty.views.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.views.adapter.ListDemoAdapter;

import java.util.ArrayList;

public class PartyListFragment extends ListFragment {
    private ArrayList<Party> partyArrayList;
    private PartyPresenter partyPresenter;

    public PartyListFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        partyPresenter = new PartyPresenter();

//        Party party = new Party();
//        party.setTitle("fdfd");
//        party.setDuration(222);
//        party.setLongitude(1.23);
//        party.setLatitude(1.23);
//
//        partyArrayList = new ArrayList<Party>();
//        partyArrayList.add(party);
//        partyArrayList.add(party);

        partyArrayList = (ArrayList<Party>) partyPresenter.getAllParties();

        setListAdapter(new ListDemoAdapter(getActivity(), partyArrayList));
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

        // do something
        Toast.makeText(getActivity(), item.getTitle().toString(), Toast.LENGTH_SHORT).show();
    }
}