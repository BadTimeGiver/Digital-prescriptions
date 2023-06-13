package org.example.Users;

import java.util.HashMap;

public class Pharmacy extends User {
    private int pharamcyNbr;
    private String address;
    private HashMap<String, Boolean> validate_medicine;


    public HashMap<String, Boolean> getValidate_medicine() {
        return validate_medicine;
    }

    public void setVaidate_medicine(HashMap<String, Boolean> vaidate_medicine) {
        this.validate_medicine = vaidate_medicine;
    }


    public Pharmacy(String name, String userName, String password, int pharamcyNbr, String address) {
        super(name, userName, password);
        this.pharamcyNbr = pharamcyNbr;
        this.address = address;
    }


    public int getPharamcyNbr() {
        return pharamcyNbr;
    }

    public void setPharamcyNbr(int pharamcyNbr) {
        this.pharamcyNbr = pharamcyNbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void validateMedicine(String medicine){
        validate_medicine.put(medicine, true);
    }

}
