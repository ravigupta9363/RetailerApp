package com.example.ravi_gupta.retailerapp;

import com.strongloop.android.loopback.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class Order extends Model {

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


    public String getGoogleAddr() {
        return googleAddr;
    }

    public void setGoogleAddr(String googleAddr) {
        this.googleAddr = googleAddr;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Object getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(Object geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCallCustomer() {
        return callCustomer;
    }

    public void setCallCustomer(Object callCustomer) {
        this.callCustomer = callCustomer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSendNotice() {
        return sendNotice;
    }

    public void setSendNotice(String sendNotice) {
        this.sendNotice = sendNotice;
    }

    public Object getForwardOrder() {
        return forwardOrder;
    }

    public void setForwardOrder(Object forwardOrder) {
        this.forwardOrder = forwardOrder;
    }

    public List<HashMap<String, Map>> getPrescription() {
        return prescription;
    }

    public void setPrescription(List<HashMap<String, Map>> prescription) {
        this.prescription = prescription;
    }

    public String getPrototypeStatusCode() {
        return prototypeStatusCode;
    }

    public void setPrototypeStatusCode(String prototypeStatusCode) {
        this.prototypeStatusCode = prototypeStatusCode;
    }

    public String getPrototypeOrderCancelReason() {
        return prototypeOrderCancelReason;
    }

    public void setPrototypeOrderCancelReason(String prototypeOrderCancelReason) {
        this.prototypeOrderCancelReason = prototypeOrderCancelReason;
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
