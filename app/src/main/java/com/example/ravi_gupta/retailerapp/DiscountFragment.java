package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DiscountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DiscountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscountFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String TAG = "DiscountFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView toolbarTitleView;
    private ImageButton toolbarBackButton;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscountFragment newInstance(String param1, String param2) {
        DiscountFragment fragment = new DiscountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DiscountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_discount, container, false);
        //Fetching layout elements from DiscountFragment.java

        toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragmentDiscountImageButton1);
        toolbarTitleView = (TextView) rootview.findViewById(R.id.fragmentDiscountTextView1);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        toolbarTitleView.setTypeface(typeFace);
        toolbarTitleView.setText("Discount & Return");
        //Discount Fragment Elements
        RadioGroup radioGroup = (RadioGroup) rootview.findViewById(R.id.fragmentDiscountRadioGroup);
        final Button editButton = (Button)rootview.findViewById(R.id.fragmentDiscountButton1);
        final Button updateButton = (Button) rootview.findViewById(R.id.fragmentDiscountButton2);
        TextView discountTextView = (TextView) rootview.findViewById(R.id.fragmentDiscountTextView2);
        TextView returnTextView = (TextView) rootview.findViewById(R.id.fragmentDiscountTextView3);
        final EditText discountEditText = (EditText) rootview.findViewById(R.id.fragmentDiscountEditText1);
        final RadioButton allowRadio = (RadioButton) rootview.findViewById(R.id.fragmentDiscountRadioButton1);
        final RadioButton denyRadio = (RadioButton) rootview.findViewById(R.id.fragmentDiscountRadioButton2);

        editButton.setTypeface(typeFace);
        updateButton.setTypeface(typeFace);
        discountTextView.setTypeface(typeFace);
        returnTextView.setTypeface(typeFace);
        allowRadio.setTypeface(typeFace);
        denyRadio.setTypeface(typeFace);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Edit is Clicked", Toast.LENGTH_SHORT).show();
                discountEditText.setEnabled(true);
                allowRadio.setClickable(true);
                denyRadio.setClickable(true);
                allowRadio.setTextColor(Color.BLACK);
                denyRadio.setTextColor(Color.BLACK);
                editButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.VISIBLE);

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String discountText = discountEditText.getText().toString();
                discountEditText.setText(discountText);
                discountEditText.setEnabled(false);
                allowRadio.setClickable(false);
                denyRadio.setClickable(false);
                allowRadio.setTextColor(Color.GRAY);
                denyRadio.setTextColor(Color.GRAY);
                editButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.GONE);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(allowRadio.isChecked()) {
                    Toast.makeText(getActivity(),"Ready to take Return",Toast.LENGTH_SHORT).show();
                }
                else if (denyRadio.isChecked()) {
                    Toast.makeText(getActivity(),"Not Ready to take Return",Toast.LENGTH_SHORT).show();

                }
            }
        });

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Back Button is Clicked", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();

            }
        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
