package com.example.admin.goparty.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.views.Helpers.GetItemsLists;
import com.example.admin.goparty.views.adapter.ListDemoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PartyListFragment extends ListFragment {
    Context context;
    private List<Party> partyArrayList;
    private ListDemoAdapter listDemoAdapter;

    public PartyListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        partyArrayList = new ArrayList<Party>();

        GetItemsLists gfl = new GetItemsLists();
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

        Bundle bundle = new Bundle();
        if (item.getTitle() == null) {
            bundle.putString("title", "No Title");
        } else {
            bundle.putString("title", item.getTitle().toString());
        }
        bundle.putString("duration", item.getStartDate());
        bundle.putDouble("latitude", item.getLatitude());
        bundle.putDouble("longitude", item.getLongitude());

        PartyDetailsFragment partyDetailsFragment = new PartyDetailsFragment();
        partyDetailsFragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.party_content_main, partyDetailsFragment)
                .addToBackStack(null)
                .commit();
    }
}