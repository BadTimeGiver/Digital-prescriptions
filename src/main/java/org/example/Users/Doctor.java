package org.example.Users;

import org.example.Prescription;

import java.util.ArrayList;
import java.util.HashSet;

public class Doctor extends User {
    private int rpps;
    private HashSet<Patient> patients;
    private ArrayList<Prescription> prescriptions;

    public Doctor(String name, String password, int rppsNumber) {
        super(name, password);
        this.rpps = rppsNumber;
    }

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Doctor(String name, String userName, String password, int rppsNumber) {
        super(name, userName, password);
        this.rpps = rppsNumber;
        this.patients = new HashSet<>();
        this.prescriptions = new ArrayList<>();
    }

    public int getRpps() {
        return rpps;
    }

    public void setRpps(int rppsNumber) {
        this.rpps = rppsNumber;
    }

    public void addPrescription(Patient patient, Prescription prescription) {
        this.patients.add(patient);
        this.prescriptions.add(prescription);
        patient.setNewPrescriptions(prescription);
        prescription.setPatient(patient);
    }

    @Override
    public String toString() {
        return "name= " + super.getName() +
                "\nrpps=" + rpps;
    }

}
