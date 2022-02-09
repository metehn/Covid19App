package com.metehanersoy.covid19app.Card;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.metehanersoy.covid19app.R;

import java.util.HashMap;

public class CardActivity extends AppCompatActivity {

    FrameLayout fl_Card;


    FragmentManager fragmentManager;
    ObservedSymptomsFragment fragmentSymptoms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        fl_Card = findViewById(R.id.fl_Card);


        fragmentManager = getSupportFragmentManager();

        fragmentSymptoms = new ObservedSymptomsFragment();


        fragmentManager.beginTransaction().replace(R.id.fl_Card, fragmentSymptoms).commit();
        //fragmentManager.beginTransaction().replace(R.id.fl_Card, new UploadCardFilesFragment()).commit();

    }
    public void replaceFragments(Fragment fragment){

        fragmentManager.beginTransaction().replace(R.id.fl_Card, fragment).commit();
    }

}