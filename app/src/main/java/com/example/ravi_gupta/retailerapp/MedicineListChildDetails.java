package com.example.ravi_gupta.retailerapp;

import android.graphics.drawable.Drawable;

/**
 * Created by Ravi-Gupta on 6/14/2015.
 */
public class MedicineListChildDetails {
    String salt;
    String composition;
    String medicineName;
    double price;
    String companyName;
    MedicineListHeaderDetails.ItemType item;
    Drawable prescription;


    public MedicineListChildDetails() {
        super();
    }

    public MedicineListChildDetails(String salt,
            String composition) {
        this.salt =salt;
        this.composition = composition;
        this.item = MedicineListHeaderDetails.ItemType.Medicines;

    }

    public MedicineListChildDetails(String medicineName, double price, String companyName) {
        this.medicineName = medicineName;
        this.price = price;
        this.companyName = companyName;
        this.item = MedicineListHeaderDetails.ItemType.Salts;
    }

    public MedicineListChildDetails(Drawable prescription) {
        this.prescription = prescription;
    }

}
