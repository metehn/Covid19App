package com.metehanersoy.covid19app.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.LogIn;
import com.metehanersoy.covid19app.MedicalConditions;
import com.metehanersoy.covid19app.R;
import com.metehanersoy.covid19app.UserDemographics;

public class PatientHomePage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    FirebaseUser user;
    String userId;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.flFragment, new PatientHomeFragment()).commit();

        //fragmentTransaction = fragmentManager.beginTransaction();


        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        userId = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        checkDataInDatabase();



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {

                    case R.id.bottomBarMenuHomePage:
                        fragment = new PatientHomeFragment();
                        break;
                    case R.id.bottomBarMenuProfile:
                        fragment = new PatientProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flFragment,fragment).commit();
                return true;
            }
        });

    }

    // checks if user gave Demographics and medical conditions or not
    public void checkDataInDatabase() {

        mDatabase.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                                && dataSnapshot.child("currentCity").getValue() != null) {

                            //All data is okay for user demographics, now check for medical conditions data
                            mDatabase.child("medical_conditions").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {

                                    if (task.isSuccessful()) {
                                        DataSnapshot dataSnapshot = task.getResult();
                                        if (dataSnapshot.exists()) {
                                            // there is medical conditions in database, check them all and be sure any of them null. if at least one empty&null data, aim user to medical conditions page
                                            if (dataSnapshot.child("hasARDS").getValue() != null
                                                    && dataSnapshot.child("hasPneumonia").getValue() != null
                                                    && dataSnapshot.child("hasCovid").getValue() != null
                                                    && dataSnapshot.child("hasSARS").getValue() != null
                                                    && dataSnapshot.child("beenToICU").getValue() != null
                                                    && dataSnapshot.child("hasLungDisease").getValue() != null
                                                    && dataSnapshot.child("hasDiabetes").getValue() != null
                                                    && dataSnapshot.child("hasHypertension").getValue() != null
                                                    && dataSnapshot.child("hasLiverDisease").getValue() != null
                                                    && dataSnapshot.child("hasKidneyDisease").getValue() != null
                                                    && dataSnapshot.child("hasHeartDisease").getValue() != null
                                                    && dataSnapshot.child("hasGeneticDisorder").getValue() != null
                                                    && dataSnapshot.child("hasBloodCancer").getValue() != null
                                                    && dataSnapshot.child("hasCancer").getValue() != null
                                                    && dataSnapshot.child("isTakingChemo").getValue() != null
                                                    && dataSnapshot.child("hasImmuneSystemDisorder").getValue() != null
                                                    && dataSnapshot.child("isTakingPainkillersRegularly").getValue() != null
                                                    && dataSnapshot.child("isTakingimmunosuppressive").getValue() != null
                                                    && dataSnapshot.child("isCarrierOrPatientOfThalassemia").getValue() != null

                                            ) {
                                                // There is no null & empty value in medical conditions table so do nothing!


                                            } else {
                                                // at least there is one null medical condition in database aim user to medical conditions page
                                                Log.e("covid19App", "PatientHomePage -> MedicalConditions ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");
                                                startActivity(new Intent(PatientHomePage.this, MedicalConditions.class));
                                                finish();

                                            }
                                        } else {
                                            // there is no medical conditions in database aim user to medical conditions page
                                            Log.e("covid19App", "PatientHomePage -> MedicalConditions ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");
                                            startActivity(new Intent(PatientHomePage.this, MedicalConditions.class));
                                            finish();
                                        }

                                    } else {
                                        Log.e("covid19App", task.getException().getLocalizedMessage() + " Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage");
                                    }
                                }
                            });


                        } else {
                            //Some data is missing aim user to UserDemographics Page
                            Log.e("covid19App", "PatientHomePage -> UserDemographics ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in PatientHomePage)");
                            Intent intent = new Intent(PatientHomePage.this, UserDemographics.class);
                            intent.putExtra("userType","Patient");
                            startActivity(intent);
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


    public  void addFragments(Fragment fragment,String tag) {

        fragmentManager.beginTransaction().add(R.id.flFragment, fragment, tag).commit();
    }
    public void removeFragments(String tag ) {

        Fragment fragmentB =  fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentB != null) {
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }
    }
    public boolean isExist(String tag ) {

        Fragment fragmentB =  fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentB != null) {
            return true;
        }
        return false;
    }


}