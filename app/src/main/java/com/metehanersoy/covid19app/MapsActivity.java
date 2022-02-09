package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.Class.Location;
import com.metehanersoy.covid19app.databinding.ActivityMapsBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    float zoom = 17.0f;

    String aimedFrom="empty";
    DatabaseReference mDatabase;
    ArrayList<Location> locationArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng lefkosa = new LatLng(35.219606533537046, 33.3837476588813);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng zoomLocation = lefkosa;

        Hospital hospital = (Hospital) getIntent().getSerializableExtra("hospital");
        int position = getIntent().getIntExtra("position", -1);

        Bundle bundle = getIntent().getExtras();

        aimedFrom = (String)bundle.getString("aimedFrom");
        if(aimedFrom ==null || aimedFrom.isEmpty() ){
            aimedFrom="empty";
        }

        //that means patient clicked covid map and he is directed this page
        if(aimedFrom.equals("Patient") || aimedFrom.equals("Doctor")){

            zoom = 10.0f;

            //initializing firebase component
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("locations").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){

                        locationArrayList.clear();

                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            Location location = snapshot.getValue(Location.class);

                            long today = System.currentTimeMillis();
                            long userDate = (long) location.getDate() * 1000;
                            long diffInDays = getDateDiff(userDate, today, TimeUnit.DAYS);

                            if(diffInDays >14){

                            }else{
                                //We are saving patientId inside to location object because we don't keep doctor id in the database table!
                                LatLng myzoomLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                location.setPatientId( snapshot.getKey());
                                locationArrayList.add(location);
                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(myzoomLocation);
                                markerOptions.title("");
                                mMap.addMarker(markerOptions);
                            }

                        }
                    }
                }
            });




        } else if(aimedFrom.equals("Admin")){


        }else{
            if (hospital != null) {
                LatLng selectedHospital = new LatLng(hospital.getLatitude(), hospital.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(selectedHospital);
                markerOptions.title(hospital.getHospitalName());
                mMap.addMarker(markerOptions);
                zoomLocation = selectedHospital;

            } else if (position != -1) {


                ArrayList<Hospital> mList = (ArrayList<Hospital>) getIntent().getSerializableExtra("hospitalArrayList");

                if (!mList.isEmpty()) {
                    LatLng selectedHospital = new LatLng(mList.get(position).getLatitude(), mList.get(position).getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(selectedHospital);
                    markerOptions.title(mList.get(position).getHospitalName());
                    mMap.addMarker(markerOptions);
                    zoomLocation = selectedHospital;
                }

            }

        }



        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(zoomLocation, zoom));
    }

    public static long getDateDiff(long timeUpdate, long timeNow, TimeUnit timeUnit)
    {
        long diffInMillies = Math.abs(timeNow - timeUpdate);
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}