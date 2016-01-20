package com.example.ravi_gupta.retailerapp;

/**
 * Created by Ravi-Gupta on 6/13/2015.
 */
public class TransactionDetails {
    public String patientName;
    public float price;
    public String transactionId;

    public TransactionDetails() {
        super();
    }

    public TransactionDetails(String patientName,float price,String transactionId) {
        this.patientName = patientName;
        this.price = price;
        this.transactionId =transactionId;
    }
}
