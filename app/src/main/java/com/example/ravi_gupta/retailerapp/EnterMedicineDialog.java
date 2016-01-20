package com.example.ravi_gupta.retailerapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ravi-Gupta on 6/21/2015.
 */
public class EnterMedicineDialog extends DialogFragment{

    public static String TAG = "EnterMedicineDialog";
    String editTextPrice;
    MainActivity mainActivity;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getDialog().getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_enter_price, container,false);
        this.mainActivity = (MainActivity) getActivity();
        final TextView enterMedicineEditText = (TextView)view.findViewById(R.id.dialog_enter_price_edittext1);
        Button cancelButton = (Button)view.findViewById(R.id.dialog_enter_medicine_button1);
        Button okButton = (Button)view.findViewById(R.id.dialog_enter_medicine_button2);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        enterMedicineEditText.setTypeface(typeFace);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPrice = enterMedicineEditText.getText().toString();
                Log.v("signin",getPrice);
                mainActivity.replaceFragment(R.id.dialog_enter_medicine_button2,getPrice);
                dismiss();
            }
        });
        return view;
    }
}
