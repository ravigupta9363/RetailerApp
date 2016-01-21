package com.example.ravi_gupta.retailerapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 1/21/2016.
 */
public class MedicineListDrugAdapter extends BaseExpandableListAdapter {

    ArrayList<MedicineListModel> medicineListModelArrayList = new ArrayList<MedicineListModel>();
    MainActivity mainActivity;
    Context context;
    MedicineListModel medicineListModel;
    Button notAvailable;
    Button available;

    public MedicineListDrugAdapter(MainActivity mainActivity, ArrayList<MedicineListModel> medicineListModelArrayList ) {
        this.context = (Context)mainActivity;
        this.mainActivity = mainActivity;
        this.medicineListModelArrayList = medicineListModelArrayList;
    }

    @Override
    public int getGroupCount() {
        return medicineListModelArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        MedicineListModel medicineListModel = medicineListModelArrayList.get(groupPosition);
        return medicineListModel.getSalt().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return medicineListModelArrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        MedicineListModel medicineListModel = medicineListModelArrayList.get(groupPosition);
        return medicineListModel.getSalt().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        medicineListModel = (MedicineListModel) getGroup(groupPosition);
        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_medicine_list_header, null);
        }

        TextView medicineName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview1);
        TextView composition = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview2);
        TextView quantity = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview3);
        TextView form = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview4);
        TextView price = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview5);
        TextView companyName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview6);
        notAvailable = (Button) convertView.findViewById(R.id.fragment_medicine_list_header_button1);
        available = (Button) convertView.findViewById(R.id.fragment_medicine_list_header_button2);

        medicineName.setText(medicineListModel.getDrug());
        composition.setText((medicineListModel.getPackingDetails()));
        quantity.setText(String.valueOf(medicineListModel.getQuantityRequired()));
        form.setText(medicineListModel.getFormName());
        price.setText(String.valueOf(medicineListModel.getPrice()) + "/-");
        companyName.setText(medicineListModel.getCompany());
        available.setText("Available");
        notAvailable.setText("Not Available");
        notAvailable.setFocusable(false);
        available.setFocusable(false);
        final View finalConvertView = convertView;

        notAvailable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("signup", "not available");
                final Animation animation = AnimationUtils.loadAnimation(context,
                        R.anim.slide_out);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        medicineListModelArrayList.remove(groupPosition);
                        notifyDataSetChanged();
                        if(medicineListModelArrayList.size() == 0)
                            mainActivity.onBackPressed();
                            EventBus.getDefault().post("2",Constants.NOTIFY_ORDER_CONFIRMATION_ON_SERVER);
                    }
                });
                finalConvertView.startAnimation(animation);

            }
        });

        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("signin", "available");
                mainActivity.replaceFragment(R.id.fragment_medicine_list_header_button2, groupPosition);
            }
        });

        return  convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.fragment_medicine_list_child, null);
        }

        TextView saltName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_child_textview1);
        saltName.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void updatePrice(String updatedPrice,int position) {
        getGroupId(position);
        medicineListModel = (MedicineListModel) getGroup(position);
        if(updatedPrice.isEmpty()) {
            Toast.makeText(context, "Price cannot be left Blank", Toast.LENGTH_SHORT).show();
        }
        else {
            medicineListModel.setPrice(Double.parseDouble(updatedPrice));
            available.setText("Edit Price");
            notAvailable.setText("");

        }

        //Log.v("signin",this.updatedPrice);
    }
}
