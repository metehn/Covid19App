package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Patient.PatientHomePage;
import com.metehanersoy.covid19app.R;

public class TravelnfoFragment extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    AppCompatButton btn_travelInfoFragment;
    Spinner spn_travelInfoFragment;

    RadioButton rd_beenToSCTravelInfoYes, rd_beenToSCTravelInfoNo,
            rd_beenAbroadYes, rd_beenAbroadNo,
            rd_beenInContactWithSbFromAbroadYes, rd_beenInContactWithSbFromAbroadNo,
            rd_beenToHealthCenterYes, rd_beenToHealthCenterNo,
            rd_beenInContactWithCovidPotientalYes, rd_beenInContactWithCovidPotientalNo;

    RadioGroup rdg_beenToSCTravelInfoFragment, rdg_beenAbroadFragment, rdg_beenInContactWithSbFromAbroadFragment,
            rdg_beenToHealthCenterFragment, rdg_beenInContactWithCovidPotientalFragment;

    String[] countries = new String[]{"Select Your Country", "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla",
            "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam",
            "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands",
            "Central African Republic", "Chad", "Chile", "China, People's republic of", "Christmas Island", "Cocos (Keeling) Islands", "Colombia",
            "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
            "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
            "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia",
            "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana",
            "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
            "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kosovo", "Kuwait",
            "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Palestine", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda",
            "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino",
            "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore",
            "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
            "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon",
            "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic",
            "Taiwan", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Tibet", "Togo", "Tokelau", "Tonga",
            "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine",
            "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
            "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_travelnfo, container, false);

        rd_beenToSCTravelInfoYes = view.findViewById(R.id.rd_beenToSCTravelInfoYes);
        rd_beenToSCTravelInfoNo = view.findViewById(R.id.rd_beenToSCTravelInfoNo);
        rd_beenAbroadYes = view.findViewById(R.id.rd_beenAbroadYes);
        rd_beenAbroadNo = view.findViewById(R.id.rd_beenAbroadNo);
        rd_beenInContactWithSbFromAbroadYes = view.findViewById(R.id.rd_beenInContactWithSbFromAbroadYes);
        rd_beenInContactWithSbFromAbroadNo = view.findViewById(R.id.rd_beenInContactWithSbFromAbroadNo);
        rd_beenToHealthCenterYes = view.findViewById(R.id.rd_beenToHealthCenterYes);
        rd_beenToHealthCenterNo = view.findViewById(R.id.rd_beenToHealthCenterNo);
        rd_beenInContactWithCovidPotientalYes = view.findViewById(R.id.rd_beenInContactWithCovidPotientalYes);
        rd_beenInContactWithCovidPotientalNo = view.findViewById(R.id.rd_beenInContactWithCovidPotientalNo);

        rdg_beenToSCTravelInfoFragment = view.findViewById(R.id.rdg_beenToSCTravelInfoFragment);
        rdg_beenAbroadFragment = view.findViewById(R.id.rdg_beenAbroadFragment);
        rdg_beenInContactWithSbFromAbroadFragment = view.findViewById(R.id.rdg_beenInContactWithSbFromAbroadFragment);
        rdg_beenToHealthCenterFragment = view.findViewById(R.id.rdg_beenToHealthCenterFragment);
        rdg_beenInContactWithCovidPotientalFragment = view.findViewById(R.id.rdg_beenInContactWithCovidPotientalFragment);

        btn_travelInfoFragment = view.findViewById(R.id.btn_travelInfoFragment);
        spn_travelInfoFragment = view.findViewById(R.id.spn_travelInfoFragment);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, countries);
        spn_travelInfoFragment.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                // ((SubmittedCardsActivity) getActivity()).popBackStack("submittedToFeedback", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ((PatientHomePage) getActivity()).removeFragments("patientProfileToTravelInfo");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        mDatabase.child("travel_info").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    if (task.getResult().child("beenToSCInLast14Days").getValue() != null) {
                        boolean beenToSCInLast14Days = (boolean) task.getResult().child("beenToSCInLast14Days").getValue();
                        if (beenToSCInLast14Days) {
                            rd_beenToSCTravelInfoYes.setChecked(true);
                        } else {
                            rd_beenToSCTravelInfoNo.setChecked(true);
                        }
                    }

                    if (task.getResult().child("beenAbroadInLast14Days").getValue() != null) {

                        boolean beenAbroadInLast14Days = (boolean) task.getResult().child("beenAbroadInLast14Days").getValue();
                        if (beenAbroadInLast14Days) {
                            rd_beenAbroadYes.setChecked(true);
                        } else {
                            rd_beenAbroadNo.setChecked(true);
                        }
                    }

                    if (task.getResult().child("country").getValue() != null) {
                        String country = (String) task.getResult().child("country").getValue();
                        spn_travelInfoFragment.setSelection(adapter.getPosition(country));
                    }

                    if (task.getResult().child("beenInContactWithSbFromAbroadInLast14Days").getValue() != null) {

                        boolean beenInContactWithSbFromAbroadInLast14Days = (boolean) task.getResult().child("beenInContactWithSbFromAbroadInLast14Days").getValue();
                        if(beenInContactWithSbFromAbroadInLast14Days){
                            rd_beenInContactWithSbFromAbroadYes.setChecked(true);
                        }else{
                            rd_beenInContactWithSbFromAbroadNo.setChecked(true);
                        }
                    }
                    if (task.getResult().child("beenToHealthCenterInLast14Days").getValue() != null) {

                        boolean beenToHealthCenterInLast14Days = (boolean) task.getResult().child("beenToHealthCenterInLast14Days").getValue();
                        if(beenToHealthCenterInLast14Days){
                            rd_beenToHealthCenterYes.setChecked(true);
                        }else{
                            rd_beenToHealthCenterNo.setChecked(true);
                        }
                    }
                    if (task.getResult().child("beenInContactWithCovidPotientalPaitent").getValue() != null) {

                        boolean beenInContactWithCovidPotientalPaitent = (boolean) task.getResult().child("beenInContactWithCovidPotientalPaitent").getValue();
                        if(beenInContactWithCovidPotientalPaitent){
                            rd_beenInContactWithCovidPotientalYes.setChecked(true);
                        }else{
                            rd_beenInContactWithCovidPotientalNo.setChecked(true);
                        }
                    }


                }
            }
        });

        btn_travelInfoFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rdg_beenToSCTravelInfoFragment.getCheckedRadioButtonId() != -1 &&
                        rdg_beenAbroadFragment.getCheckedRadioButtonId() != -1 &&
                        rdg_beenInContactWithSbFromAbroadFragment.getCheckedRadioButtonId() != -1 &&
                        rdg_beenToHealthCenterFragment.getCheckedRadioButtonId() != -1 &&
                        rdg_beenInContactWithCovidPotientalFragment.getCheckedRadioButtonId() != -1 &&
                        spn_travelInfoFragment.getSelectedItemPosition() != 0) {
                    boolean beenToSCInLast14Days = rd_beenToSCTravelInfoYes.isChecked();
                    boolean beenAbroadInLast14Days = rd_beenAbroadYes.isChecked();
                    String country = spn_travelInfoFragment.getSelectedItem().toString();
                    boolean beenInContactWithSbFromAbroadInLast14Days = rd_beenInContactWithSbFromAbroadYes.isChecked();
                    boolean beenToHealthCenterInLast14Days = rd_beenToHealthCenterYes.isChecked();
                    boolean beenInContactWithCovidPotientalPaitent = rd_beenInContactWithCovidPotientalYes.isChecked();


                    mDatabase.child("travel_info").child(mAuth.getUid()).child("beenToSCInLast14Days").setValue(beenToSCInLast14Days);
                    mDatabase.child("travel_info").child(mAuth.getUid()).child("beenAbroadInLast14Days").setValue(beenAbroadInLast14Days);
                    mDatabase.child("travel_info").child(mAuth.getUid()).child("country").setValue(country);
                    mDatabase.child("travel_info").child(mAuth.getUid()).child("beenInContactWithSbFromAbroadInLast14Days").setValue(beenInContactWithSbFromAbroadInLast14Days);
                    mDatabase.child("travel_info").child(mAuth.getUid()).child("beenToHealthCenterInLast14Days").setValue(beenToHealthCenterInLast14Days);
                    mDatabase.child("travel_info").child(mAuth.getUid()).child("beenInContactWithCovidPotientalPaitent").setValue(beenInContactWithCovidPotientalPaitent);

                    Toast.makeText(getActivity(), "Updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Please fill all areas", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return view;


    }
}