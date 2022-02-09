package com.metehanersoy.covid19app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.metehanersoy.covid19app.Fragment.SubmittedCardsListFragment;
import com.metehanersoy.covid19app.Fragment.SuggestionFragment;
import com.metehanersoy.covid19app.R;

public class SubmittedCardsActivity extends AppCompatActivity {

    FrameLayout fl_submittedCardsActivity;

    FragmentManager fragmentManager;
    SubmittedCardsListFragment submittedCardsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_cards);

        fl_submittedCardsActivity = findViewById(R.id.fl_submittedCardsActivity);

        fragmentManager = getSupportFragmentManager();

        submittedCardsListFragment = new SubmittedCardsListFragment();

        replaceFragments(submittedCardsListFragment);

    }

    public void replaceFragments(Fragment fragment){

        fragmentManager.beginTransaction().replace(R.id.fl_submittedCardsActivity, fragment).commit();


    }
    public  void addFragments(Fragment fragment,String tag) {

        fragmentManager.beginTransaction().add(R.id.fl_submittedCardsActivity, fragment, tag).commit();
    }

    public void removeFragments(String tag ) {

        Fragment fragmentB =  fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentB != null) {
            fragmentTransaction.remove(fragmentB);
            fragmentTransaction.commit();
        }
    }

    public void popBackStack(String tag,int i){
        // i = 0 or i= FragmentManager.POP_BACK_STACK_INCLUSIVE (1)
        fragmentManager.popBackStack(tag, i);

    }
}