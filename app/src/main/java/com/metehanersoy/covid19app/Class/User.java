package com.metehanersoy.covid19app.Class;

public class User {
    String patientId, profileImageURL, name, surname, email, weight, height, sex, maritalStatus, healthSector, currentCity, mobileNumber, status, lastUpdate;
   long birthdate;

 public User() {
 }

 public User( String profileImageURL, String name, String surname, String email, String weight, String height, String sex, String maritalStatus, String healthSector, String currentCity, String mobileNumber, String status, String lastUpdate, long birthdate) {
  this.profileImageURL = profileImageURL;
  this.name = name;
  this.surname = surname;
  this.email = email;
  this.weight = weight;
  this.height = height;
  this.sex = sex;
  this.maritalStatus = maritalStatus;
 this.healthSector = healthSector;
 this.currentCity = currentCity;
  this.mobileNumber = mobileNumber;
  this.status = status;
  this.lastUpdate = lastUpdate;
  this.birthdate = birthdate;
 }

 public User(String patientId, String profileImageURL, String name, String surname, String email, String weight, String height, String sex, String maritalStatus, String healthSector, String currentCity, String mobileNumber, String status, String lastUpdate, long birthdate) {
  this.patientId = patientId;
  this.profileImageURL = profileImageURL;
  this.name = name;
  this.surname = surname;
  this.email = email;
  this.weight = weight;
  this.height = height;
  this.sex = sex;
  this.maritalStatus = maritalStatus;
  this.healthSector = healthSector;
  this.currentCity = currentCity;
  this.mobileNumber = mobileNumber;
  this.status = status;
  this.lastUpdate = lastUpdate;
  this.birthdate = birthdate;
 }

 public User(String profileImageURL, String name, String surname, String email, String weight, String height, String sex, String maritalStatus, String healthSector, String currentCity, String mobileNumber, long birthdate) {
  this.profileImageURL = profileImageURL;
  this.name = name;
  this.surname = surname;
  this.email = email;
  this.weight = weight;
  this.height = height;
  this.sex = sex;
  this.maritalStatus = maritalStatus;
  this.healthSector = healthSector;
  this.currentCity = currentCity;
  this.mobileNumber = mobileNumber;
  this.birthdate = birthdate;
 }

 public String getPatientId() {
  return patientId;
 }

 public void setPatientId(String patientId) {
  this.patientId = patientId;
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


 public String getWeight() {
  return weight;
 }

 public void setWeight(String weight) {
  this.weight = weight;
 }

 public String getHeight() {
  return height;
 }

 public void setHeight(String height) {
  this.height = height;
 }

 public String getSex() {
  return sex;
 }

 public void setSex(String sex) {
  this.sex = sex;
 }

 public String getMaritalStatus() {
  return maritalStatus;
 }

 public void setMaritalStatus(String maritalStatus) {
  this.maritalStatus = maritalStatus;
 }

 public String getHealthSector() {
  return healthSector;
 }

 public void setHealthSector(String healthSector) {
  this.healthSector = healthSector;
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

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public String getLastUpdate() {
  return lastUpdate;
 }

 public void setLastUpdate(String lastUpdate) {
  this.lastUpdate = lastUpdate;
 }

 public long getBirthdate() {
  return birthdate;
 }

 public void setBirthdate(long birthdate) {
  this.birthdate = birthdate;
 }
}
