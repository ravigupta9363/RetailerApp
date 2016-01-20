package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangePasswordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends android.support.v4.app.Fragment {

    public static String TAG = "ChangePasswordFragment";
    MainActivity mainActivity;
    @Bind(R.id.fragment_change_password_edittext1) EditText newPasswordEdittext;
    @Bind(R.id.fragment_change_password_edittext2) EditText confirmPasswordEdittext;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        return fragment;
    }

    public ChangePasswordFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_change_password, container, false);
        ButterKnife.bind(this, rootview);

        ImageButton toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragment_change_password_imagebutton1);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        return rootview;
    }

    @OnClick(R.id.fragment_change_password_button1) void loginButton() {
        if(newPasswordEdittext.getText().toString().isEmpty() || confirmPasswordEdittext.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(),"Password cannot be left Empty",Toast.LENGTH_SHORT).show();
        }
        else if (newPasswordEdittext.getText().toString().equals(confirmPasswordEdittext.getText().toString()) ){
            closeKeyboard(getActivity(), confirmPasswordEdittext.getWindowToken());
            mainActivity.replaceFragment(R.id.fragment_change_password_button1,null);
            Log.v("called", "called");
        }
        else {
            Toast.makeText(getActivity(),"Password cannot match",Toast.LENGTH_SHORT).show();
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
