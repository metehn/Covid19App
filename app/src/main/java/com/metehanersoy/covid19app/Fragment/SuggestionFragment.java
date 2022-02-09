package com.metehanersoy.covid19app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.MapsActivity;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestionFragment extends Fragment {


    String[] one = {"1.Continue online tests."
            , "2.You need to wash hands with soap and water often and for at least 20 seconds."
            , "3.You need to practice good respiratory hygiene and avoid touching the eyes, nose or mouth with unwashed hands."
            , "4.Stay home and self-isolate even with minor symptoms such as cough, headache, mild fever, until you recover."
            , "5.Please observe your health conditions and symptoms closely."};

    String[] two = {"1.Continue online tests."
            , "2.You need to wash hands with soap and water often and for at least 20 seconds."
            , "3.You need to practice good respiratory hygiene and avoid touching the eyes, nose or mouth with unwashed hands."
            , "4.You need rest."
            , "5.You need to drink plenty of fluids."
            , "6.Consult your doctor to get an over-the-counter pain reliever that's best for you."
            , "7.Try to keep your temperature at normal levels such as by applying a cold towel, taking a warm shower."
            , "8.Have someone bring you supplies."
            , "9.Stay home and self-isolate even with mild or moderate symptoms such as cough, headache, mild fever, chills with repeated shaking, deep cough, fatigue, body aches, muscle pain, general feeling of being unwell, until you recover."
            , "10.Please observe your health conditions and symptoms closely."
            , "11.You have a medium-to-risk of Covid-19 according to your Online Covid-19 test result, contact your doctor, please do not be panic!"};

    String[] three = {"1.You need to seek medical attention / hospitalization but call by telephone in advance if possible and follow the directions of your local health authority."
            , "2.You need to use personal protective equipment."
            , "3.You are at higher risk of Covid-19 according to your Online Covid-19 test result, please do not be panic!"
            , "4.Please observe your health conditions and symptoms closely."
            , "5.Consult your doctor for a physical examination as soon as possible."
            , "6.Have Real-Time PCR (RT-qPCR) test for SARS-CoV-2."};


    TextView tw_probability;
    ListView lv_suggestions;
    AppCompatButton bt_suggestions;
    Bundle bundle;

    int scoreBasedCase;
    double probability;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);

        tw_probability = view.findViewById(R.id.tw_probability);
        lv_suggestions = view.findViewById(R.id.lv_suggestions);
        bt_suggestions = view.findViewById(R.id.bt_suggestions);



        bundle = this.getArguments();


        if (bundle != null) {
            scoreBasedCase = (int) bundle.get("scoreBasedCase");
            probability = (double) bundle.get("probability");

            tw_probability.setText("Probability | "+ String.format("%.2f",probability) + "%" );

            if (scoreBasedCase == 1) {

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, one);
                lv_suggestions.setAdapter(adapter);

            } else if (scoreBasedCase == 2) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, two);
                lv_suggestions.setAdapter(adapter);

            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, three);
                lv_suggestions.setAdapter(adapter);

            }



        }

        bt_suggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                String userType = bundle.getString("userType", "null");

                if(userType.equals("Doctor")){

                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToSuggestion");
                    return;

                }

                if((SuggestionFragment) fragmentManager.findFragmentByTag("submittedToSuggestion") !=null){

                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToSuggestion");
                    return;
                }

                getActivity().finish();

            }
        });


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                String userType = bundle.getString("userType", "null");
                if(userType != null && userType.equals("Doctor")){
                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToSuggestion");
                }else{

                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToSuggestion");
                }


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);




        return view;
    }



}