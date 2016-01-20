package com.example.ravi_gupta.retailerapp;

import java.util.ArrayList;

/**
 * Created by Ravi-Gupta on 1/20/2016.
 */
public class MedicineListModel {

    private String drug;
    private String company;
    private String formName;
    private String type;
    private double price;
    private int contains;
    private String packingDetails;
    private ArrayList<String> salt = new ArrayList<String>();
    private int quantityRequired;
    private String category;
    String available;
    String notAvailable;

    public MedicineListModel(String drug, String company, String formName, String type,
                             double price, int contains, String packingDetails, ArrayList<String> salt,
                             int quantityRequired, String category) {

        this.drug = drug;
        this.company = company;
        this.formName =  formName;
        this.type = type;
        this.price = price;
        this.contains = contains;
        this.packingDetails  = packingDetails;
        this.salt = salt;
        this.quantityRequired = quantityRequired;
        this.category = category;
    }



    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getContains() {
        return contains;
    }

    public void setContains(int contains) {
        this.contains = contains;
    }

    public String getPackingDetails() {
        return packingDetails;
    }

    public void setPackingDetails(String packingDetails) {
        this.packingDetails = packingDetails;
    }

    public ArrayList<String> getSalt() {
        return salt;
    }

    public void setSalt(ArrayList<String> salt) {
        this.salt = salt;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
