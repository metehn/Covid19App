package com.metehanersoy.covid19app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Patient.PatientHomePage;

public class MedicalConditions extends AppCompatActivity {

    // Firebase elements
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    String userId;

    RadioButton radioButton1_1, radioButton1_2, radioButton2_1, radioButton2_2, radioButton3_1, radioButton3_2, radioButton4_1, radioButton4_2, radioButton5_1, radioButton5_2, radioButton6_1, radioButton6_2, radioButton7_1, radioButton7_2, radioButton8_1, radioButton8_2, radioButton9_1, radioButton9_2, radioButton10_1, radioButton10_2, radioButton11_1, radioButton11_2, radioButton12_1, radioButton12_2, radioButton13_1, radioButton13_2, radioButton14_1, radioButton14_2, radioButton15_1, radioButton15_2, radioButton16_1, radioButton16_2, radioButton17_1, radioButton17_2, radioButton18_1, radioButton18_2, radioButton19_1, radioButton19_2;
    Button buttonMedicalConditionsNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_conditions);

        buttonMedicalConditionsNext = findViewById(R.id.buttonMedicalConditionsNext);

        radioButton1_1 = findViewById(R.id.radioButton1_1);
        radioButton1_2 = findViewById(R.id.radioButton1_2);
        radioButton2_1 = findViewById(R.id.radioButton2_1);
        radioButton2_2 = findViewById(R.id.radioButton2_2);
        radioButton3_1 = findViewById(R.id.radioButton3_1);
        radioButton3_2 = findViewById(R.id.radioButton3_2);
        radioButton4_1 = findViewById(R.id.radioButton4_1);
        radioButton4_2 = findViewById(R.id.radioButton4_2);
        radioButton5_1 = findViewById(R.id.radioButton5_1);
        radioButton5_2 = findViewById(R.id.radioButton5_2);
        radioButton6_1 = findViewById(R.id.radioButton6_1);
        radioButton6_2 = findViewById(R.id.radioButton6_2);
        radioButton7_1 = findViewById(R.id.radioButton7_1);
        radioButton7_2 = findViewById(R.id.radioButton7_2);
        radioButton8_1 = findViewById(R.id.radioButton8_1);
        radioButton8_2 = findViewById(R.id.radioButton8_2);
        radioButton9_1 = findViewById(R.id.radioButton9_1);
        radioButton9_2 = findViewById(R.id.radioButton9_2);
        radioButton10_1 = findViewById(R.id.radioButton10_1);
        radioButton10_2 = findViewById(R.id.radioButton10_2);
        radioButton11_1 = findViewById(R.id.radioButton11_1);
        radioButton11_2 = findViewById(R.id.radioButton11_2);
        radioButton12_1 = findViewById(R.id.radioButton12_1);
        radioButton12_2 = findViewById(R.id.radioButton12_2);
        radioButton13_1 = findViewById(R.id.radioButton13_1);
        radioButton13_2 = findViewById(R.id.radioButton13_2);
        radioButton14_1 = findViewById(R.id.radioButton14_1);
        radioButton14_2 = findViewById(R.id.radioButton14_2);
        radioButton15_1 = findViewById(R.id.radioButton15_1);
        radioButton15_2 = findViewById(R.id.radioButton15_2);
        radioButton16_1 = findViewById(R.id.radioButton16_1);
        radioButton16_2 = findViewById(R.id.radioButton16_2);
        radioButton17_1 = findViewById(R.id.radioButton17_1);
        radioButton17_2 = findViewById(R.id.radioButton17_2);
        radioButton18_1 = findViewById(R.id.radioButton18_1);
        radioButton18_2 = findViewById(R.id.radioButton18_2);
        radioButton19_1 = findViewById(R.id.radioButton19_1);
        radioButton19_2 = findViewById(R.id.radioButton19_2);

        // initialize firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        //userId
        userId = user.getUid();
    }

    public void nextButtonClicked(View view) {

        if ((radioButton1_1.isChecked() || radioButton1_2.isChecked())
                && (radioButton2_1.isChecked() || radioButton2_2.isChecked())
                && (radioButton3_1.isChecked() || radioButton3_2.isChecked())
                && (radioButton4_1.isChecked() || radioButton4_2.isChecked())
                && (radioButton5_1.isChecked() || radioButton5_2.isChecked())
                && (radioButton6_1.isChecked() || radioButton6_2.isChecked())
                && (radioButton7_1.isChecked() || radioButton7_2.isChecked())
                && (radioButton8_1.isChecked() || radioButton8_2.isChecked())
                && (radioButton9_1.isChecked() || radioButton9_2.isChecked())
                && (radioButton10_1.isChecked() || radioButton10_2.isChecked())
                && (radioButton11_1.isChecked() || radioButton11_2.isChecked())
                && (radioButton12_1.isChecked() || radioButton12_2.isChecked())
                && (radioButton13_1.isChecked() || radioButton13_2.isChecked())
                && (radioButton14_1.isChecked() || radioButton14_2.isChecked())
                && (radioButton15_1.isChecked() || radioButton15_2.isChecked())
                && (radioButton16_1.isChecked() || radioButton16_2.isChecked())
                && (radioButton17_1.isChecked() || radioButton17_2.isChecked())
                && (radioButton18_1.isChecked() || radioButton18_2.isChecked())
                && (radioButton19_1.isChecked() || radioButton19_2.isChecked())
        ) {

            // write all data to database
            if (radioButton1_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasARDS").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasARDS").setValue(false);
            }

            if (radioButton2_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasPneumonia").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasPneumonia").setValue(false);
            }

            if (radioButton3_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasCovid").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasCovid").setValue(false);
            }

            if (radioButton4_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasSARS").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasSARS").setValue(false);
            }

            if (radioButton5_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("beenToICU").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("beenToICU").setValue(false);
            }

            if (radioButton6_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasLungDisease").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasLungDisease").setValue(false);
            }

            if (radioButton7_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasDiabetes").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasDiabetes").setValue(false);
            }

            if (radioButton8_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasHypertension").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasHypertension").setValue(false);
            }

            if (radioButton9_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasLiverDisease").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasLiverDisease").setValue(false);
            }

            if (radioButton10_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasKidneyDisease").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasKidneyDisease").setValue(false);
            }

            if (radioButton11_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasHeartDisease").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasHeartDisease").setValue(false);
            }

            if (radioButton12_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasGeneticDisorder").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasGeneticDisorder").setValue(false);
            }

            if (radioButton13_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasBloodCancer").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasBloodCancer").setValue(false);
            }

            if (radioButton14_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasCancer").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasCancer").setValue(false);
            }

            if (radioButton15_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("isTakingChemo").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("isTakingChemo").setValue(false);
            }

            if (radioButton16_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("hasImmuneSystemDisorder").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("hasImmuneSystemDisorder").setValue(false);
            }

            if (radioButton17_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("isTakingPainkillersRegularly").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("isTakingPainkillersRegularly").setValue(false);
            }

            if (radioButton18_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("isTakingimmunosuppressive").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("isTakingimmunosuppressive").setValue(false);
            }

            if (radioButton19_1.isChecked()) {
                mDatabase.child("medical_conditions").child(userId).child("isCarrierOrPatientOfThalassemia").setValue(true);
            } else {
                mDatabase.child("medical_conditions").child(userId).child("isCarrierOrPatientOfThalassemia").setValue(false);
            }

            // intent to Patient Home Page
            Log.e("covid19App","MedicalConditions -> PatientHomePage ( Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in MedicalConditions)");
            startActivity(new Intent(MedicalConditions.this, PatientHomePage.class));
            finish();

        } else {

            Toast.makeText(MedicalConditions.this, "Please Fill ALl Areas!", Toast.LENGTH_SHORT).show();

        }

    }
}