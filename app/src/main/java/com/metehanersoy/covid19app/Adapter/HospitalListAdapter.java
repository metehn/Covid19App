package com.metehanersoy.covid19app.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.MapsActivity;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HospitalListAdapter extends ArrayAdapter<Hospital> {

    private ArrayList<Hospital> hospitalArrayList;
    private Context context;

    public HospitalListAdapter(Context context, int resource, ArrayList<Hospital> hospitalArrayList) {
        super(context, resource, hospitalArrayList);

        this.context = context;
        this.hospitalArrayList = hospitalArrayList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_hospital_row, parent, false);

            ImageView img_hospital = view.findViewById(R.id.img_hospital);
            TextView tw_hospital = view.findViewById(R.id.tw_hospital);
            ImageView img_location = view.findViewById(R.id.img_location);
            Picasso.get().load(hospitalArrayList.get(position).getHospitalImageURL()).into(img_hospital);
            tw_hospital.setText(hospitalArrayList.get(position).getHospitalName());


            img_location.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Ask for location permission
                    Intent intent = new Intent(context, MapsActivity.class);
                    intent.putExtra("hospitalArrayList",hospitalArrayList);
                    intent.putExtra("position", position);
                    context.startActivity(intent);

                }
            });
        }


        return view;
    }

}
