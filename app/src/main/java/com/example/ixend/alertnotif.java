package com.example.ixend;

public class alertnotif {
    String name;
    String lon;
    String lat;
    String phone;

    public alertnotif() {
    }

    public alertnotif(String name, String lon, String lat, String phone) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
