package com.example.admin.goparty.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PartyResponseModel implements Parcelable {
    private Double latitude;
    private Double longitude;
    private Integer duration;
    private String title;

    public PartyResponseModel(Double latitude, Double longitude, String title, Integer duration) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.duration = duration;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeValue(this.duration);
        dest.writeString(this.title);
    }

    protected PartyResponseModel(Parcel in) {
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.duration = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
    }

    public static final Parcelable.Creator<PartyResponseModel> CREATOR = new Parcelable.Creator<PartyResponseModel>() {
        @Override
        public PartyResponseModel createFromParcel(Parcel source) {
            return new PartyResponseModel(source);
        }

        @Override
        public PartyResponseModel[] newArray(int size) {
            return new PartyResponseModel[size];
        }
    };
}
