package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.R;

import java.util.HashMap;
import java.util.Map;

public class MedicalConditionsFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;



    RadioButton radioButton1_1_MedicalConditions, radioButton1_2_MedicalConditions, radioButton2_1_MedicalConditions
            , radioButton2_2_MedicalConditions, radioButton3_1_MedicalConditions, radioButton3_2_MedicalConditions
            , radioButton4_1_MedicalConditions, radioButton4_2_MedicalConditions, radioButton5_1_MedicalConditions
            , radioButton5_2_MedicalConditions, radioButton6_1_MedicalConditions, radioButton6_2_MedicalConditions
            , radioButton7_1_MedicalConditions, radioButton7_2_MedicalConditions, radioButton8_1_MedicalConditions
            , radioButton8_2_MedicalConditions, radioButton9_1_MedicalConditions, radioButton9_2_MedicalConditions
            , radioButton10_1_MedicalConditions, radioButton10_2_MedicalConditions, radioButton11_1_MedicalConditions
            , radioButton11_2_MedicalConditions, radioButton12_1_MedicalConditions, radioButton12_2_MedicalConditions
            , radioButton13_1_MedicalConditions, radioButton13_2_MedicalConditions, radioButton14_1_MedicalConditions
            , radioButton14_2_MedicalConditions, radioButton15_1_MedicalConditions, radioButton15_2_MedicalConditions
            , radioButton16_1_MedicalConditions, radioButton16_2_MedicalConditions, radioButton17_1_MedicalConditions
            , radioButton17_2_MedicalConditions, radioButton18_1_MedicalConditions, radioButton18_2_MedicalConditions
            , radioButton19_1_MedicalConditions, radioButton19_2_MedicalConditions;

    Button buttonMedicalConditionsNext_MedicalConditions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_medical_conditions, container, false);

        // initialize firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        radioButton1_1_MedicalConditions = view.findViewById(R.id.radioButton1_1_MedicalConditions);
        radioButton1_2_MedicalConditions = view.findViewById(R.id.radioButton1_2_MedicalConditions);
        radioButton2_1_MedicalConditions = view.findViewById(R.id.radioButton2_1_MedicalConditions);
        radioButton2_2_MedicalConditions = view.findViewById(R.id.radioButton2_2_MedicalConditions);
        radioButton3_1_MedicalConditions = view.findViewById(R.id.radioButton3_1_MedicalConditions);
        radioButton3_2_MedicalConditions = view.findViewById(R.id.radioButton3_2_MedicalConditions);
        radioButton4_1_MedicalConditions = view.findViewById(R.id.radioButton4_1_MedicalConditions);
        radioButton4_2_MedicalConditions = view.findViewById(R.id.radioButton4_2_MedicalConditions);
        radioButton5_1_MedicalConditions = view.findViewById(R.id.radioButton5_1_MedicalConditions);
        radioButton5_2_MedicalConditions = view.findViewById(R.id.radioButton5_2_MedicalConditions);
        radioButton6_1_MedicalConditions = view.findViewById(R.id.radioButton6_1_MedicalConditions);
        radioButton6_2_MedicalConditions = view.findViewById(R.id.radioButton6_2_MedicalConditions);
        radioButton7_1_MedicalConditions = view.findViewById(R.id.radioButton7_1_MedicalConditions);
        radioButton7_2_MedicalConditions = view.findViewById(R.id.radioButton7_2_MedicalConditions);
        radioButton8_1_MedicalConditions = view.findViewById(R.id.radioButton8_1_MedicalConditions);
        radioButton8_2_MedicalConditions = view.findViewById(R.id.radioButton8_2_MedicalConditions);
        radioButton9_1_MedicalConditions = view.findViewById(R.id.radioButton9_1_MedicalConditions);
        radioButton9_2_MedicalConditions = view.findViewById(R.id.radioButton9_2_MedicalConditions);
        radioButton10_1_MedicalConditions = view.findViewById(R.id.radioButton10_1_MedicalConditions);
        radioButton10_2_MedicalConditions = view.findViewById(R.id.radioButton10_2_MedicalConditions);
        radioButton11_1_MedicalConditions = view.findViewById(R.id.radioButton11_1_MedicalConditions);
        radioButton11_2_MedicalConditions = view.findViewById(R.id.radioButton11_2_MedicalConditions);
        radioButton12_1_MedicalConditions = view.findViewById(R.id.radioButton12_1_MedicalConditions);
        radioButton12_2_MedicalConditions = view.findViewById(R.id.radioButton12_2_MedicalConditions);
        radioButton13_1_MedicalConditions = view.findViewById(R.id.radioButton13_1_MedicalConditions);
        radioButton13_2_MedicalConditions = view.findViewById(R.id.radioButton13_2_MedicalConditions);
        radioButton14_1_MedicalConditions = view.findViewById(R.id.radioButton14_1_MedicalConditions);
        radioButton14_2_MedicalConditions = view.findViewById(R.id.radioButton14_2_MedicalConditions);
        radioButton15_1_MedicalConditions = view.findViewById(R.id.radioButton15_1_MedicalConditions);
        radioButton15_2_MedicalConditions = view.findViewById(R.id.radioButton15_2_MedicalConditions);
        radioButton16_1_MedicalConditions = view.findViewById(R.id.radioButton16_1_MedicalConditions);
        radioButton16_2_MedicalConditions = view.findViewById(R.id.radioButton16_2_MedicalConditions);
        radioButton17_1_MedicalConditions = view.findViewById(R.id.radioButton17_1_MedicalConditions);
        radioButton17_2_MedicalConditions = view.findViewById(R.id.radioButton17_2_MedicalConditions);
        radioButton18_1_MedicalConditions = view.findViewById(R.id.radioButton18_1_MedicalConditions);
        radioButton18_2_MedicalConditions = view.findViewById(R.id.radioButton18_2_MedicalConditions);
        radioButton19_1_MedicalConditions = view.findViewById(R.id.radioButton19_1_MedicalConditions);
        radioButton19_2_MedicalConditions = view.findViewById(R.id.radioButton19_2_MedicalConditions);
        buttonMedicalConditionsNext_MedicalConditions = view.findViewById(R.id.buttonMedicalConditionsNext_MedicalConditions);


        mDatabase.child("medical_conditions").child(mAuth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                //set update button clickable
                buttonMedicalConditionsNext_MedicalConditions.setClickable(true);

                boolean hasARDS = (boolean) dataSnapshot.child("hasARDS").getValue();
                if(hasARDS){
                    radioButton1_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton1_2_MedicalConditions.setChecked(true);
                }

                boolean hasPneumonia = (boolean) dataSnapshot.child("hasPneumonia").getValue();
                if(hasPneumonia){
                    radioButton2_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton2_2_MedicalConditions.setChecked(true);
                }

                boolean hasCovid = (boolean) dataSnapshot.child("hasCovid").getValue();
                if(hasCovid){
                    radioButton3_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton3_2_MedicalConditions.setChecked(true);
                }

                boolean hasSARS = (boolean) dataSnapshot.child("hasSARS").getValue();
                if(hasSARS){
                    radioButton4_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton4_2_MedicalConditions.setChecked(true);
                }

                boolean beenToICU = (boolean) dataSnapshot.child("beenToICU").getValue();
                if(beenToICU){
                    radioButton5_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton5_2_MedicalConditions.setChecked(true);
                }

                boolean hasLungDisease = (boolean) dataSnapshot.child("hasLungDisease").getValue();
                if(hasLungDisease){
                    radioButton6_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton6_2_MedicalConditions.setChecked(true);
                }

                boolean hasDiabetes = (boolean) dataSnapshot.child("hasDiabetes").getValue();
                if(hasDiabetes){
                    radioButton7_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton7_2_MedicalConditions.setChecked(true);
                }

                boolean hasHypertension = (boolean) dataSnapshot.child("hasHypertension").getValue();
                if(hasHypertension){
                    radioButton8_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton8_2_MedicalConditions.setChecked(true);
                }

                boolean hasLiverDisease = (boolean) dataSnapshot.child("hasLiverDisease").getValue();
                if(hasLiverDisease){
                    radioButton9_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton9_2_MedicalConditions.setChecked(true);
                }

                boolean hasKidneyDisease = (boolean) dataSnapshot.child("hasKidneyDisease").getValue();
                if(hasKidneyDisease){
                    radioButton10_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton10_2_MedicalConditions.setChecked(true);
                }

                boolean hasHeartDisease = (boolean) dataSnapshot.child("hasHeartDisease").getValue();
                if(hasHeartDisease){
                    radioButton11_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton11_2_MedicalConditions.setChecked(true);
                }

                boolean hasGeneticDisorder = (boolean) dataSnapshot.child("hasGeneticDisorder").getValue();
                if(hasGeneticDisorder){
                    radioButton12_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton12_2_MedicalConditions.setChecked(true);
                }

                boolean hasBloodCancer = (boolean) dataSnapshot.child("hasBloodCancer").getValue();
                if(hasBloodCancer){
                    radioButton13_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton13_2_MedicalConditions.setChecked(true);
                }

                boolean hasCancer = (boolean) dataSnapshot.child("hasCancer").getValue();
                if(hasCancer){
                    radioButton14_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton14_2_MedicalConditions.setChecked(true);
                }

                boolean isTakingChemo = (boolean) dataSnapshot.child("isTakingChemo").getValue();
                if(isTakingChemo){
                    radioButton15_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton15_2_MedicalConditions.setChecked(true);
                }

                boolean hasImmuneSystemDisorder = (boolean) dataSnapshot.child("hasImmuneSystemDisorder").getValue();
                if(hasImmuneSystemDisorder){
                    radioButton16_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton16_2_MedicalConditions.setChecked(true);
                }

                boolean isTakingPainkillersRegularly = (boolean) dataSnapshot.child("isTakingPainkillersRegularly").getValue();
                if(isTakingPainkillersRegularly){
                    radioButton17_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton17_2_MedicalConditions.setChecked(true);
                }

                boolean isTakingimmunosuppressive = (boolean) dataSnapshot.child("isTakingimmunosuppressive").getValue();
                if(isTakingimmunosuppressive){
                    radioButton18_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton18_2_MedicalConditions.setChecked(true);
                }
                boolean isCarrierOrPatientOfThalassemia = (boolean) dataSnapshot.child("isCarrierOrPatientOfThalassemia").getValue();
                if(isCarrierOrPatientOfThalassemia){
                    radioButton19_1_MedicalConditions.setChecked(true);
                }else{
                    radioButton19_2_MedicalConditions.setChecked(true);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // set update button not clickable
                buttonMedicalConditionsNext_MedicalConditions.setClickable(false);
            }
        });




        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                // ((SubmittedCardsActivity) getActivity()).popBackStack("submittedToFeedback", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ((PatientHomePage) getActivity()).removeFragments("patientProfileToMedicalConditions");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);



        buttonMedicalConditionsNext_MedicalConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Boolean> updates = new HashMap<>();
                updates.put("hasARDS", radioButton1_1_MedicalConditions.isChecked());
                updates.put("hasPneumonia", radioButton2_1_MedicalConditions.isChecked());
                updates.put("hasCovid", radioButton3_1_MedicalConditions.isChecked());
                updates.put("hasSARS", radioButton4_1_MedicalConditions.isChecked());
                updates.put("beenToICU", radioButton5_1_MedicalConditions.isChecked());
                updates.put("hasLungDisease", radioButton6_1_MedicalConditions.isChecked());
                updates.put("hasDiabetes", radioButton7_1_MedicalConditions.isChecked());
                updates.put("hasHypertension", radioButton8_1_MedicalConditions.isChecked());
                updates.put("hasLiverDisease", radioButton9_1_MedicalConditions.isChecked());
                updates.put("hasKidneyDisease", radioButton10_1_MedicalConditions.isChecked());
                updates.put("hasHeartDisease", radioButton11_1_MedicalConditions.isChecked());
                updates.put("hasGeneticDisorder", radioButton12_1_MedicalConditions.isChecked());
                updates.put("hasBloodCancer", radioButton13_1_MedicalConditions.isChecked());
                updates.put("hasCancer", radioButton14_1_MedicalConditions.isChecked());
                updates.put("isTakingChemo", radioButton15_1_MedicalConditions.isChecked());
                updates.put("hasImmuneSystemDisorder", radioButton16_1_MedicalConditions.isChecked());
                updates.put("isTakingPainkillersRegularly", radioButton17_1_MedicalConditions.isChecked());
                updates.put("isTakingimmunosuppressive", radioButton18_1_MedicalConditions.isChecked());
                updates.put("isCarrierOrPatientOfThalassemia",radioButton19_1_MedicalConditions.isChecked());

                mDatabase.child("medical_conditions").child(mAuth.getUid()).setValue(updates);
                Toast.makeText(getActivity(), "Medical Conditions successfully updated ", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}