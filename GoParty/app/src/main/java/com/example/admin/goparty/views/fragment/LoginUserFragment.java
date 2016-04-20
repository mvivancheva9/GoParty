package com.example.admin.goparty.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.User;
import com.example.admin.goparty.views.Helpers.LoginUser;
import com.example.admin.goparty.views.activity.PartyActivity;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
@SuppressWarnings("unused")
public class LoginUserFragment extends Fragment implements View.OnTouchListener {

    @SuppressWarnings({"WeakerAccess", "unused"})
    @Bind(R.id.input_username)
    EditText inputUsername;
    @SuppressWarnings({"WeakerAccess", "unused"})
    @Bind(R.id.input_password)
    EditText inputPassword;
    @SuppressWarnings("WeakerAccess")
    @Bind(R.id.link_forgotten_password)
    TextView linkForgottenPassword;
    private Context context;
    private String result = "";

    public LoginUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_user, container, false);
        ButterKnife.bind(this, view);
        context = inflater.getContext();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("unused")
    @OnClick({R.id.btn_login, R.id.link_forgotten_password})
    public void onClick(View view) {
        Toast toast;
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_login:
                if (inputUsername.getText().toString().trim().isEmpty() || inputPassword.getText().toString().trim().isEmpty()) {
                    CharSequence text = "Both fields are required";
                    int durationLength = Toast.LENGTH_SHORT;
                    toast = Toast.makeText(context, text, durationLength);
                    toast.show();
                } else {
                    CharSequence text = "User Successfully Logged In!";
                    int duration = Toast.LENGTH_SHORT;
                    User user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(), inputUsername.getText().toString());

                    LoginUser LoginUser = new LoginUser(context, user);
                    LoginUser.execute();

                    try {
                        result = LoginUser.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                    if (result.equals("Success")) {

                        toast = Toast.makeText(context, text, duration);
                        toast.show();

                        intent = new Intent(getActivity(), PartyActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        text = "Email ot password doesn't match";

                        toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                break;
            case R.id.link_forgotten_password:
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                        .replace(R.id.user_content_main, new RegisterNewUserFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        linkForgottenPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //noinspection deprecation
                linkForgottenPassword.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                return false;
            }
        });
        return false;
    }
}
