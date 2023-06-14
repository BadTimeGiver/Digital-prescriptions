package org.example.Users;

import org.example.Prescription;

import java.util.ArrayList;

public class Pharmacy extends User {
    private int pharamcyNbr;
    private String address;
    private ArrayList<Prescription> validated_prescriptions;
    private ArrayList<Prescription> not_validated_prescriptions;


    public ArrayList<Prescription> getValidated_prescriptions() {
        return validated_prescriptions;
    }

    public ArrayList<Prescription> getNot_validated_prescriptions() {
        return not_validated_prescriptions;
    }

    public void validate_prescription(Prescription prescription,boolean checked){
        this.not_validated_prescriptions.remove(prescription);
        this.validated_prescriptions.add(prescription);
        prescription.getPatient().getReturned_prescriptions().add(prescription);
        prescription.setValidate(checked);
    }

    //private HashMap<String, Boolean> validate_medicine;


    /*public HashMap<String, Boolean> getValidate_medicine() {
        return validate_medicine;
    }

    public void setValidate_medicine(HashMap<String, Boolean> vaidate_medicine) {
        this.validate_medicine = vaidate_medicine;
    }*/


    public Pharmacy(String name, String userName, String password, int pharamcyNbr, String address) {
        super(name, userName, password);
        this.pharamcyNbr = pharamcyNbr;
        this.address = address;
        not_validated_prescriptions = new ArrayList<>();
        validated_prescriptions = new ArrayList<>();
        //this.validate_medicine = new HashMap<>();
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

    /*public void validateMedicine(String medicine){
        validate_medicine.put(medicine, true);
    }*/

}
