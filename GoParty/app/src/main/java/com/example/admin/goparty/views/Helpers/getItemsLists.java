package com.example.admin.goparty.views.Helpers;

import android.os.AsyncTask;

import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.presenters.PartyPresenter;

import java.util.ArrayList;

public class GetItemsLists extends
        AsyncTask<Void, String, ArrayList<Party>> {
    private final PartyPresenter partyPresenter = new PartyPresenter();

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Party> doInBackground(Void... params) {
        return (ArrayList<Party>) partyPresenter.getAllParties();
    }

    @Override
    protected void onPostExecute(ArrayList<Party> result) {
        super.onPostExecute(result);
    }
}