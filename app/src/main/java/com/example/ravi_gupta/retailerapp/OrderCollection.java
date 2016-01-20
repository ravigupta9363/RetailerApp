package com.example.ravi_gupta.retailerapp;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class OrderCollection {

    ArrayList<OrderDetails> orderDetailsArrayList = new ArrayList<OrderDetails>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();

    private HashMap<String,Map> prescription =  new HashMap<String, Map>();
    Map<String, String> map = new HashMap<String, String>();

    private List<HashMap<String,String>> drugList = new ArrayList<HashMap<String,String>>();
    private HashMap<String,String> drugMap =  new HashMap<String, String>();

    public OrderCollection() {
        initialize();
    }

    /**
     *  Data is fetch from the server when app will start
     */
    public void initialize() {
        orderArrayList.clear();

        /**
         *  TODO Fetch Data From Server
         */
        map.put("url","http://www.laviniaes.com/files/9514/0420/1878/roskilde-festival-1.jpg");
        prescription.put("url",map);

        drugMap.put("name","Crocin");
        drugMap.put("name","Sumo");
        drugMap.put("name","Combiflame");
        drugMap.put("name","Acolyte Plus");
        drugMap.put("name","Disprin");

        drugList.add(drugMap);

        orderDetailsArrayList.add(new OrderDetails(prescription, drugList, "Shyam Sharma", "Dr Shyam", "SatarBhai Clinic", "24 June 2016", 1));
        //orderDetailsArrayList.add(new OrderDetails(prescription ,drugList ,"Ravi Gupta","Dr Shyam","SatarBhai Clinic","24 June 2016", 1));
        //orderDetailsArrayList.add(new OrderDetails(prescription ,drugList ,"Ravi Gupta","Dr Shyam","SatarBhai Clinic","24 June 2016", 1));
        //orderDetailsArrayList.add(new OrderDetails(prescription ,drugList ,"Ravi Gupta","Dr Shyam","SatarBhai Clinic","24 June 2016", 1));

        /*HashMap<String,String> imageURL =  new HashMap<String, String>();
        imageURL.put("name","http://www.laviniaes.com/files/9514/0420/1878/");
        imageURL.put("container","roskilde-festival-1.jpg");

        HashMap<String,Double> latLong = new HashMap<String, Double>();
        latLong.put("latitude",28.4591179);
        latLong.put("longitude",77.1703644);*/

        orderArrayList.add(new Order("1","26 June 2016","1","1",orderDetailsArrayList));
        orderArrayList.add(new Order("2","26 June 2016","1","1",orderDetailsArrayList));
        orderArrayList.add(new Order("3","26 June 2016","1","1",orderDetailsArrayList));
        orderArrayList.add(new Order("4","26 June 2016","1","1",orderDetailsArrayList));
        orderArrayList.add(new Order("5","26 June 2016","1","1",orderDetailsArrayList));


        EventBus.getDefault().postSticky(orderArrayList, Order.ORDER_INITIALIZE);

    }

}
