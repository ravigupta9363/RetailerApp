package com.example.ravi_gupta.retailerapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VerifyOtpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VerifyOtpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerifyOtpFragment extends android.support.v4.app.Fragment {

    private OnFragmentInteractionListener mListener;
    MainActivity mainActivity;
    @Bind(R.id.fragment_incoming_sms_edittext1) EditText otpCode;
    public static String TAG = "VerifyOtpFragment";

    public VerifyOtpFragment() {
        // Required empty public constructor
    }

    public static VerifyOtpFragment newInstance() {
        VerifyOtpFragment fragment = new VerifyOtpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_otp, container, false);
        ButterKnife.bind(this, view);
        textChangedListener();
        return view;
    }

    public void textChangedListener() {
        otpCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int codeLength = start + count;
                Log.v("Drugcorner", String.valueOf(codeLength));
                Log.v("Drugcorner", String.valueOf(start));
                Log.v("Drugcorner", String.valueOf(count));

                String code = otpCode.getText().toString().trim();
                if (codeLength == 4) {
                    mainActivity.replaceFragment(R.id.fragment_incoming_sms_edittext1,null);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.fragment_verify_otp_imagebutton1) void backButton() {
        mainActivity.onBackPressed();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
