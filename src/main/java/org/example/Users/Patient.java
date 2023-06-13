package org.example.Users;

import org.example.Prescription;

public class Patient extends User {
    private int numeroSec;
    private int age;
    private int weight;
    private int height;
    private String specialMentions;

    public Patient(String name, String userName, String password, int numeroSec, int age, int weight, int height, String specialMentions) {
        super(name, userName, password);
        this.numeroSec = numeroSec;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.specialMentions = specialMentions;
    }


    public int getNumeroSec() {
        return numeroSec;
    }

    public void setNumeroSec(int numeroSec) {
        this.numeroSec = numeroSec;
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

    public void sendPrescription(Prescription prescription, Pharmacy pharmacy){
        //do something
    }
}
