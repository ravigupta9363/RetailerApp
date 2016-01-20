package com.example.ravi_gupta.retailerapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;


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
    ExpandableListView mExpandableListView;

    private TextView toolbarTitleView;
    private ImageButton toolbarBackButton;
    private TextView toolbarDoctorName;
    private TextView toolbarDoctorAddress;
    MainActivity mainActivity;
    MedicineListAdapter mListAdapter;
    ArrayList<MedicineListHeaderDetails> listDataHeader;
    MedicineListHeaderDetails medicineListHeaderDetails2;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_medicine_list, container, false);
        String patientName = this.getArguments().getString("patientName");
        String doctorName = this.getArguments().getString("doctorName");
        String doctorAddress = this.getArguments().getString("doctorAddress");
        final String orderNumber = this.getArguments().getString("orderNumber");
        String updatedPrice = this.getArguments().getString("price");

        Log.v("signin",updatedPrice+"121");
        toolbarBackButton = (ImageButton)rootview.findViewById(R.id.fragmentOrderListItemImageButton1);
        toolbarTitleView = (TextView) rootview.findViewById(R.id.fragmentOrderListItemTextView1);
        toolbarDoctorName = (TextView) rootview.findViewById(R.id.fragmentOrderListItemTextView2);
        toolbarDoctorAddress = (TextView) rootview.findViewById(R.id.fragmentOrderListItemTextView3);
        Button confirmButton = (Button) rootview.findViewById(R.id.fragment_medicine_list_button1);

        Typeface typeFace = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gothic.ttf");
        confirmButton.setTypeface(typeFace);
        toolbarTitleView.setTypeface(typeFace);
        toolbarDoctorName.setTypeface(typeFace);
        toolbarDoctorAddress.setTypeface(typeFace);
        
        toolbarTitleView.setText(patientName);
        toolbarDoctorName.setText("Dr. " + doctorName);
        toolbarDoctorAddress.setText(doctorAddress);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.replaceFragment(R.id.fragment_medicine_list_button1,orderNumber);

            }
        });

        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = getFragmentManager().getBackStackEntryCount();
                onBackPressed();
            }
        });

        // get the listview
        mExpandableListView = (ExpandableListView) rootview.findViewById(R.id.fragment_medicine_list_expandable_listview);

        //Defining ArrayList for Group and Child List
        listDataHeader = new ArrayList<MedicineListHeaderDetails>();

        ArrayList<MedicineListChildDetails> medicineListChildDetailses1 = new ArrayList<MedicineListChildDetails>();
        final ArrayList<MedicineListChildDetails> medicineListChildDetailses2 = new ArrayList<MedicineListChildDetails>();
        ArrayList<MedicineListChildDetails> medicineListChildDetailses3 = new ArrayList<MedicineListChildDetails>();
        //ArrayList<MedicineListChildDetails> medicineListChildDetailses4 = new ArrayList<MedicineListChildDetails>();


        //Assigning data to Child List
        medicineListChildDetailses1.add(new MedicineListChildDetails("Paracetamol","250 mg"));
        medicineListChildDetailses1.add(new MedicineListChildDetails("Citrizine","500 mg"));
        medicineListChildDetailses1.add(new MedicineListChildDetails("Pseudoephedrine","350 mg"));
        medicineListChildDetailses1.add(new MedicineListChildDetails("Omeprazole","200 mg"));
        medicineListChildDetailses2.add(new MedicineListChildDetails("Azithromycin","100 mg"));
        medicineListChildDetailses2.add(new MedicineListChildDetails("Paracetamol","500 mg"));
        medicineListChildDetailses3.add(new MedicineListChildDetails("Ranidom",12.23,"Abott Healthcare"));
        medicineListChildDetailses3.add(new MedicineListChildDetails("Omez",5.23,"Dr Reddy"));
        medicineListChildDetailses3.add(new MedicineListChildDetails("Nor Metrozyl",1.32,"Mankind"));
        //medicineListChildDetailses4.add(new MedicineListChildDetails());
        //medicineListChildDetailses4.add(new MedicineListChildDetails("Nor Metrozyl",1.32,"Mankind"));


        //Assigning data to Group List
        listDataHeader.add(new MedicineListHeaderDetails(getResources().getDrawable(R.drawable.prescription)));
        listDataHeader.add(new MedicineListHeaderDetails("Crocin", "0","350mg", 5, "Tablets", "Piramal",medicineListChildDetailses1));
        listDataHeader.add(new MedicineListHeaderDetails("Acolate", "0","100mg", 10, "Tablets", "Gujarat Terce Laboratories Ltd", medicineListChildDetailses2));
        listDataHeader.add(new MedicineListHeaderDetails("Paracetamol","350mg",10,"Tablets",medicineListChildDetailses3));




        mListAdapter = new MedicineListAdapter(getActivity(), listDataHeader);
        //Child Click Listener
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Handle clicks on the children here...

                Log.v("signup", "Child");
                if (1 == mListAdapter.getChildType(groupPosition, childPosition)) {
                    listDataHeader.remove(groupPosition);
                    listDataHeader.add(new MedicineListHeaderDetails("Omez", "0","100mg", 10, "Syrup", "Abott Healthcare", medicineListChildDetailses2));
                    mListAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });


        // setting list adapter
        mExpandableListView.setAdapter(mListAdapter);

        return rootview;
    }

    @Subscriber(tag = Constants.OPEN_ORDER_DETAILS)
    public void openOrderDetails(OrderDetails orderDetails) {

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
        Log.v("signin", "price" + price);
        Log.v("signin", "rice" + position);
        mListAdapter.updatePrice(price, position);
        mExpandableListView.setAdapter(mListAdapter);
        //mListAdapter.notifyDataSetChanged();

    }



}
