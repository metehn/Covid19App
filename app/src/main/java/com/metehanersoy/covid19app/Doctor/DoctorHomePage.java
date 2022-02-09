package com.metehanersoy.covid19app.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metehanersoy.covid19app.LogIn;
import com.metehanersoy.covid19app.MedicalConditions;
import com.metehanersoy.covid19app.Patient.PatientHomeFragment;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.Patient.PatientProfileFragment;
import com.metehanersoy.covid19app.R;
import com.metehanersoy.covid19app.UserDemographics;
import com.metehanersoy.covid19app.databinding.ActivityDoctorHomePageBinding;

public class DoctorHomePage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    FrameLayout flFragment_Patient;
    BottomNavigationView bottomNavigationView_Patient;

    FragmentManager fragmentManager;
    String alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        fragmentManager = getSupportFragmentManager();

        flFragment_Patient = findViewById(R.id.flFragment_Doctor);
        bottomNavigationView_Patient = findViewById(R.id.bottomNavigationView_Doctor);


        mDatabase.child("doctors").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists()) {

                        if (dataSnapshot.child("weight").getValue() != null
                                && dataSnapshot.child("height").getValue() != null
                                && dataSnapshot.child("birthdate").getValue() != null
                                && dataSnapshot.child("mobileNumber").getValue() != null
                                && dataSnapshot.child("maritalStatus").getValue() != null
                                && dataSnapshot.child("specialty").getValue() != null
                                && dataSnapshot.child("currentCity").getValue() != null
                                && dataSnapshot.child("hospitalId").getValue() != null) {

                            // There is no null & empty value in medical conditions table so do nothing!


                        } else {
                            // at least one data is missing so direct page to UserDemographics page
                            Intent intent = new Intent(DoctorHomePage.this, UserDemographics.class);
                            intent.putExtra("userType", "Doctor");
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        // There is no user record exit from application
                        finish();
                        System.exit(0);
                    }
                }
            }
        });


        mDatabase.child("doctor_requests").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String request = String.valueOf(snapshot.getValue());
                alert = request;

                if (request.equals("true")) {

                    bottomNavigationView_Patient.setSelectedItemId(R.id.bottomBarMenuHomePage);

                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, new DoctorHomeFragment()).commit();

                } else if (request.equals("pending")) {
                    AlertViewFragment alertViewFragment = new AlertViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("request", request);
                    alertViewFragment.setArguments(bundle);

                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, alertViewFragment).commit();

                } else {
                    AlertViewFragment alertViewFragment = new AlertViewFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("request", request);
                    alertViewFragment.setArguments(bundle);
                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, alertViewFragment).commit();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bottomNavigationView_Patient.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (alert.equals("true")) {
                    switch (item.getItemId()) {

                        case R.id.bottomBarMenuHomePage:

                            fragment = new DoctorHomeFragment();
                            break;
                        case R.id.bottomBarMenuProfile:

                            fragment = new DoctorProfileFragment();
                            break;
                    }
                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, fragment).commit();
                }
                return true;
            }
        });


    }

    public void checkDoctorRequest() {
        mDatabase.child("doctor_requests").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String request = String.valueOf(snapshot.getValue());
                if (request.equals("true")) {

                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, new DoctorHomeFragment()).commit();


                } else if (request.equals("pending")) {

                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, new AlertViewFragment()).commit();

                } else {

                    fragmentManager.beginTransaction().replace(R.id.flFragment_Doctor, new AlertViewFragment()).commit();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mDatabase.child("doctors_requests").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {

                        String request = String.valueOf(task.getResult().getValue());
                        if (request.equals("pending")) {


                        } else if (request.equals("true")) {


                        } else {


                        }

                    } else {//no record found in users table

                        Log.e("covid19App", "No user found in database register user again may fix it ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");
                        finish();
                        System.exit(0);
                    }

                } else {
                    Log.e("covid19App", task.getException().getLocalizedMessage() + " Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage");
                    finish();
                    System.exit(0);
                }
            }
        });

    }

    // checks if user gave Demographics and medical conditions or not
    public void checkDataInDatabase() {

        mDatabase.child("doctors").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists()) {

                        if (dataSnapshot.child("weight").getValue() != null
                                && dataSnapshot.child("height").getValue() != null
                                && dataSnapshot.child("birthdate").getValue() != null
                                && dataSnapshot.child("mobileNumber").getValue() != null
                                && dataSnapshot.child("maritalStatus").getValue() != null
                                && dataSnapshot.child("healthSector").getValue() != null
                                && dataSnapshot.child("currentCity").getValue() != null
                                && dataSnapshot.child("").getValue() != null) {

                        } else {
                            //Some data is missing aim user to UserDemographics Page
                            Log.e("covid19App", "PatientHomePage -> UserDemographics ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");
                            startActivity(new Intent(DoctorHomePage.this, UserDemographics.class));
                            finish();
                        }
                    } else {//no record found in users table

                        Log.e("covid19App", "No user found in database register user again may fix it ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");

                    }

                } else {
                    Log.e("covid19App", task.getException().getLocalizedMessage() + " Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage");

                }
            }
        });


    }




}