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
import com.example.admin.goparty.views.Helpers.RegisterUser;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("ALL")
public class RegisterNewUserFragment extends Fragment {
    @SuppressWarnings("WeakerAccess")
    @Bind(R.id.input_username)
    EditText inputUsername;
    @Bind(R.id.username_content)
    TextInputLayout usernameContent;
    @SuppressWarnings("WeakerAccess")
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.password_content)
    TextInputLayout passwordContent;
    @SuppressWarnings("WeakerAccess")
    @Bind(R.id.input_confirm_password)
    EditText inputConfirmPassword;
    @Bind(R.id.confirm_password_content)
    TextInputLayout confirmPasswordContent;
    @Bind(R.id.btn_register)
    AppCompatButton btnRegister;
    PartyPresenter partyPresenter = new PartyPresenter();
    Intent intent;
    private Context context;

    public RegisterNewUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_new_user, container, false);
        ButterKnife.bind(this, view);
        context = inflater.getContext();
        UserPresenter rp = new UserPresenter();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        int duration;
        Toast toast1;
        CharSequence text1;
        if (inputUsername.getText().toString().trim().isEmpty() || inputPassword.getText().toString().trim().isEmpty()
                || inputConfirmPassword.getText().toString().trim().isEmpty()) {
            CharSequence text = "All fields are required";
            int durationLength = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, durationLength);
            toast.show();
        } else if (!inputPassword.getText().toString().equals(inputConfirmPassword.getText().toString())) {
            text1 = "Passwords does not match";

            duration = Toast.LENGTH_LONG;

            toast1 = Toast.makeText(context, text1, duration);
            toast1.show();
        } else {
            text1 = "User Successfully Registered!";

            duration = Toast.LENGTH_LONG;

            User user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(), inputUsername.getText().toString());

            toast1 = Toast.makeText(context, text1, duration);
            toast1.show();

            getFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                    .replace(R.id.user_content_main, new LoginUserFragment())
                    .addToBackStack("register")
                    .commit();

            RegisterUser RegisterUser = new RegisterUser(context, user);
            RegisterUser.execute();

            try {
                String result = RegisterUser.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}