package com.metehanersoy.covid19app.Doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.metehanersoy.covid19app.R;


public class AlertViewFragment extends Fragment {

    String request;

    ImageView img_alertViewPage;
    TextView tw_alertViewPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alert_view, container, false);

        img_alertViewPage = view.findViewById(R.id.img_alertViewPage);
        tw_alertViewPage = view.findViewById(R.id.tw_alertViewPage);


        Bundle bundle = getArguments();

       request = (String) bundle.get("request");

        if (request.equals("pending")) {
            img_alertViewPage.setImageResource(R.drawable.ic_waiting_list_clock_svgrepo_com);
            tw_alertViewPage.setText("Your account has not yet been approved!, it is pending");

        } else if (request.equals("false")) {

            img_alertViewPage.setImageResource(R.drawable.ic_error);
            tw_alertViewPage.setText("Your account has  been rejected! ");
        }

        return view;
    }
}