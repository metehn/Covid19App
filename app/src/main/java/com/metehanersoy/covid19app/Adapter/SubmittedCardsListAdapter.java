package com.metehanersoy.covid19app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.metehanersoy.covid19app.Class.Card;
import com.metehanersoy.covid19app.Fragment.SuggestionFragment;
import com.metehanersoy.covid19app.R;
import java.util.ArrayList;

public class SubmittedCardsListAdapter extends ArrayAdapter<Card> {

    private ArrayList<Card> cardArrayList;
    private Context context;

    FragmentManager fragmentManager;


    public SubmittedCardsListAdapter(Context context, int resource, ArrayList<Card> cardArrayList, FragmentManager fragmentManager) {
        super(context, resource, cardArrayList);

        this.context = context;
        this.cardArrayList = cardArrayList;
        this.fragmentManager = fragmentManager;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View view = convertView;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_submitted_card_row, parent, false);

            TextView tv_submittedCardDate = view.findViewById(R.id.tv_submittedCardDate);
            TextView tv_submittedCardDoctorName = view.findViewById(R.id.tv_submittedCardDoctorName);
            TextView tv_submittedCardHospitalName =view.findViewById(R.id.tv_submittedCardHospitalName);
            TextView tv_submittedCardProbability = view.findViewById(R.id.tv_submittedCardProbability);

            //timestamp to date
            String humanDate = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(cardArrayList.get(position).getDate() * 1000));
            tv_submittedCardDate.setText(humanDate);
            tv_submittedCardDoctorName.setText(cardArrayList.get(position).getDoctorName());
            tv_submittedCardHospitalName.setText(cardArrayList.get(position).getHospitalName());
            tv_submittedCardProbability.setText(String.format("%.2f", cardArrayList.get(position).getProbability()));



        }

        return view;
    }



}
