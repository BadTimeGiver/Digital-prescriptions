package org.example.Users;

import org.example.Prescription;

import java.util.ArrayList;
import java.util.HashSet;

public class Doctor extends User{
    private int rppsNumber;
    private HashSet<Patient> patients;
    private ArrayList<Prescription> prescriptions;

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }


    public Doctor(String name, String userName, String password, int rppsNumber) {
        super(name, userName, password);
        this.rppsNumber = rppsNumber;
        this.patients = new HashSet<>();
        this.prescriptions = new ArrayList<>();
    }

    public int getRppsNumber() {
        return rppsNumber;
    }

    public void setRppsNumber(int rppsNumber) {
        this.rppsNumber = rppsNumber;
    }

    public void addPrescription(Patient patient, Prescription prescription){
        this.patients.add(patient);
        this.prescriptions.add(prescription);
        patient.setNewPrescriptions(prescription);
        prescription.setPatient(patient);
    }

}
