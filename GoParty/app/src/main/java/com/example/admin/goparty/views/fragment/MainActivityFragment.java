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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.models.User;
import com.example.admin.goparty.presenters.PartyPresenter;
import com.example.admin.goparty.presenters.UserPresenter;
import com.example.admin.goparty.views.activity.MapsActivity;
import com.example.admin.goparty.views.activity.PartyActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @Bind(R.id.logo_image)
    ImageView logoImage;
    @Bind(R.id.input_username)
    EditText inputUsername;
    @Bind(R.id.username_content)
    TextInputLayout usernameContent;
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.password_content)
    TextInputLayout passwordContent;
    @Bind(R.id.btn_login)
    AppCompatButton btnLogin;
    @Bind(R.id.link_forgotten_password)
    TextView linkForgottenPassword;
    Context context;
    User user;
    UserPresenter rp = new UserPresenter();
    PartyPresenter partyPresenter = new PartyPresenter();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        context = inflater.getContext();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_login, R.id.link_forgotten_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                CharSequence text = "User Successfully Logged In!";
                int duration = Toast.LENGTH_SHORT;
                user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(),inputUsername.getText().toString());
                rp.loginUser(user,this.getContext());

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent intent = new Intent(getActivity(), PartyActivity.class);
                startActivity(intent);
                break;
            case R.id.link_forgotten_password:
                text = inputUsername.getText() + " registered";

                duration = Toast.LENGTH_SHORT;

                user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(),inputUsername.getText().toString());

                rp.registerUser(user);

                toast = Toast.makeText(context, text, duration);
                toast.show();
                break;
        }
    }
}
