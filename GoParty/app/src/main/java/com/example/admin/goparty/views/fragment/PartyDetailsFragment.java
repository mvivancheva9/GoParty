package com.example.admin.goparty.views.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.goparty.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartyDetailsFragment extends Fragment implements OnMapReadyCallback {

    private static LatLng partyLocation;
    @Bind(R.id.outputTitle)
    TextView outputTitle;
    @Bind(R.id.outputDuration)
    TextView outputDuration;
    Context context;
    @Bind(R.id.btn_add_to_calendar)
    AppCompatButton btnAddToCalendar;
    Double latitude;
    Double longitude;
    String title;
    String duration;
    private GoogleMap mMap;
    private SupportMapFragment fragment;


    public PartyDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString("title");
        duration = getArguments().getString("duration");
        latitude = getArguments().getDouble("latitude");
        longitude = getArguments().getDouble("longitude");

        partyLocation = new LatLng(latitude, longitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_party_details, container, false);
        ButterKnife.bind(this, view);

        // Inflate the layout for this fragment
        context = inflater.getContext();

        outputTitle.setText(title);
        outputDuration.setText(duration);

        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.mapFragment);
        fragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        GoogleMapOptions options = new GoogleMapOptions();

        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true)
                .ambientEnabled(true);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("Party Here"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(partyLocation, 15));
    }

    @OnClick(R.id.btn_add_to_calendar)
    public void onClick() {
        Location myAddress = new Location("location");
        myAddress.setLatitude(latitude);
        myAddress.setLongitude(longitude);

        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", duration);
        intent.putExtra("allDay", false);
        intent.putExtra("eventLocation", myAddress.getLatitude() + " " + myAddress.getLongitude());
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
