package com.example.admin.goparty.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserResponseModel {

    @SuppressWarnings("unused")
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SuppressWarnings("unused")
    @SerializedName("userName")
    @Expose
    private String userName;

    /**
     * @return The accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @return The userName
     */
    public String getUserName() {
        return userName;
    }

}
