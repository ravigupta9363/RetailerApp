package com.example.ravi_gupta.retailerapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class Order {

    private String id;
    private String date;
    private String googleAddr;
    private String landmark;
    private int pincode;
    private String flatNo;
    private Object geoLocation;
    private String status;
    private Object callCustomer;
    private String note;
    private String sendNotice;
    private Object forwardOrder;
    private List<HashMap<String,Map>> prescription;
    private String retailerId;
    private String prototypeStatusCode;
    private String prototypeOrderCancelReason;
    private String registrationId;
    private ArrayList<OrderDetails> orderDetails;
    public static final String ORDER_INITIALIZE = "OrderCollectionInitialize";

    public Order(String id, String date, String retailerId, String registrationId, ArrayList<OrderDetails> orderDetails) {
        this.id = id;
        this.date = date;
        this.retailerId = retailerId;
        this.registrationId = registrationId;
        this.orderDetails = orderDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(String retailerId) {
        this.retailerId = retailerId;
    }

}
