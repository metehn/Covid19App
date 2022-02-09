package com.metehanersoy.covid19app.Card;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Adapter.DoctorListAdapter;
import com.metehanersoy.covid19app.Adapter.HospitalListAdapter;
import com.metehanersoy.covid19app.Class.Doctor;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.HospitalListPage;
import com.metehanersoy.covid19app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectDoctorFragment extends Fragment {

    Double bodyTemperature;

    private DatabaseReference mDatabase;


    ListView lv_fragmentSelectDoctor;
    ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    DoctorListAdapter listAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_doctor, container, false);

        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();

        lv_fragmentSelectDoctor = view.findViewById(R.id.lv_fragmentSelectDoctor);

        Bundle bundle = this.getArguments();


        mDatabase.child("doctors").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){

                    doctorArrayList.clear();

                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        Doctor doctor = snapshot.getValue(Doctor.class);
                        //We are saving doctorId inside to doctor object because we don't keep doctor id in the database table!
                        doctor.setDoctorId( snapshot.getKey());
                        doctorArrayList.add(doctor);
                    }

                    listAdapter = new DoctorListAdapter(getActivity(), R.layout.custom_doctor_row, doctorArrayList);
                    lv_fragmentSelectDoctor.setAdapter(listAdapter);

                }

            }

        });

        lv_fragmentSelectDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //resim yükleme sayfasına git. Doktor objesini bundle ile aktar!

                if (bundle != null) {

                    bundle.putSerializable("doctor",doctorArrayList.get(position));
                }

                UploadCardFilesFragment uploadCardFilesFragment = new UploadCardFilesFragment();

                uploadCardFilesFragment.setArguments(bundle);

                ((CardActivity) getActivity()).replaceFragments(uploadCardFilesFragment);

            }
        });





        return view;
    }
}