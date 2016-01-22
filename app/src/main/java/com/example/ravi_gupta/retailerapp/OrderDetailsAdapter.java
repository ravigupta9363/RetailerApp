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
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Ravi-Gupta on 6/6/2015.
 */
public class OrderDetailsAdapter extends ArrayAdapter<Order>{

    Context context;
    MainActivity mainActivity;
    int resource;
    private OnFragmentChange callback1;
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
    ListIterator<Order> listIterator;

    public OrderDetailsAdapter(MainActivity mainActivity, int resource, ArrayList<Order> orderArrayList) {
        super(mainActivity, resource, orderArrayList);
        this.mainActivity = mainActivity;
        this.context = (Context) mainActivity;
        callback1 = (OnFragmentChange)context;
        this.orderArrayList = orderArrayList;
        this.resource = resource;
        EventBus.getDefault().register(this);
        EventBus.getDefault().registerSticky(this);
        listIterator = this.orderArrayList.listIterator();

    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        OrderHolder holder = null;
        Order order1;
        Log.d("Retailer", position + "");

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
        if(listIterator.hasNext()) {
            order1 = listIterator.next();


            if(order1.getOrderDetails() != null) {
                final OrderDetails orderDetails1 = order1.getOrderDetails().get(0);
                Order order = order1;
                holder.openOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // callback2.onFragmentInteraction(uri);
                        EventBus.getDefault().postSticky(orderDetails1, Constants.OPEN_ORDER_DETAILS);
                        mainActivity.replaceFragment(R.id.order_list_view_open_order, null);

                    }
                });

                holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(position);
                    }
                });
                String medicineList = "";
                if (!orderDetails1.getDrugList().isEmpty()) {
                    medicineList = (String) orderDetails1.getDrugList().get(0).get("formName");
                }
                holder.patientName.setText(orderDetails1.patientName);
                String orderIdValue = String.valueOf(order.getId());
                holder.orderNumber.setText(orderIdValue);
                EventBus.getDefault().postSticky(order, Constants.SEND_ORDER_CONFIRM_BUTTON);
                for (int i = 1; i < orderDetails1.getDrugList().size(); i++) {
                    medicineList = medicineList + ", " + orderDetails1.getDrugList().get(i).get("formName");
                }
                holder.medicineList.setText(medicineList);
            }
        }

        return row;
    }

    public void showDialog(final int position){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Sure, You want to reject the order?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                EventBus.getDefault().post(orderArrayList.get(position), Constants.CANCEL_ORDER_BUTTON);
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

    @Subscriber( tag = Constants.CANCEL_ORDER_BUTTON)
    private void removeList(Order order) {
        orderArrayList.remove(order);
        notifyDataSetChanged();
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

    public void updateOrderList(Order order) {
        Log.v("dialog","OrderAdapter");
        orderArrayList.remove(order);
        notifyDataSetChanged();
    }
}
