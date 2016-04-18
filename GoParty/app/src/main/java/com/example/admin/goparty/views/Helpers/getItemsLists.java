package com.example.admin.goparty.views.Helpers;

import android.os.AsyncTask;

import com.example.admin.goparty.models.Party;
import com.example.admin.goparty.presenters.PartyPresenter;

import java.util.ArrayList;

/**
 * Created by Admin on 4/15/2016.
 */
public class GetItemsLists extends
        AsyncTask<Void, String, ArrayList<Party>> {
    private PartyPresenter partyPresenter = new PartyPresenter();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Party> doInBackground(Void... params) {
        ArrayList<Party> fal = (ArrayList<Party>) partyPresenter.getAllParties();
        return fal;
    }

    @Override
    protected void onPostExecute(ArrayList<Party> result) {
        super.onPostExecute(result);
    }
}