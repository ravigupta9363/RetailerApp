package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class SignInFragment extends android.support.v4.app.Fragment {

    public static String TAG = "SignInFragment";
    MainActivity mainActivity;
    @Bind(R.id.fragment_sign_in_edittext1) EditText emailEditText;
    @Bind(R.id.fragment_sign_in_edittext2) EditText passwordEditText;

    private OnFragmentInteractionListener mListener;


    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    public SignInFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, rootview);
        return rootview;
    }

    @OnClick(R.id.fragment_sign_in_button2) void forgetPassword() {
        mainActivity.replaceFragment(R.id.fragment_sign_in_button2, null);
    }

    @OnClick(R.id.fragment_sign_in_imagebutton1) void backButton() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.fragment_sign_in_button1) void signInButton() {

            Pattern patternEmail = Pattern.compile("^[a-zA-Z\\s]+$", Pattern.CASE_INSENSITIVE);
            Pattern patternPassword = Pattern.compile("^[a-zA-Z\\s]+$");

            Matcher matcherEmail = patternEmail.matcher(emailEditText.getText().toString());
            Matcher matcherPassword = patternPassword.matcher(passwordEditText.getText().toString());

            if (matcherEmail.matches() && matcherPassword.matches()) {
                closeKeyboard(getActivity(), passwordEditText.getWindowToken());
                mainActivity.replaceFragment(R.id.fragment_sign_in_button1, null);
            }
            else {
                Snackbar.make(getView(), "Username or Password is incorrect", Snackbar.LENGTH_SHORT)
                        .show();
            }
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

    public static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
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
