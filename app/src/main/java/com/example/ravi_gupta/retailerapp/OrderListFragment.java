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
    OrderDetailsAdapter orderDetailsAdapter;
    ArrayList<OrderDetails> orderDetails;
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
        orderDetails = new ArrayList<OrderDetails>();
    }

    @Subscriber(tag = Order.ORDER_INITIALIZE)
    private void OrderInitilize(ArrayList<Order> orderArrayList) {
        orderDetails.clear();
        for(int i = 0; i< orderArrayList.size(); i++) {
            if(orderArrayList.get(i).getOrderDetails() != null){
                OrderDetails orderDetails_ = new OrderDetails();
                HashMap<String, Map<String, String>> prescription = new HashMap<>();
                orderArrayList.get(i).getOrderDetails().get(0).getPrescription();
                if(orderArrayList.get(i).getOrderDetails().get(0).getPrescription().size() != 0){
                    orderDetails_.setPrescription(orderArrayList.get(i).getOrderDetails().get(0).getPrescription());
                }
                if(orderArrayList.get(i).getOrderDetails().get(0).getDrugList().size() != 0){
                    orderDetails_.setDrugList(orderArrayList.get(i).getOrderDetails().get(0).getDrugList());
                }
                if(orderArrayList.get(i).getOrderDetails().get(0).getDoctorName() != null){
                    orderDetails_.setDoctorName(orderArrayList.get(i).getOrderDetails().get(0).getDoctorName());
                }
                if(orderArrayList.get(i).getOrderDetails().get(0).getClinicName() != null){
                    orderDetails_.setClinicName(orderArrayList.get(i).getOrderDetails().get(0).getClinicName());
                }
                if(String.valueOf(orderArrayList.get(i).getOrderDetails().get(0).getStatus()) != null){
                    orderDetails_.setStatus(orderArrayList.get(i).getOrderDetails().get(0).getStatus());
                }
                if(orderArrayList.get(i).getOrderDetails().get(0).getExpirationDate() != null){
                    orderDetails_.setExpirationDate(orderArrayList.get(i).getOrderDetails().get(0).getExpirationDate());
                }

                orderDetails.add(orderDetails_);

            }


            /*orderDetails.add(new OrderDetails(orderArrayList.get(i).getOrderDetails().get(0).getPrescription(),
                    orderArrayList.get(i).getOrderDetails().get(0).getDrugList(),
                    orderArrayList.get(i).getOrderDetails().get(0).patientName,
                    orderArrayList.get(i).getOrderDetails().get(0).doctorName,
                    orderArrayList.get(i).getOrderDetails().get(0).doctorName,
                    orderArrayList.get(i).getOrderDetails().get(0).expirationDate,
                    orderArrayList.get(i).getOrderDetails().get(0).status));*/
        }
        orderDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        orderDetailsAdapter = new OrderDetailsAdapter(mainActivity,R.layout.order_list_view_items,orderDetails);

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
