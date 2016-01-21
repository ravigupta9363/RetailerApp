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
import java.util.List;

/**
 * Created by Ravi-Gupta on 6/6/2015.
 */
public class OrderDetailsAdapter extends ArrayAdapter<Order>{

    Context context;
    MainActivity mainActivity;
    int resource;
    private OnFragmentChange callback1;
    int orderListPosition;

    private OrderListFragment.OnFragmentInteractionListener callback2;
    ArrayList<Order> order = new ArrayList<Order>();

    public OrderDetailsAdapter(MainActivity mainActivity, int resource, ArrayList<Order> order) {
        super(mainActivity, resource, order);
        this.mainActivity = mainActivity;
        this.context = (Context) mainActivity;
        callback1 = (OnFragmentChange)context;
        callback2 = (OrderListFragment.OnFragmentInteractionListener) context;
        this.resource = resource;
        this.order = order;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        OrderHolder holder = null;


        if(row == null)
        {

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            row = layoutInflater.inflate(resource,parent,false);
            holder = new OrderHolder();
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

            holder = (OrderHolder)row.getTag();
        }
        final OrderDetails orderDetails1 = ((List<OrderDetails>)order.get(position).get("orderDetails")).get(0);
        Order order = this.order.get(position);
        holder.openOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // callback2.onFragmentInteraction(uri);
                EventBus.getDefault().postSticky(orderDetails1,Constants.OPEN_ORDER_DETAILS);
                mainActivity.replaceFragment(R.id.order_list_view_open_order, null);
                orderListPosition = position;
            }
        });

        holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(position);
            }
        });
        String medicineList = "";
        if(!orderDetails1.getDrugList().isEmpty()){
            medicineList = (String)orderDetails1.getDrugList().get(0).get("drug");
        }
        holder.patientName.setText(orderDetails1.patientName);
        holder.orderNumber.setText(String.valueOf(order.getId()));
        for(int i = 1; i<orderDetails1.getDrugList().size(); i++) {
            medicineList = medicineList + ", " +orderDetails1.getDrugList().get(i).get("drug");
        }
        holder.medicineList.setText(medicineList);
        return row;
    }

    public void showDialog(final int position){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Sure, You want to reject the order?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                order.remove(order.get(position));
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



    static class OrderHolder {
        TextView patientName;
        TextView orderNumber;
        TextView medicineList;
        TextView expirationTime;
        TextView expirationText;
        Button openOrder;
        Button cancelOrder;
    }

    public void updateOrderList() {
        Log.v("dialog","OrderAdapter");
        order.remove(order.get(orderListPosition));
        notifyDataSetChanged();
    }
}
