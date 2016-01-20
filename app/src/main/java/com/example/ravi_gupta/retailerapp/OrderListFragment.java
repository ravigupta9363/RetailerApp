package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;


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
    private ListView mListView;
    MainActivity mainActivity;
    OrderDetailsAdapter orderDetailsAdapter;
    ArrayList<OrderDetails> orderDetails;

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
        String[] medicineList = {"Paracetamol","Crocin","Azithomycin","Strepsils"};
        orderDetails = new ArrayList<OrderDetails>();
    }

    @Subscriber(tag = Order.ORDER_INITIALIZE)
    private void OrderInitilize(ArrayList<Order> orderArrayList) {
        orderDetails.clear();
        for(int i = 0; i< orderArrayList.size(); i++) {
            orderDetails.add(new OrderDetails(orderArrayList.get(i).getOrderDetails().get(0).getPrescription(),
                    orderArrayList.get(i).getOrderDetails().get(0).getDrugList(),
                    orderArrayList.get(i).getOrderDetails().get(0).patientName,
                    orderArrayList.get(i).getOrderDetails().get(0).doctorName,
                    orderArrayList.get(i).getOrderDetails().get(0).doctorName,
                    orderArrayList.get(i).getOrderDetails().get(0).expirationDate,
                    orderArrayList.get(i).getOrderDetails().get(0).status));
        }
        orderDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //dummy data for list view


        //Set adapter for Order List
        orderDetailsAdapter = new OrderDetailsAdapter(mainActivity,R.layout.order_list_view_items,orderDetails);

        View rootview = inflater.inflate(R.layout.fragment_order_list, container, false);
        //View rootview2 = (View)getActivity().getLayoutInflater().inflate(R.layout.order_list_view_items,null);
        mListView = (ListView)rootview.findViewById(R.id.fragment_order_listview);
        ImageButton menuButton = (ImageButton)rootview.findViewById(R.id.fragment_order_list_imagebutton1);
        TextView toolbarTextview = (TextView)rootview.findViewById(R.id.fragment_order_list_textview1);
     
        View header = (View)getActivity().getLayoutInflater().inflate(R.layout.order_list_view_header, null);
        mListView.addHeaderView(header);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        TextView listHeader = (TextView)rootview.findViewById(R.id.order_list_view_header_date);
        listHeader.setTypeface(typeface);
        toolbarTextview.setTypeface(typeface);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                //drawerLayout.openDrawer(mDrawer);
                mainActivity.mDrawerLayout.openDrawer(Gravity.LEFT);
                Log.v("signup", "menu is clicked");
            }
        });

        mListView.setAdapter(orderDetailsAdapter);

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
        orderDetailsAdapter.updateOrderList();
        mListView.setAdapter(orderDetailsAdapter);
    }

}
