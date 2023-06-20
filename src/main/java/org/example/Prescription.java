package org.example;

import org.example.Users.Patient;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prescription {
    private ArrayList<String> listMedicine;
    private String medicines;
    private LocalDate date;
    private Patient patient;
    private String Instructions;
    private int rpps;
    private int num_pharmacy;
    private int nss;
    private boolean isValidate = false;

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String instructions) {
        Instructions = instructions;
    }

    public void setRpps(int rpps) {
        this.rpps = rpps;
    }

    public int getNum_pharmacy() {
        return num_pharmacy;
    }

    public void setNum_pharmacy(int num_pharmacy) {
        this.num_pharmacy = num_pharmacy;
    }

    public int getNss() {
        return nss;
    }

    public void setNss(int nss) {
        this.nss = nss;
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

    public Prescription(ArrayList<String> listMedicine, LocalDate date, String instructions, int doctorRPPS) {
        this.listMedicine = listMedicine;
        this.date = date;
        this.Instructions = instructions;
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
        return Instructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.Instructions = additionalInstructions;
    }

    public int getRpps() {
        return rpps;
    }

    @Override
    public String toString() {
        return "listMedicine=" + listMedicine +
                "\ndate=" + date +
                "\nInstructions='" + Instructions + '\'' +
                "\ndoctorRPPS=" + rpps;
    }
}
