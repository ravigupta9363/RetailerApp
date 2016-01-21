package com.example.ravi_gupta.retailerapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravi-Gupta on 6/6/2015.
 */
public class OrderDetails {
    public String patientName;
    public String doctorName;


    public String clinicName;
    public String expirationDate;
    public int status;
    private HashMap<String, Map<String, String>> prescription =  new HashMap<String, Map<String, String>>();
    private List<HashMap<String,String>> drugList = new ArrayList<HashMap<String,String >>();

    public OrderDetails(HashMap<String, Map<String, String>> prescription,  List<HashMap<String,String>> drugList,
    String patientName ,String doctorName, String clinicName
    ,String expirationDate, int status
    )
    {
        this.patientName = patientName;
        this.prescription = prescription;
        this.expirationDate = expirationDate;
        this.clinicName = clinicName;
        this.doctorName = doctorName;
        this.drugList = drugList;
        this.status = status;
    }

    public OrderDetails(){
        super();
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }




    public HashMap<String, Map<String, String>> getPrescription() {
        return prescription;
    }

    public void setPrescription(HashMap<String, Map<String, String>> prescription) {
        this.prescription = prescription;
    }

    public List<HashMap<String, String>> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<HashMap<String, String >> drugList) {
        this.drugList = drugList;
    }


}
