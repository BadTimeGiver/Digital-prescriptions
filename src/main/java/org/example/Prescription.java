package org.example;

import org.example.Users.Doctor;
import org.example.Users.Patient;
import org.example.Users.Pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prescription {
    private ArrayList<String> listMedicine; // à enlever
    private int id;
    private String medicines;
    private LocalDate date;
    private Patient patient;
    private Doctor doctor;
    private Pharmacy pharmacy;
    private String instructions;
    private String rpps; // à enlever
    private boolean isValidate = false;

    public Prescription(int id, String medicines, LocalDate date, Patient patient, Doctor doctor, Pharmacy pharmacy,
            String instructions, boolean isValidate) {
        this.id = id;
        this.medicines = medicines;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.pharmacy = pharmacy;
        this.instructions = instructions;
        this.isValidate = isValidate;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        instructions = instructions;
    }

    public void setRpps(String rpps) {
        this.rpps = rpps;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Prescription(ArrayList<String> listMedicine, LocalDate date, String instructions, String doctorRPPS) {
        this.listMedicine = listMedicine;
        this.date = date;
        this.instructions = instructions;
        this.rpps = doctorRPPS;
    }

    public ArrayList<String> getListMedicine() {
        return listMedicine;
    }

    public void setListMedicine(ArrayList<String> listMedicine) {
        this.listMedicine = listMedicine;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAdditionalInstructions() {
        return instructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.instructions = additionalInstructions;
    }

    public String getRpps() {
        return rpps;
    }

    @Override
    public String toString() {
        return "listMedicine=" + listMedicine +
                "\ndate=" + date +
                "\nInstructions='" + instructions + '\'' +
                "\ndoctorRPPS=" + rpps;
    }
}
