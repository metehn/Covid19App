package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.DoctorListActivity;
import com.metehanersoy.covid19app.Adapter.DoctorListAdapter;
import com.metehanersoy.covid19app.Class.Doctor;
import com.metehanersoy.covid19app.Doctor.DoctorProfileFragment;
import com.metehanersoy.covid19app.R;
import java.util.ArrayList;

public class DoctorListFragment extends Fragment {

    DatabaseReference mDatabase;
    ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    DoctorListAdapter listAdapter;

    EditText et_SearchFragmentDoctorlist;
    ImageView img_SearchFragmentDoctorlist, img_ShowFragmentDoctorlist;
    ListView lv_fragmentDoctorlist;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        et_SearchFragmentDoctorlist = view.findViewById(R.id.et_SearchFragmentDoctorlist);
        img_SearchFragmentDoctorlist = view.findViewById(R.id.img_SearchFragmentDoctorlist);
        img_ShowFragmentDoctorlist = view.findViewById(R.id.img_ShowFragmentDoctorlist);
        lv_fragmentDoctorlist = view.findViewById(R.id.lv_fragmentDoctorlist);

        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();

        updateList();

        bundle = this.getArguments();

        img_SearchFragmentDoctorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_SearchFragmentDoctorlist.getText().toString();
                if(!name.isEmpty()){

                    mDatabase.child("doctors").orderByChild("name")
                            .startAt(name)
                            .endAt(name+"\uf8ff").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            if(task.isSuccessful()){


                                doctorArrayList.clear();

                                for (DataSnapshot snapshot : task.getResult().getChildren()) {
                                    Doctor doctor = snapshot.getValue(Doctor.class);
                                    //We are saving doctorId inside to doctor object because we don't keep doctor id in the database table!
                                    doctor.setDoctorId( snapshot.getKey());
                                    doctorArrayList.add(doctor);
                                    if(!doctorArrayList.isEmpty()){
                                        DoctorListAdapter listAdapterSEARCH  = new DoctorListAdapter(getActivity(), R.layout.custom_doctor_row, doctorArrayList);
                                        lv_fragmentDoctorlist.setAdapter(listAdapterSEARCH);
                                    }

                                }


                            }


                        }
                    });


                }


            }
        });

        img_ShowFragmentDoctorlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!doctorArrayList.isEmpty()){
                    updateList();
                    lv_fragmentDoctorlist.setAdapter(listAdapter);

                }

            }
        });

        lv_fragmentDoctorlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Doctor doctor = doctorArrayList.get(position);

                bundle.putString("aimedDoctorId", doctor.getDoctorId());
                bundle.putString("aimedFrom", bundle.getString("aimedFrom"));

                DoctorProfileFragment doctorProfileFragment = new DoctorProfileFragment();
                doctorProfileFragment.setArguments(bundle);

                ((DoctorListActivity) getActivity()).replaceFragments(doctorProfileFragment);

            }
        });



        return view;
    }


    public void updateList(){
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
                    lv_fragmentDoctorlist.setAdapter(listAdapter);


                }

            }

        });

    }
}