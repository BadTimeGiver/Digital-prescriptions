package org.example.Users;

import org.example.Prescription;

import java.util.ArrayList;

public class Pharmacy extends User {
    private String id;
    private String address;
    private ArrayList<Prescription> validated_prescriptions = new ArrayList<>();
    private ArrayList<Prescription> not_validated_prescriptions = new ArrayList<>();

    public Pharmacy(String name, String password, String id, String address) {
        super(name, password);
        this.id = id;
        this.address = address;
    }

    public ArrayList<Prescription> getValidated_prescriptions() {
        return validated_prescriptions;
    }

    public ArrayList<Prescription> getNot_validated_prescriptions() {
        return not_validated_prescriptions;
    }

    public void validate_prescription(Prescription prescription, boolean checked) {
        this.not_validated_prescriptions.remove(prescription);
        this.validated_prescriptions.add(prescription);
        prescription.getPatient().getReturned_prescriptions().add(prescription);
        prescription.setValidate(checked);
    }

    // private HashMap<String, Boolean> validate_medicine;

    /*
     * public HashMap<String, Boolean> getValidate_medicine() {
     * return validate_medicine;
     * }
     * 
     * public void setValidate_medicine(HashMap<String, Boolean> vaidate_medicine) {
     * this.validate_medicine = vaidate_medicine;
     * }
     */

    public Pharmacy(String name, String userName, String password, String pharamcyNbr, String address) {
        super(name, userName, password);
        if (pharamcyNbr.matches("[0-9]+") && pharamcyNbr.length() == 5)
            this.id = pharamcyNbr;
        this.address = address;
        not_validated_prescriptions = new ArrayList<>();
        validated_prescriptions = new ArrayList<>();
        // this.validate_medicine = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String pharamcyNbr) {
        this.id = pharamcyNbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*
     * public void validateMedicine(String medicine){
     * validate_medicine.put(medicine, true);
     * }
     */

    @Override
    public String toString() {
        return "name= " + super.getName() +
                "\nid=" + id +
                "\naddress=" + address;
    }

}
