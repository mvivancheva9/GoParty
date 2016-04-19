package com.example.admin.goparty.models;

import com.google.android.gms.maps.model.LatLng;

public class MyLocationModel {
    private LatLng location;

    public MyLocationModel(LatLng location) {
        this.location = location;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
