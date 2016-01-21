package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
    public class OrderListFragment extends android.support.v4.app.Fragment {

    public static String TAG = "OrderListFragment";
    MainActivity mainActivity;
    OrderDetailsAdapter orderAdapter;
    ArrayList<Order> orders;
    @Bind(R.id.fragment_order_listview) ListView mListView;
    @Bind(R.id.fragment_order_list_imagebutton1) ImageButton menuButton;
    @Bind(R.id.fragment_order_list_textview1) TextView toolbarTextview;


    private OnFragmentInteractionListener mListener;

    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();
        return fragment;
    }

    public OrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().registerSticky(this);
        EventBus.getDefault().register(this);
        orders = new ArrayList<Order>();
    }

    @Subscriber(tag = Order.ORDER_INITIALIZE)
    private void OrderInitilize(ArrayList<Order> orderArrayList) {
        orders.clear();
        orders  = orderArrayList;
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        orderAdapter = new OrderDetailsAdapter(mainActivity,R.layout.order_list_view_items,orders);

        View rootview = inflater.inflate(R.layout.fragment_order_list, container, false);
        ButterKnife.bind(this, rootview);
     
        View header = (View)getActivity().getLayoutInflater().inflate(R.layout.order_list_view_header, null);
        mListView.addHeaderView(header);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        TextView listHeader  = (TextView)header.findViewById(R.id.order_list_view_header_date) ;
        listHeader.setTypeface(typeface);
        toolbarTextview.setTypeface(typeface);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String todaysDate = df.format(calendar.getTime());
        listHeader.setText(todaysDate);

        mListView.setAdapter(orderAdapter);
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
        mainActivity = (MainActivity) activity;
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

    @Subscriber(tag = Constants.UPDATE_ORDER_LIST)
    public  void updateOrderList(String code) {
        orderAdapter.updateOrderList();
        mListView.setAdapter(orderAdapter);
    }

}
