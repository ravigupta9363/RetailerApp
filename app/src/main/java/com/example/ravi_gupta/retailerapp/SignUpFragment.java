package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends android.support.v4.app.Fragment {

    private OnFragmentChange callBack1;
    public static String TAG = "SignUpFragment";
    MainActivity mainActivity;

    private OnFragmentInteractionListener mListener;


    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Retriving all the fields from sign_up.xml
        TextView shopTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview1);
        TextView firstNameTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview2);
        TextView lastNameTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview3);
        TextView phoneTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview4);
        TextView emailTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview5);
        TextView passwordTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview6);
        TextView toolbsrTitleTextView = (TextView)rootview.findViewById(R.id.fragment_sign_up_textview7);
        ImageButton toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragment_sign_up_imagebutton1);
        Button signUpButton = (Button)rootview.findViewById(R.id.fragment_sign_up_button1);

        //Retriving all the editText fom sign_up.xml
        final EditText shopEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext1);
        final EditText firstNameEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext2);
        EditText lastNameEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext3);
        EditText phoneEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext4);
        EditText emailEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext5);
        EditText passwordEditText = (EditText)rootview.findViewById(R.id.fragment_sign_up_edittext6);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        emailTextView.setTypeface(typeface);
        passwordTextView.setTypeface(typeface);
        shopTextView.setTypeface(typeface);
        firstNameTextView.setTypeface(typeface);
        lastNameTextView.setTypeface(typeface);
        phoneTextView.setTypeface(typeface);
        toolbsrTitleTextView.setTypeface(typeface);
        signUpButton.setTypeface(typeface);

        signUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Snackbar.make(getView(), "Coming soon", Snackbar.LENGTH_SHORT).show();

                /*Pattern patternShop = Pattern.compile("^[a-zA-Z\\s]+$",Pattern.CASE_INSENSITIVE);
                Pattern patternFirstName = Pattern.compile("^[a-zA-Z\\s]+$",Pattern.CASE_INSENSITIVE);
                Pattern patternLastName = Pattern.compile("^[a-zA-Z\\s]+$",Pattern.CASE_INSENSITIVE);
                Pattern patternPhone = Pattern.compile("^[789]\\d{9}$");
                Pattern patternEmail = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$",Pattern.CASE_INSENSITIVE);
                //Pattern patternPassword = Pattern.compile("^[a-zA-Z\\s]+$",Pattern.CASE_INSENSITIVE);

                Matcher matcherShop = patternShop.matcher(shopEditText.getText().toString());
                Matcher matcherFirstName = patternFirstName.matcher(firstNameEditText.getText().toString());
                Matcher matcherLastName = patternLastName.matcher(firstNameEditText.getText().toString());
                Matcher matcherPhone = patternPhone.matcher(firstNameEditText.getText().toString());
                Matcher matcherEmail = patternEmail.matcher(firstNameEditText.getText().toString());
                //Matcher matcherPassword = patternPassword.matcher(firstNameEditText.getText().toString());

               /* if(matcherEmail.matches() && matcherPassword.matches() && matcherShop.matches() && matcherFirstName.matches()
                        && matcherLastName.matches() && matcherPhone.matches()) {*/

                    //mainActivity.replaceFragment(R.id.fragment_sign_up_button1,null);
               /* }
                else {
                    Toast.makeText(getActivity(), "Enter All Information", Toast.LENGTH_LONG).show();
                }*/

                Log.v("signup", "signup");
            }
        });

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        this.mainActivity = (MainActivity) activity;
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
