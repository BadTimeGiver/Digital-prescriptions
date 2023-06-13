package org.example;
import org.example.Frames.DrFrame;
import org.example.Frames.PatientFrame;
import org.example.Frames.PharmaFrame;
import org.example.Users.Doctor;
import org.example.Users.Patient;
import org.example.Users.Pharmacy;
import org.example.Users.User;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Main {
    public static ArrayList<Doctor> listDoctor = new ArrayList<>();
    public static ArrayList<Pharmacy> listPharmacy = new ArrayList<>();
    public static ArrayList<Patient> listPatient = new ArrayList<>();
    public static ArrayList<Prescription> listPrescription = new ArrayList<>();
    public static ArrayList<User> listUsers = new ArrayList<>();

    public static void main(String[] args) {
        start();
    }

    public static void start(){



        JFrame frame = new JFrame("Ordonnencement Numerique");
        frame.setSize(400, 300);



        JPanel panel1 = new JPanel();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        //User Inputs
        JButton login_btn = new JButton("Login");
        JButton sign_up = new JButton("Sign Up");
        final Boolean[] is_in_sign_up = {false};
        JTextField user = new JTextField(8);
        JTextField name = new JTextField(8);
        JPasswordField pass = new JPasswordField(8);
        JLabel name_label = new JLabel("Name:");
        JLabel profession_label = new JLabel("Profession:");

        //Dr Inputs
        JLabel drRPPS_label = new JLabel("RPPS:");
        JTextField drRPPS = new JTextField(8);

        //User Inputs
        JLabel height_label = new JLabel("Height:");
        JLabel weight_label = new JLabel("Weight:");
        JLabel sec_number_label = new JLabel("Security Number:");
        JLabel special_mention_label = new JLabel("Special Mentions:");
        JLabel age_label = new JLabel("Age:");

        JTextField age = new JTextField(8);
        JTextField height = new JTextField(8);
        JTextField weight = new JTextField(8);
        JTextField sec_number = new JTextField(8);
        JTextArea special_mention = new JTextArea(3,8);


        //Pharmacy Inputs
        JLabel pharmacy_nbr_label = new JLabel("Pharmacy Number:");
        JLabel address_label = new JLabel("Address:");

        JTextField pharmacy_nbr = new JTextField(8);
        JTextField address = new JTextField(8);

        //set visibility of used items when signing up
        class set_visibility {
            public void set_visibility_patient(boolean check_visibility) {
                height.setVisible(check_visibility);
                weight.setVisible(check_visibility);
                sec_number.setVisible(check_visibility);
                special_mention.setVisible(check_visibility);
                height_label.setVisible(check_visibility);
                weight_label.setVisible(check_visibility);
                sec_number_label.setVisible(check_visibility);
                age_label.setVisible(check_visibility);
                age.setVisible(check_visibility);
                sec_number_label.setVisible(check_visibility);
                special_mention_label.setVisible(check_visibility);
            }
            public void set_visibility_doctor(boolean check_visibility){
                drRPPS_label.setVisible(check_visibility);
                drRPPS.setVisible(check_visibility);
            }
            public void set_visibility_pharmacy(boolean check_visibility){
                address.setVisible(check_visibility);
                pharmacy_nbr.setVisible(check_visibility);
                address_label.setVisible(check_visibility);
                pharmacy_nbr_label.setVisible(check_visibility);
            }
        }

        class check_users{
            public boolean isPresent(String user_val){
                for(var i : listUsers){
                    if(i.getUserName().equals(user_val)){
                        return true;
                    }
                }
                return false;
            }
        }


        JComboBox<String> profession_CB = new JComboBox<>();
        profession_CB.addItem("Doctor");
        profession_CB.addItem("Patient");
        profession_CB.addItem("Pharmacist");

        //Combo Box User Changes
        profession_CB.addActionListener(e -> {
            if(profession_CB.getSelectedItem() == "Doctor"){

                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(false);
                set_V.set_visibility_pharmacy(false);
                set_V.set_visibility_doctor(true);
            }
            else if (profession_CB.getSelectedItem() == "Patient"){
                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(true);
                set_V.set_visibility_doctor(false);
                set_V.set_visibility_pharmacy(false);
            }
            else if(profession_CB.getSelectedItem() == "Pharmacist"){
                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(false);
                set_V.set_visibility_doctor(false);
                set_V.set_visibility_pharmacy(true);
            }
        });

        //Sign Up button
        sign_up.addActionListener(e -> {
            if (is_in_sign_up[0]) {

                if (name.getText().equals("") || user.getText().equals("") || pass.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Some Fields are empty!!");

                }
                else if(profession_CB.getSelectedItem() == "Doctor" && !drRPPS.getText().matches("-?\\d+(\\.\\d+)?")){
                    JOptionPane.showMessageDialog(null, "RPPS is a number!!");

                } else if (profession_CB.getSelectedItem() == "Patient" && (!height.getText().matches("-?\\d+(\\.\\d+)?") || !weight.getText().matches("-?\\d+(\\.\\d+)?") || !sec_number.getText().matches("-?\\d+(\\.\\d+)?") || !age.getText().matches("-?\\d+(\\.\\d+)?"))) {
                    JOptionPane.showMessageDialog(null, "Wrong Input!!!");
                }
                else if(profession_CB.getSelectedItem() == "Pharmacist" && (!pharmacy_nbr.getText().matches("-?\\d+(\\.\\d+)?") || address.getText().equals(""))){
                    JOptionPane.showMessageDialog(null, "Wrong Input!!");
                }else {
                    check_users check_user = new check_users();
                    if(check_user.isPresent(user.getText())) {
                        JOptionPane.showMessageDialog(null, "User Already Present!!");
                    }
                    else{
                        if (profession_CB.getSelectedItem() == "Patient") {
                            listUsers.add(new Patient(name.getText(), user.getText(), String.valueOf(pass.getPassword()), parseInt(sec_number.getText()), parseInt(age.getText()), parseInt(weight.getText()), parseInt(height.getText()), special_mention.getText()));
                        } else if (profession_CB.getSelectedItem() == "Doctor") {
                            listUsers.add(new Doctor(name.getText(), user.getText(), String.valueOf(pass.getPassword()), parseInt(drRPPS.getText())));
                        } else if (profession_CB.getSelectedItem() == "Pharmacist") {
                            listUsers.add(new Pharmacy(name.getText(), user.getText(), String.valueOf(pass.getPassword()), parseInt(pharmacy_nbr.getText()), address.getText()));
                        }

                        JOptionPane.showMessageDialog(null, "You are now ready to Login");
                        is_in_sign_up[0] = false;
                        name_label.setVisible(false);
                        profession_label.setVisible(false);
                        name.setVisible(false);
                        login_btn.setVisible(true);
                        profession_CB.setVisible(false);
                        set_visibility set_V = new set_visibility();
                        set_V.set_visibility_pharmacy(false);
                        set_V.set_visibility_doctor(false);
                        set_V.set_visibility_patient(false);
                        name.setText("");
                        user.setText("");
                        pass.setText("");
                    }

                }
            }
            else{
                is_in_sign_up[0] = true;
                name_label.setVisible(true);
                profession_label.setVisible(true);
                profession_CB.setVisible(true);
                name.setVisible(true);
                login_btn.setVisible(false);
                drRPPS.setVisible(true);
                drRPPS_label.setVisible(true);
                profession_CB.setSelectedItem("Doctor");
            }
        });

        //Login Button
        login_btn.addActionListener(e -> {
            String input = user.getText();
            String password = pass.getText();
            if(profession_CB.getSelectedItem() == "Patient"){
                frame.dispose();
                new PatientFrame();
            } else if (profession_CB.getSelectedItem() == "Doctor") {
                frame.dispose();
                new DrFrame();
            }else if(profession_CB.getSelectedItem() == "Pharmacist"){
                frame.dispose();
                new PharmaFrame();
            }
        });

        name_label.setVisible(false);
        name.setVisible(false);
        panel1.add(name_label);
        panel1.add(name);

        profession_label.setVisible(false);
        profession_CB.setVisible(false);
        panel1.add(profession_label);
        panel1.add(profession_CB);

        panel1.add(new JLabel("Username:"));
        panel1.add(user);
        panel1.add(new JLabel("Password:"));
        panel1.add(pass);

        //Dr panel
        panel1.add(drRPPS_label);
        panel1.add(drRPPS);

        set_visibility set_V = new set_visibility();
        set_V.set_visibility_patient(false);
        set_V.set_visibility_doctor(false);
        set_V.set_visibility_pharmacy(false);

        //Patient Panel
        panel1.add(height_label);
        panel1.add(height);
        panel1.add(weight_label);
        panel1.add(weight);
        panel1.add(sec_number_label);
        panel1.add(sec_number);
        panel1.add(special_mention_label);
        panel1.add(special_mention);
        panel1.add(age_label);
        panel1.add(age);

        //Pharmacist Panel
        panel1.add(pharmacy_nbr_label);
        panel1.add(pharmacy_nbr);
        panel1.add(address_label);
        panel1.add(address);


        //login Panel
        panel1.add(login_btn);
        panel1.add(sign_up);



        frame.add(panel1);
        frame.setVisible(true);
    }
}
