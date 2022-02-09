package com.metehanersoy.covid19app.Doctor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.DoctorListActivity;
import com.metehanersoy.covid19app.HospitalListPage;
import com.metehanersoy.covid19app.MapsActivity;
import com.metehanersoy.covid19app.R;


public class DoctorHomeFragment extends Fragment {

ImageView img_cardRequest_Doctor, img_DoctorList_Doctor, img_RecentData_Doctor, img_CovidMap_Doctor, img_HospitalList_Doctor, img_News_Doctor;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_home, container, false);
        updateStatusBarColor(R.color.AppGreen);

        img_cardRequest_Doctor = view.findViewById(R.id.img_cardRequest_Doctor);
        img_DoctorList_Doctor = view.findViewById(R.id.img_DoctorList_Doctor);
        img_RecentData_Doctor = view.findViewById(R.id.img_RecentData_Doctor);
        img_CovidMap_Doctor = view.findViewById(R.id.img_CovidMap_Doctor);
        img_HospitalList_Doctor = view.findViewById(R.id.img_HospitalList_Doctor);
        img_News_Doctor = view.findViewById(R.id.img_News_Doctor);

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

        img_cardRequest_Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), CardRequestsActivity.class));

            }
        });

        img_HospitalList_Doctor.setOnClickListener(new View.OnClickListener() {
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

        img_CovidMap_Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra("aimedFrom", "Doctor");
                startActivity(intent);
            }
        });

        img_DoctorList_Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DoctorListActivity.class);
                intent.putExtra("aimedFrom", "Doctor");
                startActivity(intent);

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

}