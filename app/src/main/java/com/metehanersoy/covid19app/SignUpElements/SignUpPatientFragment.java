package com.metehanersoy.covid19app.SignUpElements;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metehanersoy.covid19app.R;
import com.metehanersoy.covid19app.VerifyEmailWarning;

import java.util.ArrayList;
import java.util.HashMap;


public class SignUpPatientFragment extends Fragment {

    public static final int GALLERY_REQUEST = 1;

    ShapeableImageView signUpImageViewPatient;
    EditText signUpNamePatient;
    EditText signUpSurnamePatient;
    EditText signUpEmailPatient;
    EditText signUpPasswordPatient;
    Spinner signUpSpinnerPatient;
    CheckBox signUpCheckBoxPatient;
    Button signUpButtonPatient;
    Uri selectedImagePatient;

    ArrayAdapter<String> signUpSpinnerAdapter;

    private FirebaseAuth mAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DatabaseReference mDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_patient, container, false);

        signUpImageViewPatient = view.findViewById(R.id.signUpImageViewPatient);
        signUpNamePatient = view.findViewById(R.id.signUpNamePatient);
        signUpSurnamePatient = view.findViewById(R.id.signUpSurnamePatient);
        signUpEmailPatient = view.findViewById(R.id.signUpEmailPatient);
        signUpPasswordPatient = view.findViewById(R.id.signUpPasswordPatient);
        signUpSpinnerPatient = view.findViewById(R.id.signUpSpinnerPatient);
        signUpCheckBoxPatient = view.findViewById(R.id.signUpCheckBoxPatient);
        signUpButtonPatient = view.findViewById(R.id.signUpButtonPatient);

        ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add("Select Sex");
        spinnerList.add("Male");
        spinnerList.add("Female");

        signUpSpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, spinnerList);
        signUpSpinnerPatient.setAdapter(signUpSpinnerAdapter);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Create a storage and storage reference from our app
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        //Create database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        signUpImageViewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQUEST);
            }
        });

        signUpButtonPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = signUpEmailPatient.getText().toString();
                String password = signUpPasswordPatient.getText().toString();

                if(!email.isEmpty() && !password.isEmpty() && !signUpNamePatient.getText().toString().isEmpty() && !signUpSurnamePatient.getText().toString().isEmpty() && signUpCheckBoxPatient.isChecked() && selectedImagePatient !=null && signUpSpinnerPatient.getSelectedItemPosition() !=0 ) {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(getContext(), "Registered Successfully. Please check your email for verification!", Toast.LENGTH_LONG).show();
                                    //Write information to database


                                    String userId = mAuth.getCurrentUser().getUid();
                                    String imageName = "profile_images/" + userId + ".png";

                                    storageReference.child(imageName).putFile(selectedImagePatient).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            //Download url
                                            StorageReference newReference = firebaseStorage.getReference(imageName);
                                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String downloadUri = uri.toString();
                                                    String name = signUpNamePatient.getText().toString();
                                                    String surname = signUpSurnamePatient.getText().toString();
                                                    String email= signUpEmailPatient.getText().toString();
                                                    //String password = signUpPasswordPatient.getText().toString();
                                                    String sex = (signUpSpinnerPatient.getSelectedItemPosition() == 1) ? "Male" : "Female";

                                                    HashMap<String,Object> postData = new HashMap<>();
                                                    postData.put("name",name);
                                                    postData.put("surname",surname);
                                                    postData.put("email", email);
                                                    postData.put("profileImageURL",downloadUri);
                                                    postData.put("sex",sex);

                                                    mDatabase.child("users").child(userId).setValue(postData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Intent intent = new Intent(getContext(), VerifyEmailWarning.class);
                                                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                            intent.putExtra("userType","Patient");
                                                            startActivity(intent);
                                                            getActivity().finish();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                                        }
                                                    });


                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(getContext(), "Fill All Areas!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && data != null){

            selectedImagePatient = data.getData();
            signUpImageViewPatient.setImageURI(selectedImagePatient);
        }
    }


}