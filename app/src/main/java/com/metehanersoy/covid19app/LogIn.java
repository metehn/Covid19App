package com.metehanersoy.covid19app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Admin.AdminHomePage;
import com.metehanersoy.covid19app.Class.User;
import com.metehanersoy.covid19app.Doctor.DoctorHomePage;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.SignUpElements.SignUp2;

public class LogIn extends AppCompatActivity {

    public static final int PERMISSION_LOCATION = 100;

    EditText EditTextEmail;
    EditText EditTextPassword;
    Button logIn;
    TextView signUp;
    TextView forgot;
    FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    User userObject; // used in returnUserType()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //Define Component
        EditTextEmail = findViewById(R.id.email);
        EditTextPassword = findViewById(R.id.password);
        logIn = findViewById(R.id.logIn);
        forgot = findViewById(R.id.forgot);
        signUp = findViewById(R.id.signUp);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Firebase User
        user = mAuth.getCurrentUser();

        // Access RealtimeDatabase
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // If User already logged in, go home page directly
        if (user != null && user.isEmailVerified()) {

            //autoLogIn(db, user);
        }

        // check for location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_LOCATION);
        }



    }

    // activated whet logIn button clicked
    public void logInClicked(View view) {
        String email = EditTextEmail.getText().toString();

        String password = EditTextPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LogIn.this, "Email and Password must be entered", Toast.LENGTH_SHORT).show();
        } else {

            logIn.setEnabled(false);


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user = mAuth.getCurrentUser();
                        //check if it is verified user
                        if (user.isEmailVerified()) {

                            // Direct user depending his type
                            intentUserPage();

                        } else {

                            Toast.makeText(LogIn.this, "You have not verified your account yet!!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(LogIn.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            logIn.setEnabled(true);
        }
    }

    // activated when signUp text clicked
      public void signUpClicked(View view) {

        startActivity(new Intent(LogIn.this, SignUp2.class));
    }

    //This method directs logged in user to his home page
    public void intentUserPage() {

        String userId = user.getUid();
        mDatabase.child("admins").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("covid19App", "Error getting data ", task.getException());
                } else {
                    if (task.getResult().exists()) {
                        Log.e("covid19App", "LogIn -> AdminHomePage (Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in LogIn");
                        startActivity(new Intent(LogIn.this, AdminHomePage.class));
                        finish();
                    }
                }
            }
        });

        mDatabase.child("doctors").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("covid19App", "Error getting data ", task.getException());
                } else {
                    if (task.getResult().exists()) {

                        mDatabase.child("doctor_requests").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DataSnapshot> task) {

                                if(task.isSuccessful()){

                                   String request =String.valueOf(task.getResult().getValue());
                                    if (request.equals("pending")){

                                        Toast.makeText(LogIn.this, "Your account has not yet been approved!", Toast.LENGTH_SHORT).show();
                                    }else if(request.equals("true")){

                                        Log.e("covid19App", "LogIn -> DoctorHomePage (Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in LogIn");
                                        startActivity(new Intent(LogIn.this, DoctorHomePage.class));
                                        finish();
                                    }else{

                                        Toast.makeText(LogIn.this, "Your account has  been rejected!", Toast.LENGTH_SHORT).show();

                                    }
                                }


                            }
                        });


                    }
                }
            }
        });

        mDatabase.child("users").child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data ", task.getException());
                } else {
                    if (task.getResult().exists()) {
                        Log.e("covid19App", "LogIn -> PatientHomePage (Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in LogIn");
                        startActivity(new Intent(LogIn.this, PatientHomePage.class));
                        finish();
                    }
                }
            }
        });

    }

    //activated when you click the text named 'forgot password? or send verification email'
    public void forgotSendVerification(View view) {
        Log.e("covid19App", "LogIn -> ForgotPassword (Line " + Thread.currentThread().getStackTrace()[1].getLineNumber() + " in LogIn");
        startActivity(new Intent(LogIn.this, ForgotPassword.class));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_LOCATION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {



            } else {
                Toast.makeText(this, "App requires Location permission!", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    } // end of onRequestPermissionsResult() method
/*
    public void autoLogIn(FirebaseFirestore db, FirebaseUser user) {

        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        Log.i("FirebaseFirestore_", "" + document.getData());

                        User userObject = document.toObject(User.class);

                        //check user type and direct him to related home page
                        if (userObject.getGender().equals("admin")) {

                            //intent admin home page
                            Intent intent = new Intent(getApplicationContext(), AdminHomePage.class);
                            startActivity(intent);
                            finish();

                        } else if (userObject.getGender().equals("doctor")) {

                            //intent doctor home page
                            Intent intent = new Intent(getApplicationContext(), DoctorHomePage.class);
                            startActivity(intent);
                            finish();

                        } else if (userObject.getGender().equals("patient")) {

                            //intent patient home page
                            Intent intent = new Intent(getApplicationContext(), PatientHomePage.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Log.e("FirebaseFirestore_", "No such document");
                    }
                } else {
                    Log.e("FirebaseFirestore_", "get failed with");
                }
            }
        });
    }
    public void clickLogIn(FirebaseFirestore db, FirebaseUser user) {

        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {

                        Log.i("FirebaseFirestore_", "" + document.getData());

                        User userObject = document.toObject(User.class);

                        //check user type and direct him to related home page
                        if (userObject.getGender().equals("admin")) {

                            //intent admin home page
                            Intent intent = new Intent(getApplicationContext(), AdminHomePage.class);
                            startActivity(intent);
                            finish();

                        } else if (userObject.getGender().equals("doctor")) {

                            //intent doctor home page
                            Intent intent = new Intent(getApplicationContext(), DoctorHomePage.class);
                            startActivity(intent);
                            finish();

                        } else if (userObject.getGender().equals("patient")) {

                            //intent patient home page
                            Intent intent = new Intent(getApplicationContext(), PatientHomePage.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        Log.e("FirebaseFirestore_", "No such document");
                        Toast.makeText(LogIn.this, "There is no user with this type.Check Database", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Log.e("FirebaseFirestore_", "get failed with");
                }
            }
        });
    }
*/
}