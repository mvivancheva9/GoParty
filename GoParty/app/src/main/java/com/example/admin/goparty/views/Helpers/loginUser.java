package com.example.admin.goparty.views.Helpers;

/**
 * Created by Admin on 4/15/2016.
 */

import android.content.Context;
import android.os.AsyncTask;

import com.example.admin.goparty.models.User;
import com.example.admin.goparty.presenters.UserPresenter;

public class LoginUser extends
        AsyncTask<Void, String, String> {
    UserPresenter rp;
    Context context;
    private User user;

    public LoginUser(Context context, User user) {
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
        String result = rp.loginUser(user, context);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}