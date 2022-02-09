package com.metehanersoy.covid19app.Class;

public class Card {
    String examinationCardId;
    long date;
    double bodyTemperature;
    String doctorName;
    String hospitalName;
    String leftEarImageURL;
    String rightEarImageURL;
    String faceRecordingURL;
    String throatImageURL;
    double probability;
    String feedback;
    int scoreBasedCase;


    public Card() {
    }

    public Card(long date, double bodyTemperature, String doctorName, String hospitalName, String leftEarImageURL, String rightEarImageURL, String faceRecordingURL, String throatImageURL, double probability, String feedback, int scoreBasedCase) {
        this.date = date;
        this.bodyTemperature = bodyTemperature;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.leftEarImageURL = leftEarImageURL;
        this.rightEarImageURL = rightEarImageURL;
        this.faceRecordingURL = faceRecordingURL;
        this.throatImageURL = throatImageURL;
        this.probability = probability;
        this.feedback = feedback;
        this.scoreBasedCase = scoreBasedCase;
    }

    public Card(String examinationCardId, long date, double bodyTemperature, String doctorName, String hospitalName, String leftEarImageURL, String rightEarImageURL, String faceRecordingURL, String throatImageURL, double probability, String feedback, int scoreBasedCase) {
        this.examinationCardId = examinationCardId;
        this.date = date;
        this.bodyTemperature = bodyTemperature;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.leftEarImageURL = leftEarImageURL;
        this.rightEarImageURL = rightEarImageURL;
        this.faceRecordingURL = faceRecordingURL;
        this.throatImageURL = throatImageURL;
        this.probability = probability;
        this.feedback = feedback;
        this.scoreBasedCase = scoreBasedCase;
    }

    public String getExaminationCardId() {
        return examinationCardId;
    }

    public void setExaminationCardId(String examinationCardId) {
        this.examinationCardId = examinationCardId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLeftEarImageURL() {
        return leftEarImageURL;
    }

    public void setLeftEarImageURL(String leftEarImageURL) {
        this.leftEarImageURL = leftEarImageURL;
    }

    public String getRightEarImageURL() {
        return rightEarImageURL;
    }

    public void setRightEarImageURL(String rightEarImageURL) {
        this.rightEarImageURL = rightEarImageURL;
    }

    public String getFaceRecordingURL() {
        return faceRecordingURL;
    }

    public void setFaceRecordingURL(String faceRecordingURL) {
        this.faceRecordingURL = faceRecordingURL;
    }

    public String getThroatImageURL() {
        return throatImageURL;
    }

    public void setThroatImageURL(String throatImageURL) {
        this.throatImageURL = throatImageURL;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getScoreBasedCase() {
        return scoreBasedCase;
    }

    public void setScoreBasedCase(int scoreBasedCase) {
        this.scoreBasedCase = scoreBasedCase;
    }
}
