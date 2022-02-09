package com.metehanersoy.covid19app.Fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class AttachmentsFragment extends Fragment {

    VideoView vd_attachmentsFragment;
    ImageView img_attachmentsFragment;
    FrameLayout fl_attachmentsFragment;
    LinearLayout ll_leftEarPhotoAttachmentsFragment, ll_rightEarPhotoAttachmentsFragment, ll_throatPhotoAttachmentsFragment, ll_faceRecordAttachmentsFragment;

    String leftEarImageURL = "", rightEarImageURL= "", throatImageURL= "", faceRecordingURL= "";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    Bundle bundle;
    String cardId;
    String userType;
    String userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attachments, container, false);

        vd_attachmentsFragment = view.findViewById(R.id.vd_attachmentsFragment);
        img_attachmentsFragment = view.findViewById(R.id.img_attachmentsFragment);
        fl_attachmentsFragment = view.findViewById(R.id.fl_attachmentsFragment);
        ll_leftEarPhotoAttachmentsFragment = view.findViewById(R.id.ll_leftEarPhotoAttachmentsFragment);
        ll_rightEarPhotoAttachmentsFragment = view.findViewById(R.id.ll_rightEarPhotoAttachmentsFragment);
        ll_throatPhotoAttachmentsFragment = view.findViewById(R.id.ll_throatPhotoAttachmentsFragment);
        ll_faceRecordAttachmentsFragment = view.findViewById(R.id.ll_faceRecordAttachmentsFragment);

        //Initialize FriebaseAuth
        mAuth = FirebaseAuth.getInstance();
        //Create database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {

                if(userType != null && userType.equals("Doctor")){
                    ((CardRequestsActivity) getActivity()).removeFragments("submittedToAttachments");
                }else{
                    ((SubmittedCardsActivity) getActivity()).removeFragments("submittedToAttachments");
                }


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


        bundle = this.getArguments();

        if (bundle != null) {
            userType = bundle.getString("userType","null");
            cardId = bundle.getString("ExaminationCardId","null");
            userId = bundle.getString("userId",mAuth.getUid()) ;

            mDatabase.child("examination_cards").child(userId).child(cardId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    if( dataSnapshot.child("leftEarImageURL").getValue() != null){

                        leftEarImageURL = (String) dataSnapshot.child("leftEarImageURL").getValue();
                    }
                    if( dataSnapshot.child("rightEarImageURL").getValue() != null){

                        rightEarImageURL = (String) dataSnapshot.child("rightEarImageURL").getValue();
                    }
                    if(dataSnapshot.child("throatImageURL").getValue() != null){

                        throatImageURL = (String) dataSnapshot.child("throatImageURL").getValue();
                    }
                    if(dataSnapshot.child("faceRecordingURL").getValue() != null){

                        faceRecordingURL = (String) dataSnapshot.child("faceRecordingURL").getValue();

                    }





                    img_attachmentsFragment.setVisibility(View.INVISIBLE);
                    vd_attachmentsFragment.setVisibility(View.INVISIBLE);

                    // Uri object to refer the
                    // resource from the videoUrl
                    Uri uri = Uri.parse(faceRecordingURL);

                    // sets the resource from the
                    // videoUrl to the videoView
                    vd_attachmentsFragment.setVideoURI(uri);

                    // creating object of
                    // media controller class
                    MediaController mediaController = new MediaController(getContext());

                    // sets the anchor view
                    // anchor view for the videoView
                    mediaController.setAnchorView(vd_attachmentsFragment);

                    // sets the media player to the videoView
                    mediaController.setMediaPlayer(vd_attachmentsFragment);

                    // sets the media controller to the videoView
                    vd_attachmentsFragment.setMediaController(mediaController);

                }
            });

        }


        ll_leftEarPhotoAttachmentsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_attachmentsFragment.setVisibility(View.VISIBLE);
                vd_attachmentsFragment.setVisibility(View.INVISIBLE);
                vd_attachmentsFragment.stopPlayback();
                Picasso.get().load(leftEarImageURL).resize(img_attachmentsFragment.getWidth(),img_attachmentsFragment.getHeight()).into(img_attachmentsFragment);
            }
        });

        ll_rightEarPhotoAttachmentsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_attachmentsFragment.setVisibility(View.VISIBLE);
                vd_attachmentsFragment.setVisibility(View.INVISIBLE);
                vd_attachmentsFragment.stopPlayback();
                Picasso.get().load(rightEarImageURL).resize(img_attachmentsFragment.getWidth(),img_attachmentsFragment.getHeight()).into(img_attachmentsFragment);
            }
        });
        ll_throatPhotoAttachmentsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_attachmentsFragment.setVisibility(View.VISIBLE);
                vd_attachmentsFragment.setVisibility(View.INVISIBLE);
                vd_attachmentsFragment.stopPlayback();
                Picasso.get().load(throatImageURL).resize(img_attachmentsFragment.getWidth(),img_attachmentsFragment.getHeight()).into(img_attachmentsFragment);


            }
        });
        ll_faceRecordAttachmentsFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(leftEarImageURL).resize(img_attachmentsFragment.getWidth(),img_attachmentsFragment.getHeight()).into(img_attachmentsFragment);
                img_attachmentsFragment.setVisibility(View.INVISIBLE);
                vd_attachmentsFragment.setVisibility(View.VISIBLE);
                // starts the video
                vd_attachmentsFragment.start();

            }
        });


        return view;
    }
}