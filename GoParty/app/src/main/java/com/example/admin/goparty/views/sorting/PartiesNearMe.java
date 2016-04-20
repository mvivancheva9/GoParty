package com.example.admin.goparty.views.sorting;

import com.example.admin.goparty.models.Party;
import com.google.android.gms.maps.model.LatLng;

import java.util.Comparator;

class PartiesNearMe implements Comparator<Party> {
    private final LatLng currentLoc;

    @SuppressWarnings("unused")
    public PartiesNearMe(LatLng current) {
        currentLoc = current;
    }

    @Override
    public int compare(final Party firstParty, final Party secondParty) {
        double lat1 = firstParty.getLatitude();
        double lon1 = firstParty.getLongitude();
        double lat2 = secondParty.getLatitude();
        double lon2 = secondParty.getLongitude();

        double distanceToFirstParty = distance(currentLoc.latitude, currentLoc.longitude, lat1, lon1);
        double distanceToSecondParty = distance(currentLoc.latitude, currentLoc.longitude, lat2, lon2);
        return (int) (distanceToFirstParty - distanceToSecondParty);
    }

    private double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(deltaLat / 2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon / 2), 2)));
        return radius * angle;
    }
}