package com.metehanersoy.covid19app.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.metehanersoy.covid19app.LogIn;
import com.metehanersoy.covid19app.R;

import java.util.HashMap;
import java.util.UUID;


public class AdminHomePage extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_home_page);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        //Create database

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    public void deneme(View view){
        //Add hotels
        UUID uuid =  UUID.randomUUID();
        String hospitalName = "Gazimağusa Devlet Hastanesi";
        String hospitalImageURL  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FGazima%C4%9Fusa%20Devlet%20Hastanesi.jpg?alt=media&token=91d69079-10a6-46bc-bca0-33297f3e1511";
        double latitude  = 35.154975902262784;
        double longitude = 33.90425819931936;
        int currentCases = 10;
        int criticalCases = 2;
        int deaths = 1;
        int totalCases = currentCases + deaths;
        int recovered = 50;
        HashMap<String,Object> postData = new HashMap<>();
        postData.put("hospitalName",hospitalName);
        postData.put("hospitalImageURL",hospitalImageURL);
        postData.put("latitude",latitude);
        postData.put("longitude",longitude);
        postData.put("currentCases",currentCases);
        postData.put("criticalCases",criticalCases);
        postData.put("deaths",deaths);
        postData.put("totalCases",totalCases);
        postData.put("recovered",recovered);
        mDatabase.child("hospitals").child(""+uuid).setValue(postData);

        UUID uuid2 =  UUID.randomUUID();
        String hospitalName2 = "Cengiz Topel Hastanesi";
        String hospitalImageURL2  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FCengiz%20Topel%20Hastanesi.jpg?alt=media&token=ed25e1c6-f8b8-492f-935c-fe3a554e4ed7";
        double latitude2  = 35.15800506475584;
        double longitude2 = 32.86828763068847;
        int currentCases2 = 4;
        int criticalCases2 = 2;
        int deaths2 = 1;
        int totalCases2 = currentCases2 + deaths2;
        int recovered2 = 10;
        HashMap<String,Object> postData2 = new HashMap<>();
        postData2.put("hospitalName",hospitalName2);
        postData2.put("hospitalImageURL",hospitalImageURL2);
        postData2.put("latitude",latitude2);
        postData2.put("longitude",longitude2);
        postData2.put("currentCases",currentCases2);
        postData2.put("criticalCases",criticalCases2);
        postData2.put("deaths",deaths2);
        postData2.put("totalCases",totalCases2);
        postData2.put("recovered",recovered2);
        mDatabase.child("hospitals").child(""+uuid2).setValue(postData2);

        UUID uuid3 =  UUID.randomUUID();
        String hospitalName3 = "Cyprus Central Hospital";
        String hospitalImageURL3  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FCyprus%20Central%20Hospital.jpg?alt=media&token=8632d960-a48e-4750-85bd-1603a4b0ae29";
        double latitude3  = 35.12768322779706;
        double longitude3 = 33.92581105143355;
        int currentCases3 = 13;
        int criticalCases3 = 3;
        int deaths3 = 2;
        int totalCases3 = currentCases3 + deaths3;
        int recovered3 = 20;
        HashMap<String,Object> postData3 = new HashMap<>();
        postData3.put("hospitalName",hospitalName3);
        postData3.put("hospitalImageURL",hospitalImageURL3);
        postData3.put("latitude",latitude3);
        postData3.put("longitude",longitude3);
        postData3.put("currentCases",currentCases3);
        postData3.put("criticalCases",criticalCases3);
        postData3.put("deaths",deaths3);
        postData3.put("totalCases",totalCases3);
        postData3.put("recovered",recovered3);
        mDatabase.child("hospitals").child(""+uuid3).setValue(postData3);

        UUID uuid4 =  UUID.randomUUID();
        String hospitalName4 = "Dr. Burhan Nalbantoğlu Devlet Hastanesi";
        String hospitalImageURL4  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FDr.%20Burhan%20Nalbanto%C4%9Flu%20Devlet%20Hastanesi.jpg?alt=media&token=448f8c78-c620-4af1-8424-9da8352e980e";
        double latitude4  = 35.20533160801963;
        double longitude4 = 33.33077548212417;
        int currentCases4 = 16;
        int criticalCases4 = 3;
        int deaths4 = 5;
        int totalCases4 = currentCases4 + deaths4;
        int recovered4 = 10;
        HashMap<String,Object> postData4 = new HashMap<>();
        postData4.put("hospitalName",hospitalName4);
        postData4.put("hospitalImageURL",hospitalImageURL4);
        postData4.put("latitude",latitude4);
        postData4.put("longitude",longitude4);
        postData4.put("currentCases",currentCases4);
        postData4.put("criticalCases",criticalCases4);
        postData4.put("deaths",deaths4);
        postData4.put("totalCases",totalCases4);
        postData4.put("recovered",recovered4);
        mDatabase.child("hospitals").child(""+uuid4).setValue(postData4);

        UUID uuid5 =  UUID.randomUUID();
        String hospitalName5 = "Dr.Suat Günsel Girne Üniversitesi Hastenesi";
        String hospitalImageURL5  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FDr.Suat%20G%C3%BCnsel%20Girne%20%C3%9Cniversitesi%20Hastenesi.jpg?alt=media&token=a9d5641e-2f52-40f2-b2d6-671833b9fc08";
        double latitude5  = 35.33621291564981;
        double longitude5 = 33.34036671948008;
        int currentCases5 = 20;
        int criticalCases5 = 6;
        int deaths5 = 6;
        int totalCases5 = currentCases5 + deaths5;
        int recovered5 = 60;
        HashMap<String,Object> postData5 = new HashMap<>();
        postData5.put("hospitalName",hospitalName5);
        postData5.put("hospitalImageURL",hospitalImageURL5);
        postData5.put("latitude",latitude5);
        postData5.put("longitude",longitude5);
        postData5.put("currentCases",currentCases5);
        postData5.put("criticalCases",criticalCases5);
        postData5.put("deaths",deaths5);
        postData5.put("totalCases",totalCases5);
        postData5.put("recovered",recovered5);
        mDatabase.child("hospitals").child(""+uuid5).setValue(postData5);

        UUID uuid6 =  UUID.randomUUID();
        String hospitalName6 = "Mağusa Yaşam Hastanesi";
        String hospitalImageURL6  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FMa%C4%9Fusa%20Ya%C5%9Fam%20Hastanesi.jpg?alt=media&token=120a87d8-7898-4c99-bd27-aebcc8497b7c";
        double latitude6  = 35.12721390289727;
        double longitude6 = 33.92439066863019;
        int currentCases6 = 30;
        int criticalCases6 = 4;
        int deaths6 = 7;
        int totalCases6 = currentCases6 + deaths6;
        int recovered6 = 80;
        HashMap<String,Object> postData6 = new HashMap<>();
        postData6.put("hospitalName",hospitalName6);
        postData6.put("hospitalImageURL",hospitalImageURL6);
        postData6.put("latitude",latitude6);
        postData6.put("longitude",longitude6);
        postData6.put("currentCases",currentCases6);
        postData6.put("criticalCases",criticalCases6);
        postData6.put("deaths",deaths6);
        postData6.put("totalCases",totalCases6);
        postData6.put("recovered",recovered6);
        mDatabase.child("hospitals").child(""+uuid6).setValue(postData6);

        UUID uuid7 =  UUID.randomUUID();
        String hospitalName7 = "Özel Girne Hastanesi";
        String hospitalImageURL7  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2F%C3%96zel%20Girne%20Hastanesi.jpg?alt=media&token=9981848c-a34d-4b5a-bf6f-5c4c9248052d";
        double latitude7  = 35.33387027870814;
        double longitude7 = 33.32706273794725;
        int currentCases7 = 13;
        int criticalCases7 = 4;
        int deaths7 = 4;
        int totalCases7 = currentCases7 + deaths7;
        int recovered7 = 40;
        HashMap<String,Object> postData7 = new HashMap<>();
        postData7.put("hospitalName",hospitalName7);
        postData7.put("hospitalImageURL",hospitalImageURL7);
        postData7.put("latitude",latitude7);
        postData7.put("longitude",longitude7);
        postData7.put("currentCases",currentCases7);
        postData7.put("criticalCases",criticalCases7);
        postData7.put("deaths",deaths7);
        postData7.put("totalCases",totalCases7);
        postData7.put("recovered",recovered7);
        mDatabase.child("hospitals").child(""+uuid7).setValue(postData7);

        UUID uuid8 =  UUID.randomUUID();
        String hospitalName8 = "Yakın Doğu Üniversitesi Hastanesi";
        String hospitalImageURL8  = "https://firebasestorage.googleapis.com/v0/b/coguard-231be.appspot.com/o/hospital_images%2FYak%C4%B1n%20Do%C4%9Fu%20%C3%9Cniversitesi%20Hastanesi.jpg?alt=media&token=51736dff-de3b-4cc1-9d5f-bb9d676b5e78";
        double latitude8  = 35.22925652557336;
        double longitude8 = 33.3197789525054;
        int currentCases8 = 20;
        int criticalCases8 = 7;
        int deaths8 = 8;
        int totalCases8 = currentCases8 + deaths8;
        int recovered8 = 90;
        HashMap<String,Object> postData8 = new HashMap<>();
        postData8.put("hospitalName",hospitalName8);
        postData8.put("hospitalImageURL",hospitalImageURL8);
        postData8.put("latitude",latitude8);
        postData8.put("longitude",longitude8);
        postData8.put("currentCases",currentCases8);
        postData8.put("criticalCases",criticalCases8);
        postData8.put("deaths",deaths8);
        postData8.put("totalCases",totalCases8);
        postData8.put("recovered",recovered8);
        mDatabase.child("hospitals").child(""+uuid8).setValue(postData8);

    }

    public void adminLogOutClicked(View view){

        mAuth.signOut();
        startActivity(new Intent(AdminHomePage.this, LogIn.class));
        finish();
    }
}