package com.metehanersoy.covid19app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.metehanersoy.covid19app.R;
import com.metehanersoy.covid19app.Fragment.DoctorListFragment;

public class DoctorListActivity extends AppCompatActivity {

    FrameLayout fl_DoctorListActivity;

    FragmentManager fragmentManager;
    DoctorListFragment doctorListFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        fl_DoctorListActivity = findViewById(R.id.fl_DoctorListActivity);


        fragmentManager = getSupportFragmentManager();

        doctorListFragment = new DoctorListFragment();

        Bundle bundle = getIntent().getExtras();
        String aimedFrom = (String) bundle.get("aimedFrom");
        Bundle bundle2 = new Bundle();
        bundle2.putString("aimedFrom",aimedFrom);
        doctorListFragment.setArguments(bundle2);

        replaceFragments(doctorListFragment);

    }

    public void replaceFragments(Fragment fragment){

        fragmentManager.beginTransaction().replace(R.id.fl_DoctorListActivity, fragment).commit();
    }

}