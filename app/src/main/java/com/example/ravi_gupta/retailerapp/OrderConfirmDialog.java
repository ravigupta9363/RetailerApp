package com.example.ravi_gupta.retailerapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ravi-Gupta on 6/20/2015.
 */
public class OrderConfirmDialog extends DialogFragment {

    public static String TAG = "OrderConfirmDialog";
    String orderNumber;
    MainActivity mainActivity;

    public OrderConfirmDialog() {
        super();
    }

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
        this.orderNumber = getArguments().getString("orderNumber");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_order_confirm, container,false);
        this.mainActivity = (MainActivity) getActivity();
        TextView packOrderText = (TextView)view.findViewById(R.id.dialog_order_confirm_textview1);
        TextView orderCode = (TextView)view.findViewById(R.id.dialog_order_confirm_textview2);
        Button cancelButton = (Button)view.findViewById(R.id.dialog_order_confirm_button1);
        Button okButton = (Button)view.findViewById(R.id.dialog_order_confirm_button2);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");

        packOrderText.setTypeface(typeFace);
        orderCode.setTypeface(typeFace);
        orderCode.setText(orderNumber);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }

        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.replaceFragment(R.id.dialog_order_confirm_button2,null);
                dismiss();
            }

        });
        return view;
    }
}
