package org.example.Users;

import org.example.Prescription;
import java.util.ArrayList;
import java.util.HashSet;
import org.example.Users.DataFromMySQL;

public class Doctor extends User {
    private String rpps;
    private HashSet<Patient> patients; // à retirer
    private ArrayList<Prescription> prescriptions;

    public Doctor(String name, String password, String rppsNumber) {
        super(name, password);
        if (rppsNumber.matches("[0-9]+") && rppsNumber.length() == 11)
            this.rpps = rppsNumber;
    }

    public HashSet<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Doctor(String name, String userName, String password, String rppsNumber) {
        super(name, userName, password);
        if (rppsNumber.matches("[0-9]+") && rppsNumber.length() == 11)
            this.rpps = rppsNumber;
        this.patients = new HashSet<>();
        this.prescriptions = new ArrayList<>();
    }

    public String getRpps() {
        return rpps;
    }

    public void setRpps(String rppsNumber) {
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
