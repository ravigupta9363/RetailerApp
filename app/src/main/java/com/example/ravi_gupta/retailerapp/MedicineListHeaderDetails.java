package com.example.ravi_gupta.retailerapp;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 6/14/2015.
 */
public class MedicineListHeaderDetails  {
    String medicineName;
    String composition;
    int quantity;
    String form;
    String price;
    String companyName;
    String saltName;
    String available;
    Drawable prescription;
    String notAvailable;

    private ArrayList<MedicineListChildDetails> medicineListChildDetailses;
    public enum ItemType{
        Medicines,Salts,Prescription
    }
    private ItemType itemType;

    public MedicineListHeaderDetails() {
        super();
    }

    public MedicineListHeaderDetails(String medicineName,String price,
            String composition,
            int quantity,
            String form,
            String companyName,ArrayList<MedicineListChildDetails> medicineListChildDetailses) {
        this.medicineName = medicineName;
        this.composition = composition;
        this.quantity = quantity;
        this.price = price;
        this.form = form;
        this.companyName = companyName;
        this.available = "Available";
        this.medicineListChildDetailses = medicineListChildDetailses;
        this.itemType = ItemType.Medicines;
        this.notAvailable = "Not Available";

    }

    public MedicineListHeaderDetails(String saltName, String composition,int quantity, String form,ArrayList<MedicineListChildDetails> medicineListChildDetailses ) {
        this.saltName = saltName;
        this.composition  = composition;
        this.quantity = quantity;
        this.form = form;
        //this.price = price;
        this.medicineListChildDetailses = medicineListChildDetailses;
        this.itemType = itemType.Salts;
    }

    public MedicineListHeaderDetails(Drawable prescription) {
        this.medicineListChildDetailses = medicineListChildDetailses;
        this.itemType = itemType.Prescription;
        this.prescription = prescription;
    }

    public ArrayList<MedicineListChildDetails> getItems(){
        return medicineListChildDetailses;
    }

    public void setItems(ArrayList<MedicineListChildDetails> medicineListChildDetailses) {
        this.medicineListChildDetailses = medicineListChildDetailses;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        Log.v("signin","detail"+price);
    }

}
