package com.metehanersoy.covid19app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.Utils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class EditProfileFragment extends Fragment {

    public static final int GALLERY_REQUEST = 1;
    //Patient component
    LinearLayout ll_editProfilePatient;
    ShapeableImageView img_editProfileFragmentProfile_Patient;
    TextView tv_editProfileFragmentName_Patient, tv_editProfileFragmentSurname_Patient, tv_editProfileFragmentHeight_Patient,
            tv_editProfileFragmentWeight_Patient, tv_editProfileFragmentMobileNo_Patient, tv_editProfileFragmentBirthdate_Patient;
    AppCompatButton btn_editProfileFragment_Patient;
    Spinner sp_editProfileFragmentCity_Patient, sp_editProfileFragmentHealthSector_Patient, sp_editProfileFragmentMaritalStatus_Patient,
            sp_editProfileFragmentSex_Patient;
    ProgressBar pb_editProfileFragment_Patient;


    //Doctor component

    ShapeableImageView img_editProfileFragmentProfile_Doctor;

    String[] cities = new String[]{"Select Current City Your Apartment Located", "GaziMagusa", "Girne", "Lefkosa", "Lefke"};
    String[] maritalStatus = new String[]{"Marital Status", "Single", "Married"};
    String[] healthSector = new String[]{"Do you work in Health area?", "Nurse", "Others", "No"};
    String[] sex = new String[]{"Select Your Sex", "Female", "Male"};
    String[] speciality = new String[]{"Select your Specialty", "Internal diseases", "Cardiology", "Chest Diseases", "Infectious Diseases", "Neurology",
            "Child Health and Diseases", "Dermatology", "Radiology"};

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    Uri profileImage = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (((PatientHomePage) getActivity()).isExist("patientProfileToEditProfile")) {

            ll_editProfilePatient = view.findViewById(R.id.ll_editProfilePatient);
            img_editProfileFragmentProfile_Patient = view.findViewById(R.id.img_editProfileFragmentProfile_Patient);
            tv_editProfileFragmentName_Patient = view.findViewById(R.id.tv_editProfileFragmentName_Patient);
            tv_editProfileFragmentSurname_Patient = view.findViewById(R.id.tv_editProfileFragmentSurname_Patient);
            tv_editProfileFragmentHeight_Patient = view.findViewById(R.id.tv_editProfileFragmentHeight_Patient);
            tv_editProfileFragmentWeight_Patient = view.findViewById(R.id.tv_editProfileFragmentWeight_Patient);
            tv_editProfileFragmentMobileNo_Patient = view.findViewById(R.id.tv_editProfileFragmentMobileNo_Patient);
            tv_editProfileFragmentBirthdate_Patient = view.findViewById(R.id.tv_editProfileFragmentBirthdate_Patient);
            sp_editProfileFragmentCity_Patient = view.findViewById(R.id.sp_editProfileFragmentCity_Patient);
            sp_editProfileFragmentHealthSector_Patient = view.findViewById(R.id.sp_editProfileFragmentHealthSector_Patient);
            sp_editProfileFragmentMaritalStatus_Patient = view.findViewById(R.id.sp_editProfileFragmentMaritalStatus_Patient);
            sp_editProfileFragmentSex_Patient = view.findViewById(R.id.sp_editProfileFragmentSex_Patient);
            btn_editProfileFragment_Patient = view.findViewById(R.id.btn_editProfileFragment_Patient);
            pb_editProfileFragment_Patient = view.findViewById(R.id.pb_editProfileFragment_Patient);

            ArrayAdapter<String> sp_editProfileFragmentCity_PatientAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, cities);
            sp_editProfileFragmentCity_Patient.setAdapter(sp_editProfileFragmentCity_PatientAdapter);

            ArrayAdapter<String> sp_editProfileFragmentHealthSector_PatientAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, healthSector);
            sp_editProfileFragmentHealthSector_Patient.setAdapter(sp_editProfileFragmentHealthSector_PatientAdapter);


            ArrayAdapter<String> sp_editProfileFragmentMaritalStatus_PatientAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, maritalStatus);
            sp_editProfileFragmentMaritalStatus_Patient.setAdapter(sp_editProfileFragmentMaritalStatus_PatientAdapter);

            ArrayAdapter<String> sp_editProfileFragmentSex_PatientAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, sex);
            sp_editProfileFragmentSex_Patient.setAdapter(sp_editProfileFragmentSex_PatientAdapter);


            ll_editProfilePatient.setVisibility(View.VISIBLE);
            //set doctor's ll invisible

            pb_editProfileFragment_Patient.setVisibility(View.INVISIBLE);

            //Read data from database
            mDatabase.child("users").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {


                    String name = (String) task.getResult().child("name").getValue();
                    String surname = (String) task.getResult().child("surname").getValue();
                    int height = 0;
                    if (task.getResult().child("height").getValue() != null) {
                        height = Integer.parseInt(task.getResult().child("height").getValue().toString());
                    }
                    int weight = 0;
                    if (task.getResult().child("weight").getValue() != null) {
                        weight = Integer.parseInt(task.getResult().child("weight").getValue().toString());
                    }

                    String mobileNumber = (String) task.getResult().child("mobileNumber").getValue();
                    long birthdate = 0L;
                    if (task.getResult().child("birthdate").getValue() != null) {
                        birthdate = Integer.parseInt(task.getResult().child("birthdate").getValue().toString());
                        String date = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(birthdate * 1000));
                        tv_editProfileFragmentBirthdate_Patient.setText(date);
                    }

                    String currentCity = (String) task.getResult().child("currentCity").getValue();
                    String healthSector = (String) task.getResult().child("healthSector").getValue();
                    String maritalStatus = (String) task.getResult().child("maritalStatus").getValue();
                    String sex = (String) task.getResult().child("sex").getValue();
                    String profileImageURL = (String) task.getResult().child("profileImageURL").getValue();

                    if (profileImageURL != null) {
                        Picasso.get().load(profileImageURL).into(img_editProfileFragmentProfile_Patient);
                    }
                    if (currentCity != null) {
                        sp_editProfileFragmentCity_Patient.setSelection(sp_editProfileFragmentCity_PatientAdapter.getPosition(currentCity));
                    }
                    if (healthSector != null) {
                        sp_editProfileFragmentHealthSector_Patient.setSelection(sp_editProfileFragmentHealthSector_PatientAdapter.getPosition(healthSector));
                    }
                    if (maritalStatus != null) {
                        sp_editProfileFragmentMaritalStatus_Patient.setSelection(sp_editProfileFragmentMaritalStatus_PatientAdapter.getPosition(maritalStatus));
                    }
                    if (sex != null) {
                        sp_editProfileFragmentSex_Patient.setSelection(sp_editProfileFragmentSex_PatientAdapter.getPosition(sex));
                    }
                    if (name != null) {
                        tv_editProfileFragmentName_Patient.setText(name);
                    }
                    if (surname != null) {
                        tv_editProfileFragmentSurname_Patient.setText(surname);
                    }
                    tv_editProfileFragmentHeight_Patient.setText(height + "");
                    tv_editProfileFragmentWeight_Patient.setText(weight + "");
                    if (mobileNumber != null) {
                        tv_editProfileFragmentMobileNo_Patient.setText(mobileNumber);
                    }
                }
            });

            img_editProfileFragmentProfile_Patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GALLERY_REQUEST);
                }
            });

            btn_editProfileFragment_Patient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pb_editProfileFragment_Patient.setVisibility(View.VISIBLE);
                    btn_editProfileFragment_Patient.setVisibility(View.INVISIBLE);

                    if (!tv_editProfileFragmentName_Patient.getText().toString().isEmpty() &&
                            !tv_editProfileFragmentSurname_Patient.getText().toString().isEmpty() &&
                            !tv_editProfileFragmentHeight_Patient.getText().toString().isEmpty() &&
                            !tv_editProfileFragmentWeight_Patient.getText().toString().isEmpty() &&
                            !tv_editProfileFragmentMobileNo_Patient.getText().toString().isEmpty() &&
                            !tv_editProfileFragmentBirthdate_Patient.getText().toString().isEmpty() &&
                            sp_editProfileFragmentCity_Patient.getSelectedItemPosition() != 0 &&
                            sp_editProfileFragmentHealthSector_Patient.getSelectedItemPosition() != 0 &&
                            sp_editProfileFragmentMaritalStatus_Patient.getSelectedItemPosition() != 0 &&
                            sp_editProfileFragmentSex_Patient.getSelectedItemPosition() != 0) {

                        mDatabase.child("users").child(mAuth.getUid()).child("name").setValue(tv_editProfileFragmentName_Patient.getText().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("surname").setValue(tv_editProfileFragmentSurname_Patient.getText().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("height").setValue(tv_editProfileFragmentHeight_Patient.getText().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("weight").setValue(tv_editProfileFragmentWeight_Patient.getText().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("mobileNumber").setValue(tv_editProfileFragmentMobileNo_Patient.getText().toString());
                        String date = tv_editProfileFragmentBirthdate_Patient.getText().toString();
                        long epoch = 0;
                        try {
                            epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(date + " 01:00:00").getTime() / 1000;
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        mDatabase.child("users").child(mAuth.getUid()).child("birthdate").setValue(epoch);
                        mDatabase.child("users").child(mAuth.getUid()).child("currentCity").setValue(sp_editProfileFragmentCity_Patient.getSelectedItem().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("healthSector").setValue(sp_editProfileFragmentHealthSector_Patient.getSelectedItem().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("maritalStatus").setValue(sp_editProfileFragmentMaritalStatus_Patient.getSelectedItem().toString());
                        mDatabase.child("users").child(mAuth.getUid()).child("sex").setValue(sp_editProfileFragmentSex_Patient.getSelectedItem().toString());

                        if (profileImage != null) {
                            // Create a storage and storage reference from our app
                            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
                            StorageReference storageReference = firebaseStorage.getReference();

                            String userId = mAuth.getCurrentUser().getUid();
                            String imageName = "profile_images/" + userId + ".png";
                            storageReference.child(imageName).putFile(profileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    //Download url
                                    StorageReference newReference = firebaseStorage.getReference(imageName);
                                    newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            String downloadUri = uri.toString();
                                            mDatabase.child("users").child(mAuth.getUid()).child("profileImageURL").setValue(downloadUri);

                                        }
                                    });

                                }
                            });

                        }

                    } else {
                        Toast.makeText(getActivity(), "Please fill all areas!", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getActivity(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
                    pb_editProfileFragment_Patient.setVisibility(View.INVISIBLE);
                    btn_editProfileFragment_Patient.setVisibility(View.VISIBLE);
                }
            });


        } else if (((PatientHomePage) getActivity()).isExist("doctorProfileToEditProfile")) {

        }


        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                // ((SubmittedCardsActivity) getActivity()).popBackStack("submittedToFeedback", FragmentManager.POP_BACK_STACK_INCLUSIVE);

                //if user type is patient remove that fragment
                if (((PatientHomePage) getActivity()).isExist("patientProfileToEditProfile")) {

                    ((PatientHomePage) getActivity()).removeFragments("patientProfileToEditProfile");

                    //if user type is doctor remove that fragment
                } else if (((PatientHomePage) getActivity()).isExist("doctorProfileToEditProfile")) {

                    ((PatientHomePage) getActivity()).removeFragments("doctorProfileToEditProfile");
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {

            profileImage = data.getData();

            if (((PatientHomePage) getActivity()).isExist("patientProfileToEditProfile")) {

                img_editProfileFragmentProfile_Patient.setImageURI(profileImage);

                //if user type is doctor remove that fragment
            } else if (((PatientHomePage) getActivity()).isExist("doctorProfileToEditProfile")) {

                img_editProfileFragmentProfile_Doctor.setImageURI(profileImage);
            }

        }
    }


}