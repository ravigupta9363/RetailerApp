package com.example.ravi_gupta.retailerapp;

import com.strongloop.android.loopback.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Ravi-Gupta on 6/6/2015.
 */
public class OrderDetails extends Model {
    private Object id;
    public String patientName;
    public String doctorName;


    public String clinicName;
    public String expirationDate;
    public int status;
    private HashMap<String, Map<String, String>> prescription =  new HashMap<String, Map<String, String>>();
    private List<HashMap<String, Object>> drugList = new ArrayList<>();

    public OrderDetails(HashMap<String, Map<String, String>> prescription,  List<HashMap<String,Object>> drugList,
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
        HashMap<String, Map<String, String>> hashMap =
                (prescription instanceof HashMap)
                        ? (HashMap) prescription
                        : new HashMap<String, Map<String, String>>(prescription);
        this.prescription = hashMap;
    }

    public List<HashMap<String, Object>> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<HashMap<String, Object >> drugList) {
        this.drugList = drugList;
    }


}
