package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Adapter.HospitalListAdapter;
import com.metehanersoy.covid19app.Class.Hospital;

import java.util.ArrayList;

public class HospitalListPage extends AppCompatActivity {

    ListView lv_HospitalListPage;
    ArrayList<Hospital> hospitalArrayList = new ArrayList<>();
    HospitalListAdapter listAdapter;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list_page);

        //updateStatusBarColor(R.color.AppGreenDark);

        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();

        lv_HospitalListPage = findViewById(R.id.lv_HospitalListPage);


        mDatabase.child("hospitals").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){

                    hospitalArrayList.clear();

                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        Hospital hospital = snapshot.getValue(Hospital.class);
                        hospital.setHospitalId(snapshot.getKey());// doctor tablesini yaparken farkettim, hospitalId'sini kaydetmemişim...
                        Log.e("hospital", hospital.getHospitalName());
                        hospitalArrayList.add(hospital);
                    }

                    listAdapter = new HospitalListAdapter(HospitalListPage.this, R.layout.custom_hospital_row, hospitalArrayList);
                    lv_HospitalListPage.setAdapter(listAdapter);

                }

            }

        });

    }

    public void updateStatusBarColor(int color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));       }
    }
}