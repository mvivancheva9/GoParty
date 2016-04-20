package com.example.admin.goparty.models;

import com.google.android.gms.maps.model.LatLng;

@SuppressWarnings({"WeakerAccess", "unused"})
public class MyLocationModel {
    private LatLng location;

    @SuppressWarnings("unused")
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
