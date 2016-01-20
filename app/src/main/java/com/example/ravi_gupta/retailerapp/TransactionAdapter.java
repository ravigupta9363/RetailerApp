package com.example.ravi_gupta.retailerapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 6/13/2015.
 */
public class TransactionAdapter extends ArrayAdapter<TransactionDetails> {
    Context context;
    int resource;
    ArrayList<TransactionDetails> transactionDetails = new ArrayList<TransactionDetails>();

    public TransactionAdapter(Context context,int resource,ArrayList<TransactionDetails> transactionDetails) {
        super(context,resource,transactionDetails);
        this.context = context;
        this.resource = resource;
        this.transactionDetails = transactionDetails;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TransactionHolder holder = null;
        if(row == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            row = layoutInflater.inflate(resource,parent,false);
            holder = new TransactionHolder();
            holder.patientName = (TextView)row.findViewById(R.id.transaction_list_view_patient_name);
            holder.price = (TextView)row.findViewById(R.id.transaction_list_view_price);
            holder.transactionId = (TextView)row.findViewById(R.id.transaction_list_view_transactionId);

            //Assigning custom fonts
            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/gothic.ttf");
            holder.patientName.setTypeface(typeface);
            holder.price.setTypeface(typeface);
            holder.transactionId.setTypeface(typeface);

            row.setTag(holder);

        }
        else {
            holder = (TransactionHolder)row.getTag();
        }
        TransactionDetails transactionDetails1 = transactionDetails.get(position);
        //Log.v("qwerty",getItemViewType(position)+"");
        holder.patientName.setText(transactionDetails1.patientName);
        holder.price.setText(String.valueOf(transactionDetails1.price) +" /-");
        holder.transactionId.setText(transactionDetails1.transactionId);
        return row;
    }

    static class TransactionHolder {
        TextView patientName;
        TextView price;
        TextView transactionId;
    }
}
