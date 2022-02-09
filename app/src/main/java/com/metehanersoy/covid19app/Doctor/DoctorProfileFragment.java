package com.metehanersoy.covid19app.Doctor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.LogIn;
import com.metehanersoy.covid19app.MapsActivity;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DoctorProfileFragment extends Fragment {


    ShapeableImageView img_profile_doctor;
    TextView tv_editProfile_doctor;
    TextView tv_name_doctor;
    TextView tv_hospitalname_doctor;
    AppCompatImageView img_hospital_doctor; //hata olursa ImageView ile değiştir
    AppCompatImageView img_patient_list_doctor; //hata olursa ImageView ile değiştir
    TextView tv_patient_list_doctor;
    Button signOutButton_doctor;
    TextView tv_changePassword_doctor;

    String name;
    String surname;
    String profileImageURL;
    String specialty="";

    Hospital hospital;
    String hospitalId;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ViewGroup mContainer;
    String doctorId;
    String aimedFrom = "";
    String aimedDoctorId = " ";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doctor_profile, container, false);
        mContainer = container;

        img_profile_doctor = view.findViewById(R.id.img_profile_doctor);
        tv_editProfile_doctor = view.findViewById(R.id.tv_editProfile_doctor);
        tv_name_doctor = view.findViewById(R.id.tv_name_doctor);
        tv_hospitalname_doctor = view.findViewById(R.id.tv_hospitalname_doctor);
        img_hospital_doctor = view.findViewById(R.id.img_hospital_doctor);
        img_patient_list_doctor = view.findViewById(R.id.img_patient_list_doctor);
        tv_patient_list_doctor = view.findViewById(R.id.tv_patient_list_doctor);
        signOutButton_doctor = view.findViewById(R.id.signOutButton_doctor);
        tv_changePassword_doctor = view.findViewById(R.id.tv_changePassword_doctor);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        updateStatusBarColor(R.color.AppGreenDark);

        hospital = new Hospital();
        Bundle bundle = this.getArguments();

        //if bundle null that means a doctor clicked fragmentprofile
        if (bundle == null) {
            doctorId = mAuth.getUid();

            //else someone clicked his profile
        } else {

            aimedDoctorId = bundle.getString("aimedDoctorId", "null");
            aimedFrom = bundle.getString("aimedFrom", "null");
            if (!aimedFrom.equals("null") && aimedFrom.equals("Patient")) {

                doctorId = aimedDoctorId;
                tv_editProfile_doctor.setVisibility(View.INVISIBLE);
                img_patient_list_doctor.setVisibility(View.INVISIBLE);
                tv_patient_list_doctor.setVisibility(View.INVISIBLE);
                signOutButton_doctor.setVisibility(View.INVISIBLE);
                tv_changePassword_doctor.setVisibility(View.INVISIBLE);


            } else if (!aimedFrom.equals("null") && aimedFrom.equals("Doctor")) {

                doctorId = aimedDoctorId;
                tv_editProfile_doctor.setVisibility(View.INVISIBLE);
                signOutButton_doctor.setVisibility(View.INVISIBLE);
                tv_changePassword_doctor.setVisibility(View.INVISIBLE);
                img_patient_list_doctor.setVisibility(View.VISIBLE);
                tv_patient_list_doctor.setVisibility(View.VISIBLE);


            } else if (!aimedFrom.equals("null") && aimedFrom.equals("Admin")) {

                doctorId = aimedDoctorId;
                tv_editProfile_doctor.setVisibility(View.INVISIBLE);
                signOutButton_doctor.setVisibility(View.INVISIBLE);
                tv_changePassword_doctor.setVisibility(View.INVISIBLE);


            }
        }

        mDatabase.child("doctors").child(doctorId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    name = (String) task.getResult().child("name").getValue();
                    specialty = (String)  task.getResult().child("specialty").getValue();
                    surname = (String) task.getResult().child("surname").getValue();
                    profileImageURL = (String) task.getResult().child("profileImageURL").getValue();
                    hospitalId = (String) task.getResult().child("hospitalId").getValue();
                    hospital.setHospitalId(hospitalId);
                    hospital.setHospitalName(" ");

                    updateUI();

                    mDatabase.child("hospitals").child(hospital.getHospitalId()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {


                                hospital = task.getResult().getValue(Hospital.class);
                                hospital.setHospitalId(hospitalId);
                                updateUI();
                            } else {
                                Toast.makeText(container.getContext(), "There is a connection error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    Toast.makeText(container.getContext(), "There is a connection error!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {

                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                                Log.e("covid19", "locationPermissionRequest if");
                                Intent intent = new Intent(getActivity(), MapsActivity.class);
                                intent.putExtra("hospital", hospital);
                                startActivity(intent);

                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                                Log.e("covid19", "locationPermissionRequest else if");
                                Intent intent = new Intent(getActivity(), MapsActivity.class);
                                intent.putExtra("hospital", hospital);
                                startActivity(intent);

                            } else {
                                // No location access granted.
                                Toast.makeText(container.getContext(), "Location permissions  required !", Toast.LENGTH_SHORT).show();
                                Log.e("covid19", "locationPermissionRequest else");
                            }
                        }
                );

        img_patient_list_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "process\n"+doctorId, Toast.LENGTH_SHORT).show();
            }
        });
        img_hospital_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(container.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(container.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.e("covid19app", "if");
                    Intent intent = new Intent(getActivity(), MapsActivity.class);

                    intent.putExtra("hospital", hospital);
                    startActivity(intent);

                } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.
                    Log.e("covid19app", "else if");
                    locationPermissionRequest.launch(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    });

                } else {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    //requestPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
                    locationPermissionRequest.launch(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION});

                    Log.e("covid19app", "else ");
                }

            }
        });


        signOutButton_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LogIn.class));
                getActivity().finish();
                //System.exit(0);
            }
        });


        return view;
    }

    public void updateStatusBarColor(int color) {// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }
    }

    public void updateUI() {

        Picasso.get().load(profileImageURL).into(img_profile_doctor);
        tv_name_doctor.setText(name + " " + surname);
        tv_hospitalname_doctor.setText(hospital.getHospitalName() + "|"+ specialty);

    }
}