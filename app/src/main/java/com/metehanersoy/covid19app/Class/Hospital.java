package com.metehanersoy.covid19app.Class;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Hospital implements Serializable {
    String city;
    String hospitalId;
    String hospitalName;
    double latitude;
    double longitude;
    String hospitalImageURL;
    int currentCases;
    int criticalCases;
    int deaths;
    int totalCases;
    int recovered;

    public Hospital() {
    }

    public Hospital(String city, String hospitalName, double latitude, double longitude, String hospitalImageURL, int currentCases, int criticalCases, int deaths, int totalCases, int recovered) {
        this.city = city;
        this.hospitalName = hospitalName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hospitalImageURL = hospitalImageURL;
        this.currentCases = currentCases;
        this.criticalCases = criticalCases;
        this.deaths = deaths;
        this.totalCases = totalCases;
        this.recovered = recovered;
    }

    public Hospital(String city, String hospitalId, String hospitalName, double latitude, double longitude, String hospitalImageURL, int currentCases, int criticalCases, int deaths, int totalCases, int recovered) {
        this.city = city;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hospitalImageURL = hospitalImageURL;
        this.currentCases = currentCases;
        this.criticalCases = criticalCases;
        this.deaths = deaths;
        this.totalCases = totalCases;
        this.recovered = recovered;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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

    public String getHospitalImageURL() {
        return hospitalImageURL;
    }

    public void setHospitalImageURL(String hospitalImageURL) {
        this.hospitalImageURL = hospitalImageURL;
    }

    public int getCurrentCases() {
        return currentCases;
    }

    public void setCurrentCases(int currentCases) {
        this.currentCases = currentCases;
    }

    public int getCriticalCases() {
        return criticalCases;
    }

    public void setCriticalCases(int criticalCases) {
        this.criticalCases = criticalCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }


}
