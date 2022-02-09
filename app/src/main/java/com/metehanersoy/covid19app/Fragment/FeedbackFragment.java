package com.metehanersoy.covid19app.Fragment;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Class.Symptoms;
import com.metehanersoy.covid19app.R;


public class FeedbackFragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    EditText et_feedbackFeedback;
    AppCompatButton bt_feedback;

    String aimedFrom;
    String cardId;
    String userId;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        et_feedbackFeedback = view.findViewById(R.id.et_feedbackFeedback);
        bt_feedback = view.findViewById(R.id.bt_feedback);

        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //get aimedFrom bundle
        bundle = this.getArguments();

        if (bundle != null) {
            aimedFrom = bundle.getString("aimedFrom", "null");
            cardId = bundle.getString("cardId", "null");


            if (aimedFrom.equals("Patient")) {
                et_feedbackFeedback.setEnabled(false  );
                bt_feedback.setText("Close");

                mDatabase.child("examination_cards").child(mAuth.getUid()).child(cardId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        String feedback = (String) dataSnapshot.child("feedback").getValue();
                        et_feedbackFeedback.setText(feedback);
                    }
                });

            } else if (aimedFrom.equals("Doctor")) {

                userId = bundle.getString("userId", "null");
                et_feedbackFeedback.setEnabled(true);

                bt_feedback.setText("Submit");

                mDatabase.child("examination_cards").child(userId).child(cardId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        String feedback = (String) dataSnapshot.child("feedback").getValue();
                        et_feedbackFeedback.setText(feedback);
                    }
                });


            }

        }
        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if(aimedFrom != null && aimedFrom.equals("Doctor")){
                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToFeedback");
                }else{

                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToFeedback");
                }


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        bt_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                if(aimedFrom.equals("Patient")){

                    if((FeedbackFragment) fragmentManager.findFragmentByTag("submittedToFeedback") !=null){
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToFeedback");
                        return;
                    }
                }
                else if(aimedFrom.equals("Doctor")){

                    //first upload to database then close page and toast successfull message
                    userId = bundle.getString("userId", "null");
                    String txt = et_feedbackFeedback.getText().toString();

                    if(txt.isEmpty()){
                        Toast.makeText(getActivity(), "Please fill all areas", Toast.LENGTH_SHORT).show();
                    }else{
                        mDatabase.child("examination_cards").child(userId).child(cardId).child("feedback").setValue(txt);
                        Toast.makeText(getActivity(), "Submitted", Toast.LENGTH_SHORT).show();
                        ((CardRequestsActivity) getActivity()).removeFragments("submittedToFeedback");
                    }


                }


            }
        });


        return view;
    }
}