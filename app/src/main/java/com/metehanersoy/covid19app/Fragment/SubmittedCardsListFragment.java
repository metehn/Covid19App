package com.metehanersoy.covid19app.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metehanersoy.covid19app.Activity.SubmittedCardsActivity;
import com.metehanersoy.covid19app.Adapter.SubmittedCardsListAdapter;
import com.metehanersoy.covid19app.Class.Card;
import com.metehanersoy.covid19app.R;

import java.util.ArrayList;

public class SubmittedCardsListFragment extends Fragment {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    ArrayList<Card> cardArrayList = new ArrayList<Card>();
    SubmittedCardsListAdapter listAdapter;

    String ExaminationCardId="";

    ListView lv_fragmentSubmittedCardsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_submitted_cards_list, container, false);

        lv_fragmentSubmittedCardsList = view.findViewById(R.id.lv_fragmentSubmittedCardsList);

        //initializing firebase component
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        updateListForPatient();

        lv_fragmentSubmittedCardsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        bundle.putString("ExaminationCardId",ExaminationCardId);

                    SymptomsFragment symptomsFragment = new SymptomsFragment();
                    symptomsFragment.setArguments(bundle);

                        ((SubmittedCardsActivity) getActivity()).addFragments(symptomsFragment,"submittedToSymptoms");
                    }
                });

                btn_submittedCardAttachments.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bundle bundle = new Bundle();
                        ExaminationCardId = cardArrayList.get(position).getExaminationCardId();
                        bundle.putString("ExaminationCardId",ExaminationCardId);

                        AttachmentsFragment attachmentsFragment = new AttachmentsFragment();
                        attachmentsFragment.setArguments(bundle);

                        ((SubmittedCardsActivity) getActivity()).addFragments(attachmentsFragment,"submittedToAttachments");
                    }
                });

                btn_submittedCardFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("aimedFrom", "Patient");
                        bundle.putString("cardId", cardArrayList.get(position).getExaminationCardId() );
                        FeedbackFragment feedbackFragment = new FeedbackFragment();
                        feedbackFragment.setArguments(bundle);

                        ((SubmittedCardsActivity) getActivity()).addFragments(feedbackFragment,"submittedToFeedback");

                    }
                });
                btn_submittedCardSuggestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Card card = cardArrayList.get(position);

                        Bundle bundleSuggestion = new Bundle();
                        bundleSuggestion.putInt("scoreBasedCase", card.getScoreBasedCase());
                        bundleSuggestion.putDouble("probability", card.getProbability());

                        SuggestionFragment suggestionFragment = new SuggestionFragment();
                        suggestionFragment.setArguments(bundleSuggestion);

                        //((SubmittedCardsActivity) getActivity()).replaceFragments(suggestionFragment);
                        ((SubmittedCardsActivity) getActivity()).addFragments(suggestionFragment, "submittedToSuggestion");
                    }
                });
            }
        });


        return view;
    }

    private void updateListForPatient() {

        mDatabase.child("examination_cards").child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (task.isSuccessful()) {

                    cardArrayList.clear();

                    for (DataSnapshot snapshot : task.getResult().getChildren()) {
                        Card card = snapshot.getValue(Card.class);
                        //We are saving doctorId inside to doctor object because we don't keep doctor id in the database table!
                        card.setExaminationCardId(snapshot.getKey());
                        cardArrayList.add(card);
                    }

                    listAdapter = new SubmittedCardsListAdapter(getActivity(), R.layout.custom_submitted_card_row, cardArrayList, getChildFragmentManager());
                    lv_fragmentSubmittedCardsList.setAdapter(listAdapter);
                }

            }

        });


    }

}