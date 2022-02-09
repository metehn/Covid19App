package com.metehanersoy.covid19app.Class;

import java.io.Serializable;

public class Doctor implements Serializable {

    String doctorId;
    String hospitalId;
    String hospitalName;
    String specialty;
    String profileImageURL;
    String name;
    String surname;
    String email;
    String sex;
    Double birthdate;
    String maritalStatus;
    String currentCity;
    String mobileNumber;

    public Doctor() {
    }

    public Doctor(String hospitalId, String hospitalName, String specialty, String profileImageURL, String name, String surname, String email, String sex, Double birthdate, String maritalStatus, String currentCity, String mobileNumber) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.specialty = specialty;
        this.profileImageURL = profileImageURL;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.maritalStatus = maritalStatus;
        this.currentCity = currentCity;
        this.mobileNumber = mobileNumber;
    }

    public Doctor(String doctorId, String hospitalId, String hospitalName, String specialty, String profileImageURL, String name, String surname, String email, String sex, Double birthdate, String maritalStatus, String currentCity, String mobileNumber) {
        this.doctorId = doctorId;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.specialty = specialty;
        this.profileImageURL = profileImageURL;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthdate = birthdate;
        this.maritalStatus = maritalStatus;
        this.currentCity = currentCity;
        this.mobileNumber = mobileNumber;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Double birthdate) {
        this.birthdate = birthdate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
