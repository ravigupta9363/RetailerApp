package com.example.ravi_gupta.retailerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 6/14/2015.
 */
public class MedicineListAdapter extends BaseExpandableListAdapter {

    private ArrayList<MedicineListHeaderDetails>  medicineListHeaderDetailses;
    Context context;
    private OnFragmentChange callback1;
    MainActivity mainActivity;
    String updatedPrice;
    MedicineListHeaderDetails medicineListHeaderDetails;


    public MedicineListAdapter(Context context,ArrayList<MedicineListHeaderDetails> medicineListHeaderDetailses) {
        this.context = context;
        this.medicineListHeaderDetailses = medicineListHeaderDetailses;
        callback1  = (OnFragmentChange) context;
        mainActivity = (MainActivity) context;
    }
    @Override
    public int getGroupCount() {
        return this.medicineListHeaderDetailses.size();
    }

    @Override
    public int getGroupTypeCount() {
        return 3;
    }

    @Override
    public int getGroupType(int groupPosition) {
        MedicineListHeaderDetails medicineListHeaderDetails = (MedicineListHeaderDetails) getGroup(groupPosition);
        if(medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Medicines ){
            return 0;
        }
        else if(medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Salts) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<MedicineListChildDetails> medicineListChildDetailses = medicineListHeaderDetailses.get(groupPosition).getItems();
        MedicineListHeaderDetails medicineListHeaderDetails = (MedicineListHeaderDetails) getGroup(groupPosition);
        if(medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Prescription)
            return 0;
        return medicineListChildDetailses.size();
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public int getChildType(int groupPosition,int childPosition){
        MedicineListHeaderDetails medicineListHeaderDetails = (MedicineListHeaderDetails) getGroup(groupPosition);
        //return medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Medicines ? 0: 1;
        if(medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Medicines ){
            return 0;
        }
        else if(medicineListHeaderDetails.getItemType() == MedicineListHeaderDetails.ItemType.Salts) {
            return 1;
        }
        else {
            return 2;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.medicineListHeaderDetailses.get(groupPosition);
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<MedicineListChildDetails> medicineListChildDetailses = medicineListHeaderDetailses.get(groupPosition).getItems();
        return medicineListChildDetailses.get(childPosition);
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
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, final ViewGroup parent) {
        Log.v("hello",getGroupCount()+"");
        Log.v("hello",getChildrenCount(groupPosition)+"");
        final View row = convertView;
        int type = getGroupType(groupPosition);
        medicineListHeaderDetails = (MedicineListHeaderDetails) getGroup(groupPosition);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/gothic.ttf");

        if (convertView == null || convertView!=null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if(type == 0) {

                convertView = infalInflater.inflate(R.layout.fragment_medicine_list_header, null);

                TextView medicineName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview1);
                TextView composition = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview2);
                TextView quantity = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview3);
                TextView form = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview4);
                TextView price = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview5);
                TextView companyName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_header_textview6);
                final Button notAvailable = (Button) convertView.findViewById(R.id.fragment_medicine_list_header_button1);
                final Button available = (Button) convertView.findViewById(R.id.fragment_medicine_list_header_button2);


                medicineName.setTypeface(typeface);
                composition.setTypeface(typeface);
                quantity.setTypeface(typeface);
                form.setTypeface(typeface);
                price.setTypeface(typeface);
                companyName.setTypeface(typeface);
                notAvailable.setTypeface(typeface);
                available.setTypeface(typeface);

                medicineName.setText(medicineListHeaderDetails.medicineName.toString());
                composition.setText((medicineListHeaderDetails.composition.toString()));
                quantity.setText(String.valueOf(medicineListHeaderDetails.quantity));
                form.setText(medicineListHeaderDetails.form.toString());
                price.setText(medicineListHeaderDetails.price + "/-");
                companyName.setText(medicineListHeaderDetails.companyName.toString());
                available.setText(medicineListHeaderDetails.available.toString());
                notAvailable.setText(medicineListHeaderDetails.notAvailable.toString());
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
                                medicineListHeaderDetailses.remove(groupPosition);
                                notifyDataSetChanged();
                            }
                        });
                        finalConvertView.startAnimation(animation);

                    }
                });

                available.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("signin", "available");
                        callback1.replaceFragment(R.id.fragment_medicine_list_header_button2, groupPosition);
                    }
                });

                Log.v("signup", medicineListHeaderDetails.medicineName.toString());
            }
            else if(type == 1){

                convertView = infalInflater.inflate(R.layout.fragment_salt_list,null);

                TextView saltName = (TextView) convertView.findViewById(R.id.fragment_salt_textview1);
                TextView saltComposition = (TextView) convertView.findViewById(R.id.fragment_salt_textview2);
                TextView quantitySalt = (TextView) convertView.findViewById(R.id.fragment_salt_textview3);
                TextView formSalt = (TextView) convertView.findViewById(R.id.fragment_salt_textview4);
                final TextView price = (TextView) convertView.findViewById(R.id.fragment_salt_textview5);
                TextView companyName = (TextView) convertView.findViewById(R.id.fragment_salt_textview6);
                Button available = (Button) convertView.findViewById(R.id.fragment_salt_button1);
                Button notAvailable = (Button) convertView.findViewById(R.id.fragment_salt_button2);

                available.setFocusable(false);
                notAvailable.setFocusable(false);

                saltName.setTypeface(typeface);
                saltComposition.setTypeface(typeface);
                price.setTypeface(typeface);
                companyName.setTypeface(typeface);
                available.setTypeface(typeface);
                notAvailable.setTypeface(typeface);
                quantitySalt.setTypeface(typeface);
                formSalt.setTypeface(typeface);

                //saltName.setText(medicineListHeaderDetails.saltName.toString());
                quantitySalt.setText(String.valueOf(medicineListHeaderDetails.quantity));
                formSalt.setText(medicineListHeaderDetails.form.toString());
                //Log.v("signup", medicineListHeaderDetails.medicineName.toString());
            }

            else {
                convertView = infalInflater.inflate(R.layout.fragment_prescription_header,null);
                ImageView prescriptionImage = (ImageView) convertView.findViewById(R.id.fragment_prescription_header_imageview1);
                prescriptionImage.setImageDrawable(medicineListHeaderDetails.prescription);
                final Bitmap expandedPrescription = ((BitmapDrawable) medicineListHeaderDetails.prescription).getBitmap();
                Log.v("pres",medicineListHeaderDetails.prescription+"");

                Log.v("signin", "image2");
                prescriptionImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("signin", "image");
                        mainActivity.replaceFragment(R.id.fragment_prescription_header_imageview1,expandedPrescription);
                        Log.v("signin","Bitmap  "+expandedPrescription);
                    }
                });
            }

        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final MedicineListChildDetails medicineListChildDetails = (MedicineListChildDetails)getChild(groupPosition, childPosition);
        int type = getChildType(groupPosition, childPosition);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/gothic.ttf");

        if(convertView == null || convertView != null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(type == 0){
                convertView = infalInflater.inflate(R.layout.fragment_medicine_list_child, null);
                TextView saltName = (TextView) convertView.findViewById(R.id.fragment_medicine_list_child_textview1);
               // TextView composition = (TextView) convertView.findViewById(R.id.fragment_medicine_list_child_textview2);

                saltName.setTypeface(typeface);
               // composition.setTypeface(typeface);

                saltName.setText(medicineListChildDetails.salt.toString());
                //composition.setText(medicineListChildDetails.composition.toString());
            }
            else if(type == 1){
                convertView = infalInflater.inflate(R.layout.fragment_salt_list_child,null);
                TextView medicineName = (TextView) convertView.findViewById(R.id.fragment_salt_list_child_textview1);
                TextView price = (TextView) convertView.findViewById(R.id.fragment_salt_list_child_textview2);
                TextView companyName = (TextView) convertView.findViewById(R.id.fragment_salt_list_child_textview3);

                medicineName.setTypeface(typeface);
                price.setTypeface(typeface);
                companyName.setTypeface(typeface);

                medicineName.setText(medicineListChildDetails.medicineName.toString());
                price.setText(String.valueOf(medicineListChildDetails.price));
                companyName.setText(medicineListChildDetails.companyName.toString());
            }

            else {
                convertView = infalInflater.inflate(R.layout.fragment_prescription_child,null);
               /* ImageView prescriptionImage = (ImageView) convertView.findViewById(R.id.fragment_prescription_child_imageview1);
                prescriptionImage.setImageDrawable(medicineListChildDetails.prescription);
                final Bitmap expandedPrescription = ((BitmapDrawable) medicineListChildDetails.prescription).getBitmap();
                Log.v("pres",medicineListChildDetails.prescription+"");

                Log.v("signin", "image2");
                prescriptionImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("signin", "image");
                        mainActivity.replaceFragment(R.id.fragment_prescription_child_imageview1,expandedPrescription);
                        Log.v("signin","Bitmap  "+expandedPrescription);
                    }
                });*/

            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void updatePrice(String updatedPrice,int position) {
        getGroupId(position);
        medicineListHeaderDetails = (MedicineListHeaderDetails) getGroup(position);
        if(updatedPrice.isEmpty()) {
            Toast.makeText(context, "Price cannot be left Blank", Toast.LENGTH_SHORT).show();
        }
        else {
            medicineListHeaderDetails.price = updatedPrice;
            medicineListHeaderDetails.available = "Edit Price";
            medicineListHeaderDetails.notAvailable = "";

        }

        //Log.v("signin",this.updatedPrice);
    }
}
