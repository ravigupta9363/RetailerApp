package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodayTotalTransactionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodayTotalTransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodayTotalTransactionFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String TAG = "TodayTotalTransactionFragment";
    private ListView mListView;

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
     * @return A new instance of fragment LastTransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayTotalTransactionFragment newInstance(String param1, String param2) {
        TodayTotalTransactionFragment fragment = new TodayTotalTransactionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TodayTotalTransactionFragment() {
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
        View rootview =  inflater.inflate(R.layout.fragment_today_total_transaction, container, false);

        ArrayList<TransactionDetails> transactionDetailses = new ArrayList<TransactionDetails>();
        transactionDetailses.add(new TransactionDetails("Ravi Gupta",204,"DC1254"));
        transactionDetailses.add(new TransactionDetails("Robins Gupta",452,"DC1569"));
        transactionDetailses.add(new TransactionDetails("Shourya Goel",998,"DC9658"));

        TransactionAdapter transactionAdapter = new TransactionAdapter(getActivity(),R.layout.transaction_list_view_items,transactionDetailses);

        //Fetching layout elements from LastTransactionFragment.java
        mListView = (ListView)rootview.findViewById(R.id.fragment_today_total_listview);
        toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragmentTodayTotalTransactionImageButton1);
        toolbarTitleView = (TextView) rootview.findViewById(R.id.fragmentTodayTotalTransactionTextView1);
        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        toolbarTitleView.setTypeface(typeFace);
        toolbarTitleView.setText("Today's Transactions");
        mListView.setAdapter(transactionAdapter);

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
