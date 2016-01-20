package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String TAG = "ProfileFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView toolbarTitleView;
    private ImageButton toolbarBackButton;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        //Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_profile, container, false);

        //Fetching layout elements from ProfileFragment.java

        toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragmentProfileImageButton1);
        toolbarTitleView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView1);

        //TextViews of Profile Fragment
        TextView shopTextView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView2);
        TextView firstNameTextView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView3);
        TextView lastNameTextView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView4);
        TextView phoneTextView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView5);
        TextView emailTextView = (TextView) rootview.findViewById(R.id.fragmentProfileTextView6);

        //EditText of Profile Fragment
        final EditText shopEditText = (EditText)rootview.findViewById(R.id.fragmentProfileEditText1);
        final EditText firstNameEditText = (EditText)rootview.findViewById(R.id.fragmentProfileEditText2);
        final EditText lastNameEditText = (EditText)rootview.findViewById(R.id.fragmentProfileEditText3);
        final EditText phoneEditText = (EditText)rootview.findViewById(R.id.fragmentProfileEditText4);
        final EditText emailEditText = (EditText)rootview.findViewById(R.id.fragmentProfileEditText5);

        //Buttons of Profile Fragment
        final Button editButton = (Button) rootview.findViewById(R.id.fragmentProfileButton1);
        final Button updateButton = (Button) rootview.findViewById(R.id.fragmentProfileButton2);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        toolbarTitleView.setTypeface(typeFace);
        shopTextView.setTypeface(typeFace);
        firstNameTextView.setTypeface(typeFace);
        lastNameTextView.setTypeface(typeFace);
        phoneTextView.setTypeface(typeFace);
        emailTextView.setTypeface(typeFace);
        editButton.setTypeface(typeFace);
        updateButton.setTypeface(typeFace);
        toolbarTitleView.setText("Profile");

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = getFragmentManager().getBackStackEntryCount();

                getActivity().onBackPressed();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopEditText.setEnabled(true);
                firstNameEditText.setEnabled(true);
                lastNameEditText.setEnabled(true);
                phoneEditText.setEnabled(true);
                emailEditText.setEnabled(true);
                editButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.VISIBLE);

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shopText = shopEditText.getText().toString();
                String firstNameText = firstNameEditText.getText().toString();
                String lastNameText = lastNameEditText.getText().toString();
                String phoneText = phoneEditText.getText().toString();
                String emailText = emailEditText.getText().toString();

                shopEditText.setText(shopText);
                firstNameEditText.setText(firstNameText);
                lastNameEditText.setText(lastNameText);
                phoneEditText.setText(phoneText);
                emailEditText.setText(emailText);

                shopEditText.setEnabled(false);
                firstNameEditText.setEnabled(false);
                lastNameEditText.setEnabled(false);
                phoneEditText.setEnabled(false);
                emailEditText.setEnabled(false);

                editButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.GONE);
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
