package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.Map;

public class VerifyEmailWarning extends AppCompatActivity {

    Button verifyEmailWarningButton;
    FirebaseAuth mAuth;
    FirebaseUser user;
    String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email_warning);

        verifyEmailWarningButton = findViewById(R.id.verifyEmailWarningButton);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        userType = getIntent().getStringExtra("userType");
    }

    public void verifyEmailWarningButtonClicked(View view) {

        // reload user
        user.reload().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                if (mAuth.getCurrentUser().isEmailVerified()) {

                    Log.e("covid19App", "VerifyEmailWarning -> UserDemographics (Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in VerifyEmailWarning");
                    Intent intent = new Intent(VerifyEmailWarning.this, UserDemographics.class);
                    intent.putExtra("userType",userType);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VerifyEmailWarning.this, "Please Verify Your Email !", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VerifyEmailWarning.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}