package org.example;

import org.example.Users.Patient;

import java.time.LocalDate;
import java.util.ArrayList;

public class Prescription {
    private ArrayList<String> listMedicine;
    private LocalDate date;
    private String Instructions;
    private int doctorRPPS;
    private Patient patient;
    private boolean isValidate = false;

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
        this.doctorRPPS = doctorRPPS;
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

    public int getDoctorRPPS() {
        return doctorRPPS;
    }



    @Override
    public String toString() {
        return "listMedicine=" + listMedicine +
                "\ndate=" + date +
                "\nInstructions='" + Instructions + '\'' +
                "\ndoctorRPPS=" + doctorRPPS;
    }
}
