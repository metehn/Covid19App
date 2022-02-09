package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Class.Doctor;
import com.metehanersoy.covid19app.Class.Symptoms;
import com.metehanersoy.covid19app.R;

import java.util.ArrayList;

public class SymptomsFragment extends Fragment {

    TextView hasLossOfSmellAndTaste, hasAbdominalPain, hasAnorexia, hasBluishFaceAndLips, hadBodyAches, hasChestPain, hasChillsAndShaking, hasConfusion, hasDelirium, hasDiarrhea, hasDizziness, hasFatigue, hasFever, isFeelingUnwell, hasHeadache, hasHoarseness, hasRunnyNose, hasMusclePain, hasNasalStuffiness, hasNausea, hasOcularReaction, hasPersistentCough, hasRhinorrhea, hasShortnessOfBreath, hasSkinRash, hasInappetence, hasSneeze, hasSoreThroat, hasSputum, hasVomiting;

    AppCompatButton btn_symptomsFragment;


    FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    Bundle bundle;
    String cardId = "null";
    Symptoms symptoms;
    String userId = "null";
    String userType;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        hasLossOfSmellAndTaste = view.findViewById(R.id.hasLossOfSmellAndTaste);
        hasAbdominalPain = view.findViewById(R.id.hasAbdominalPain);
        hasAnorexia = view.findViewById(R.id.hasAnorexia);
        hasBluishFaceAndLips = view.findViewById(R.id.hasBluishFaceAndLips);
        hadBodyAches = view.findViewById(R.id.hadBodyAches);
        hasChestPain = view.findViewById(R.id.hasChestPain);
        hasChillsAndShaking = view.findViewById(R.id.hasChillsAndShaking);
        hasConfusion = view.findViewById(R.id.hasConfusion);
        hasDelirium = view.findViewById(R.id.hasDelirium);
        hasDiarrhea = view.findViewById(R.id.hasDiarrhea);
        hasDizziness = view.findViewById(R.id.hasDizziness);
        hasFatigue = view.findViewById(R.id.hasFatigue);
        hasFever = view.findViewById(R.id.hasFever);
        isFeelingUnwell = view.findViewById(R.id.isFeelingUnwell);
        hasHeadache = view.findViewById(R.id.hasHeadache);
        hasHoarseness = view.findViewById(R.id.hasHoarseness);
        hasRunnyNose = view.findViewById(R.id.hasRunnyNose);
        hasMusclePain = view.findViewById(R.id.hasMusclePain);
        hasNasalStuffiness = view.findViewById(R.id.hasNasalStuffiness);
        hasNausea = view.findViewById(R.id.hasNausea);
        hasOcularReaction = view.findViewById(R.id.hasOcularReaction);
        hasPersistentCough = view.findViewById(R.id.hasPersistentCough);
        hasRhinorrhea = view.findViewById(R.id.hasRhinorrhea);
        hasShortnessOfBreath = view.findViewById(R.id.hasShortnessOfBreath);
        hasSkinRash = view.findViewById(R.id.hasSkinRash);
        hasInappetence = view.findViewById(R.id.hasInappetence);
        hasSneeze = view.findViewById(R.id.hasSneeze);
        hasSoreThroat = view.findViewById(R.id.hasSoreThroat);
        hasSputum = view.findViewById(R.id.hasSputum);
        hasVomiting = view.findViewById(R.id.hasVomiting);

        btn_symptomsFragment = view.findViewById(R.id.btn_symptomsFragment);


        userId = mAuth.getCurrentUser().getUid();


        bundle = this.getArguments();

        if (bundle != null) {
            userType = bundle.getString("userType","null");
            cardId = bundle.getString("ExaminationCardId","null");
            userId = bundle.getString("userId",mAuth.getUid()) ;
        }

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if(userType != null && userType.equals("Doctor")){
                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToSymptoms");
                }else{

                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToSymptoms");
                }


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        getSymptoms();


        btn_symptomsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                if(userType != null && userType.equals("Doctor")){

                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToSymptoms");
                    return;
                }

                if((SymptomsFragment) fragmentManager.findFragmentByTag("submittedToSymptoms") !=null){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToSymptoms");
                    return;
                }

                getActivity().finish();

            }
        });


        return view;
    }

    public void getSymptoms(){

        mDatabase.child("observed_symptoms").child(userId).child(cardId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()) {

                    symptoms = task.getResult().getValue(Symptoms.class);
                    symptoms.setSymptomsId(cardId);


                    hasLossOfSmellAndTaste.setText(symptoms.getHasLossOfSmellAndTaste());
                    if (symptoms.getHasLossOfSmellAndTaste().equals("Severe")) {
                        hasLossOfSmellAndTaste.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasLossOfSmellAndTaste().equals("Moderate")) {

                        hasLossOfSmellAndTaste.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasLossOfSmellAndTaste().equals("Low")) {

                        hasLossOfSmellAndTaste.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasLossOfSmellAndTaste().equals("N.O.")) {

                        hasLossOfSmellAndTaste.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasAbdominalPain.setText(symptoms.getHasAbdominalPain());
                    if (symptoms.getHasAbdominalPain().equals("Severe")) {
                        hasAbdominalPain.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasAbdominalPain().equals("Moderate")) {

                        hasAbdominalPain.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasAbdominalPain().equals("Low")) {

                        hasAbdominalPain.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasAbdominalPain().equals("N.O.")) {

                        hasAbdominalPain.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasAnorexia.setText(symptoms.getHasAnorexia());
                    if (symptoms.getHasAnorexia().equals("Severe")) {
                        hasAnorexia.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasAnorexia().equals("Moderate")) {

                        hasAnorexia.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasAnorexia().equals("Low")) {

                        hasAnorexia.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasAnorexia().equals("N.O.")) {

                        hasAnorexia.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasBluishFaceAndLips.setText(symptoms.getHasBluishFaceAndLips());
                    if (symptoms.getHasBluishFaceAndLips().equals("Severe")) {
                        hasBluishFaceAndLips.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasBluishFaceAndLips().equals("Moderate")) {

                        hasBluishFaceAndLips.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasBluishFaceAndLips().equals("Low")) {

                        hasBluishFaceAndLips.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasBluishFaceAndLips().equals("N.O.")) {

                        hasBluishFaceAndLips.setTextColor(getResources().getColor(R.color.AppBlue));
                    }


                    hadBodyAches.setText(symptoms.getHadBodyAches());
                    if (symptoms.getHadBodyAches().equals("Severe")) {
                        hadBodyAches.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHadBodyAches().equals("Moderate")) {

                        hadBodyAches.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHadBodyAches().equals("Low")) {

                        hadBodyAches.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHadBodyAches().equals("N.O.")) {

                        hadBodyAches.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasChestPain.setText(symptoms.getHasChestPain());
                    if (symptoms.getHasChestPain().equals("Severe")) {
                        hasChestPain.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasChestPain().equals("Moderate")) {

                        hasChestPain.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasChestPain().equals("Low")) {

                        hasChestPain.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasChestPain().equals("N.O.")) {

                        hasChestPain.setTextColor(getResources().getColor(R.color.AppBlue));
                    }


                    hasChillsAndShaking.setText(symptoms.getHasChillsAndShaking());
                    if (symptoms.getHasChillsAndShaking().equals("Severe")) {
                        hasChillsAndShaking.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasChillsAndShaking().equals("Moderate")) {

                        hasChillsAndShaking.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasChillsAndShaking().equals("Low")) {

                        hasChillsAndShaking.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasChillsAndShaking().equals("N.O.")) {

                        hasChillsAndShaking.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasConfusion.setText(symptoms.getHasConfusion());
                    if (symptoms.getHasConfusion().equals("Severe")) {
                        hasConfusion.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasConfusion().equals("Moderate")) {

                        hasConfusion.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasConfusion().equals("Low")) {

                        hasConfusion.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasConfusion().equals("N.O.")) {

                        hasConfusion.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasDelirium.setText(symptoms.getHasDelirium());
                    if (symptoms.getHasDelirium().equals("Severe")) {
                        hasDelirium.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasDelirium().equals("Moderate")) {

                        hasDelirium.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasDelirium().equals("Low")) {

                        hasDelirium.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasDelirium().equals("N.O.")) {

                        hasDelirium.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasDiarrhea.setText(symptoms.getHasDiarrhea());
                    if (symptoms.getHasDiarrhea().equals("Severe")) {
                        hasDiarrhea.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasDiarrhea().equals("Moderate")) {

                        hasDiarrhea.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasDiarrhea().equals("Low")) {

                        hasDiarrhea.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasDiarrhea().equals("N.O.")) {

                        hasDiarrhea.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasDizziness.setText(symptoms.getHasDizziness());
                    if (symptoms.getHasDizziness().equals("Severe")) {
                        hasDizziness.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasDizziness().equals("Moderate")) {

                        hasDizziness.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasDizziness().equals("Low")) {

                        hasDizziness.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasDizziness().equals("N.O.")) {

                        hasDizziness.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasFatigue.setText(symptoms.getHasFatigue());
                    if (symptoms.getHasFatigue().equals("Severe")) {
                        hasFatigue.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasFatigue().equals("Moderate")) {

                        hasFatigue.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasFatigue().equals("Low")) {

                        hasFatigue.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasFatigue().equals("N.O.")) {

                        hasFatigue.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasFever.setText(symptoms.getHasFever());
                    if (symptoms.getHasFever().equals("Severe")) {
                        hasFever.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasFever().equals("Moderate")) {

                        hasFever.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasFever().equals("Low")) {

                        hasFever.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasFever().equals("N.O.")) {

                        hasFever.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    isFeelingUnwell.setText(symptoms.getIsFeelingUnwell());
                    if (symptoms.getIsFeelingUnwell().equals("Severe")) {
                        isFeelingUnwell.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getIsFeelingUnwell().equals("Moderate")) {

                        isFeelingUnwell.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getIsFeelingUnwell().equals("Low")) {

                        isFeelingUnwell.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getIsFeelingUnwell().equals("N.O.")) {

                        isFeelingUnwell.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasHeadache.setText(symptoms.getHasHeadache());
                    if (symptoms.getHasHeadache().equals("Severe")) {
                        hasHeadache.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasHeadache().equals("Moderate")) {

                        hasHeadache.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasHeadache().equals("Low")) {

                        hasHeadache.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasHeadache().equals("N.O.")) {

                        hasHeadache.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasHoarseness.setText(symptoms.getHasHoarseness());
                    if (symptoms.getHasHoarseness().equals("Severe")) {
                        hasHoarseness.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasHoarseness().equals("Moderate")) {

                        hasHoarseness.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasHoarseness().equals("Low")) {

                        hasHoarseness.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasHoarseness().equals("N.O.")) {

                        hasHoarseness.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasRunnyNose.setText(symptoms.getHasRunnyNose());
                    if (symptoms.getHasRunnyNose().equals("Severe")) {
                        hasRunnyNose.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasRunnyNose().equals("Moderate")) {

                        hasRunnyNose.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasRunnyNose().equals("Low")) {

                        hasRunnyNose.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasRunnyNose().equals("N.O.")) {

                        hasRunnyNose.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasMusclePain.setText(symptoms.getHasMusclePain());
                    if (symptoms.getHasMusclePain().equals("Severe")) {
                        hasMusclePain.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasMusclePain().equals("Moderate")) {

                        hasMusclePain.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasMusclePain().equals("Low")) {

                        hasMusclePain.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasMusclePain().equals("N.O.")) {

                        hasMusclePain.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasNasalStuffiness.setText(symptoms.getHasNasalStuffiness());
                    if (symptoms.getHasNasalStuffiness().equals("Severe")) {
                        hasNasalStuffiness.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasNasalStuffiness().equals("Moderate")) {

                        hasNasalStuffiness.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasNasalStuffiness().equals("Low")) {

                        hasNasalStuffiness.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasNasalStuffiness().equals("N.O.")) {

                        hasNasalStuffiness.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasNausea.setText(symptoms.getHasNausea());
                    if (symptoms.getHasNausea().equals("Severe")) {
                        hasNausea.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasNausea().equals("Moderate")) {

                        hasNausea.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasNausea().equals("Low")) {

                        hasNausea.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasNausea().equals("N.O.")) {

                        hasNausea.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasOcularReaction.setText(symptoms.getHasOcularReaction());
                    if (symptoms.getHasOcularReaction().equals("Severe")) {
                        hasOcularReaction.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasNausea().equals("Moderate")) {

                        hasOcularReaction.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasOcularReaction().equals("Low")) {

                        hasOcularReaction.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasOcularReaction().equals("N.O.")) {

                        hasOcularReaction.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasPersistentCough.setText(symptoms.getHasPersistentCough());
                    if (symptoms.getHasPersistentCough().equals("Severe")) {
                        hasPersistentCough.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasPersistentCough().equals("Moderate")) {

                        hasPersistentCough.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasPersistentCough().equals("Low")) {

                        hasPersistentCough.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasPersistentCough().equals("N.O.")) {

                        hasPersistentCough.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasRhinorrhea.setText(symptoms.getHasRhinorrhea());
                    if (symptoms.getHasRhinorrhea().equals("Severe")) {
                        hasRhinorrhea.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasRhinorrhea().equals("Moderate")) {

                        hasRhinorrhea.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasRhinorrhea().equals("Low")) {

                        hasRhinorrhea.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasRhinorrhea().equals("N.O.")) {

                        hasRhinorrhea.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasShortnessOfBreath.setText(symptoms.getHasShortnessOfBreath());
                    if (symptoms.getHasShortnessOfBreath().equals("Severe")) {
                        hasShortnessOfBreath.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasShortnessOfBreath().equals("Moderate")) {

                        hasShortnessOfBreath.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasShortnessOfBreath().equals("Low")) {

                        hasShortnessOfBreath.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasShortnessOfBreath().equals("N.O.")) {

                        hasShortnessOfBreath.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasSkinRash.setText(symptoms.getHasSkinRash());
                    if (symptoms.getHasSkinRash().equals("Severe")) {
                        hasSkinRash.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasSkinRash().equals("Moderate")) {

                        hasSkinRash.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasSkinRash().equals("Low")) {

                        hasSkinRash.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasSkinRash().equals("N.O.")) {

                        hasSkinRash.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasInappetence.setText(symptoms.getHasInappetence());
                    if (symptoms.getHasInappetence().equals("Severe")) {
                        hasInappetence.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasInappetence().equals("Moderate")) {

                        hasInappetence.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasInappetence().equals("Low")) {

                        hasInappetence.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasInappetence().equals("N.O.")) {

                        hasInappetence.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasSneeze.setText(symptoms.getHasSneeze());
                    if (symptoms.getHasSneeze().equals("Severe")) {
                        hasSneeze.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasSneeze().equals("Moderate")) {

                        hasSneeze.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasSneeze().equals("Low")) {

                        hasSneeze.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasSneeze().equals("N.O.")) {

                        hasSneeze.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasSoreThroat.setText(symptoms.getHasSoreThroat());
                    if (symptoms.getHasSoreThroat().equals("Severe")) {
                        hasSoreThroat.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasSoreThroat().equals("Moderate")) {

                        hasSoreThroat.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasSoreThroat().equals("Low")) {

                        hasSoreThroat.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasSoreThroat().equals("N.O.")) {

                        hasSoreThroat.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasSputum.setText(symptoms.getHasSputum());
                    if (symptoms.getHasSputum().equals("Severe")) {
                        hasSputum.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasSputum().equals("Moderate")) {

                        hasSputum.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasSputum().equals("Low")) {

                        hasSputum.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasSputum().equals("N.O.")) {

                        hasSputum.setTextColor(getResources().getColor(R.color.AppBlue));
                    }

                    hasVomiting.setText(symptoms.getHasVomiting());
                    if (symptoms.getHasVomiting().equals("Severe")) {
                        hasVomiting.setTextColor(getResources().getColor(R.color.AppRed));

                    } else if (symptoms.getHasVomiting().equals("Moderate")) {

                        hasVomiting.setTextColor(getResources().getColor(R.color.AppOrange));
                    } else if (symptoms.getHasVomiting().equals("Low")) {

                        hasVomiting.setTextColor(getResources().getColor(R.color.AppYellow));

                    } else if (symptoms.getHasVomiting().equals("N.O.")) {

                        hasVomiting.setTextColor(getResources().getColor(R.color.AppBlue));
                    }



                } else {


                }
            }
        });


    }


}