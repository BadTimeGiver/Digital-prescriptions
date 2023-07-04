package org.example;

import org.example.Frames.DrFrame;
import org.example.Frames.PatientFrame;
import org.example.Frames.PharmaFrame;
import org.example.Users.Doctor;
import org.example.Users.Patient;
import org.example.Users.Pharmacy;
import org.example.Users.User;
import org.example.Users.DataFromMySQL;

import javax.print.Doc;
import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import java.time.LocalDate;

public class Main {
    public static ArrayList<User> listUsers = new ArrayList<>();
    public static DataFromMySQL dataFromMySQL;

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ordonnancement";
        String username = "root";
        String password = "root";
        String[] connexionSQL = { jdbcUrl, username, password };
        dataFromMySQL = new DataFromMySQL(connexionSQL);
        dataFromMySQL.initData();
        start();
    }

    public static void start() {

        JFrame frame = new JFrame("Ordo'digit");
        frame.setSize(400, 300);

        JPanel panel1 = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        // User Inputs
        JButton login_btn = new JButton("Login");
        JButton sign_up = new JButton("Sign Up");
        JButton back_btn = new JButton("Back");
        final Boolean[] is_in_sign_up = { false };
        JLabel username_label = new JLabel("RPSS:");
        JTextField user = new JTextField(8);
        JTextField name = new JTextField(8);
        JPasswordField pass = new JPasswordField(8);
        JLabel name_label = new JLabel("Name:");
        JLabel profession_label = new JLabel("Profession:");

        // Patient Inputs
        JLabel height_label = new JLabel("Height:");
        JLabel weight_label = new JLabel("Weight:");
        JLabel special_mention_label = new JLabel("Special Mentions:");
        JLabel age_label = new JLabel("Age:");

        JTextField age = new JTextField(8);
        JTextField height = new JTextField(8);
        JTextField weight = new JTextField(8);
        JTextArea special_mention = new JTextArea(3, 8);

        // Pharmacy Inputs
        JLabel address_label = new JLabel("Address:");
        JTextField address = new JTextField(8);

        // set visibility of used items when signing up
        class set_visibility {
            public void set_visibility_patient(boolean check_visibility) {
                height.setVisible(check_visibility);
                weight.setVisible(check_visibility);
                special_mention.setVisible(check_visibility);
                height_label.setVisible(check_visibility);
                weight_label.setVisible(check_visibility);
                age_label.setVisible(check_visibility);
                age.setVisible(check_visibility);
                special_mention_label.setVisible(check_visibility);
            }

            public void set_visibility_doctor(boolean check_visibility) {
            }

            public void set_visibility_pharmacy(boolean check_visibility) {
                address.setVisible(check_visibility);
                address_label.setVisible(check_visibility);
            }
        }

        JComboBox<String> profession_CB = new JComboBox<>();
        profession_CB.addItem("Doctor");
        profession_CB.addItem("Patient");
        profession_CB.addItem("Pharmacist");

        // Combo Box User Changes
        profession_CB.addActionListener(e -> {
            if (profession_CB.getSelectedItem() == "Doctor" && is_in_sign_up[0]) {
                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(false);
                set_V.set_visibility_pharmacy(false);
                set_V.set_visibility_doctor(true);
            } else if (profession_CB.getSelectedItem() == "Patient" && is_in_sign_up[0]) {
                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(true);
                set_V.set_visibility_doctor(false);
                set_V.set_visibility_pharmacy(false);
            } else if (profession_CB.getSelectedItem() == "Pharmacist" && is_in_sign_up[0]) {
                set_visibility set_V = new set_visibility();
                set_V.set_visibility_patient(false);
                set_V.set_visibility_doctor(false);
                set_V.set_visibility_pharmacy(true);
            }
        });

        // Sign Up button
        sign_up.addActionListener(e -> {
            if (is_in_sign_up[0]) {

                if (name.getText().isEmpty() || pass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Some Fields are empty!!");

                } else if (profession_CB.getSelectedItem() == "Doctor"
                        && (!user.getText().matches("-?\\d+(\\.\\d+)?") || user.getText().length() != 11)) {
                    JOptionPane.showMessageDialog(null, "RPPS must be a 11 length number!!");

                } else if (profession_CB.getSelectedItem() == "Patient"
                        && (!height.getText().matches("-?\\d+(\\.\\d+)?")
                                || !weight.getText().matches("-?\\d+(\\.\\d+)?")
                                || !user.getText().matches("-?\\d+(\\.\\d+)?")
                                || user.getText().length() != 13
                                || !age.getText().matches("-?\\d+(\\.\\d+)?"))) {
                    JOptionPane.showMessageDialog(null, "Wrong Input!!!");
                } else if (profession_CB.getSelectedItem() == "Pharmacist"
                        && ((!user.getText().matches("-?\\d+(\\.\\d+)?")
                                || user.getText().length() != 6)
                                || address.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Wrong Input!!");
                } else {
                    if (dataFromMySQL.isExistingDoctor(user.getText()) != null
                            || dataFromMySQL.isExistingPatient(user.getText()) != null
                            || dataFromMySQL.isExistingPharmacy(user.getText()) != null) {
                        JOptionPane.showMessageDialog(null, "User Already Present!!");
                    } else {
                        if (profession_CB.getSelectedItem() == "Patient") {
                            dataFromMySQL.addPatientToDB(user.getText(), name.getText(),
                                    String.valueOf(pass.getPassword()), parseInt(age.getText()),
                                    parseInt(weight.getText()), parseInt(height.getText()), special_mention.getText());
                        } else if (profession_CB.getSelectedItem() == "Doctor") {
                            dataFromMySQL.addDoctorToDB(name.getText(), String.valueOf(pass.getPassword()),
                                    user.getText());

                        } else if (profession_CB.getSelectedItem() == "Pharmacist") {
                            dataFromMySQL.addPharmacyToDB(user.getText(), name.getText(),
                                    String.valueOf(pass.getPassword()), address.getText());
                        }

                        JOptionPane.showMessageDialog(null, "You are now ready to Login");
                        is_in_sign_up[0] = false;
                        name_label.setVisible(false);
                        profession_label.setVisible(false);
                        name.setVisible(false);
                        login_btn.setVisible(true);
                        back_btn.setVisible(false);
                        set_visibility set_V = new set_visibility();
                        set_V.set_visibility_pharmacy(false);
                        set_V.set_visibility_doctor(false);
                        set_V.set_visibility_patient(false);
                        name.setText("");
                        user.setText("");
                        pass.setText("");
                    }

                }
            } else {
                is_in_sign_up[0] = true;
                name_label.setVisible(true);
                profession_label.setVisible(true);
                name.setVisible(true);
                back_btn.setVisible(true);
                login_btn.setVisible(false);
                profession_CB.setSelectedItem("Doctor");
            }
        });

        // Login Button
        login_btn.addActionListener(e -> {
            String input = user.getText();
            String password = pass.getText();
            User temp_user;

            // Rémi
            if ((temp_user = dataFromMySQL.isExistingPatient(input)) != null
                    && profession_CB.getSelectedItem() == "Patient") {

                if (temp_user.getPassword().equals(password)) {
                    frame.dispose();
                    new PatientFrame((Patient) temp_user);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!");
                }
            }

            else if ((temp_user = dataFromMySQL.isExistingDoctor(input)) != null
                    && profession_CB.getSelectedItem() == "Doctor") {

                if (temp_user.getPassword().equals(password)) {
                    frame.dispose();
                    new DrFrame((Doctor) temp_user);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!");
                }
            }

            else if ((temp_user = dataFromMySQL.isExistingPharmacy(input)) != null
                    && profession_CB.getSelectedItem() == "Pharmacist") {

                if (temp_user.getPassword().equals(password)) {
                    frame.dispose();
                    new PharmaFrame((Pharmacy) temp_user);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!");
                }
            }

            if (temp_user == null)
                JOptionPane.showMessageDialog(null, "User Not Found!");

            // Gilbert
            /*
             * if ((temp_user = check_user.isPresent(input)) != null) {
             * 
             * if (temp_user.getPassword().equals(password) && temp_user instanceof Patient)
             * {
             * frame.dispose();
             * new PatientFrame((Patient) temp_user);
             * } else if (temp_user.getPassword().equals(password) && temp_user instanceof
             * Doctor) {
             * frame.dispose();
             * new DrFrame((Doctor) temp_user);
             * } else if (temp_user.getPassword().equals(password) && temp_user instanceof
             * Pharmacy) {
             * frame.dispose();
             * new PharmaFrame((Pharmacy) temp_user);
             * } else {
             * JOptionPane.showMessageDialog(null, "Incorrect Password!");
             * }
             * 
             * } else {
             * JOptionPane.showMessageDialog(null, "User Not Found!");
             * }
             */
        });

        profession_CB.addActionListener(e -> {
            if (profession_CB.getSelectedItem() == "Doctor") {
                username_label.setText("RPSS:");
                System.out.println("Test");
            } else if (profession_CB.getSelectedItem() == "Patient") {
                username_label.setText("NSS:");
            } else if (profession_CB.getSelectedItem() == "Pharmacist") {
                username_label.setText("Pharmacy Number:");
            }
        });

        back_btn.addActionListener(e -> {
            is_in_sign_up[0] = false;
            name_label.setVisible(false);
            profession_label.setVisible(false);
            name.setVisible(false);
            login_btn.setVisible(true);
            back_btn.setVisible(false);
            set_visibility set_V = new set_visibility();
            set_V.set_visibility_pharmacy(false);
            set_V.set_visibility_doctor(false);
            set_V.set_visibility_patient(false);
        });

        name_label.setVisible(false);
        name.setVisible(false);
        panel1.add(name_label);
        panel1.add(name);

        profession_label.setVisible(false);
        panel1.add(profession_label);
        panel1.add(profession_CB);

        panel1.add(username_label);
        panel1.add(user);
        panel1.add(new JLabel("Password:"));
        panel1.add(pass);

        // Dr panel

        set_visibility set_V = new set_visibility();
        set_V.set_visibility_patient(false);
        set_V.set_visibility_doctor(false);
        set_V.set_visibility_pharmacy(false);

        // Patient Panel
        panel1.add(height_label);
        panel1.add(height);
        panel1.add(weight_label);
        panel1.add(weight);
        panel1.add(special_mention_label);
        panel1.add(special_mention);
        panel1.add(age_label);
        panel1.add(age);

        // Pharmacist Panel
        panel1.add(address_label);
        panel1.add(address);

        // login Panel
        panel1.add(login_btn);
        panel1.add(sign_up);
        back_btn.setVisible(false);
        panel1.add(back_btn);

        frame.add(panel1);
        frame.setVisible(true);
    }
}
