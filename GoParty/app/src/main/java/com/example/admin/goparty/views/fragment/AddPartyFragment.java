package com.example.admin.goparty.views.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.admin.goparty.R;
import com.example.admin.goparty.presenters.PartyPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPartyFragment extends Fragment {

    @Bind(R.id.logo_image)
    ImageView logoImage;
    @Bind(R.id.input_title)
    EditText inputTitle;
    @Bind(R.id.title_content)
    TextInputLayout titleContent;
    @Bind(R.id.input_duration)
    EditText inputDuration;
    @Bind(R.id.duration_content)
    TextInputLayout durationContent;
    @Bind(R.id.btn_add_location)
    AppCompatButton btnAddLocation;
    PartyPresenter partyPresenter = new PartyPresenter();

    public AddPartyFragment() {
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
        View view = inflater.inflate(R.layout.add_party_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_add_location)
    public void onClick() {
        partyPresenter.GetAllParties();
    }
}
