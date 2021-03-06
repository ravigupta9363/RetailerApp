package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String TAG = "ContactUsFragment";

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
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactUsFragment() {
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
        View rootview = inflater.inflate(R.layout.fragment_conatct_us, container, false);

        //Fetching layout elements from ContactusFragment.java

        toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragmentContactUsImageButton1);
        toolbarTitleView = (TextView) rootview.findViewById(R.id.fragmentContactUsTextView1);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        toolbarTitleView.setTypeface(typeFace);
        toolbarTitleView.setText("Contact us");

        //Button of Contact us Fragment
        Button callButton = (Button) rootview.findViewById(R.id.fragmentContactUsButton1);
        Button mailButton = (Button) rootview.findViewById(R.id.fragmentContactUsButton2);

        //Textview of Contact us Fragment
        TextView callTextView = (TextView) rootview.findViewById(R.id.fragmentContactUsTextView2);
        TextView mailTextView = (TextView) rootview.findViewById(R.id.fragmentContactUsTextView3);

        callTextView.setText("For any issue call us");
        mailTextView.setText("or mail us");

        callTextView.setTypeface(typeFace);
        mailTextView.setTypeface(typeFace);
        callButton.setTypeface(typeFace);
        mailButton.setTypeface(typeFace);

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Back Button is Clicked", Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();

            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick (View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:91-9460109363"));
                startActivity(callIntent);
                Toast.makeText(getActivity(), "Call is clicked", Toast.LENGTH_LONG).show();
            }
        });

        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "ravigupta9363@gmail.com"));
                //intent.putExtra(Intent.EXTRA_SUBJECT, "Drug Corner");
                //intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                startActivity(intent);
                Toast.makeText(getActivity(), "Mail is clicked", Toast.LENGTH_LONG).show();
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
