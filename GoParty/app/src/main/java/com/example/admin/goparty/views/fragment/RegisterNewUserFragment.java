package com.example.admin.goparty.views.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.User;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.presenters.UserPresenter;
import com.example.admin.goparty.views.Helpers.registerUser;
import com.example.admin.goparty.views.activity.UserActivity;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterNewUserFragment extends Fragment {
    @Bind(R.id.input_username)
    EditText inputUsername;
    @Bind(R.id.username_content)
    TextInputLayout usernameContent;
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.password_content)
    TextInputLayout passwordContent;
    @Bind(R.id.input_confirm_password)
    EditText inputConfirmPassword;
    @Bind(R.id.confirm_password_content)
    TextInputLayout confirmPasswordContent;
    @Bind(R.id.btn_register)
    AppCompatButton btnRegister;
    Context context;
    User user;
    UserPresenter rp;
    PartyPresenter partyPresenter = new PartyPresenter();
    int duration;
    Toast toast;
    Intent intent;
    CharSequence text;
    String result;

    public RegisterNewUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_new_user, container, false);
        ButterKnife.bind(this, view);
        context = inflater.getContext();
        rp = new UserPresenter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        if (inputUsername.getText().toString().trim().isEmpty() || inputPassword.getText().toString().trim().isEmpty()
                || inputConfirmPassword.getText().toString().trim().isEmpty()){
            CharSequence text = "All fields are required";
            int durationLength = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, durationLength);
            toast.show();
        }else if (!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())){
            text = "Passwords does not match";

            duration = Toast.LENGTH_LONG;

            toast = Toast.makeText(context, text, duration);
            toast.show();
        }else {
            text = "User Successfully Registered!";

            duration = Toast.LENGTH_LONG;

            user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(), inputUsername.getText().toString());

            toast = Toast.makeText(context, text, duration);
            toast.show();

            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                    .replace(R.id.user_content_main, new LoginUserFragment())
                    .addToBackStack("register")
                    .commit();

            registerUser registerUser = new registerUser(context, user);
            registerUser.execute();

            try {
                result = registerUser.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}