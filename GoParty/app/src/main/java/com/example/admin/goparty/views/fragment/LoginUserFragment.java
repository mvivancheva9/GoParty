package com.example.admin.goparty.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.admin.goparty.views.Helpers.loginUser;
import com.example.admin.goparty.views.activity.PartyActivity;

import java.util.concurrent.ExecutionException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginUserFragment extends Fragment implements View.OnTouchListener {

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
    UserPresenter rp;
    PartyPresenter partyPresenter = new PartyPresenter();

    String result = "";

    public LoginUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_user, container, false);
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

    @OnClick({R.id.btn_login, R.id.link_forgotten_password})
    public void onClick(View view) {
        Toast toast = new Toast(context);
        Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.btn_login:
                    if (inputUsername.getText().toString().trim().isEmpty() || inputPassword.getText().toString().trim().isEmpty()){
                        CharSequence text = "Both fields are required";
                        int durationLength = Toast.LENGTH_SHORT;
                        toast = Toast.makeText(context, text, durationLength);
                        toast.show();
                    }else {
                        CharSequence text = "User Successfully Logged In!";
                        int duration = Toast.LENGTH_SHORT;
                        user = new User(inputUsername.getText().toString(), inputPassword.getText().toString(), inputUsername.getText().toString());

                        loginUser loginUser = new loginUser(context, user);
                        loginUser.execute();

                        try {
                            result = loginUser.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                        if (result == "Success") {

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
                linkForgottenPassword.setTextColor(R.color.colorPrimaryDark);
                return false;
            }
        });
        return false;
    }
}
