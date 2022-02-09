package com.metehanersoy.covid19app.Card;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.metehanersoy.covid19app.R;

import java.util.HashMap;

public class TemperatureFragment extends Fragment {

    HashMap<String, String> observedSymptoms = new HashMap<>();

    int left, right;

    AppCompatButton btn_fragmentTemperature;
    NumberPicker np_fragmentTemperature, np_fragmentTemperatureRight;

    Double bodyTemperature;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);

        btn_fragmentTemperature = view.findViewById(R.id.btn_fragmentTemperature);

        np_fragmentTemperature = view.findViewById(R.id.np_fragmentTemperature);
        np_fragmentTemperatureRight = view.findViewById(R.id.np_fragmentTemperatureRight);

        np_fragmentTemperature.setMinValue(36);
        np_fragmentTemperature.setMaxValue(39);
        left = np_fragmentTemperature.getValue();

        np_fragmentTemperatureRight.setMinValue(0);
        np_fragmentTemperatureRight.setMaxValue(9);
        right = np_fragmentTemperatureRight.getValue();

        bodyTemperature = Double.parseDouble(left + "." + right);

        Bundle bundle = this.getArguments();

        np_fragmentTemperature.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                left = newVal;
            }
        });
        np_fragmentTemperatureRight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                right = newVal;
            }
        });

        btn_fragmentTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bodyTemperature = Double.parseDouble(left + "." + right);

                if (bundle != null) {
                    //observedSymptoms = (HashMap<String, String>) bundle.getSerializable("observedSymptoms");
                    bundle.putDouble("bodyTemperature", bodyTemperature);


                    SelectDoctorFragment selectDoctorFragment = new SelectDoctorFragment();


                    //bundle.putSerializable("observedSymptoms", observedSymptoms);

                    selectDoctorFragment.setArguments(bundle);

                    ((CardActivity) getActivity()).replaceFragments(selectDoctorFragment);




                }
            }
        });




        return view;
    }
}