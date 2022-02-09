package com.metehanersoy.covid19app.Class;

public class Location {

    String patientId;
    double date;
    double latitude;
    double longitude;

    public Location() {
    }

    public Location(double date, double latitude, double longitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String patientId, double date, double latitude, double longitude) {
        this.patientId = patientId;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getDate() {
        return date;
    }

    public void setDate(double date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
