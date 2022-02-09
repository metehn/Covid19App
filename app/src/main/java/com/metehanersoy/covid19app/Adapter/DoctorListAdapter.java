package com.metehanersoy.covid19app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.metehanersoy.covid19app.Class.Doctor;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DoctorListAdapter extends ArrayAdapter<Doctor> {

    private ArrayList<Doctor> doctorArrayList;
    private Context context;

    public DoctorListAdapter(Context context, int resource, ArrayList<Doctor> doctorArrayList) {
        super(context, resource, doctorArrayList);

        this.context = context;
        this.doctorArrayList = doctorArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_doctor_row, parent, false);

            ImageView img_doctorRow = view.findViewById(R.id.img_doctorRow);
            TextView tw_doctorName = view.findViewById(R.id.tw_doctorName);
            TextView tw_hospitalName = view.findViewById(R.id.tw_hospitalName);
            Picasso.get().load(doctorArrayList.get(position).getProfileImageURL()).into(img_doctorRow);
            tw_doctorName.setText(doctorArrayList.get(position).getName());
            tw_hospitalName.setText(doctorArrayList.get(position).getHospitalName());

        }


        return view;
    }


}
