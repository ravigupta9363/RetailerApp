package com.example.ravi_gupta.retailerapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements OnFragmentChange,RetailerConfirmationFragment.OnFragmentInteractionListener,
        SignInFragment.OnFragmentInteractionListener,SignUpFragment.OnFragmentInteractionListener,
        OrderListFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks,DiscountFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener, ContactUsFragment.OnFragmentInteractionListener,
        TodayTotalTransactionFragment.OnFragmentInteractionListener, MedicineListFragment.OnFragmentInteractionListener,
        ForgetPasswordFragment.OnFragmentInteractionListener, ChangePasswordFragment.OnFragmentInteractionListener,
        ServiceFragment.OnFragmentInteractionListener, VerifyOtpFragment.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    DrawerLayout mDrawerLayout;
    int position;
    static boolean startService;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), BackgroundService.class));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_icon_home);
        startService = false;


        //Changing fonts
        //FontOverride.setDefaultFont(this, "monospace", "fonts/gothic.ttf");

            mNavigationDrawerFragment = (NavigationDrawerFragment)
                    getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
            mTitle = getTitle();
            mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

            //Set up Navigation Header
            TextView userName = (TextView) findViewById(R.id.navigationDrawerHeaderTextView1);
            userName.setText("Apollo Pharmacy");
            TextView userAddress = (TextView) findViewById(R.id.navigationDrawerHeaderTextView2);
            userAddress.setText("52N 12, Pratap Nagar");
            Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/gothic.ttf");
            userName.setTypeface(typeFace);
            userAddress.setTypeface(typeFace);
            //replaceFragment(R.layout.fragment_order_list, null);
            replaceFragment(R.layout.fragment_home,null);

            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        final FragmentManager fragmentManager = getSupportFragmentManager();

        /* On click of the navigation menu item this switch case will run*/
        switch (position) {

            case 1:
                mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new ProfileFragment()).addToBackStack(null)
                                .commitAllowingStateLoss();
                        //mDrawerLayout.closeDrawer(Gravity.START);

                        Log.v("dc", "Profile is Clicked");
                    }
                },300);
                break;
            case 2:
                if(startService == false) {
                    mDrawerLayout.closeDrawers();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, new DiscountFragment()).addToBackStack(null)
                                    .commit();
                            Log.v("dc", "Discount is Clicked");

                        }
                    }, 300);
                }
                else {
                    Toast.makeText(this,"First Stop the Service",Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                    mDrawerLayout.closeDrawers();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, new TodayTotalTransactionFragment()).addToBackStack(null)
                                    .commit();
                            Log.v("dc", "Today Total Transaction is Clicked");
                        }
                    }, 300);

                break;
            case 4:
                //mDrawerLayout.closeDrawers();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, new ContactUsFragment()).addToBackStack(OrderListFragment.TAG)
                                .commit();
                        Log.v("dc", "Contact Us is Clicked");
                    }
                },300);
                break;
            case 5:
                new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, new ServiceFragment()).addToBackStack(null)
                            .commit();
                    Log.v("dc", "Stop Service is Clicked");
                    }
                },300);
                break;
            case 6:
                Toast.makeText(getApplicationContext(),"Logout is clicked",Toast.LENGTH_LONG).show();
                Log.v("dc","Logout is Clicked");
                break;
        }

    }

    /* Assigning Title to the navigation bar*/
    public void onSectionAttached(int number) {
        String[] stringArray = getResources().getStringArray(R.array.sections_title);
        if (number >= 1) {
            mTitle = stringArray[number - 1];
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void replaceFragment(int id, Object object) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.fragment_sign_up_button1:
                RetailerConfirmationFragment frag2 = (RetailerConfirmationFragment) getSupportFragmentManager().
                        findFragmentByTag(RetailerConfirmationFragment.TAG);
                if (frag2 == null) {
                    frag2 = RetailerConfirmationFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                ft.replace(R.id.container, frag2, RetailerConfirmationFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;


            case R.id.order_list_view_open_order:
                Bundle bundle = new Bundle();
                MedicineListFragment frag3 = (MedicineListFragment) getSupportFragmentManager().
                        findFragmentByTag(MedicineListFragment.TAG);
                if (frag3 == null) {
                    frag3 = MedicineListFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                OrderDetails orderDetails = (OrderDetails) object;
                bundle.putString("patientName",orderDetails.patientName);
                bundle.putString("doctorName",orderDetails.doctorName);
                bundle.putString("doctorAddress",orderDetails.clinicName);
                bundle.putString("orderNumber","");
                frag3.setArguments(bundle);

                ft.replace(R.id.container, frag3, MedicineListFragment.TAG).addToBackStack(OrderListFragment.TAG);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.layout.fragment_home:
                HomeFragment frag4 = (HomeFragment) getSupportFragmentManager().
                        findFragmentByTag(HomeFragment.TAG);
                if (frag4 == null) {
                    frag4 = HomeFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag4, HomeFragment.TAG);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_home_button1:
                SignInFragment frag5 = (SignInFragment) getSupportFragmentManager().
                        findFragmentByTag(SignInFragment.TAG);
                if (frag5 == null) {
                    frag5 = SignInFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag5, SignInFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_home_button2:
                SignUpFragment frag6 = (SignUpFragment) getSupportFragmentManager().
                        findFragmentByTag(SignUpFragment.TAG);
                if (frag6 == null) {
                    frag6 = SignUpFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag6, SignUpFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_sign_in_button1:
                OrderListFragment frag7 = (OrderListFragment) getSupportFragmentManager().
                        findFragmentByTag(OrderListFragment.TAG);
                if (frag7 == null) {
                    frag7 = OrderListFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                ft.replace(R.id.container, frag7, OrderListFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.fragment_medicine_list_button1:
                Bundle bundle1  = new Bundle();
                OrderConfirmDialog orderConfirmDialog = new OrderConfirmDialog();
                String orderNumber = (String) object;
                bundle1.putString("orderNumber", orderNumber);
                orderConfirmDialog.setArguments(bundle1);
                orderConfirmDialog.show(getFragmentManager(),OrderConfirmDialog.TAG);
                break;

            case R.id.fragment_medicine_list_header_button2:
                EnterMedicineDialog enterMedicineDialog = new EnterMedicineDialog();
                enterMedicineDialog.show(getFragmentManager(), EnterMedicineDialog.TAG);
                position = ((int) object);
                break;

            case R.id.dialog_enter_medicine_button2:
                MedicineListFragment frag8 = (MedicineListFragment) getSupportFragmentManager().
                        findFragmentByTag(MedicineListFragment.TAG);
                frag8.updatePrice(object.toString(),position);
                break;

            case R.id.dialog_order_confirm_button2:
                Log.v("dialog", "called");
                OrderListFragment frag9 = (OrderListFragment) getSupportFragmentManager().
                        findFragmentByTag(OrderListFragment.TAG);
                frag9.updateOrderList();
                ft.replace(R.id.container, frag9, OrderListFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;

            case R.id.fragment_sign_in_button2:
                ForgetPasswordFragment frag10 = (ForgetPasswordFragment) getSupportFragmentManager().
                        findFragmentByTag(ForgetPasswordFragment.TAG);
                if (frag10 == null) {
                    frag10 = ForgetPasswordFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag10, ForgetPasswordFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_incoming_sms_edittext1:
                ChangePasswordFragment frag11 = (ChangePasswordFragment) getSupportFragmentManager().
                        findFragmentByTag(ChangePasswordFragment.TAG);
                if (frag11 == null) {
                    frag11 = ChangePasswordFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag11, ChangePasswordFragment.TAG);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_change_password_button1:
                OrderListFragment frag12 = (OrderListFragment) getSupportFragmentManager().
                        findFragmentByTag(OrderListFragment.TAG);
                Log.v("called","called1");
                if (frag12 == null) {
                    frag12 = OrderListFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                ft.replace(R.id.container, frag12, OrderListFragment.TAG);
                ft.commitAllowingStateLoss();
                mTitle = "";
                break;

            case R.id.fragment_prescription_header_imageview1:
                Bundle bundle2 = new Bundle();
                //bundle2.pu("prescription",medicineListAdapter);
                Bitmap image = (Bitmap) object;
                bundle2.putParcelable("prescription", image);
                ImageZoomDialog imageZoomDialog = new ImageZoomDialog();
                imageZoomDialog.setArguments(bundle2);
                imageZoomDialog.show(getFragmentManager(), ImageZoomDialog.TAG);
                Log.v("signin","image  "+image);
                break;

            case R.id.fragment_forget_password_button2:
                VerifyOtpFragment verifyOtpFragment = (VerifyOtpFragment) getSupportFragmentManager().
                        findFragmentByTag(VerifyOtpFragment.TAG);
                if (verifyOtpFragment == null) {
                    verifyOtpFragment = VerifyOtpFragment.newInstance();
                }
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                ft.replace(R.id.container, verifyOtpFragment, VerifyOtpFragment.TAG).addToBackStack(null);
                ft.commitAllowingStateLoss();
                break;
        }
    }
}
