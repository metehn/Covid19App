package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.metehanersoy.covid19app.databinding.ActivityForgotPasswordBinding;


public class ForgotPassword extends AppCompatActivity {


    EditText emailForgotPassword;
    Button sendPassword;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        //Define Component
        emailForgotPassword = findViewById(R.id.emailForgotPassword);
        sendPassword = findViewById(R.id.sendPassword);



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    public void sendPasswordClicked(View view) {
        String email = emailForgotPassword.getText().toString();

        if (!email.isEmpty()) {
            sendPassword.setEnabled(false);
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Successful! Check your email address!", Toast.LENGTH_SHORT).show();
                        emailForgotPassword.setText("");
                    } else {
                        Toast.makeText(ForgotPassword.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });

        } else {
            Toast.makeText(ForgotPassword.this, "Please enter an email address!", Toast.LENGTH_SHORT).show();
        }
        sendPassword.setEnabled(true);
    }

}