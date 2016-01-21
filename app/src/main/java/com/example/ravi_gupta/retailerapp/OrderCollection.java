package com.example.ravi_gupta.retailerapp;

import android.util.Log;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class OrderCollection {
    ArrayList<Order> orderArrayList = new ArrayList<>();
    MainActivity mainActivity;

    public OrderCollection(MainActivity mainActivity) {
        EventBus.getDefault().registerSticky(this);
        EventBus.getDefault().register(this);
        this.mainActivity = mainActivity;
        initialize();
    }

    /**
     *  Data is fetch from the server when app will start
     */
    public void initialize() {
        orderArrayList.clear();

//        /**
//         *  TODO Fetch Data From Server
//         */
//        map.put("name","http://www.laviniaes.com/files/9514/0420/1878/");
//        map.put("container","roskilde-festival-1.jpg");
//        prescription.put("image",map);
//
//        drugMap.put("drug","Crocin");
//        drugMap.put("company","AHPL");
//        drugMap.put("formName","Saridon Tab");
//        drugMap.put("type","Tablet");
//        drugMap.put("price","25");
//        drugMap.put("contains","10");
//        drugMap.put("packingDetails","200 mg x 150 mg x 30 mg");
//        drugMap.put("quantityRequired","10");
//        drugMap.put("category","Drug");
//        drugMap.put("salt", "{ Paracetamol: 200 mg, Prophenazol: 150 mg,caffeine: 30 mg }");
//
//        drugList.add(drugMap);

//        orderDetailsArrayList.add(new OrderDetails(prescription, drugList, "Shyam Sharma", "Shyam", "DLF Phase 3", "24 June 2016", 1));
//        orderArrayList.add(new Order("1","26 June 2016","1","1",orderDetailsArrayList));
//        orderArrayList.add(new Order("2","26 June 2016","1","1",orderDetailsArrayList));
//        orderArrayList.add(new Order("3","26 June 2016","1","1",orderDetailsArrayList));
//        orderArrayList.add(new Order("4","26 June 2016","1","1",orderDetailsArrayList));
//        orderArrayList.add(new Order("5","26 June 2016","1","1",orderDetailsArrayList));

        RestAdapter adapter = mainActivity.adapter;
        OrderRepository repository = adapter.createRepository(OrderRepository.class);
        repository.getOrder("55bbb7874f9f1ec35c13472e", new ListCallback<Order>() {
            @Override
            public void onSuccess(List<Order> objects) {
                Log.d("Log", "" + objects);
                orderArrayList = (ArrayList) objects;
                EventBus.getDefault().postSticky(orderArrayList, Order.ORDER_INITIALIZE);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("Log", t + "");
            }
        });



    }

    @Subscriber( tag = Constants.NOTIFY_ORDER_CONFIRMATION_ON_SERVER )
    public void sendOrderDetailsOnServer(String orderId) {
        //TODO Send to server
        EventBus.getDefault().post(orderId, Constants.UPDATE_ORDER_LIST);
    }

}
