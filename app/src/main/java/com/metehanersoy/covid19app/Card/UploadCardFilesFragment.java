package com.metehanersoy.covid19app.Card;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metehanersoy.covid19app.Class.Doctor;
import com.metehanersoy.covid19app.Class.Hospital;
import com.metehanersoy.covid19app.Class.User;
import com.metehanersoy.covid19app.Fragment.SuggestionFragment;
import com.metehanersoy.covid19app.R;

import java.util.HashMap;
import java.util.UUID;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UploadCardFilesFragment extends Fragment {

    public static final int GALLERY_REQUEST_LEFT_EAR = 1;
    public static final int GALLERY_REQUEST_RIGHT_EAR = 2;
    public static final int GALLERY_REQUEST_THROAT = 3;
    public static final int GALLERY_REQUEST_FACE = 4;

    LinearLayout lv_LeftEarPhoto, lv_RightEarPhoto, lv_ThroatPhoto, lv_FaceRecording;
    ImageView img_LeftEarPhoto, img_RightEarPhoto, img_ThroatPhoto, img_FaceRecording;
    AppCompatButton btn_uploadCardFiles;

    Uri leftEarPhoto, rightEarPhoto, throatPhoto, faceRecording;
    String leftEarImageURL, rightEarImageURL, throatImageURL, faceRecordingURL;

    private FirebaseAuth mAuth;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DatabaseReference mDatabase;

    HashMap<String, String> observedSymptoms = new HashMap<>();
    Doctor doctor;

    long currentdate = System.currentTimeMillis();
    String doctorId, patientId, doctorName;
    String feedback = "";
    String status = "";
    int scoreBasedCase;
    double probability, bodyTemperature;
    int age, sex, hasLossOfSmellAndTaste, hasInappetence, hasFever, hasPersistentCough, hasFatigue, hasDiarrhea, hasAbdominalPain;
    int severehasPersistentCough, severehasFatigue;

    // Location request is a config file for all settings related to FusedLocationProviderClient
    LocationRequest locationRequest;

    // Google's API for location services. The majority of the app functions using this class.
    FusedLocationProviderClient fusedLocationProviderClient;

    LocationCallback locationCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_card_files, container, false);

        lv_LeftEarPhoto = view.findViewById(R.id.lv_LeftEarPhoto);
        lv_RightEarPhoto = view.findViewById(R.id.lv_RightEarPhoto);
        lv_ThroatPhoto = view.findViewById(R.id.lv_ThroatPhoto);
        lv_FaceRecording = view.findViewById(R.id.lv_FaceRecording);
        img_LeftEarPhoto = view.findViewById(R.id.img_LeftEarPhoto);
        img_RightEarPhoto = view.findViewById(R.id.img_RightEarPhoto);
        img_ThroatPhoto = view.findViewById(R.id.img_ThroatPhoto);
        img_FaceRecording = view.findViewById(R.id.img_FaceRecording);
        btn_uploadCardFiles = view.findViewById(R.id.btn_uploadCardFiles);

        img_LeftEarPhoto.setVisibility(View.INVISIBLE);
        img_RightEarPhoto.setVisibility(View.INVISIBLE);
        img_ThroatPhoto.setVisibility(View.INVISIBLE);
        img_FaceRecording.setVisibility(View.INVISIBLE);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Create a storage and storage reference from our app
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        //Create database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getArguments();

        mDatabase.child("users").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {

                } else {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists()) {



                        sex =   dataSnapshot.child("sex").getValue().equals("Male") ? 1 : 0;
                        long birthdate = (long) dataSnapshot.child("birthdate").getValue();

                        age = getYearsBetweenDates(new java.util.Date(birthdate * 1000), new java.util.Date(currentdate));
                    }
                    Log.e("Covid19App", String.valueOf(task.getResult().getValue()));
                }
            }
        });


        lv_LeftEarPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_LEFT_EAR);
            }
        });

        lv_RightEarPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_RIGHT_EAR);
            }
        });

        lv_ThroatPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_THROAT);
            }
        });

        lv_FaceRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_REQUEST_FACE);
            }
        });


        btn_uploadCardFiles.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                if (leftEarPhoto != null && rightEarPhoto != null && throatPhoto != null && faceRecording != null) {

/*
                    leftEarImageURL = uploadImage("denemeCardImages/" + UUID.randomUUID().toString(), leftEarPhoto)[0];
                    rightEarImageURL = uploadImage("denemeCardImages/" + UUID.randomUUID().toString(), rightEarPhoto)[0];
                    throatImageURL = uploadImage("denemeCardImages/" + UUID.randomUUID().toString(), throatPhoto)[0];
                    faceRecordingURL = uploadImage("denemeCardImages/" + UUID.randomUUID().toString(), faceRecording)[0];
*/
                    // +    examinationCardId: String(PK)
                    // +    patientId : String(FK)
                    // +    symptomsId : String(FK)
                    // bundle    bodyTemperature : Double
                    // bundle    doctorName: String
                    // bundle doctor   hospitalName: String
                    // +    leftEarImageURL: String
                    // +    rightEarImageURL: String
                    // +    faceRecordingURL : String
                    // +    throatImageURL : String
                    //     date:  Double(timestamp)
                    //     probability: String
                    //     feedback: String
                    //     scoreBasedCase: Int
                    patientId = mAuth.getUid();
                    String uuid = UUID.randomUUID().toString();
                    String examinationCard_observedSymptomsId = uuid; // examination card id ile observed symptoms id aynı olacak
                    String symptomsId = uuid;
                    currentdate = System.currentTimeMillis();
                    if (bundle != null) {

                        observedSymptoms = (HashMap<String, String>) bundle.getSerializable("observedSymptoms");
                        bodyTemperature = bundle.getDouble("bodyTemperature", -1.0);
                        doctor = (Doctor) bundle.getSerializable("doctor");
                        doctorName = doctor.getName();
                        doctorId = doctor.getDoctorId();

                        hasLossOfSmellAndTaste = observedSymptoms.get("hasLossOfSmellAndTaste").equals("N.O.") ? 0 : 1;
                        severehasPersistentCough = observedSymptoms.get("hasPersistentCough").equals("Severe") ? 1 : 0;
                        severehasFatigue = observedSymptoms.get("hasFatigue").equals("Severe") ? 1 : 0;
                        hasInappetence = observedSymptoms.get("hasInappetence").equals("N.O.") ? 0 : 1;
                        hasFever = observedSymptoms.get("hasFever").equals("N.O.") ? 0 : 1;
                        hasPersistentCough = observedSymptoms.get("hasPersistentCough").equals("N.O.") ? 0 : 1;
                        hasFatigue = observedSymptoms.get("hasFatigue").equals("N.O.") ? 0 : 1;
                        hasDiarrhea = observedSymptoms.get("hasDiarrhea").equals("N.O.") ? 0 : 1;
                        hasAbdominalPain = observedSymptoms.get("hasAbdominalPain").equals("N.O.") ? 0 : 1;

                        probability = calculateProbability(age, sex, hasLossOfSmellAndTaste,
                                severehasPersistentCough, severehasFatigue, hasInappetence
                                , hasFever, hasPersistentCough, hasFatigue, hasDiarrhea, hasAbdominalPain);

                    }
                    probability *= 100;
                    if (probability  < 50) {
                        scoreBasedCase = 1;
                        status = "Riskless";

                    } else if (probability>= 50 && probability < 85) {
                        scoreBasedCase = 2;
                        status = "Riskless";
                    } else {
                        scoreBasedCase = 3;
                        status = "Riskless";
                    }

                    long lastUpdate = System.currentTimeMillis() / 1000; //seconds

                    //update user lastUpdate and status
                    mDatabase.child("users").child(mAuth.getUid()).child("lastUpdate").setValue(lastUpdate);
                    mDatabase.child("users").child(mAuth.getUid()).child("status").setValue(status);

                    //if probability >=50 add him to location table
                    if (probability >= 50) {

                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

                        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                //we got permission so put values of location in to UI components.
                                if (location != null) {

                                    mDatabase.child("locations").child(mAuth.getUid()).child("date").setValue(System.currentTimeMillis() / 1000);
                                    mDatabase.child("locations").child(mAuth.getUid()).child("latitude").setValue(location.getLatitude());
                                    mDatabase.child("locations").child(mAuth.getUid()).child("longitude").setValue(location.getLongitude());
                                }
                            }
                        });

                    }

                    //Examination Card update
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("patientId").setValue(patientId);
                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("symptomsId").setValue(uuid);
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("bodyTemperature").setValue(bodyTemperature);
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("doctorName").setValue(doctorName);
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("hospitalName").setValue(doctor.getHospitalName());
                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);


                    UUID leftEarImageUUID  = UUID.randomUUID();
                    uploadImage2("card_attachments/" + leftEarImageUUID.toString(), leftEarPhoto, uuid, "leftEarImageURL");
                    /*
                    storageReference.child("card_attachments/" + leftEarImageUUID.toString()).putFile(leftEarPhoto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Download url
                            StorageReference newReference = firebaseStorage.getReference("card_attachments/"+ leftEarImageUUID.toString());
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUri = uri.toString();

                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(downloadUri);
                                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue("");
                                }
                            });
                        }
                    });
                    */
                    UUID rightEarImageUUID  = UUID.randomUUID();
                    uploadImage2("card_attachments/" + rightEarImageUUID.toString(), rightEarPhoto, uuid, "rightEarImageURL");
                    /*
                    storageReference.child("card_attachments/" + rightEarImageUUID.toString()).putFile(rightEarPhoto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Download url
                            StorageReference newReference = firebaseStorage.getReference("card_attachments/"+ rightEarImageUUID.toString());
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUri = uri.toString();

                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("rightEarImageURL").setValue(downloadUri);
                                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("rightEarImageURL").setValue("");
                                }
                            });
                        }
                    });
                   */
                    UUID throatImageUUID  = UUID.randomUUID();
                    uploadImage2("card_attachments/" + throatImageUUID.toString(), throatPhoto, uuid, "throatImageURL");
                    /*
                    storageReference.child("card_attachments/" + throatImageUUID.toString()).putFile(throatPhoto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Download url
                            StorageReference newReference = firebaseStorage.getReference("card_attachments/"+ throatImageUUID.toString());
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUri = uri.toString();

                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("throatImageURL").setValue(downloadUri);
                                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("throatImageURL").setValue("");
                                }
                            });
                        }
                    });
                    */
                    UUID faceRecordingUUID  = UUID.randomUUID();
                    uploadImage2("card_attachments/" + faceRecordingUUID.toString(), faceRecording, uuid, "faceRecordingURL");
                    /*
                    storageReference.child("card_attachments/" + faceRecordingUUID.toString()).putFile(faceRecording).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Download url
                            StorageReference newReference = firebaseStorage.getReference("card_attachments/"+ faceRecordingUUID.toString());
                            newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUri = uri.toString();

                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("faceRecordingURL").setValue(downloadUri);
                                    //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("faceRecordingURL").setValue("");
                                }
                            });
                        }
                    });
                       */
                   mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("date").setValue(System.currentTimeMillis() / 1000);
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("probability").setValue(probability);
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("feedback").setValue("");
                    mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("scoreBasedCase").setValue(scoreBasedCase);

                    //observed symptoms update
                    mDatabase.child("observed_symptoms").child(mAuth.getUid()).child(uuid).setValue(observedSymptoms);

                    // review request update
                    mDatabase.child("review_requests").child(doctorId).child(uuid).setValue(mAuth.getUid());

                    //intent to suggestion fragment
                    Bundle bundleSuggestion = new Bundle();
                    bundleSuggestion.putInt("scoreBasedCase", scoreBasedCase);
                    bundleSuggestion.putDouble("probability", probability);

                    SuggestionFragment suggestionFragment = new SuggestionFragment();

                    suggestionFragment.setArguments(bundleSuggestion);


                    ((CardActivity) getActivity()).replaceFragments(suggestionFragment);

                    //mDatabase.child("examination_cards").child(patientId).child(" 1").setValue(postData).addOnSuccessListener(new OnSuccessListener<Void>() {


                } else {

                    Toast.makeText(getActivity(), "Please Upload All Files !", Toast.LENGTH_SHORT).show();
                }

            }


        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null) {

            switch (requestCode) {

                case GALLERY_REQUEST_LEFT_EAR:

                    leftEarPhoto = data.getData();
                    img_LeftEarPhoto.setVisibility(View.VISIBLE);
                    break;
                case GALLERY_REQUEST_RIGHT_EAR:

                    rightEarPhoto = data.getData();
                    img_RightEarPhoto.setVisibility(View.VISIBLE);
                    break;
                case GALLERY_REQUEST_THROAT:

                    throatPhoto = data.getData();
                    img_ThroatPhoto.setVisibility(View.VISIBLE);
                    break;
                case GALLERY_REQUEST_FACE:

                    faceRecording = data.getData();
                    img_FaceRecording.setVisibility(View.VISIBLE);
                    break;

            }

        }
    }





    public void uploadImage2(String imageName, Uri image, String uuid, String child){

        String url;

        storageReference.child(imageName).putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //Download url
                StorageReference newReference = firebaseStorage.getReference(imageName);
                newReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUri = uri.toString();

                        mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child(child).setValue(downloadUri);
                        //mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child("leftEarImageURL").setValue(leftEarImageURL);

                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mDatabase.child("examination_cards").child(mAuth.getUid()).child(uuid).child(child).setValue("");
                    }
                });
            }
        });

    }



    public static int getYearsBetweenDates(Date first, Date second) {
        Calendar firstCal = GregorianCalendar.getInstance();
        Calendar secondCal = GregorianCalendar.getInstance();

        firstCal.setTime(first);
        secondCal.setTime(second);

        secondCal.add(Calendar.DAY_OF_YEAR, 1 - firstCal.get(Calendar.DAY_OF_YEAR));

        return secondCal.get(Calendar.YEAR) - firstCal.get(Calendar.YEAR);
    }

    public double calculateProbability(int age, int sex, int losssmelltaste, int severepersistentcough, int severefatigue, int skippedmeals
            , int fever, int percistentcou, int fatigue, int diarre, int abdominalpain) {


        double model1 = -1.32 - (0.01 * age) + (0.44 * sex) + (1.75 * losssmelltaste)
                + (0.31 * severepersistentcough) + (0.49 * severefatigue)
                + (0.39 * skippedmeals);

        Double model2 = -2.30 + (0.01 * age) - (0.24 * sex) + (1.16 * losssmelltaste)
                + (0.76 * fever) + (0.33 * percistentcou) + (0.25 * fatigue)
                + (0.31 * diarre) + (0.46 * skippedmeals) - (0.48 * abdominalpain);

        double exp1 = Math.exp(model1) / (1 + Math.exp(model1));
        double exp2 = Math.exp(model2) / (1 + Math.exp(model2));
        return (exp1 + exp2) / 2;
    }


}