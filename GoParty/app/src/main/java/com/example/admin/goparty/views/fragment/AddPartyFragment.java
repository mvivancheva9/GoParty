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
import android.widget.Toast;

import com.example.admin.goparty.R;
import com.example.admin.goparty.views.activity.MapsActivity;

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
    Context context;

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
        View view = inflater.inflate(R.layout.fragment_add_party, container, false);
        ButterKnife.bind(this, view);
        context = inflater.getContext();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_add_location)
    public void onClick() {

        if(inputTitle.getText().toString().trim().isEmpty() || inputDuration.getText().toString().trim().isEmpty()){
            CharSequence text = "Both fields are required";
            int durationLength = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, durationLength);
            toast.show();
        }else{
            String title = inputTitle.getText().toString();
            Integer duration = Integer.parseInt(inputDuration.getText().toString());

            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("duration", duration);
            startActivity(intent);
        }

    }
}
