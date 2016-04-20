package com.example.admin.goparty.views.Helpers;

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.goparty.models.User;
import com.example.admin.goparty.presenters.UserPresenter;

/**
 * Created by Admin on 4/15/2016.
 */
@SuppressWarnings("ALL")
public class RegisterUser extends
        AsyncTask<Void, String, String> {
    private UserPresenter rp;
    private final User user;

    public RegisterUser(Context context, User user) {
        this.user = user;
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
        return rp.registerUser(user);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
