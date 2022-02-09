package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.Doctor.DoctorHomePage;
import com.metehanersoy.covid19app.Patient.PatientHomePage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserDemographics extends AppCompatActivity {

    EditText userDemographicsWeight;
    EditText userDemographicsHeight;
    EditText userDemographicsBirthDate;
    EditText userDemographicsMobileNo;
    Spinner userDemographicsSpinnerMaritalStatus;
    Spinner userDemographicsSpinnerDoYouWorkHealth;
    Spinner userDemographicsSpinnerCurrentCity;
    Spinner userDemographicsSpinnerHospital;
    Spinner userDemographicsSpinnerSpecialty;
    private DatabaseReference mDatabase;
    ArrayList<Hospital> hospitalList;
    ArrayList<String> userDemographicsSpinnerHospitalList;
    FirebaseAuth mAuth;
    int day;
    int month;
    int year;
    String userType;


    /*
        userDemographicsSpinnerMaritalStatusList.add("Gazimağusa Devlet Hastanesi"); //35.154975902262784,33.90425819931936
        userDemographicsSpinnerMaritalStatusList.add("Mağusa Yaşam Hastanesi"); //38.05723264419656,34.29334230549217
        userDemographicsSpinnerMaritalStatusList.add("Cyprus Central Hospital");//35.44809657088082,34.02714558308784
        userDemographicsSpinnerMaritalStatusList.add("Cengiz Topel Hastanesi"); //35.48112300578117,32.8945145661227
        userDemographicsSpinnerMaritalStatusList.add("Dr.Suat Günsel Girne Üniversitesi Hastenesi"); //35.71445681937493,33.25032911225044
        userDemographicsSpinnerMaritalStatusList.add("Özel Girne Hastanesi"); //35.69758866562414,33.35597193066684
        userDemographicsSpinnerMaritalStatusList.add("Yakın Doğu Üniversitesi Hastanesi"); //35.533796042430815,33.3120070443034
        userDemographicsSpinnerMaritalStatusList.add("Dr. Burhan Nalbantoğlu Devlet Hastanesi"); //35.52514891683723,33.31200405816014

         */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_demographics);

        userDemographicsWeight = findViewById(R.id.userDemographicsWeight);
        userDemographicsHeight = findViewById(R.id.userDemographicsHeight);
        userDemographicsBirthDate = findViewById(R.id.userDemographicsBirthDate);
        userDemographicsMobileNo = findViewById(R.id.userDemographicsMobileNo);
        userDemographicsSpinnerMaritalStatus = findViewById(R.id.userDemographicsSpinnerMaritalStatus);
        userDemographicsSpinnerDoYouWorkHealth = findViewById(R.id.userDemographicsSpinnerDoYouWorkHealth);
        userDemographicsSpinnerCurrentCity = findViewById(R.id.userDemographicsSpinnerCurrentCity);
        userDemographicsSpinnerHospital = findViewById(R.id.userDemographicsSpinnerHospital);
        userDemographicsSpinnerSpecialty = findViewById(R.id.userDemographicsSpinnerSpecialty);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        userType = getIntent().getStringExtra("userType");


        hospitalList = new ArrayList<>();
        userDemographicsSpinnerHospitalList = new ArrayList<>();
        userDemographicsSpinnerHospitalList.add("Select Hospital That You are Working at");

        // loadHospital();

        //hospital id yok hospital valuesi olarak
        mDatabase.child("hospitals").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {


                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        String hospitalId = snapshot.getKey();
                        Hospital hospital = snapshot.getValue(Hospital.class);
                        hospital.setHospitalId(hospitalId);
                        hospitalList.add(hospital);
                        userDemographicsSpinnerHospitalList.add(hospital.getHospitalName());

                    }
                }
            }
        });


        if (userType.equals("Doctor")) {

            userDemographicsSpinnerHospital.setVisibility(View.VISIBLE);
            userDemographicsSpinnerSpecialty.setVisibility(View.VISIBLE);
            userDemographicsSpinnerDoYouWorkHealth.setVisibility(View.INVISIBLE);
            // userDemographicsSpinnerDoYouWorkHealth.setEnabled(false);

            //userDemographicsSpinnerHospital.setEnabled(true);

            //loadHospital();


            ArrayAdapter<String> userDemographicsSpinnerHospitalAdapter = new ArrayAdapter<String>(UserDemographics.this, android.R.layout.simple_expandable_list_item_1, userDemographicsSpinnerHospitalList);
            userDemographicsSpinnerHospital.setAdapter(userDemographicsSpinnerHospitalAdapter);


            ArrayList<String> userDemographicsSpinnerSpecialtyList = new ArrayList<>();
            userDemographicsSpinnerSpecialtyList.add("Select your Specialty");
            userDemographicsSpinnerSpecialtyList.add("Internal diseases");
            userDemographicsSpinnerSpecialtyList.add("Cardiology");
            userDemographicsSpinnerSpecialtyList.add("Chest Diseases");
            userDemographicsSpinnerSpecialtyList.add("Infectious Diseases");
            userDemographicsSpinnerSpecialtyList.add("Neurology");
            userDemographicsSpinnerSpecialtyList.add("Child Health and Diseases");
            userDemographicsSpinnerSpecialtyList.add("Dermatology");
            userDemographicsSpinnerSpecialtyList.add("Radiology");
            ArrayAdapter<String> userDemographicsSpinnerSpecialtyAdapter = new ArrayAdapter<String>(UserDemographics.this, android.R.layout.simple_expandable_list_item_1, userDemographicsSpinnerSpecialtyList);
            userDemographicsSpinnerSpecialty.setAdapter(userDemographicsSpinnerSpecialtyAdapter);


        } else {
            userDemographicsSpinnerHospital.setVisibility(View.INVISIBLE);
            userDemographicsSpinnerSpecialty.setVisibility(View.INVISIBLE);
            userDemographicsSpinnerDoYouWorkHealth.setVisibility(View.VISIBLE);

            ArrayList<String> userDemographicsSpinnerDoYouWorkHealthList = new ArrayList<>();
            userDemographicsSpinnerDoYouWorkHealthList.add("Do you work in Health area?");
            userDemographicsSpinnerDoYouWorkHealthList.add("Nurse");
            userDemographicsSpinnerDoYouWorkHealthList.add("Doctor");
            userDemographicsSpinnerDoYouWorkHealthList.add("Others");
            userDemographicsSpinnerDoYouWorkHealthList.add("No");

            ArrayAdapter<String> userDemographicsSpinnerDoYouWorkHealthAdapter = new ArrayAdapter<String>(UserDemographics.this, android.R.layout.simple_expandable_list_item_1, userDemographicsSpinnerDoYouWorkHealthList);
            userDemographicsSpinnerDoYouWorkHealth.setAdapter(userDemographicsSpinnerDoYouWorkHealthAdapter);

        }


        ArrayList<String> userDemographicsSpinnerMaritalStatusList = new ArrayList<>();
        userDemographicsSpinnerMaritalStatusList.add("Marital Status");
        userDemographicsSpinnerMaritalStatusList.add("Single");
        userDemographicsSpinnerMaritalStatusList.add("Married");
        ArrayAdapter<String> userDemographicsSpinnerMaritalStatusAdapter = new ArrayAdapter<String>(UserDemographics.this, android.R.layout.simple_expandable_list_item_1, userDemographicsSpinnerMaritalStatusList);
        userDemographicsSpinnerMaritalStatus.setAdapter(userDemographicsSpinnerMaritalStatusAdapter);

        ArrayList<String> userDemographicsSpinnerCurrentCityList = new ArrayList<>();
        userDemographicsSpinnerCurrentCityList.add("Current City Your Apartment Located");
        userDemographicsSpinnerCurrentCityList.add("GaziMagusa");
        userDemographicsSpinnerCurrentCityList.add("Girne");
        userDemographicsSpinnerCurrentCityList.add("Lefkosa");
        userDemographicsSpinnerCurrentCityList.add("Lefke");
        ArrayAdapter<String> userDemographicsSpinnerCurrentCityAdapter = new ArrayAdapter<String>(UserDemographics.this, android.R.layout.simple_expandable_list_item_1, userDemographicsSpinnerCurrentCityList);
        userDemographicsSpinnerCurrentCity.setAdapter(userDemographicsSpinnerCurrentCityAdapter);


    }

    public void loadHospital() {

        mDatabase.child("hospitals").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {

                    hospitalList.clear();

                    for (DataSnapshot snapshot : task.getResult().getChildren()) {

                        Hospital hospital = snapshot.getValue(Hospital.class);
                        hospitalList.add(hospital);
                        userDemographicsSpinnerHospitalList.add(hospital.getHospitalName());

                    }
                }
            }
        });

    }

    public void userDemographicsButtonClicked(View view) {

        if (userType.equals("Doctor")) {

            if (!userDemographicsWeight.getText().toString().isEmpty() &&
                    !userDemographicsHeight.getText().toString().isEmpty() &&
                    !userDemographicsBirthDate.getText().toString().isEmpty() &&
                    !userDemographicsMobileNo.getText().toString().isEmpty() &&
                    userDemographicsSpinnerMaritalStatus.getSelectedItemPosition() != 0 &&
                    userDemographicsSpinnerCurrentCity.getSelectedItemPosition() != 0 &&
                    userDemographicsSpinnerHospital.getSelectedItemPosition() != 0 &&
                    userDemographicsSpinnerSpecialty.getSelectedItemPosition() != 0) {

                // write database
                String userId = mAuth.getCurrentUser().getUid();
                Double weight = Double.parseDouble(userDemographicsWeight.getText().toString());
                Double height = Double.parseDouble(userDemographicsHeight.getText().toString());
                String birthDate = userDemographicsBirthDate.getText().toString();
                String mobileNumber = userDemographicsMobileNo.getText().toString();
                String maritalStatus = userDemographicsSpinnerMaritalStatus.getSelectedItemPosition() == 1 ? "Single" : "Married";
                String currentCity = userDemographicsSpinnerCurrentCity.getSelectedItem().toString();
                String specialty = userDemographicsSpinnerSpecialty.getSelectedItem().toString();
                int index = userDemographicsSpinnerHospital.getSelectedItemPosition();
                String hospitalId = hospitalList.get(index - 1).getHospitalId();
                String hospitalName = hospitalList.get(index - 1).getHospitalName();
                try {
                    /*
                    String[] dateList = birthDate.split("/");
                    day = Integer.parseInt(dateList[0]);
                    month = Integer.parseInt(dateList[1]);
                    year = Integer.parseInt(dateList[2]);

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, day);
                    Date date = cal.getTime();
                    int bithdateInt = (int) (date.getTime() / 1000);

                     */
                    long birthdate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(birthDate+" 01:00:00").getTime() / 1000;

                    // Add information to database
                    mDatabase.child("doctors").child(userId).child("weight").setValue(weight);
                    mDatabase.child("doctors").child(userId).child("height").setValue(height);
                    mDatabase.child("doctors").child(userId).child("birthdate").setValue(birthdate);
                    mDatabase.child("doctors").child(userId).child("mobileNumber").setValue(mobileNumber);
                    mDatabase.child("doctors").child(userId).child("maritalStatus").setValue(maritalStatus);
                    mDatabase.child("doctors").child(userId).child("currentCity").setValue(currentCity);
                    mDatabase.child("doctors").child(userId).child("hospitalId").setValue(hospitalId);
                    mDatabase.child("doctors").child(userId).child("hospitalName").setValue(hospitalName);
                    mDatabase.child("doctors").child(userId).child("specialty").setValue(specialty);

                    //Intent Doctor Home Page
                    Log.e("covid19App", "UserDemographics -> DoctorHomePage ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                    startActivity(new Intent(UserDemographics.this, DoctorHomePage.class));
                    finish();

                } catch (Exception e) {
                    Log.e("covid19App", e.getLocalizedMessage() + " in UserDemographics ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                    Toast.makeText(UserDemographics.this, "Birthdate must be given format! dd/mm/yyyy", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(UserDemographics.this, "Fill all areas!", Toast.LENGTH_SHORT).show();
            }
        } else if (userType.equals("Patient")) {

            if (!userDemographicsWeight.getText().toString().isEmpty() &&
                    !userDemographicsHeight.getText().toString().isEmpty() &&
                    !userDemographicsBirthDate.getText().toString().isEmpty() &&
                    !userDemographicsMobileNo.getText().toString().isEmpty() &&
                    userDemographicsSpinnerMaritalStatus.getSelectedItemPosition() != 0 &&
                    userDemographicsSpinnerDoYouWorkHealth.getSelectedItemPosition() != 0 &&
                    userDemographicsSpinnerCurrentCity.getSelectedItemPosition() != 0) {

                String userId = mAuth.getCurrentUser().getUid();
                Double weight = Double.parseDouble(userDemographicsWeight.getText().toString());
                Double height = Double.parseDouble(userDemographicsHeight.getText().toString());
                String birthDate = userDemographicsBirthDate.getText().toString();
                String mobileNumber = userDemographicsMobileNo.getText().toString();
                String maritalStatus = userDemographicsSpinnerMaritalStatus.getSelectedItemPosition() == 1 ? "Single" : "Married";
                String healthSector = userDemographicsSpinnerDoYouWorkHealth.getSelectedItem().toString();
                String currentCity = userDemographicsSpinnerCurrentCity.getSelectedItem().toString();

                try {
                    /*
                    String[] dateList = birthDate.split("/");
                    day = Integer.parseInt(dateList[0]);
                    month = Integer.parseInt(dateList[1]);
                    year = Integer.parseInt(dateList[2]);

                    Calendar cal = Calendar.getInstance();
                    cal.set(year, month, day);
                    Date date = cal.getTime();
                    int bithdateInt = (int) (date.getTime() / 1000);

                     */
                    long birthdate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(birthDate+" 01:00:00").getTime() / 1000;


                    // Add all data to database
                    mDatabase.child("users").child(userId).child("weight").setValue(weight);
                    mDatabase.child("users").child(userId).child("height").setValue(height);
                    mDatabase.child("users").child(userId).child("birthdate").setValue(birthdate);
                    mDatabase.child("users").child(userId).child("mobileNumber").setValue(mobileNumber);
                    mDatabase.child("users").child(userId).child("maritalStatus").setValue(maritalStatus);
                    mDatabase.child("users").child(userId).child("healthSector").setValue(healthSector);
                    mDatabase.child("users").child(userId).child("currentCity").setValue(currentCity);

                    //Check if user exist in Medical Condition table. If not aim user to medical condition page
                    mDatabase.child("medical_conditions").child(userId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {

                            // if user didn't give medical conditions information aim him to medical condition page
                            if (!dataSnapshot.exists()) {

                                Log.e("covid19App", "UserDemographics -> MedicalConditions ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                                startActivity(new Intent(UserDemographics.this, MedicalConditions.class));
                                finish();
                            } else {
                                //Aim to Patient page
                                Log.e("covid19App", "UserDemographics -> PatientHomePage ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                                startActivity(new Intent(UserDemographics.this, PatientHomePage.class));
                                finish();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("covid19App", e.getLocalizedMessage() + " ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                            Toast.makeText(UserDemographics.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    Log.e("covid19App", e.getLocalizedMessage() + " in UserDemographics ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in UserDemographics)");
                    Toast.makeText(UserDemographics.this, "Birthdate must be given format! dd/mm/yyyy", Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(UserDemographics.this, "Fill all areas and wait!", Toast.LENGTH_SHORT).show();
            }

        }

    }


}