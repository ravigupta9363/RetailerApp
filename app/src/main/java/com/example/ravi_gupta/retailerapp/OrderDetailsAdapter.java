package com.example.ravi_gupta.retailerapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 6/6/2015.
 */
public class OrderDetailsAdapter extends ArrayAdapter<OrderDetails>{

    Context context;
    int resource;
    private OnFragmentChange callback1;
    int orderListPosition;

    private OrderListFragment.OnFragmentInteractionListener callback2;
    ArrayList<OrderDetails> orderDetails = new ArrayList<OrderDetails>();

    public OrderDetailsAdapter(Context context, int resource, ArrayList<OrderDetails> orderDetails) {
        super(context, resource, orderDetails);
        this.context = context;
        callback1 = (OnFragmentChange)context;
        callback2 = (OrderListFragment.OnFragmentInteractionListener) context;
        this.resource = resource;
        this.orderDetails = orderDetails;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        OrderDetailsHolder holder = null;


        if(row == null)
        {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            row = layoutInflater.inflate(resource,parent,false);
            holder = new OrderDetailsHolder();
            holder.patientName = (TextView)row.findViewById(R.id.order_list_view_patient_name);
            holder.orderNumber = (TextView)row.findViewById(R.id.order_list_view_order_no);
            holder.medicineList = (TextView)row.findViewById(R.id.order_list_view_medicine_list);
            holder.expirationTime = (TextView)row.findViewById(R.id.order_list_view_expiration_time);
            holder.expirationText = (TextView)row.findViewById(R.id.order_list_view_order_expire_text);
            holder.openOrder = (Button) row.findViewById(R.id.order_list_view_open_order);
            holder.cancelOrder = (Button) row.findViewById(R.id.order_list_view_cancel_order);
            
            row.setTag(holder);
        }
        else {

            holder = (OrderDetailsHolder)row.getTag();
        }
        //Assigning custom fonts

        /*holder.patientName.setTypeface(typeface);
        holder.orderNumber.setTypeface(typeface);
        holder.expirationTime.setTypeface(typeface);
        holder.expirationText.setTypeface(typeface);
        holder.openOrder.setTypeface(typeface);
        holder.cancelOrder.setTypeface(typeface);*/
        // final String uri = holder.patientName.toString();
        final OrderDetails orderDetails1 = orderDetails.get(position);

        holder.openOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // callback2.onFragmentInteraction(uri);
                EventBus.getDefault().postSticky(orderDetails1,Constants.OPEN_ORDER_DETAILS);
                callback1.replaceFragment(R.id.order_list_view_open_order, orderDetails.get(position));
                orderListPosition = position;
            }
        });

        holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });

        //String medicineList = orderDetails1.medicineList[0];
        holder.patientName.setText(orderDetails1.patientName);
        //holder.orderNumber.setText(String.valueOf(orderDetails1.orderNumber));
        /*for(int i = 1; i<orderDetails1.medicineList.length; i++) {
            medicineList = medicineList + ", " +orderDetails1.medicineList[i];
        }
        holder.medicineList.setText(medicineList);
        holder.expirationTime.setText(String.valueOf(orderDetails1.expirationTime)+" mins");*/
        return row;
    }

    public void showDialog(final int position){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Sure, You want to reject the order?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                orderDetails.remove(orderDetails.get(position));
                notifyDataSetChanged();
                arg0.dismiss();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    static class OrderDetailsHolder {
        TextView patientName;
        TextView orderNumber;
        TextView medicineList;
        TextView expirationTime;
        TextView expirationText;
        Button openOrder;
        Button cancelOrder;
    }

    public void updateOrderList() {
        Log.v("dialog","OrderDetailsAdapter");
        orderDetails.remove(orderDetails.get(orderListPosition));
        notifyDataSetChanged();
    }
}
