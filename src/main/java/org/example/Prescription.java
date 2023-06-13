package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Prescription {
    private ArrayList<String> listMedicine;
    private LocalDateTime date;
    private String Instructions;
    private int doctorRPPS;



    public ArrayList<String> getListMedicine() {
        return listMedicine;
    }

    public void setListMedicine(ArrayList<String> listMedicine) {
        this.listMedicine = listMedicine;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    public void setDoctorRPPS(int doctorRPPS) {
        this.doctorRPPS = doctorRPPS;
    }


}
