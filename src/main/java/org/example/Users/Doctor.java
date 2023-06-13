package org.example.Users;

public class Doctor extends User{
    private int rppsNumber;

    public Doctor(String name, String userName, String password, int rppsNumber) {
        super(name, userName, password);
        this.rppsNumber = rppsNumber;
    }

    public int getRppsNumber() {
        return rppsNumber;
    }

    public void setRppsNumber(int rppsNumber) {
        this.rppsNumber = rppsNumber;
    }

    public void addPrescription(){

    }

}
