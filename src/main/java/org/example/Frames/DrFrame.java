package org.example.Frames;

import org.example.Main;
import org.example.Prescription;
import org.example.Users.Doctor;
import org.example.Users.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;
import static org.example.Main.listUsers;

public class DrFrame {

    public DrFrame(Doctor doctor) {


        JFrame frame = new JFrame("Doctor");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        //Panel
        JPanel edit_panel = new JPanel();
        JPanel new_pres_panel = new JPanel();
        JPanel history_pres_panel = new JPanel();
        JPanel client_panel = new JPanel();


        //Edit tab
        JLabel name_label = new JLabel("Name:");
        JLabel username_label = new JLabel("UserName:");
        JLabel password_label = new JLabel("Password:");
        JLabel rpps_label = new JLabel("RPPS:");
        JButton edit_btn = new JButton("Save Changes");

        JTextField name = new JTextField(8);
        name.setText(doctor.getName());
        JPasswordField pass = new JPasswordField(8);
        pass.setText(doctor.getPassword());
        JTextField username = new JTextField(8);
        username.setText(doctor.getUserName());
        username.setEnabled(false);
        JTextField rpps = new JTextField(8);
        rpps.setText(Integer.toString(doctor.getRppsNumber()));

        //adding elements to edit panel
        edit_panel.add(name_label);
        edit_panel.add(name);
        edit_panel.add(username_label);
        edit_panel.add(username);
        edit_panel.add(password_label);
        edit_panel.add(pass);
        edit_panel.add(rpps_label);
        edit_panel.add(rpps);
        edit_panel.add(edit_btn);

        //New Prescription tab
        JLabel date_label = new JLabel("Date (YYYY-MM-DD):");
        JLabel instruction_label = new JLabel("Instruction:");
        JLabel doctor_rpps_label = new JLabel("Doctor RPPS:");
        JLabel patient_user_label = new JLabel("Patient User:");
        JLabel medicines_label = new JLabel("Medicines:");
        JButton create_prescription_btn = new JButton("Confirm");

        JTextField date = new JTextField(8);
        date.setText("1900-01-01");
        JTextField doctorRPPS = new JTextField(8);
        JTextField patientUser = new JTextField(8);
        doctorRPPS.setText(Integer.toString(doctor.getRppsNumber()));
        doctorRPPS.setEnabled(false);
        JTextArea instructions = new JTextArea(2, 8);
        JTextArea medicines = new JTextArea(2, 8);


        //adding elements to new prescription panel
        new_pres_panel.add(date_label);
        new_pres_panel.add(date);
        new_pres_panel.add(instruction_label);
        new_pres_panel.add(instructions);
        new_pres_panel.add(doctor_rpps_label);
        new_pres_panel.add(doctorRPPS);
        new_pres_panel.add(patient_user_label);
        new_pres_panel.add(patientUser);
        new_pres_panel.add(medicines_label);
        new_pres_panel.add(medicines);
        new_pres_panel.add(create_prescription_btn);

        //History of Prescriptions tab
        JLabel history_Prescriptions_label = new JLabel("Prescriptions");
        JTextArea history_prescriptions = new JTextArea(4, 8);

        //adding elements to history prescriptions panel
        history_pres_panel.add(history_Prescriptions_label);
        history_pres_panel.add(history_prescriptions);


        //Clients tab
        JLabel clients_label = new JLabel("Clients");
        JTextArea clients = new JTextArea(4, 8);

        //adding elements to Client panel
        client_panel.add(clients_label);
        client_panel.add(clients);


        //Menu Bar
        JMenuBar menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);

        JMenu prescrMenu = new JMenu("Prescriptions");
        prescrMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(prescrMenu);

        JMenuItem newPrescrItem = new JMenuItem("New", KeyEvent.VK_N);
        newPrescrItem.addActionListener(e -> {
            frame.remove(edit_panel);
            frame.add(new_pres_panel);
            frame.remove(client_panel);
            frame.remove(history_pres_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(newPrescrItem);

        JMenuItem historyItem = new JMenuItem("History", KeyEvent.VK_H);
        historyItem.addActionListener(e -> {
            String result = "";
            for (var i : doctor.getPrescriptions()) {
                result += i + "\n------------------\n";
            }
            history_prescriptions.setText(result);
            frame.remove(edit_panel);
            frame.remove(new_pres_panel);
            frame.remove(client_panel);
            frame.add(history_pres_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(historyItem);

        JMenu clientMenu = new JMenu("Patients");
        clientMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(clientMenu);

        JMenuItem viewClientsItem = new JMenuItem("View Clients");
        viewClientsItem.addActionListener(e -> {
            String result = "";
            for (var i : doctor.getPatients()) {
                result += i + "\n------------------\n";
            }
            clients.setText(result);
            frame.remove(edit_panel);
            frame.remove(new_pres_panel);
            frame.add(client_panel);
            frame.remove(history_pres_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        clientMenu.add(viewClientsItem);

        JMenu account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_A);
        menuBar.add(account);

        JMenuItem edit = new JMenuItem("Edit");
        edit.addActionListener(e -> {
            frame.add(edit_panel);
            frame.remove(new_pres_panel);
            frame.remove(client_panel);
            frame.remove(history_pres_panel);
            SwingUtilities.updateComponentTreeUI(frame);

        });
        account.add(edit);

        JMenuItem log_out = new JMenuItem("LogOut");
        log_out.addActionListener(e -> {
            frame.dispose();
            Main.start();
        });
        account.add(log_out);

        //check if a user is present
        class check_users {
            public Patient isPresent(String user_val) {
                for (var i : listUsers) {
                    if (i.getUserName().equals(user_val) && i instanceof Patient) {
                        return (Patient) i;
                    }
                }
                return null;
            }
        }

        //edit button
        edit_btn.addActionListener(e -> {
            if ((name.getText().isEmpty() || pass.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            } else if (rpps.getText().matches("-?\\d+(\\.\\d+)?")) {
                doctor.setName(name.getText());
                doctor.setRppsNumber(parseInt(rpps.getText()));
                doctor.setPassword(pass.getText());
                JOptionPane.showMessageDialog(null, "Update Successful");
            } else {
                JOptionPane.showMessageDialog(null, "RPPS should be a number");
            }
        });

        //new prescription
        create_prescription_btn.addActionListener(e -> {
            if (medicines.getText().isEmpty() || patientUser.getText().isEmpty() || instructions.getText().isEmpty() || !date.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(null, "Wrong Input");
            } else if (parseInt(date.getText().substring(0, 4)) >= 1900 &&
                    parseInt(date.getText().substring(0, 4)) < 2024 &&
                    parseInt(date.getText().substring(5, 7)) >= 1 &&
                    parseInt(date.getText().substring(5, 7)) <= 12 &&
                    parseInt(date.getText().substring(8, 10)) >= 1 &&
                    parseInt(date.getText().substring(8, 10)) <= 31) {
                check_users check_user = new check_users();
                Patient temp_patient;
                if ((temp_patient = check_user.isPresent(patientUser.getText())) != null) {
                    ArrayList<String> temp_arr = new ArrayList<>();
                    Collections.addAll(temp_arr, medicines.getText().split("\n"));

                    doctor.addPrescription(temp_patient, new Prescription(temp_arr,LocalDate.parse(date.getText()), instructions.getText(), doctor.getRppsNumber()));
                    JOptionPane.showMessageDialog(null, "New Prescription added successfully!");

                } else {
                    JOptionPane.showMessageDialog(null, "Couldn't find User!");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Date should be between 1900-01-01 && 2023-12-31!");
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);


        frame.setJMenuBar(menuBar);
        frame.add(new_pres_panel);

        frame.setVisible(true);
    }
}
