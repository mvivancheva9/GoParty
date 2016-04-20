package com.example.admin.goparty.models;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Party {

    private Integer Id;
    private Object Title;
    private Double Latitude;
    private Double Longitude;
    private Integer Duration;
    private String startDate;
    private String UserId;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return The Title
     */
    public Object getTitle() {
        return Title;
    }

    /**
     * @param Title The Title
     */
    public void setTitle(Object Title) {
        this.Title = Title;
    }

    /**
     * @return The Latitude
     */
    public Double getLatitude() {
        return Latitude;
    }

    /**
     * @param Latitude The Latitude
     */
    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * @return The Longitude
     */
    public Double getLongitude() {
        return Longitude;
    }

    /**
     * @param Longitude The Longitude
     */
    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * @return The Duration
     */
    public Integer getDuration() {
        return Duration;
    }

    /**
     * @param Duration The Duration
     */
    public void setDuration(Integer Duration) {
        this.Duration = Duration;
    }

    /**
     * @return The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return The UserId
     */
    public String getUserId() {
        return UserId;
    }

    /**
     * @param UserId The UserId
     */
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}