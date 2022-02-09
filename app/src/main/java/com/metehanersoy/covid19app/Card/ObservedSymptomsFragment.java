package com.metehanersoy.covid19app.Card;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.R;

import java.util.HashMap;


public class ObservedSymptomsFragment extends Fragment {

    private DatabaseReference mDatabase;


    HashMap<String, String> observedSymptoms = new HashMap<>();

    AppCompatButton btn_ObservedSymptomsFragment;

    //LossOfSmellAndTaste
    TextView tw_LossOfSmellAndTaste_severe, tw_LossOfSmellAndTaste_moderate, tw_LossOfSmellAndTaste_low, tw_LossOfSmellAndTaste_no;

    //AbdominalPain
    TextView tw_AbdominalPain_severe, tw_AbdominalPain_moderate, tw_AbdominalPain_low, tw_AbdominalPain_no;

    //Anorexia
    TextView tw_Anorexia_severe, tw_Anorexia_moderate, tw_Anorexia_low, tw_Anorexia_no;

    //BluishFaceAndLips
    TextView tw_BluishFaceAndLips_severe, tw_BluishFaceAndLips_moderate, tw_BluishFaceAndLips_low, tw_BluishFaceAndLips_no;

    //BodyAches
    TextView tw_BodyAches_severe, tw_BodyAches_moderate, tw_BodyAches_low, tw_BodyAches_no;

    //ChestPain
    TextView tw_ChestPain_severe, tw_ChestPain_moderate, tw_ChestPain_low, tw_ChestPain_no;

    //ChillsAndShaking
    TextView tw_ChillsAndShaking_severe, tw_ChillsAndShaking_moderate, tw_ChillsAndShaking_low, tw_ChillsAndShaking_no;

    //Confusion
    TextView tw_Confusion_severe, tw_Confusion_moderate, tw_Confusion_low, tw_Confusion_no;

    //Delirium
    TextView tw_Delirium_severe, tw_Delirium_moderate, tw_Delirium_low, tw_Delirium_no;

    //Diarrhea
    TextView tw_Diarrhea_severe, tw_Diarrhea_moderate, tw_Diarrhea_low, tw_Diarrhea_no;

    //Dizziness
    TextView tw_Dizziness_severe, tw_Dizziness_moderate, tw_Dizziness_low, tw_Dizziness_no;

    //Fatigue
    TextView tw_Fatigue_severe, tw_Fatigue_moderate, tw_Fatigue_low, tw_Fatigue_no;

    //Fever
    TextView tw_Fever_severe, tw_Fever_moderate, tw_Fever_low, tw_Fever_no;

    //FeelingUnwell
    TextView tw_FeelingUnwell_severe, tw_FeelingUnwell_moderate, tw_FeelingUnwell_low, tw_FeelingUnwell_no;

    //Headache
    TextView tw_Headache_severe, tw_Headache_moderate, tw_Headache_low, tw_Headache_no;

    //Hoarseness
    TextView tw_Hoarseness_severe, tw_Hoarseness_moderate, tw_Hoarseness_low, tw_Hoarseness_no;

    //hasRunnyNose
    TextView tw_RunnyNose_severe, tw_RunnyNose_moderate, tw_RunnyNose_low, tw_RunnyNose_no;

    //MusclePain
    TextView tw_MusclePain_severe, tw_MusclePain_moderate, tw_MusclePain_low, tw_MusclePain_no;

    //NasalStuffiness
    TextView tw_NasalStuffiness_severe, tw_NasalStuffiness_moderate, tw_NasalStuffiness_low, tw_NasalStuffiness_no;

    //Nausea
    TextView tw_Nausea_severe, tw_Nausea_moderate, tw_Nausea_low, tw_Nausea_no;

    //OcularReaction
    TextView tw_OcularReaction_severe, tw_OcularReaction_moderate, tw_OcularReaction_low, tw_OcularReaction_no;

    //PersistentCough
    TextView tw_PersistentCough_severe, tw_PersistentCough_moderate, tw_PersistentCough_low, tw_PersistentCough_no;

    //Rhinorrhea
    TextView tw_Rhinorrhea_severe, tw_Rhinorrhea_moderate, tw_Rhinorrhea_low, tw_Rhinorrhea_no;

    //ShortnessOfBreath
    TextView tw_ShortnessOfBreath_severe, tw_ShortnessOfBreath_moderate, tw_ShortnessOfBreath_low, tw_ShortnessOfBreath_no;

    //SkinRash
    TextView tw_SkinRash_severe, tw_SkinRash_moderate, tw_SkinRash_low, tw_SkinRash_no;

    //Inappetence
    TextView tw_Inappetence_severe, tw_Inappetence_moderate, tw_Inappetence_low, tw_Inappetence_no;

    //Sneeze
    TextView tw_Sneeze_severe, tw_Sneeze_moderate, tw_Sneeze_low, tw_Sneeze_no;

    //SoreThroat
    TextView tw_SoreThroat_severe, tw_SoreThroat_moderate, tw_SoreThroat_low, tw_SoreThroat_no;

    //Sputum
    TextView tw_Sputum_severe, tw_Sputum_moderate, tw_Sputum_low, tw_Sputum_no;

    //Vomiting
    TextView tw_Vomiting_severe, tw_Vomiting_moderate, tw_Vomiting_low, tw_Vomiting_no;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_observed_symptoms, container, false);


        mDatabase = FirebaseDatabase.getInstance().getReference();


        btn_ObservedSymptomsFragment = view.findViewById(R.id.btn_ObservedSymptomsFragment);


        //LossOfSmellAndTaste
        tw_LossOfSmellAndTaste_severe = view.findViewById(R.id.tw_LossOfSmellAndTaste_severe);
        tw_LossOfSmellAndTaste_moderate = view.findViewById(R.id.tw_LossOfSmellAndTaste_moderate);
        tw_LossOfSmellAndTaste_low = view.findViewById(R.id.tw_LossOfSmellAndTaste_low);
        tw_LossOfSmellAndTaste_no = view.findViewById(R.id.tw_LossOfSmellAndTaste_no);

        //AbdominalPain
        tw_AbdominalPain_severe = view.findViewById(R.id.tw_AbdominalPain_severe);
        tw_AbdominalPain_moderate = view.findViewById(R.id.tw_AbdominalPain_moderate);
        tw_AbdominalPain_low = view.findViewById(R.id.tw_AbdominalPain_low);
        tw_AbdominalPain_no = view.findViewById(R.id.tw_AbdominalPain_no);

        //Anorexia
        tw_Anorexia_severe = view.findViewById(R.id.tw_Anorexia_severe);
        tw_Anorexia_moderate = view.findViewById(R.id.tw_Anorexia_moderate);
        tw_Anorexia_low = view.findViewById(R.id.tw_Anorexia_low);
        tw_Anorexia_no = view.findViewById(R.id.tw_Anorexia_no);

        //BluishFaceAndLips
        tw_BluishFaceAndLips_severe = view.findViewById(R.id.tw_BluishFaceAndLips_severe);
        tw_BluishFaceAndLips_moderate = view.findViewById(R.id.tw_BluishFaceAndLips_moderate);
        tw_BluishFaceAndLips_low = view.findViewById(R.id.tw_BluishFaceAndLips_low);
        tw_BluishFaceAndLips_no = view.findViewById(R.id.tw_BluishFaceAndLips_no);

        //BodyAches
        tw_BodyAches_severe = view.findViewById(R.id.tw_BodyAches_severe);
        tw_BodyAches_moderate = view.findViewById(R.id.tw_BodyAches_moderate);
        tw_BodyAches_low = view.findViewById(R.id.tw_BodyAches_low);
        tw_BodyAches_no = view.findViewById(R.id.tw_BodyAches_no);

        //ChestPain
        tw_ChestPain_severe = view.findViewById(R.id.tw_ChestPain_severe);
        tw_ChestPain_moderate = view.findViewById(R.id.tw_ChestPain_moderate);
        tw_ChestPain_low = view.findViewById(R.id.tw_ChestPain_low);
        tw_ChestPain_no = view.findViewById(R.id.tw_ChestPain_no);

        //ChillsAndShaking
        tw_ChillsAndShaking_severe = view.findViewById(R.id.tw_ChillsAndShaking_severe);
        tw_ChillsAndShaking_moderate = view.findViewById(R.id.tw_ChillsAndShaking_moderate);
        tw_ChillsAndShaking_low = view.findViewById(R.id.tw_ChillsAndShaking_low);
        tw_ChillsAndShaking_no = view.findViewById(R.id.tw_ChillsAndShaking_no);


        //Confusion
        tw_Confusion_severe = view.findViewById(R.id.tw_Confusion_severe);
        tw_Confusion_moderate = view.findViewById(R.id.tw_Confusion_moderate);
        tw_Confusion_low = view.findViewById(R.id.tw_Confusion_low);
        tw_Confusion_no = view.findViewById(R.id.tw_Confusion_no);

        //Delirium
        tw_Delirium_severe = view.findViewById(R.id.tw_Delirium_severe);
        tw_Delirium_moderate = view.findViewById(R.id.tw_Delirium_moderate);
        tw_Delirium_low = view.findViewById(R.id.tw_Delirium_low);
        tw_Delirium_no = view.findViewById(R.id.tw_Delirium_no);

        //Diarrhea
        tw_Diarrhea_severe = view.findViewById(R.id.tw_Diarrhea_severe);
        tw_Diarrhea_moderate = view.findViewById(R.id.tw_Diarrhea_moderate);
        tw_Diarrhea_low = view.findViewById(R.id.tw_Diarrhea_low);
        tw_Diarrhea_no = view.findViewById(R.id.tw_Diarrhea_no);

        //Dizziness
        tw_Dizziness_severe = view.findViewById(R.id.tw_Dizziness_severe);
        tw_Dizziness_moderate = view.findViewById(R.id.tw_Dizziness_moderate);
        tw_Dizziness_low = view.findViewById(R.id.tw_Dizziness_low);
        tw_Dizziness_no = view.findViewById(R.id.tw_Dizziness_no);

        //Fatigue
        tw_Fatigue_severe = view.findViewById(R.id.tw_Fatigue_severe);
        tw_Fatigue_moderate = view.findViewById(R.id.tw_Fatigue_moderate);
        tw_Fatigue_low = view.findViewById(R.id.tw_Fatigue_low);
        tw_Fatigue_no = view.findViewById(R.id.tw_Fatigue_no);

        //Fever
        tw_Fever_severe = view.findViewById(R.id.tw_Fever_severe);
        tw_Fever_moderate = view.findViewById(R.id.tw_Fever_moderate);
        tw_Fever_low = view.findViewById(R.id.tw_Fever_low);
        tw_Fever_no = view.findViewById(R.id.tw_Fever_no);

        //FeelingUnwell
        tw_FeelingUnwell_severe = view.findViewById(R.id.tw_FeelingUnwell_severe);
        tw_FeelingUnwell_moderate = view.findViewById(R.id.tw_FeelingUnwell_moderate);
        tw_FeelingUnwell_low = view.findViewById(R.id.tw_FeelingUnwell_low);
        tw_FeelingUnwell_no = view.findViewById(R.id.tw_FeelingUnwell_no);

        //Headache
        tw_Headache_severe = view.findViewById(R.id.tw_Headache_severe);
        tw_Headache_moderate = view.findViewById(R.id.tw_Headache_moderate);
        tw_Headache_low = view.findViewById(R.id.tw_Headache_low);
        tw_Headache_no = view.findViewById(R.id.tw_Headache_no);


        //Hoarseness
        tw_Hoarseness_severe = view.findViewById(R.id.tw_Hoarseness_severe);
        tw_Hoarseness_moderate = view.findViewById(R.id.tw_Hoarseness_moderate);
        tw_Hoarseness_low = view.findViewById(R.id.tw_Hoarseness_low);
        tw_Hoarseness_no = view.findViewById(R.id.tw_Hoarseness_no);

        //RunnyNose
        tw_RunnyNose_severe = view.findViewById(R.id.tw_RunnyNose_severe);
        tw_RunnyNose_moderate = view.findViewById(R.id.tw_RunnyNose_moderate);
        tw_RunnyNose_low = view.findViewById(R.id.tw_RunnyNose_low);
        tw_RunnyNose_no = view.findViewById(R.id.tw_RunnyNose_no);

        //MusclePain
        tw_MusclePain_severe = view.findViewById(R.id.tw_MusclePain_severe);
        tw_MusclePain_moderate = view.findViewById(R.id.tw_MusclePain_moderate);
        tw_MusclePain_low = view.findViewById(R.id.tw_MusclePain_low);
        tw_MusclePain_no = view.findViewById(R.id.tw_MusclePain_no);

        //NasalStuffiness
        tw_NasalStuffiness_severe = view.findViewById(R.id.tw_NasalStuffiness_severe);
        tw_NasalStuffiness_moderate = view.findViewById(R.id.tw_NasalStuffiness_moderate);
        tw_NasalStuffiness_low = view.findViewById(R.id.tw_NasalStuffiness_low);
        tw_NasalStuffiness_no = view.findViewById(R.id.tw_NasalStuffiness_no);

        //Nausea
        tw_Nausea_severe = view.findViewById(R.id.tw_Nausea_severe);
        tw_Nausea_moderate = view.findViewById(R.id.tw_Nausea_moderate);
        tw_Nausea_low = view.findViewById(R.id.tw_Nausea_low);
        tw_Nausea_no = view.findViewById(R.id.tw_Nausea_no);

        //OcularReaction
        tw_OcularReaction_severe = view.findViewById(R.id.tw_OcularReaction_severe);
        tw_OcularReaction_moderate = view.findViewById(R.id.tw_OcularReaction_moderate);
        tw_OcularReaction_low = view.findViewById(R.id.tw_OcularReaction_low);
        tw_OcularReaction_no = view.findViewById(R.id.tw_OcularReaction_no);

        //PersistentCough
        tw_PersistentCough_severe = view.findViewById(R.id.tw_PersistentCough_severe);
        tw_PersistentCough_moderate = view.findViewById(R.id.tw_PersistentCough_moderate);
        tw_PersistentCough_low = view.findViewById(R.id.tw_PersistentCough_low);
        tw_PersistentCough_no = view.findViewById(R.id.tw_PersistentCough_no);

        //Rhinorrhea
        tw_Rhinorrhea_severe = view.findViewById(R.id.tw_Rhinorrhea_severe);
        tw_Rhinorrhea_moderate = view.findViewById(R.id.tw_Rhinorrhea_moderate);
        tw_Rhinorrhea_low = view.findViewById(R.id.tw_Rhinorrhea_low);
        tw_Rhinorrhea_no = view.findViewById(R.id.tw_Rhinorrhea_no);

        //ShortnessOfBreath
        tw_ShortnessOfBreath_severe = view.findViewById(R.id.tw_ShortnessOfBreath_severe);
        tw_ShortnessOfBreath_moderate = view.findViewById(R.id.tw_ShortnessOfBreath_moderate);
        tw_ShortnessOfBreath_low = view.findViewById(R.id.tw_ShortnessOfBreath_low);
        tw_ShortnessOfBreath_no = view.findViewById(R.id.tw_ShortnessOfBreath_no);

        //SkinRash
        tw_SkinRash_severe = view.findViewById(R.id.tw_SkinRash_severe);
        tw_SkinRash_moderate = view.findViewById(R.id.tw_SkinRash_moderate);
        tw_SkinRash_low = view.findViewById(R.id.tw_SkinRash_low);
        tw_SkinRash_no = view.findViewById(R.id.tw_SkinRash_no);

        //Inappetence
        tw_Inappetence_severe = view.findViewById(R.id.tw_Inappetence_severe);
        tw_Inappetence_moderate = view.findViewById(R.id.tw_Inappetence_moderate);
        tw_Inappetence_low = view.findViewById(R.id.tw_Inappetence_low);
        tw_Inappetence_no = view.findViewById(R.id.tw_Inappetence_no);

        //Sneeze
        tw_Sneeze_severe = view.findViewById(R.id.tw_Sneeze_severe);
        tw_Sneeze_moderate = view.findViewById(R.id.tw_Sneeze_moderate);
        tw_Sneeze_low = view.findViewById(R.id.tw_Sneeze_low);
        tw_Sneeze_no = view.findViewById(R.id.tw_Sneeze_no);

        //SoreThroat
        tw_SoreThroat_severe = view.findViewById(R.id.tw_SoreThroat_severe);
        tw_SoreThroat_moderate = view.findViewById(R.id.tw_SoreThroat_moderate);
        tw_SoreThroat_low = view.findViewById(R.id.tw_SoreThroat_low);
        tw_SoreThroat_no = view.findViewById(R.id.tw_SoreThroat_no);

        //Sputum
        tw_Sputum_severe = view.findViewById(R.id.tw_Sputum_severe);
        tw_Sputum_moderate = view.findViewById(R.id.tw_Sputum_moderate);
        tw_Sputum_low = view.findViewById(R.id.tw_Sputum_low);
        tw_Sputum_no = view.findViewById(R.id.tw_Sputum_no);

        //Vomiting
        tw_Vomiting_severe = view.findViewById(R.id.tw_Vomiting_severe);
        tw_Vomiting_moderate = view.findViewById(R.id.tw_Vomiting_moderate);
        tw_Vomiting_low = view.findViewById(R.id.tw_Vomiting_low);
        tw_Vomiting_no = view.findViewById(R.id.tw_Vomiting_no);

        //listeners
/*
            btn_ObservedSymptoms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isFilledAll()) {
                        UUID uuid = UUID.randomUUID();
                        mDatabase.child("deneme").child(uuid.toString()).setValue(result);
                        //intent other pages
                    } else {
                        Toast.makeText(getActivity(), "Please Fill All Areas!", Toast.LENGTH_SHORT).show();
                    }


                }
            });

*/
        //LossOfSmellAndTaste
        tw_LossOfSmellAndTaste_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_LossOfSmellAndTaste_severe.setTextSize(18);
                tw_LossOfSmellAndTaste_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasLossOfSmellAndTaste", "Severe");

                tw_LossOfSmellAndTaste_moderate.setTextSize(16);
                tw_LossOfSmellAndTaste_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_low.setTextSize(16);
                tw_LossOfSmellAndTaste_low.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_no.setTextSize(16);
                tw_LossOfSmellAndTaste_no.setTextColor(getResources().getColor(R.color.white));
            }
        });
        tw_LossOfSmellAndTaste_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_LossOfSmellAndTaste_severe.setTextSize(16);
                tw_LossOfSmellAndTaste_severe.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_moderate.setTextSize(18);
                tw_LossOfSmellAndTaste_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasLossOfSmellAndTaste", "Moderate");

                tw_LossOfSmellAndTaste_low.setTextSize(16);
                tw_LossOfSmellAndTaste_low.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_no.setTextSize(16);
                tw_LossOfSmellAndTaste_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_LossOfSmellAndTaste_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_LossOfSmellAndTaste_severe.setTextSize(16);
                tw_LossOfSmellAndTaste_severe.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_moderate.setTextSize(16);
                tw_LossOfSmellAndTaste_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_low.setTextSize(18);
                tw_LossOfSmellAndTaste_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasLossOfSmellAndTaste", "Low");

                tw_LossOfSmellAndTaste_no.setTextSize(16);
                tw_LossOfSmellAndTaste_no.setTextColor(getResources().getColor(R.color.white));
            }
        });
        tw_LossOfSmellAndTaste_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tw_LossOfSmellAndTaste_severe.setTextSize(16);
                tw_LossOfSmellAndTaste_severe.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_moderate.setTextSize(16);
                tw_LossOfSmellAndTaste_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_low.setTextSize(16);
                tw_LossOfSmellAndTaste_low.setTextColor(getResources().getColor(R.color.white));

                tw_LossOfSmellAndTaste_no.setTextSize(18);
                tw_LossOfSmellAndTaste_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasLossOfSmellAndTaste", "N.O.");
            }
        });

        //AbdominalPain
        tw_AbdominalPain_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_AbdominalPain_severe.setTextSize(18);
                tw_AbdominalPain_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasAbdominalPain", "Severe");

                tw_AbdominalPain_moderate.setTextSize(16);
                tw_AbdominalPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_low.setTextSize(16);
                tw_AbdominalPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_no.setTextSize(16);
                tw_AbdominalPain_no.setTextColor(getResources().getColor(R.color.white));


            }
        });
        tw_AbdominalPain_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_AbdominalPain_severe.setTextSize(16);
                tw_AbdominalPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_moderate.setTextSize(18);
                tw_AbdominalPain_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasAbdominalPain", "Moderate");

                tw_AbdominalPain_low.setTextSize(16);
                tw_AbdominalPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_no.setTextSize(16);
                tw_AbdominalPain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_AbdominalPain_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_AbdominalPain_severe.setTextSize(16);
                tw_AbdominalPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_moderate.setTextSize(16);
                tw_AbdominalPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_low.setTextSize(18);
                tw_AbdominalPain_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasAbdominalPain", "Low");

                tw_AbdominalPain_no.setTextSize(16);
                tw_AbdominalPain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_AbdominalPain_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_AbdominalPain_severe.setTextSize(16);
                tw_AbdominalPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_moderate.setTextSize(16);
                tw_AbdominalPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_low.setTextSize(16);
                tw_AbdominalPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_AbdominalPain_no.setTextSize(18);
                tw_AbdominalPain_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasAbdominalPain", "N.O.");

            }
        });

        //Anorexia
        tw_Anorexia_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Anorexia_severe.setTextSize(18);
                tw_Anorexia_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasAnorexia", "Severe");

                tw_Anorexia_moderate.setTextSize(16);
                tw_Anorexia_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_low.setTextSize(16);
                tw_Anorexia_low.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_no.setTextSize(16);
                tw_Anorexia_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Anorexia_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Anorexia_severe.setTextSize(16);
                tw_Anorexia_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_moderate.setTextSize(18);
                tw_Anorexia_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasAnorexia", "Moderate");

                tw_Anorexia_low.setTextSize(16);
                tw_Anorexia_low.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_no.setTextSize(16);
                tw_Anorexia_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Anorexia_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Anorexia_severe.setTextSize(16);
                tw_Anorexia_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_moderate.setTextSize(16);
                tw_Anorexia_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_low.setTextSize(18);
                tw_Anorexia_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasAnorexia", "Low");

                tw_Anorexia_no.setTextSize(16);
                tw_Anorexia_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Anorexia_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Anorexia_severe.setTextSize(16);
                tw_Anorexia_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_moderate.setTextSize(16);
                tw_Anorexia_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_low.setTextSize(16);
                tw_Anorexia_low.setTextColor(getResources().getColor(R.color.white));

                tw_Anorexia_no.setTextSize(18);
                tw_Anorexia_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasAnorexia", "N.O.");

            }
        });

        //BluishFaceAndLips
        tw_BluishFaceAndLips_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BluishFaceAndLips_severe.setTextSize(18);
                tw_BluishFaceAndLips_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasBluishFaceAndLips", "Severe");

                tw_BluishFaceAndLips_moderate.setTextSize(16);
                tw_BluishFaceAndLips_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_low.setTextSize(16);
                tw_BluishFaceAndLips_low.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_no.setTextSize(16);
                tw_BluishFaceAndLips_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BluishFaceAndLips_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BluishFaceAndLips_severe.setTextSize(16);
                tw_BluishFaceAndLips_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_moderate.setTextSize(18);
                tw_BluishFaceAndLips_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasBluishFaceAndLips", "Moderate");

                tw_BluishFaceAndLips_low.setTextSize(16);
                tw_BluishFaceAndLips_low.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_no.setTextSize(16);
                tw_BluishFaceAndLips_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BluishFaceAndLips_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BluishFaceAndLips_severe.setTextSize(16);
                tw_BluishFaceAndLips_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_moderate.setTextSize(16);
                tw_BluishFaceAndLips_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_low.setTextSize(18);
                tw_BluishFaceAndLips_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasBluishFaceAndLips", "Low");

                tw_BluishFaceAndLips_no.setTextSize(16);
                tw_BluishFaceAndLips_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BluishFaceAndLips_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BluishFaceAndLips_severe.setTextSize(16);
                tw_BluishFaceAndLips_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_moderate.setTextSize(16);
                tw_BluishFaceAndLips_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_low.setTextSize(16);
                tw_BluishFaceAndLips_low.setTextColor(getResources().getColor(R.color.white));

                tw_BluishFaceAndLips_no.setTextSize(18);
                tw_BluishFaceAndLips_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasBluishFaceAndLips", "N.O.");

            }
        });

        //BodyAches
        tw_BodyAches_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BodyAches_severe.setTextSize(18);
                tw_BodyAches_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hadBodyAches", "Severe");

                tw_BodyAches_moderate.setTextSize(16);
                tw_BodyAches_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_low.setTextSize(16);
                tw_BodyAches_low.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_no.setTextSize(16);
                tw_BodyAches_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BodyAches_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BodyAches_severe.setTextSize(16);
                tw_BodyAches_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_moderate.setTextSize(18);
                tw_BodyAches_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hadBodyAches", "Moderate");

                tw_BodyAches_low.setTextSize(16);
                tw_BodyAches_low.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_no.setTextSize(16);
                tw_BodyAches_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BodyAches_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BodyAches_severe.setTextSize(16);
                tw_BodyAches_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_moderate.setTextSize(16);
                tw_BodyAches_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_low.setTextSize(18);
                tw_BodyAches_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hadBodyAches", "Low");

                tw_BodyAches_no.setTextSize(16);
                tw_BodyAches_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_BodyAches_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_BodyAches_severe.setTextSize(16);
                tw_BodyAches_severe.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_moderate.setTextSize(16);
                tw_BodyAches_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_low.setTextSize(16);
                tw_BodyAches_low.setTextColor(getResources().getColor(R.color.white));

                tw_BodyAches_no.setTextSize(18);
                tw_BodyAches_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hadBodyAches", "N.O.");

            }
        });

        //ChestPain
        tw_ChestPain_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChestPain_severe.setTextSize(18);
                tw_ChestPain_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasChestPain", "Severe");

                tw_ChestPain_moderate.setTextSize(16);
                tw_ChestPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_low.setTextSize(16);
                tw_ChestPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_no.setTextSize(16);
                tw_ChestPain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChestPain_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChestPain_severe.setTextSize(16);
                tw_ChestPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_moderate.setTextSize(18);
                tw_ChestPain_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasChestPain", "Moderate");

                tw_ChestPain_low.setTextSize(16);
                tw_ChestPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_no.setTextSize(16);
                tw_ChestPain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChestPain_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChestPain_severe.setTextSize(16);
                tw_ChestPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_moderate.setTextSize(16);
                tw_ChestPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_low.setTextSize(18);
                tw_ChestPain_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasChestPain", "Low");

                tw_ChestPain_no.setTextSize(16);
                tw_ChestPain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChestPain_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChestPain_severe.setTextSize(16);
                tw_ChestPain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_moderate.setTextSize(16);
                tw_ChestPain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_low.setTextSize(16);
                tw_ChestPain_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChestPain_no.setTextSize(18);
                tw_ChestPain_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasChestPain", "N.O.");

            }
        });

        //ChillsAndShaking
        tw_ChillsAndShaking_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChillsAndShaking_severe.setTextSize(18);
                tw_ChillsAndShaking_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasChillsAndShaking", "Severe");

                tw_ChillsAndShaking_moderate.setTextSize(16);
                tw_ChillsAndShaking_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_low.setTextSize(16);
                tw_ChillsAndShaking_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_no.setTextSize(16);
                tw_ChillsAndShaking_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChillsAndShaking_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChillsAndShaking_severe.setTextSize(16);
                tw_ChillsAndShaking_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_moderate.setTextSize(18);
                tw_ChillsAndShaking_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasChillsAndShaking", "Moderate");

                tw_ChillsAndShaking_low.setTextSize(16);
                tw_ChillsAndShaking_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_no.setTextSize(16);
                tw_ChillsAndShaking_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChillsAndShaking_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChillsAndShaking_severe.setTextSize(16);
                tw_ChillsAndShaking_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_moderate.setTextSize(16);
                tw_ChillsAndShaking_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_low.setTextSize(18);
                tw_ChillsAndShaking_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasChillsAndShaking", "Low");

                tw_ChillsAndShaking_no.setTextSize(16);
                tw_ChillsAndShaking_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ChillsAndShaking_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ChillsAndShaking_severe.setTextSize(16);
                tw_ChillsAndShaking_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_moderate.setTextSize(16);
                tw_ChillsAndShaking_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_low.setTextSize(16);
                tw_ChillsAndShaking_low.setTextColor(getResources().getColor(R.color.white));

                tw_ChillsAndShaking_no.setTextSize(18);
                tw_ChillsAndShaking_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasChillsAndShaking", "N.O.");
            }
        });


        //Confusion
        tw_Confusion_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Confusion_severe.setTextSize(18);
                tw_Confusion_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasConfusion", "Severe");

                tw_Confusion_moderate.setTextSize(16);
                tw_Confusion_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_low.setTextSize(16);
                tw_Confusion_low.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_no.setTextSize(16);
                tw_Confusion_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Confusion_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Confusion_severe.setTextSize(16);
                tw_Confusion_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_moderate.setTextSize(18);
                tw_Confusion_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasConfusion", "Moderate");

                tw_Confusion_low.setTextSize(16);
                tw_Confusion_low.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_no.setTextSize(16);
                tw_Confusion_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Confusion_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Confusion_severe.setTextSize(16);
                tw_Confusion_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_moderate.setTextSize(16);
                tw_Confusion_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_low.setTextSize(18);
                tw_Confusion_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasConfusion", "Low");

                tw_Confusion_no.setTextSize(16);
                tw_Confusion_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Confusion_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Confusion_severe.setTextSize(16);
                tw_Confusion_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_moderate.setTextSize(16);
                tw_Confusion_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_low.setTextSize(16);
                tw_Confusion_low.setTextColor(getResources().getColor(R.color.white));

                tw_Confusion_no.setTextSize(18);
                tw_Confusion_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasConfusion", "N.O.");

            }
        });

        //Delirium
        tw_Delirium_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Delirium_severe.setTextSize(18);
                tw_Delirium_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasDelirium", "Severe");

                tw_Delirium_moderate.setTextSize(16);
                tw_Delirium_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_low.setTextSize(16);
                tw_Delirium_low.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_no.setTextSize(16);
                tw_Delirium_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Delirium_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Delirium_severe.setTextSize(16);
                tw_Delirium_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_moderate.setTextSize(18);
                tw_Delirium_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasDelirium", "Moderate");

                tw_Delirium_low.setTextSize(16);
                tw_Delirium_low.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_no.setTextSize(16);
                tw_Delirium_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Delirium_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Delirium_severe.setTextSize(16);
                tw_Delirium_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_moderate.setTextSize(16);
                tw_Delirium_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_low.setTextSize(18);
                tw_Delirium_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasDelirium", "Low");

                tw_Delirium_no.setTextSize(16);
                tw_Delirium_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Delirium_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Delirium_severe.setTextSize(16);
                tw_Delirium_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_moderate.setTextSize(16);
                tw_Delirium_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_low.setTextSize(16);
                tw_Delirium_low.setTextColor(getResources().getColor(R.color.white));

                tw_Delirium_no.setTextSize(18);
                tw_Delirium_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasDelirium", "N.O.");

            }
        });

        //Diarrhea
        tw_Diarrhea_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Diarrhea_severe.setTextSize(18);
                tw_Diarrhea_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasDiarrhea", "Severe");

                tw_Diarrhea_moderate.setTextSize(16);
                tw_Diarrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_low.setTextSize(16);
                tw_Diarrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_no.setTextSize(16);
                tw_Diarrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Diarrhea_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Diarrhea_severe.setTextSize(16);
                tw_Diarrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_moderate.setTextSize(18);
                tw_Diarrhea_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasDiarrhea", "Moderate");

                tw_Diarrhea_low.setTextSize(16);
                tw_Diarrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_no.setTextSize(16);
                tw_Diarrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Diarrhea_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Diarrhea_severe.setTextSize(16);
                tw_Diarrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_moderate.setTextSize(16);
                tw_Diarrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_low.setTextSize(18);
                tw_Diarrhea_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasDiarrhea", "Low");

                tw_Diarrhea_no.setTextSize(16);
                tw_Diarrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Diarrhea_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Diarrhea_severe.setTextSize(16);
                tw_Diarrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_moderate.setTextSize(16);
                tw_Diarrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_low.setTextSize(16);
                tw_Diarrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Diarrhea_no.setTextSize(18);
                tw_Diarrhea_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasDiarrhea", "N.O.");

            }
        });

        //Dizziness
        tw_Dizziness_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Dizziness_severe.setTextSize(18);
                tw_Dizziness_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasDizziness", "Severe");

                tw_Dizziness_moderate.setTextSize(16);
                tw_Dizziness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_low.setTextSize(16);
                tw_Dizziness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_no.setTextSize(16);
                tw_Dizziness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Dizziness_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Dizziness_severe.setTextSize(16);
                tw_Dizziness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_moderate.setTextSize(18);
                tw_Dizziness_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasDizziness", "Moderate");

                tw_Dizziness_low.setTextSize(16);
                tw_Dizziness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_no.setTextSize(16);
                tw_Dizziness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Dizziness_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Dizziness_severe.setTextSize(16);
                tw_Dizziness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_moderate.setTextSize(16);
                tw_Dizziness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_low.setTextSize(18);
                tw_Dizziness_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasDizziness", "Low");

                tw_Dizziness_no.setTextSize(16);
                tw_Dizziness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Dizziness_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Dizziness_severe.setTextSize(16);
                tw_Dizziness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_moderate.setTextSize(16);
                tw_Dizziness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_low.setTextSize(16);
                tw_Dizziness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Dizziness_no.setTextSize(18);
                tw_Dizziness_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasDizziness", "N.O.");

            }
        });

        //Fatigue
        tw_Fatigue_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fatigue_severe.setTextSize(18);
                tw_Fatigue_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasFatigue", "Severe");

                tw_Fatigue_moderate.setTextSize(16);
                tw_Fatigue_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_low.setTextSize(16);
                tw_Fatigue_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_no.setTextSize(16);
                tw_Fatigue_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fatigue_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fatigue_severe.setTextSize(16);
                tw_Fatigue_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_moderate.setTextSize(18);
                tw_Fatigue_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasFatigue", "Moderate");

                tw_Fatigue_low.setTextSize(16);
                tw_Fatigue_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_no.setTextSize(16);
                tw_Fatigue_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fatigue_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fatigue_severe.setTextSize(16);
                tw_Fatigue_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_moderate.setTextSize(16);
                tw_Fatigue_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_low.setTextSize(18);
                tw_Fatigue_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasFatigue", "Low");

                tw_Fatigue_no.setTextSize(16);
                tw_Fatigue_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fatigue_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fatigue_severe.setTextSize(16);
                tw_Fatigue_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_moderate.setTextSize(16);
                tw_Fatigue_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_low.setTextSize(16);
                tw_Fatigue_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fatigue_no.setTextSize(18);
                tw_Fatigue_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasFatigue", "N.O.");

            }
        });

        //Fever
        tw_Fever_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fever_severe.setTextSize(18);
                tw_Fever_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasFever", "Severe");

                tw_Fever_moderate.setTextSize(16);
                tw_Fever_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_low.setTextSize(16);
                tw_Fever_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_no.setTextSize(16);
                tw_Fever_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fever_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fever_severe.setTextSize(16);
                tw_Fever_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_moderate.setTextSize(18);
                tw_Fever_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasFever", "Moderate");

                tw_Fever_low.setTextSize(16);
                tw_Fever_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_no.setTextSize(16);
                tw_Fever_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fever_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fever_severe.setTextSize(16);
                tw_Fever_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_moderate.setTextSize(16);
                tw_Fever_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_low.setTextSize(18);
                tw_Fever_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasFever", "Low");

                tw_Fever_no.setTextSize(16);
                tw_Fever_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Fever_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Fever_severe.setTextSize(16);
                tw_Fever_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_moderate.setTextSize(16);
                tw_Fever_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_low.setTextSize(16);
                tw_Fever_low.setTextColor(getResources().getColor(R.color.white));

                tw_Fever_no.setTextSize(18);
                tw_Fever_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasFever", "N.O.");

            }
        });

        //FeelingUnwell
        tw_FeelingUnwell_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_FeelingUnwell_severe.setTextSize(18);
                tw_FeelingUnwell_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("isFeelingUnwell", "Severe");

                tw_FeelingUnwell_moderate.setTextSize(16);
                tw_FeelingUnwell_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_low.setTextSize(16);
                tw_FeelingUnwell_low.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_no.setTextSize(16);
                tw_FeelingUnwell_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_FeelingUnwell_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_FeelingUnwell_severe.setTextSize(16);
                tw_FeelingUnwell_severe.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_moderate.setTextSize(18);
                tw_FeelingUnwell_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("isFeelingUnwell", "Moderate");

                tw_FeelingUnwell_low.setTextSize(16);
                tw_FeelingUnwell_low.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_no.setTextSize(16);
                tw_FeelingUnwell_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_FeelingUnwell_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_FeelingUnwell_severe.setTextSize(16);
                tw_FeelingUnwell_severe.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_moderate.setTextSize(16);
                tw_FeelingUnwell_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_low.setTextSize(18);
                tw_FeelingUnwell_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("isFeelingUnwell", "Low");

                tw_FeelingUnwell_no.setTextSize(16);
                tw_FeelingUnwell_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_FeelingUnwell_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_FeelingUnwell_severe.setTextSize(16);
                tw_FeelingUnwell_severe.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_moderate.setTextSize(16);
                tw_FeelingUnwell_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_low.setTextSize(16);
                tw_FeelingUnwell_low.setTextColor(getResources().getColor(R.color.white));

                tw_FeelingUnwell_no.setTextSize(18);
                tw_FeelingUnwell_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("isFeelingUnwell", "N.O.");
            }
        });

        //Headache
        tw_Headache_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Headache_severe.setTextSize(18);
                tw_Headache_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasHeadache", "Severe");

                tw_Headache_moderate.setTextSize(16);
                tw_Headache_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_low.setTextSize(16);
                tw_Headache_low.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_no.setTextSize(16);
                tw_Headache_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Headache_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Headache_severe.setTextSize(16);
                tw_Headache_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_moderate.setTextSize(18);
                tw_Headache_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasHeadache", "Moderate");

                tw_Headache_low.setTextSize(16);
                tw_Headache_low.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_no.setTextSize(16);
                tw_Headache_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Headache_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Headache_severe.setTextSize(16);
                tw_Headache_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_moderate.setTextSize(16);
                tw_Headache_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_low.setTextSize(18);
                tw_Headache_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasHeadache", "Low");

                tw_Headache_no.setTextSize(16);
                tw_Headache_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Headache_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Headache_severe.setTextSize(16);
                tw_Headache_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_moderate.setTextSize(16);
                tw_Headache_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_low.setTextSize(16);
                tw_Headache_low.setTextColor(getResources().getColor(R.color.white));

                tw_Headache_no.setTextSize(18);
                tw_Headache_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasHeadache", "N.O.");
            }
        });

        //Hoarseness
        tw_Hoarseness_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Hoarseness_severe.setTextSize(18);
                tw_Hoarseness_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasHoarseness", "Severe");

                tw_Hoarseness_moderate.setTextSize(16);
                tw_Hoarseness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_low.setTextSize(16);
                tw_Hoarseness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_no.setTextSize(16);
                tw_Hoarseness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Hoarseness_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Hoarseness_severe.setTextSize(16);
                tw_Hoarseness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_moderate.setTextSize(18);
                tw_Hoarseness_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasHoarseness", "Moderate");

                tw_Hoarseness_low.setTextSize(16);
                tw_Hoarseness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_no.setTextSize(16);
                tw_Hoarseness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Hoarseness_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Hoarseness_severe.setTextSize(16);
                tw_Hoarseness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_moderate.setTextSize(16);
                tw_Hoarseness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_low.setTextSize(18);
                tw_Hoarseness_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasHoarseness", "Low");

                tw_Hoarseness_no.setTextSize(16);
                tw_Hoarseness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Hoarseness_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Hoarseness_severe.setTextSize(16);
                tw_Hoarseness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_moderate.setTextSize(16);
                tw_Hoarseness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_low.setTextSize(16);
                tw_Hoarseness_low.setTextColor(getResources().getColor(R.color.white));

                tw_Hoarseness_no.setTextSize(18);
                tw_Hoarseness_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasHoarseness", "N.O.");
            }
        });

        //RunnyNose
        tw_RunnyNose_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_RunnyNose_severe.setTextSize(18);
                tw_RunnyNose_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasRunnyNose", "Severe");
                tw_RunnyNose_moderate.setTextSize(16);
                tw_RunnyNose_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_low.setTextSize(16);
                tw_RunnyNose_low.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_no.setTextSize(16);
                tw_RunnyNose_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_RunnyNose_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_RunnyNose_severe.setTextSize(16);
                tw_RunnyNose_severe.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_moderate.setTextSize(18);
                tw_RunnyNose_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasRunnyNose", "Moderate");

                tw_RunnyNose_low.setTextSize(16);
                tw_RunnyNose_low.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_no.setTextSize(16);
                tw_RunnyNose_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_RunnyNose_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_RunnyNose_severe.setTextSize(16);
                tw_RunnyNose_severe.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_moderate.setTextSize(16);
                tw_RunnyNose_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_low.setTextSize(18);
                tw_RunnyNose_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasRunnyNose", "Low");

                tw_RunnyNose_no.setTextSize(16);
                tw_RunnyNose_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_RunnyNose_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_RunnyNose_severe.setTextSize(16);
                tw_RunnyNose_severe.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_moderate.setTextSize(16);
                tw_RunnyNose_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_low.setTextSize(16);
                tw_RunnyNose_low.setTextColor(getResources().getColor(R.color.white));

                tw_RunnyNose_no.setTextSize(18);
                tw_RunnyNose_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasRunnyNose", "N.O.");
            }
        });

        //MusclePain
        tw_MusclePain_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_MusclePain_severe.setTextSize(18);
                tw_MusclePain_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasMusclePain", "Severe");

                tw_MusclePain_moderate.setTextSize(16);
                tw_MusclePain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_low.setTextSize(16);
                tw_MusclePain_low.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_no.setTextSize(16);
                tw_MusclePain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_MusclePain_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_MusclePain_severe.setTextSize(16);
                tw_MusclePain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_moderate.setTextSize(18);
                tw_MusclePain_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasMusclePain", "Moderate");

                tw_MusclePain_low.setTextSize(16);
                tw_MusclePain_low.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_no.setTextSize(16);
                tw_MusclePain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_MusclePain_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_MusclePain_severe.setTextSize(16);
                tw_MusclePain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_moderate.setTextSize(16);
                tw_MusclePain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_low.setTextSize(18);
                tw_MusclePain_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasMusclePain", "Low");

                tw_MusclePain_no.setTextSize(16);
                tw_MusclePain_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_MusclePain_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_MusclePain_severe.setTextSize(16);
                tw_MusclePain_severe.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_moderate.setTextSize(16);
                tw_MusclePain_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_low.setTextSize(16);
                tw_MusclePain_low.setTextColor(getResources().getColor(R.color.white));

                tw_MusclePain_no.setTextSize(18);
                tw_MusclePain_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasMusclePain", "N.O.");
            }
        });

        //NasalStuffiness
        tw_NasalStuffiness_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_NasalStuffiness_severe.setTextSize(18);
                tw_NasalStuffiness_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasNasalStuffiness", "Severe");

                tw_NasalStuffiness_moderate.setTextSize(16);
                tw_NasalStuffiness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_low.setTextSize(16);
                tw_NasalStuffiness_low.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_no.setTextSize(16);
                tw_NasalStuffiness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_NasalStuffiness_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_NasalStuffiness_severe.setTextSize(16);
                tw_NasalStuffiness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_moderate.setTextSize(18);
                tw_NasalStuffiness_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasNasalStuffiness", "Moderate");

                tw_NasalStuffiness_low.setTextSize(16);
                tw_NasalStuffiness_low.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_no.setTextSize(16);
                tw_NasalStuffiness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_NasalStuffiness_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_NasalStuffiness_severe.setTextSize(16);
                tw_NasalStuffiness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_moderate.setTextSize(16);
                tw_NasalStuffiness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_low.setTextSize(18);
                tw_NasalStuffiness_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasNasalStuffiness", "Low");

                tw_NasalStuffiness_no.setTextSize(16);
                tw_NasalStuffiness_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_NasalStuffiness_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_NasalStuffiness_severe.setTextSize(16);
                tw_NasalStuffiness_severe.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_moderate.setTextSize(16);
                tw_NasalStuffiness_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_low.setTextSize(16);
                tw_NasalStuffiness_low.setTextColor(getResources().getColor(R.color.white));

                tw_NasalStuffiness_no.setTextSize(18);
                tw_NasalStuffiness_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasNasalStuffiness", "N.O.");
            }
        });

        //Nausea
        tw_Nausea_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Nausea_severe.setTextSize(18);
                tw_Nausea_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasNausea", "Severe");

                tw_Nausea_moderate.setTextSize(16);
                tw_Nausea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_low.setTextSize(16);
                tw_Nausea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_no.setTextSize(16);
                tw_Nausea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Nausea_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Nausea_severe.setTextSize(16);
                tw_Nausea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_moderate.setTextSize(18);
                tw_Nausea_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasNausea", "Moderate");

                tw_Nausea_low.setTextSize(16);
                tw_Nausea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_no.setTextSize(16);
                tw_Nausea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Nausea_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Nausea_severe.setTextSize(16);
                tw_Nausea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_moderate.setTextSize(16);
                tw_Nausea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_low.setTextSize(18);
                tw_Nausea_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasNausea", "Low");

                tw_Nausea_no.setTextSize(16);
                tw_Nausea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Nausea_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Nausea_severe.setTextSize(16);
                tw_Nausea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_moderate.setTextSize(16);
                tw_Nausea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_low.setTextSize(16);
                tw_Nausea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Nausea_no.setTextSize(18);
                tw_Nausea_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasNausea", "N.O.");
            }
        });

        //OcularReaction
        tw_OcularReaction_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_OcularReaction_severe.setTextSize(18);
                tw_OcularReaction_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasOcularReaction", "Severe");

                tw_OcularReaction_moderate.setTextSize(16);
                tw_OcularReaction_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_low.setTextSize(16);
                tw_OcularReaction_low.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_no.setTextSize(16);
                tw_OcularReaction_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_OcularReaction_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_OcularReaction_severe.setTextSize(16);
                tw_OcularReaction_severe.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_moderate.setTextSize(18);
                tw_OcularReaction_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasOcularReaction", "Moderate");

                tw_OcularReaction_low.setTextSize(16);
                tw_OcularReaction_low.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_no.setTextSize(16);
                tw_OcularReaction_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_OcularReaction_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_OcularReaction_severe.setTextSize(16);
                tw_OcularReaction_severe.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_moderate.setTextSize(16);
                tw_OcularReaction_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_low.setTextSize(18);
                tw_OcularReaction_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasOcularReaction", "Low");

                tw_OcularReaction_no.setTextSize(16);
                tw_OcularReaction_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_OcularReaction_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_OcularReaction_severe.setTextSize(16);
                tw_OcularReaction_severe.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_moderate.setTextSize(16);
                tw_OcularReaction_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_low.setTextSize(16);
                tw_OcularReaction_low.setTextColor(getResources().getColor(R.color.white));

                tw_OcularReaction_no.setTextSize(18);
                tw_OcularReaction_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasOcularReaction", "N.O.");
            }
        });

        //PersistentCough
        tw_PersistentCough_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_PersistentCough_severe.setTextSize(18);
                tw_PersistentCough_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasPersistentCough", "Severe");

                tw_PersistentCough_moderate.setTextSize(16);
                tw_PersistentCough_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_low.setTextSize(16);
                tw_PersistentCough_low.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_no.setTextSize(16);
                tw_PersistentCough_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_PersistentCough_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_PersistentCough_severe.setTextSize(16);
                tw_PersistentCough_severe.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_moderate.setTextSize(18);
                tw_PersistentCough_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasPersistentCough", "Moderate");

                tw_PersistentCough_low.setTextSize(16);
                tw_PersistentCough_low.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_no.setTextSize(16);
                tw_PersistentCough_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_PersistentCough_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_PersistentCough_severe.setTextSize(16);
                tw_PersistentCough_severe.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_moderate.setTextSize(16);
                tw_PersistentCough_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_low.setTextSize(18);
                tw_PersistentCough_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasPersistentCough", "Low");

                tw_PersistentCough_no.setTextSize(16);
                tw_PersistentCough_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_PersistentCough_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_PersistentCough_severe.setTextSize(16);
                tw_PersistentCough_severe.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_moderate.setTextSize(16);
                tw_PersistentCough_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_low.setTextSize(16);
                tw_PersistentCough_low.setTextColor(getResources().getColor(R.color.white));

                tw_PersistentCough_no.setTextSize(18);
                tw_PersistentCough_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasPersistentCough", "N.O.");
            }
        });

        //Rhinorrhea
        tw_Rhinorrhea_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Rhinorrhea_severe.setTextSize(18);
                tw_Rhinorrhea_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasRhinorrhea", "Severe");

                tw_Rhinorrhea_moderate.setTextSize(16);
                tw_Rhinorrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_low.setTextSize(16);
                tw_Rhinorrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_no.setTextSize(16);
                tw_Rhinorrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Rhinorrhea_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Rhinorrhea_severe.setTextSize(16);
                tw_Rhinorrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_moderate.setTextSize(18);
                tw_Rhinorrhea_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasRhinorrhea", "Moderate");

                tw_Rhinorrhea_low.setTextSize(16);
                tw_Rhinorrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_no.setTextSize(16);
                tw_Rhinorrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Rhinorrhea_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Rhinorrhea_severe.setTextSize(16);
                tw_Rhinorrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_moderate.setTextSize(16);
                tw_Rhinorrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_low.setTextSize(18);
                tw_Rhinorrhea_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasRhinorrhea", "Low");

                tw_Rhinorrhea_no.setTextSize(16);
                tw_Rhinorrhea_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Rhinorrhea_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Rhinorrhea_severe.setTextSize(16);
                tw_Rhinorrhea_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_moderate.setTextSize(16);
                tw_Rhinorrhea_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_low.setTextSize(16);
                tw_Rhinorrhea_low.setTextColor(getResources().getColor(R.color.white));

                tw_Rhinorrhea_no.setTextSize(18);
                tw_Rhinorrhea_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasRhinorrhea", "N.O.");
            }
        });

        //ShortnessOfBreath
        tw_ShortnessOfBreath_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ShortnessOfBreath_severe.setTextSize(18);
                tw_ShortnessOfBreath_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasShortnessOfBreath", "Severe");

                tw_ShortnessOfBreath_moderate.setTextSize(16);
                tw_ShortnessOfBreath_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_low.setTextSize(16);
                tw_ShortnessOfBreath_low.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_no.setTextSize(16);
                tw_ShortnessOfBreath_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ShortnessOfBreath_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ShortnessOfBreath_severe.setTextSize(16);
                tw_ShortnessOfBreath_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_moderate.setTextSize(18);
                tw_ShortnessOfBreath_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasShortnessOfBreath", "Moderate");

                tw_ShortnessOfBreath_low.setTextSize(16);
                tw_ShortnessOfBreath_low.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_no.setTextSize(16);
                tw_ShortnessOfBreath_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ShortnessOfBreath_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ShortnessOfBreath_severe.setTextSize(16);
                tw_ShortnessOfBreath_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_moderate.setTextSize(16);
                tw_ShortnessOfBreath_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_low.setTextSize(18);
                tw_ShortnessOfBreath_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasShortnessOfBreath", "Low");

                tw_ShortnessOfBreath_no.setTextSize(16);
                tw_ShortnessOfBreath_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_ShortnessOfBreath_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_ShortnessOfBreath_severe.setTextSize(16);
                tw_ShortnessOfBreath_severe.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_moderate.setTextSize(16);
                tw_ShortnessOfBreath_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_low.setTextSize(16);
                tw_ShortnessOfBreath_low.setTextColor(getResources().getColor(R.color.white));

                tw_ShortnessOfBreath_no.setTextSize(18);
                tw_ShortnessOfBreath_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasShortnessOfBreath", "N.O.");
            }
        });

        //SkinRash
        tw_SkinRash_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SkinRash_severe.setTextSize(18);
                tw_SkinRash_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasSkinRash", "Severe");

                tw_SkinRash_moderate.setTextSize(16);
                tw_SkinRash_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_low.setTextSize(16);
                tw_SkinRash_low.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_no.setTextSize(16);
                tw_SkinRash_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SkinRash_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SkinRash_severe.setTextSize(16);
                tw_SkinRash_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_moderate.setTextSize(18);
                tw_SkinRash_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasSkinRash", "Moderate");

                tw_SkinRash_low.setTextSize(16);
                tw_SkinRash_low.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_no.setTextSize(16);
                tw_SkinRash_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SkinRash_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SkinRash_severe.setTextSize(16);
                tw_SkinRash_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_moderate.setTextSize(16);
                tw_SkinRash_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_low.setTextSize(18);
                tw_SkinRash_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasSkinRash", "Low");

                tw_SkinRash_no.setTextSize(16);
                tw_SkinRash_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SkinRash_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SkinRash_severe.setTextSize(16);
                tw_SkinRash_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_moderate.setTextSize(16);
                tw_SkinRash_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_low.setTextSize(16);
                tw_SkinRash_low.setTextColor(getResources().getColor(R.color.white));

                tw_SkinRash_no.setTextSize(18);
                tw_SkinRash_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasSkinRash", "N.O.");
            }
        });

        //Inappetence
        tw_Inappetence_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Inappetence_severe.setTextSize(18);
                tw_Inappetence_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasInappetence", "Severe");

                tw_Inappetence_moderate.setTextSize(16);
                tw_Inappetence_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_low.setTextSize(16);
                tw_Inappetence_low.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_no.setTextSize(16);
                tw_Inappetence_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Inappetence_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Inappetence_severe.setTextSize(16);
                tw_Inappetence_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_moderate.setTextSize(18);
                tw_Inappetence_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasInappetence", "Moderate");

                tw_Inappetence_low.setTextSize(16);
                tw_Inappetence_low.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_no.setTextSize(16);
                tw_Inappetence_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Inappetence_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Inappetence_severe.setTextSize(16);
                tw_Inappetence_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_moderate.setTextSize(16);
                tw_Inappetence_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_low.setTextSize(18);
                tw_Inappetence_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasInappetence", "Low");

                tw_Inappetence_no.setTextSize(16);
                tw_Inappetence_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Inappetence_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Inappetence_severe.setTextSize(16);
                tw_Inappetence_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_moderate.setTextSize(16);
                tw_Inappetence_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_low.setTextSize(16);
                tw_Inappetence_low.setTextColor(getResources().getColor(R.color.white));

                tw_Inappetence_no.setTextSize(18);
                tw_Inappetence_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasInappetence", "N.O.");
            }
        });

        //Sneeze
        tw_Sneeze_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sneeze_severe.setTextSize(18);
                tw_Sneeze_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasSneeze", "Severe");

                tw_Sneeze_moderate.setTextSize(16);
                tw_Sneeze_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_low.setTextSize(16);
                tw_Sneeze_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_no.setTextSize(16);
                tw_Sneeze_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sneeze_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sneeze_severe.setTextSize(16);
                tw_Sneeze_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_moderate.setTextSize(18);
                tw_Sneeze_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasSneeze", "Moderate");

                tw_Sneeze_low.setTextSize(16);
                tw_Sneeze_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_no.setTextSize(16);
                tw_Sneeze_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sneeze_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sneeze_severe.setTextSize(16);
                tw_Sneeze_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_moderate.setTextSize(16);
                tw_Sneeze_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_low.setTextSize(18);
                tw_Sneeze_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasSneeze", "Low");

                tw_Sneeze_no.setTextSize(16);
                tw_Sneeze_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sneeze_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sneeze_severe.setTextSize(16);
                tw_Sneeze_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_moderate.setTextSize(16);
                tw_Sneeze_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_low.setTextSize(16);
                tw_Sneeze_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sneeze_no.setTextSize(18);
                tw_Sneeze_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasSneeze", "N.O.");
            }
        });

        //SoreThroat
        tw_SoreThroat_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SoreThroat_severe.setTextSize(18);
                tw_SoreThroat_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasSoreThroat", "Severe");

                tw_SoreThroat_moderate.setTextSize(16);
                tw_SoreThroat_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_low.setTextSize(16);
                tw_SoreThroat_low.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_no.setTextSize(16);
                tw_SoreThroat_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SoreThroat_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SoreThroat_severe.setTextSize(16);
                tw_SoreThroat_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_moderate.setTextSize(18);
                tw_SoreThroat_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasSoreThroat", "Moderate");

                tw_SoreThroat_low.setTextSize(16);
                tw_SoreThroat_low.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_no.setTextSize(16);
                tw_SoreThroat_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SoreThroat_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SoreThroat_severe.setTextSize(16);
                tw_SoreThroat_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_moderate.setTextSize(16);
                tw_SoreThroat_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_low.setTextSize(18);
                tw_SoreThroat_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasSoreThroat", "Low");

                tw_SoreThroat_no.setTextSize(16);
                tw_SoreThroat_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_SoreThroat_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_SoreThroat_severe.setTextSize(16);
                tw_SoreThroat_severe.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_moderate.setTextSize(16);
                tw_SoreThroat_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_low.setTextSize(16);
                tw_SoreThroat_low.setTextColor(getResources().getColor(R.color.white));

                tw_SoreThroat_no.setTextSize(18);
                tw_SoreThroat_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasSoreThroat", "N.O.");
            }
        });

        //Sputum
        tw_Sputum_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sputum_severe.setTextSize(18);
                tw_Sputum_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasSputum", "Severe");

                tw_Sputum_moderate.setTextSize(16);
                tw_Sputum_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_low.setTextSize(16);
                tw_Sputum_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_no.setTextSize(16);
                tw_Sputum_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sputum_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sputum_severe.setTextSize(16);
                tw_Sputum_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_moderate.setTextSize(18);
                tw_Sputum_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasSputum", "Moderate");

                tw_Sputum_low.setTextSize(16);
                tw_Sputum_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_no.setTextSize(16);
                tw_Sputum_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sputum_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sputum_severe.setTextSize(16);
                tw_Sputum_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_moderate.setTextSize(16);
                tw_Sputum_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_low.setTextSize(18);
                tw_Sputum_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasSputum", "Low");

                tw_Sputum_no.setTextSize(16);
                tw_Sputum_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Sputum_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Sputum_severe.setTextSize(16);
                tw_Sputum_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_moderate.setTextSize(16);
                tw_Sputum_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_low.setTextSize(16);
                tw_Sputum_low.setTextColor(getResources().getColor(R.color.white));

                tw_Sputum_no.setTextSize(18);
                tw_Sputum_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasSputum", "N.O.");
            }
        });

        //Vomiting
        tw_Vomiting_severe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Vomiting_severe.setTextSize(18);
                tw_Vomiting_severe.setTextColor(getResources().getColor(R.color.AppRed));
                observedSymptoms.put("hasVomiting", "Severe");


                tw_Vomiting_moderate.setTextSize(16);
                tw_Vomiting_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_low.setTextSize(16);
                tw_Vomiting_low.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_no.setTextSize(16);
                tw_Vomiting_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Vomiting_moderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Vomiting_severe.setTextSize(16);
                tw_Vomiting_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_moderate.setTextSize(18);
                tw_Vomiting_moderate.setTextColor(getResources().getColor(R.color.AppOrange));
                observedSymptoms.put("hasVomiting", "Moderate");

                tw_Vomiting_low.setTextSize(16);
                tw_Vomiting_low.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_no.setTextSize(16);
                tw_Vomiting_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Vomiting_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Vomiting_severe.setTextSize(16);
                tw_Vomiting_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_moderate.setTextSize(16);
                tw_Vomiting_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_low.setTextSize(18);
                tw_Vomiting_low.setTextColor(getResources().getColor(R.color.AppYellow));
                observedSymptoms.put("hasVomiting", "Low");

                tw_Vomiting_no.setTextSize(16);
                tw_Vomiting_no.setTextColor(getResources().getColor(R.color.white));

            }
        });
        tw_Vomiting_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tw_Vomiting_severe.setTextSize(16);
                tw_Vomiting_severe.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_moderate.setTextSize(16);
                tw_Vomiting_moderate.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_low.setTextSize(16);
                tw_Vomiting_low.setTextColor(getResources().getColor(R.color.white));

                tw_Vomiting_no.setTextSize(18);
                tw_Vomiting_no.setTextColor(getResources().getColor(R.color.AppBlue));
                observedSymptoms.put("hasVomiting", "N.O.");
            }
        });

        btn_ObservedSymptomsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( observedSymptoms.isEmpty() || observedSymptoms.size()!= 30){

                    Toast.makeText(getActivity(), "Please Fill All Areas !", Toast.LENGTH_SHORT).show();

                }else{
                    TemperatureFragment tempratureFragment = new TemperatureFragment();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("observedSymptoms", observedSymptoms);

                    tempratureFragment.setArguments(bundle);

                    ((CardActivity) getActivity()).replaceFragments(tempratureFragment);
                }

            }
        });

        return view;
    }



}