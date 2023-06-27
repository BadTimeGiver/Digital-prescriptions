package org.example.Users;

import org.example.Prescription;

import javax.swing.*;
import java.util.ArrayList;

public class Patient extends User {
    private String nss;
    private int age;
    private int weight;
    private int height;
    private String specialMentions;
    private ArrayList<Prescription> prescriptions_sent = new ArrayList<>();
    private ArrayList<Prescription> prescriptions_NOT_sent = new ArrayList<>();
    private ArrayList<Prescription> returned_prescriptions = new ArrayList<>();

    public Patient(String name, String password, String nss, int age, int weight, int height, String specialMentions) {
        super(name, password);
        if (nss.matches("[0-9]+") && nss.length() == 13)
            this.nss = nss;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.specialMentions = specialMentions;
    }

    public ArrayList<Prescription> getReturned_prescriptions() {
        return returned_prescriptions;
    }

    public ArrayList<Prescription> getPrescriptions_sent() {
        return prescriptions_sent;
    }

    public ArrayList<Prescription> getPrescriptions_NOT_sent() {
        return prescriptions_NOT_sent;
    }

    public void setNewPrescriptions(Prescription prescription) {
        this.prescriptions_NOT_sent.add(prescription);
    }

    public Patient(String name, String userName, String password, String numeroSec, int age, int weight, int height,
            String specialMentions) {
        super(name, userName, password);
        if (numeroSec.matches("[0-9]+") && numeroSec.length() == 13)
            this.nss = numeroSec;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.specialMentions = specialMentions;
        this.prescriptions_sent = new ArrayList<>();
        this.prescriptions_NOT_sent = new ArrayList<>();
        this.returned_prescriptions = new ArrayList<>();
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String numeroSec) {
        this.nss = numeroSec;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSpecialMentions() {
        return specialMentions;
    }

    public void setSpecialMentions(String specialMentions) {
        this.specialMentions = specialMentions;
    }

    public void sendPrescription(Prescription prescription, Pharmacy pharmacy) {
        this.prescriptions_NOT_sent.remove(prescription);
        this.prescriptions_sent.add(prescription);
        pharmacy.getNot_validated_prescriptions().add(prescription);
    }

    @Override
    public String toString() {
        return "Name= " + super.getName() +
                "\nnumeroSec=" + nss +
                "\nage=" + age +
                "\nweight=" + weight +
                "\nheight=" + height;
    }
}
