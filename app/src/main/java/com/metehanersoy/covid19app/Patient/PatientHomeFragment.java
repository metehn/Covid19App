package com.metehanersoy.covid19app.Patient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Card.CardActivity;
import com.metehanersoy.covid19app.Activity.DoctorListActivity;
import com.metehanersoy.covid19app.HospitalListPage;
import com.metehanersoy.covid19app.MapsActivity;
import com.metehanersoy.covid19app.R;

public class PatientHomeFragment extends Fragment {
    ImageView imageViewHospitalList, img_OpenCard_Patient, img_DoctorList_Patient, img_CovidMap_Patient, img_SubmittedCards_Patient;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_home_fragment, container, false);

        updateStatusBarColor(R.color.AppGreen);

        imageViewHospitalList = view.findViewById(R.id.imageViewHospitalList);
        img_OpenCard_Patient = view.findViewById(R.id.img_OpenCard_Patient);
        img_DoctorList_Patient = view.findViewById(R.id.img_DoctorList_Patient);
        img_CovidMap_Patient = view.findViewById(R.id.img_CovidMap_Patient);
        img_SubmittedCards_Patient = view.findViewById(R.id.img_SubmittedCards_Patient);

        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted.
                        startActivity(new Intent(getActivity(), HospitalListPage.class));
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                        startActivity(new Intent(getActivity(), HospitalListPage.class));
                    } else {
                        // No location access granted.
                        Toast.makeText(container.getContext(), "Location permissions  required !", Toast.LENGTH_SHORT).show();

                    }
                }
        );

        imageViewHospitalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), HospitalListPage.class));
                if (ContextCompat.checkSelfPermission(container.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(container.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    startActivity(new Intent(getActivity(), HospitalListPage.class));

                } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected. In this UI,
                    // include a "cancel" or "no thanks" button that allows the user to
                    // continue using your app without granting the permission.

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

                }
            }
        });

        img_OpenCard_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getActivity(), ObservedSymptoms.class));
                startActivity(new Intent(getActivity(), CardActivity.class));
            }
        });

        img_DoctorList_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DoctorListActivity.class);
                intent.putExtra("aimedFrom", "Patient");
                startActivity(intent);

            }
        });

        img_CovidMap_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("aimedFrom", "Patient");
                startActivity(intent);
            }
        });

        img_SubmittedCards_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SubmittedCardsActivity.class));
            }
        });


        return view;
    }

    public void updateStatusBarColor(int color) {// Color must be in hexadecimal format
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }
    }
}
