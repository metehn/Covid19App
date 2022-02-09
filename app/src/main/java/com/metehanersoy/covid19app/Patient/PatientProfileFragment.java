package com.metehanersoy.covid19app.Patient;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Fragment.ChangePasswordFragment;
import com.metehanersoy.covid19app.Fragment.EditProfileFragment;
import com.metehanersoy.covid19app.Fragment.MedicalConditionsFragment;
import com.metehanersoy.covid19app.Fragment.TravelnfoFragment;
import com.metehanersoy.covid19app.LogIn;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class PatientProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    AppCompatButton signOutButton;
    AppCompatImageView img_Profile, img_medicalCondition, img_travelInfo;
    TextView textViewName, textViewAge, textViewSingle, tv_changePassword_patient, tv_editProfilePatient, tv_status;
    ViewGroup mContainer;

    String name;
    String surname;
    long birthdate;
    String maritalStatus;
    String profileImageURL;
    String status;
    long lastUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_profile_fragment, container, false);


        img_Profile = view.findViewById(R.id.img_Profile);
        textViewName = view.findViewById(R.id.textViewName);
        textViewAge = view.findViewById(R.id.textViewAge);
        textViewSingle = view.findViewById(R.id.textViewSingle);
        signOutButton = view.findViewById(R.id.signOutButton);
        tv_changePassword_patient = view.findViewById(R.id.tv_changePassword_patient);
        img_medicalCondition = view.findViewById(R.id.img_medicalCondition);
        img_travelInfo = view.findViewById(R.id.img_travelInfo);
        tv_editProfilePatient = view.findViewById(R.id.tv_editProfilePatient);
        tv_status = view.findViewById(R.id.tv_status);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        updateStatusBarColor(R.color.AppGreenDark);

        mDatabase.child("users").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    name = (String) task.getResult().child("name").getValue();
                    surname = (String) task.getResult().child("surname").getValue();
                    birthdate =  Long.parseLong(task.getResult().child("birthdate").getValue().toString());
                    maritalStatus = (String) task.getResult().child("maritalStatus").getValue();
                    profileImageURL = (String) task.getResult().child("profileImageURL").getValue();
                    status = (String) task.getResult().child("status").getValue();
                    lastUpdate = Long.parseLong(task.getResult().child("lastUpdate").getValue().toString());

                    updateInfo();

                } else {
                    Toast.makeText(container.getContext(), "There is a connection error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_editProfilePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment editProfileFragment = new EditProfileFragment();
                ((PatientHomePage) getActivity()).addFragments(editProfileFragment, "patientProfileToEditProfile");
            }
        });

        tv_changePassword_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                ((PatientHomePage) getActivity()).addFragments(changePasswordFragment, "patientProfileToChangePassword");

            }
        });

        img_medicalCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedicalConditionsFragment medicalConditionsFragment = new MedicalConditionsFragment();
                ((PatientHomePage) getActivity()).addFragments(medicalConditionsFragment, "patientProfileToMedicalConditions");
            }
        });

        img_travelInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TravelnfoFragment travelnfoFragment = new TravelnfoFragment();
                ((PatientHomePage) getActivity()).addFragments(travelnfoFragment, "patientProfileToTravelInfo");


            }
        });


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                ((PatientHomePage) getActivity()).removeFragments("patientProfileToChangePassword");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


        return view;
    }


    public void updateInfo() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        Date date2 = new Date(((long) birthdate) * 1000L);

        int year = Math.abs(Integer.parseInt(formatter.format(date)) - Integer.parseInt(formatter.format(date2)));

        long epoch = System.currentTimeMillis() / 1000;

        String lastUpdateSTR = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date((epoch - lastUpdate) * 1000));
        int day = Integer.parseInt(lastUpdateSTR.split("/")[0]);

        if(day >14){
            tv_status.setText("Risky");
            tv_status.setTextColor(getResources().getColor(R.color.AppRed));
        }else{

            if(status !=null) {
                if (status.equals("Riskless")) {
                    tv_status.setText(status);
                    tv_status.setTextColor(getResources().getColor(R.color.VividGreen));

                } else if (status.equals("Risky")) {
                    tv_status.setText(status);
                    tv_status.setTextColor(getResources().getColor(R.color.VividRed));


                }
            }

        }

        Picasso.get().load(profileImageURL).into(img_Profile);
        textViewName.setText(name + " " + surname);
        textViewAge.setText(String.valueOf(year));
        textViewSingle.setText(maritalStatus);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LogIn.class));
                getActivity().finish();
                //System.exit(0);
            }
        });
    }


    public void updateStatusBarColor(int color) {// Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }
    }
}
