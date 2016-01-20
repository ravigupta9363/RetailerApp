package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForgetPasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForgetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgetPasswordFragment extends android.support.v4.app.Fragment {

    public static String TAG = "ForgetPasswordFragment";
    MainActivity mainActivity;
    @Bind(R.id.fragment_forget_password_edittext1) EditText emailEdittext;

    private OnFragmentInteractionListener mListener;

    public static ForgetPasswordFragment newInstance() {
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();
        return fragment;
    }

    public ForgetPasswordFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_forget_password, container, false);
        ButterKnife.bind(this, rootview);
        return rootview;
    }

    @OnClick(R.id.fragment_forget_password_imagebutton1) void backButton() {
        mainActivity.onBackPressed();
    }

    @OnClick(R.id.fragment_forget_password_button2) void requestOTP() {

        Pattern patternPhone = Pattern.compile("^[789]\\d{9}$");
        Matcher matcherEmail = patternPhone.matcher(emailEdittext.getText().toString());

        if(emailEdittext.getText().toString().isEmpty()) {
            Snackbar.make(getView(), "Cannot be left blank", Snackbar.LENGTH_SHORT).show();
        }
        else if(matcherEmail.matches()){
            mainActivity.replaceFragment(R.id.fragment_forget_password_button2, null);
        }
        else {
            Snackbar.make(getView(), "Phone is not valid", Snackbar.LENGTH_SHORT).show();

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
        mainActivity = (MainActivity) getActivity();
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
