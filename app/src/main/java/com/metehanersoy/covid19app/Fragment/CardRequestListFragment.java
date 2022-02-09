package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.CardRequestsActivity;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Adapter.SubmittedCardsListAdapter;
import com.metehanersoy.covid19app.Class.Card;
import com.metehanersoy.covid19app.R;

import java.util.ArrayList;

public class CardRequestListFragment extends Fragment {

    ListView lv_fragmentCardRequestList;
    TextView tv_fragmentCardRequest;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    ArrayList<ExaminationCardObject> cardRequestsList = new ArrayList<>();
    ArrayList<Card> cardArrayList = new ArrayList<Card>();
    SubmittedCardsListAdapter listAdapter;

    String ExaminationCardId="";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card_request_list, container, false);

        lv_fragmentCardRequestList = view.findViewById(R.id.lv_fragmentCardRequestList);
        tv_fragmentCardRequest = view.findViewById(R.id.tv_fragmentCardRequest);


        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        updateList();

        tv_fragmentCardRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCard();
            }
        });

       lv_fragmentCardRequestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppCompatButton btn_submittedCardSymptoms = view.findViewById(R.id.btn_submittedCardSymptoms);
                AppCompatButton btn_submittedCardAttachments = view.findViewById(R.id.btn_submittedCardAttachments);
                AppCompatButton btn_submittedCardFeedback = view.findViewById(R.id.btn_submittedCardFeedback);
                AppCompatButton btn_submittedCardSuggestion = view.findViewById(R.id.btn_submittedCardSuggestion);

                ImageView img_submittedCardClickForMore = view.findViewById(R.id.img_submittedCardClickForMore);
                LinearLayout ll_submittedCardBottom = view.findViewById(R.id.ll_submittedCardBottom);

                ll_submittedCardBottom.setVisibility(View.VISIBLE);
                img_submittedCardClickForMore.setVisibility(View.GONE);


                btn_submittedCardSymptoms.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = new Bundle();
                        ExaminationCardId = cardArrayList.get(position).getExaminationCardId();

                        for(ExaminationCardObject exa : cardRequestsList){

                            if(ExaminationCardId.equals(exa.getCardId())){

                                bundle.putString("userId",exa.getUserId());
                            }

                        }

                        bundle.putString("ExaminationCardId",ExaminationCardId);
                        bundle.putString("userType","Doctor");
                        SymptomsFragment symptomsFragment = new SymptomsFragment();
                        symptomsFragment.setArguments(bundle);

                        ((CardRequestsActivity) getActivity()).addFragments(symptomsFragment,"submittedToSymptoms");
                    }
                });

                btn_submittedCardAttachments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = new Bundle();
                        ExaminationCardId = cardArrayList.get(position).getExaminationCardId();
                        bundle.putString("ExaminationCardId",ExaminationCardId);

                        for(ExaminationCardObject exa : cardRequestsList){

                            if(ExaminationCardId.equals(exa.getCardId())){

                                bundle.putString("userId",exa.getUserId());
                            }

                        }
                        bundle.putString("userType","Doctor");

                        AttachmentsFragment attachmentsFragment = new AttachmentsFragment();
                        attachmentsFragment.setArguments(bundle);

                        ((CardRequestsActivity) getActivity()).addFragments(attachmentsFragment,"submittedToAttachments");
                    }
                });

                btn_submittedCardFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("aimedFrom", "Doctor");
                        bundle.putString("cardId", cardArrayList.get(position).getExaminationCardId() );

                        ExaminationCardId = cardArrayList.get(position).getExaminationCardId();
                        for(ExaminationCardObject exa : cardRequestsList){

                            if(ExaminationCardId.equals(exa.getCardId())){

                                bundle.putString("userId",exa.getUserId());
                            }

                        }

                        FeedbackFragment feedbackFragment = new FeedbackFragment();
                        feedbackFragment.setArguments(bundle);

                        ((CardRequestsActivity) getActivity()).addFragments(feedbackFragment,"submittedToFeedback");

                    }
                });
                btn_submittedCardSuggestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card card = cardArrayList.get(position);

                        Bundle bundleSuggestion = new Bundle();
                        bundleSuggestion.putInt("scoreBasedCase", card.getScoreBasedCase());
                        bundleSuggestion.putDouble("probability", card.getProbability());
                        bundleSuggestion.putString("userType","Doctor");

                        SuggestionFragment suggestionFragment = new SuggestionFragment();
                        suggestionFragment.setArguments(bundleSuggestion);

                        ((CardRequestsActivity) getActivity()).addFragments(suggestionFragment, "submittedToSuggestion");
                    }
                });
            }
        });


        return view;
    }


    private void updateList() {

        mDatabase.child("review_requests").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        ExaminationCardObject examinationCardObject = new ExaminationCardObject(snapshot.getKey(),snapshot.getValue(String.class));
                        cardRequestsList.add(examinationCardObject);
                        Log.e("deneme","update");

                    }

                }

            }

        });
    }

    private void getCard(){

        cardArrayList.clear();
        Log.e("deneme","getbeforenull");
        if(cardRequestsList !=null){

            for(ExaminationCardObject exa : cardRequestsList){

                mDatabase.child("examination_cards").child(exa.getUserId()).child(exa.cardId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(Task<DataSnapshot> task) {

                        if (task.isSuccessful()) {


                                DataSnapshot snapshot = task.getResult();
                                Card card = snapshot.getValue(Card.class);
                                //We are saving doctorId inside to doctor object because we don't keep doctor id in the database table!
                                card.setExaminationCardId(snapshot.getKey());
                                cardArrayList.add(card);


                            listAdapter = new SubmittedCardsListAdapter(getActivity(), R.layout.custom_submitted_card_row, cardArrayList, getChildFragmentManager());
                            lv_fragmentCardRequestList.setAdapter(listAdapter);
                        }

                    }

                });

            }

        }




    }

    public class ExaminationCardObject{

       String cardId;
       String userId;

       ExaminationCardObject(String cardId, String userId){
          this.cardId = cardId;
          this.userId =userId;

       }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

}