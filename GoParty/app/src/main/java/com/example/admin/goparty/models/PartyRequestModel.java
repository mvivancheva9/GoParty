package com.example.admin.goparty.models;

@SuppressWarnings("ALL")
public class PartyRequestModel {

    private Double latitude;
    private Double longitude;
    private Integer duration;
    private String title;

    public PartyRequestModel(Double latitude, Double longitude, String title, Integer duration) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.duration = duration;
    }
}