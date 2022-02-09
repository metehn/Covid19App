package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.R;

public class ChangePasswordFragment extends Fragment {

    FirebaseAuth mAuth;

    TextView tv_changePassword;
    AppCompatButton btn_changePassword;
    ProgressBar pb_changePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        mAuth = FirebaseAuth.getInstance();


        btn_changePassword = view.findViewById(R.id.btn_changePassword);
        pb_changePassword = view.findViewById(R.id.pb_changePassword);
        tv_changePassword = view.findViewById(R.id.tv_changePassword);

        pb_changePassword.setVisibility(View.INVISIBLE);
        tv_changePassword.setText(" Click send button to send reset password email");


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                ((PatientHomePage) getActivity()).removeFragments("patientProfileToChangePassword");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_changePassword.setVisibility(View.INVISIBLE);
                pb_changePassword.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(mAuth.getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            tv_changePassword.setText("Email sent your email addres please check your email address");
                            Toast.makeText(getActivity(), "An email sent your email adrress", Toast.LENGTH_SHORT).show();
                            ((PatientHomePage)getActivity()).removeFragments("patientProfileToChangePassword");

                        }else{
                            tv_changePassword.setText("Email sent your email addres please check your email address");
                            btn_changePassword.setVisibility(View.VISIBLE);
                            pb_changePassword.setVisibility(View.INVISIBLE);

                        }

                    }
                });

            }
        });


        return view;
    }
}