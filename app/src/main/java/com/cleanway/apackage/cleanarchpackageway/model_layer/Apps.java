package com.cleanway.apackage.cleanarchpackageway.model_layer;

import com.google.gson.annotations.SerializedName;

public class Apps {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("impressions")
    private String impressions;
    @SerializedName("active_devices")
    private String active_devices;
    @SerializedName("sales")
    public String sales;
    @SerializedName("app_units")
    private String app_units;
    @SerializedName("crashes")
    private String crashes;
    @SerializedName("sessions")
    private String sessions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getActive_devices() {
        return active_devices;
    }

    public void setActive_devices(String active_devices) {
        this.active_devices = active_devices;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getApp_units() {
        return app_units;
    }

    public void setApp_units(String app_units) {
        this.app_units = app_units;
    }

    public String getCrashes() {
        return crashes;
    }

    public void setCrashes(String crashes) {
        this.crashes = crashes;
    }

    public String getSessions() {
        return sessions;
    }

    public void setSessions(String sessions) {
        this.sessions = sessions;
    }

}
