package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MedicineListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MedicineListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicineListFragment extends android.support.v4.app.Fragment implements OnBackPressed {

    public static String TAG = "MedicineListFragment";
    Context context;
    @Bind(R.id.fragmentOrderListItemTextView1) TextView toolbarTitleView;
    @Bind(R.id.fragmentOrderListItemTextView2) TextView toolbarDoctorName;
    @Bind(R.id.fragmentOrderListItemTextView3) TextView toolbarDoctorAddress;
    @Bind(R.id.fragment_medicine_list_expandable_listview) ExpandableListView mExpandableListView;
    MainActivity mainActivity;
    MedicineListDrugAdapter medicineListDrugAdapter;
    ArrayList<MedicineListModel> medicineListModelArrayList = new ArrayList<MedicineListModel>();
    ArrayList<String> saltStringArrayList = new ArrayList<String>();
    @Bind(R.id.fragment_medicine_list_imageview) ImageView imageView;
    ImageLoader imageLoader;
    private OnFragmentInteractionListener mListener;

    public static MedicineListFragment newInstance() {
        MedicineListFragment fragment = new MedicineListFragment();

        return fragment;
    }

    public MedicineListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity().getBaseContext();
        imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        EventBus.getDefault().register(this);
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_medicine_list, container, false);
        ButterKnife.bind(this, rootview);
        medicineListDrugAdapter = new MedicineListDrugAdapter(mainActivity, medicineListModelArrayList);
        mExpandableListView.setAdapter(medicineListDrugAdapter);
        return rootview;
    }

    @OnClick(R.id.fragment_medicine_list_button1) void confirmButton() {
        mainActivity.replaceFragment(R.id.fragment_medicine_list_button1, "2");
    }

    @OnClick(R.id.fragmentOrderListItemImageButton1) void backButton() {
        mainActivity.onBackPressed();
    }

    @Subscriber(tag = Constants.OPEN_ORDER_DETAILS)
    public void openOrderDetails(OrderDetails orderDetails) {

        for(int i = 0; i < orderDetails.getDrugList().size(); i++) {
            LinkedHashMap<String, String > salt = new LinkedHashMap<String, String>();
            String value  = orderDetails.getDrugList().get(i).get("salt");
            value = value.substring(1, value.length()-1);           //remove curly brackets
            String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
            for(int j = 0; j< keyValuePairs.length; j++) {
                saltStringArrayList.add(keyValuePairs[j]);
            }

            medicineListModelArrayList.add(
                    new MedicineListModel(
                            (String) orderDetails.getDrugList().get(i).get("drug"),
                            (String) orderDetails.getDrugList().get(i).get("company"),
                            (String) orderDetails.getDrugList().get(i).get("formName"),
                            (String) orderDetails.getDrugList().get(i).get("type"),
                            Double.parseDouble(orderDetails.getDrugList().get(i).get("price")),
                            Integer.parseInt(orderDetails.getDrugList().get(i).get("contains")),
                            orderDetails.getDrugList().get(i).get("packingDetails"),
                            saltStringArrayList,
                            Integer.parseInt(orderDetails.getDrugList().get(i).get("quantityRequired")),
                            orderDetails.getDrugList().get(i).get("category")));
        }

        toolbarTitleView.setText(orderDetails.patientName);
        toolbarDoctorName.setText("Dr. " + orderDetails.doctorName);
        toolbarDoctorAddress.setText(orderDetails.clinicName);
        Map<String, Map> image = orderDetails.getPrescription();
        Object object = "image";
        /*Uri imageUri = Uri.parse(Constants.apiUrl + "/containers/" +
                image.get(object).get("container") + "/download/" + image.get(object).get("name") );*/

        String imageUri = image.get(object).get("name")+""+image.get(object).get("container");
        imageLoader.displayImage(imageUri, imageView);


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

    @Override
    public void onBackPressed() {
        getActivity().onBackPressed();
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

    public void updatePrice(String price,int position) {

        medicineListDrugAdapter.updatePrice(price, position);
        mExpandableListView.setAdapter(medicineListDrugAdapter);
        //mListAdapter.notifyDataSetChanged();

    }



}
