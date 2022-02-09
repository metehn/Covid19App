package com.metehanersoy.covid19app.SignUpElements;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.metehanersoy.covid19app.R;

import java.util.ArrayList;

public class SignUp2 extends AppCompatActivity {

TabLayout tl_Signup2;
FrameLayout fl_Signup2;

    ArrayList<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        tl_Signup2 = findViewById(R.id.tl_Signup2);
        fl_Signup2 = findViewById(R.id.fl_Signup2);

        TabLayout.Tab firstTab = tl_Signup2.newTab();
        firstTab.setText("Patient"); // set the Text for the first Tab
        tl_Signup2.addTab(firstTab); // add  the tab at in the TabLayout


        TabLayout.Tab secondTab = tl_Signup2.newTab();
        secondTab.setText("Doctor"); // set the Text for the second Tab
        tl_Signup2.addTab(secondTab); // add  the tab  in the TabLayout


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_Signup2, new SignUpPatientFragment());
        ft.commit();

        tl_Signup2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new SignUpPatientFragment();
                        break;
                    case 1:
                        fragment = new SignUpDoctorFragment();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fl_Signup2, fragment);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fragmentList.add(new SignUpPatientFragment());
        fragmentList.add(new SignUpDoctorFragment());
    }


}
