package com.example.admin.goparty.views.Helpers;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.goparty.models.User;
import com.example.admin.goparty.presenters.UserPresenter;

/**
 * Created by Admin on 4/15/2016.
 */
public class registerUser extends
        AsyncTask<Void, String, String> {
    UserPresenter rp;
    private User user;
    Context context;

    public registerUser(Context context, User user){
        this.user = user;
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        rp = new UserPresenter();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = rp.registerUser(user);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
