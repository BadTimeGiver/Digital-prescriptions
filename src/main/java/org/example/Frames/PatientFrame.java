package org.example.Frames;

import org.example.Main;
import org.example.Prescription;
import org.example.Users.Patient;
import org.example.Users.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;
import static org.example.Main.listUsers;
import static org.example.Main.dataFromMySQL;

public class PatientFrame {

    public PatientFrame(Patient patient) {

        JFrame frame = new JFrame("Patient");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        // Panel
        JPanel edit_panel = new JPanel();
        JPanel history_presc_panel = new JPanel();
        JPanel new_presc_panel = new JPanel();
        JPanel send_to_pharm_panel = new JPanel();
        JPanel receive_panel = new JPanel();

        // Edit tab
        JLabel name_label = new JLabel("Name:");
        JLabel username_label = new JLabel("UserName:");
        JLabel password_label = new JLabel("Password:");
        JLabel special_mentions_label = new JLabel("Special Mentions:");
        JLabel sec_number_label = new JLabel("Security Number:");
        JLabel weight_label = new JLabel("Weight:");
        JLabel height_label = new JLabel("Height:");
        JLabel age_label = new JLabel("Age:");
        JButton edit_btn = new JButton("Save Changes");

        JTextField name = new JTextField(8);
        name.setText(patient.getName());
        JPasswordField pass = new JPasswordField(8);
        pass.setText(patient.getPassword());
        JTextField username = new JTextField(8);
        username.setText(patient.getUserName());
        username.setEnabled(false);
        JTextField sec_number = new JTextField(8);
        sec_number.setText(patient.getNss());
        JTextField special_mentions = new JTextField(8);
        special_mentions.setText(patient.getSpecialMentions());
        JTextField weight = new JTextField(8);
        weight.setText(Integer.toString(patient.getWeight()));
        JTextField height = new JTextField(8);
        height.setText(Integer.toString(patient.getHeight()));
        JTextField age = new JTextField(8);
        age.setText(Integer.toString(patient.getAge()));

        // adding elements to edit profile panel
        edit_panel.add(name_label);
        edit_panel.add(name);
        edit_panel.add(username_label);
        edit_panel.add(username);
        edit_panel.add(password_label);
        edit_panel.add(pass);
        edit_panel.add(sec_number_label);
        edit_panel.add(sec_number);
        edit_panel.add(age_label);
        edit_panel.add(age);
        edit_panel.add(weight_label);
        edit_panel.add(weight);
        edit_panel.add(height_label);
        edit_panel.add(height);
        edit_panel.add(special_mentions_label);
        edit_panel.add(special_mentions);
        edit_panel.add(edit_btn);

        // new prescriptions tab
        JLabel prescriptions_not_sent_label = new JLabel("Prescriptions not sent:");
        JTextArea prescriptions_not_sent = new JTextArea(4, 8);

        // adding elements to new prescriptions panel
        new_presc_panel.add(prescriptions_not_sent_label);
        new_presc_panel.add(prescriptions_not_sent);

        // history prescriptions tab
        JLabel prescriptions_sent_label = new JLabel("Prescriptions sent:");
        JTextArea prescriptions_sent = new JTextArea(4, 8);
        // adding elements to history prescriptions panel
        history_presc_panel.add(prescriptions_sent_label);
        history_presc_panel.add(prescriptions_sent);

        // Received items from pharmacy tab
        JLabel combo_box_label = new JLabel("Choose a prescription:");
        JLabel pharmacy_label = new JLabel("Pharmacy User:");
        JTextField pharmacy = new JTextField(8);
        JComboBox<Prescription> combo_box_prescr = new JComboBox<>();
        for (var i : patient.getPrescriptions_NOT_sent()) {
            combo_box_prescr.addItem(i);
        }
        JButton send_btn = new JButton("Send to Pharmacy");

        // adding elements to the receiving panel
        send_to_pharm_panel.add(combo_box_label);
        send_to_pharm_panel.add(combo_box_prescr);
        send_to_pharm_panel.add(pharmacy_label);
        send_to_pharm_panel.add(pharmacy);
        send_to_pharm_panel.add(send_btn);

        // send to pharmacy a prescriptions tab
        JLabel prescriptions_received_label = new JLabel("Prescriptions:");
        JTextArea prescriptions_received = new JTextArea(4, 8);

        // adding elements to send a prescription to the pharmacy panel
        receive_panel.add(prescriptions_received_label);
        receive_panel.add(prescriptions_received);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);

        JMenu prescrMenu = new JMenu("Prescriptions");
        prescrMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(prescrMenu);

        JMenuItem newPrescrItem = new JMenuItem("New", KeyEvent.VK_N);
        newPrescrItem.addActionListener(e -> {
            String result = "";
            for (var i : dataFromMySQL.PrescriptionNotSentFromPatient(patient)) {
                result += i + "\n------------------\n";
            }
            prescriptions_not_sent.setText(result);
            frame.remove(edit_panel);
            frame.add(new_presc_panel);
            frame.remove(history_presc_panel);
            frame.remove(send_to_pharm_panel);
            frame.remove(receive_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(newPrescrItem);

        JMenuItem historyItem = new JMenuItem("History", KeyEvent.VK_H);
        historyItem.addActionListener(e -> {
            String result = "";
            for (var i : patient.getPrescriptions_sent()) {
                result += i + "\n------------------\n";
            }
            prescriptions_sent.setText(result);
            frame.remove(edit_panel);
            frame.add(history_presc_panel);
            frame.remove(new_presc_panel);
            frame.remove(send_to_pharm_panel);
            frame.remove(receive_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(historyItem);
        JMenu sendMenu = new JMenu("Send");
        sendMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(sendMenu);

        JMenuItem viewClientsItem = new JMenuItem("To Pharmacy");
        viewClientsItem.addActionListener(e -> {
            combo_box_prescr.removeAllItems();
            for (var i : patient.getPrescriptions_NOT_sent()) {
                combo_box_prescr.addItem(i);
            }
            frame.remove(edit_panel);
            frame.remove(new_presc_panel);
            frame.remove(history_presc_panel);
            frame.add(send_to_pharm_panel);
            frame.remove(receive_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        sendMenu.add(viewClientsItem);

        JMenu receiveMenu = new JMenu("Receive");
        receiveMenu.setMnemonic(KeyEvent.VK_R);
        menuBar.add(receiveMenu);

        JMenuItem viewClientsItemReceived = new JMenuItem("From Pharmacy");
        viewClientsItemReceived.addActionListener(e -> {
            String result = "";
            for (var i : patient.getReturned_prescriptions()) {
                result += ((i.isValidate() ? "CONFIRMED\n" : "NOT CONFIRMED\n") + i + "\n------------------\n");
            }
            prescriptions_received.setText(result);
            frame.remove(edit_panel);
            frame.remove(history_presc_panel);
            frame.remove(new_presc_panel);
            frame.remove(send_to_pharm_panel);
            frame.add(receive_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        receiveMenu.add(viewClientsItemReceived);

        JMenu account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_A);
        menuBar.add(account);

        JMenuItem edit = new JMenuItem("Edit");
        edit.addActionListener(e -> {
            frame.add(edit_panel);
            frame.remove(new_presc_panel);
            frame.remove(history_presc_panel);
            frame.remove(send_to_pharm_panel);
            frame.remove(receive_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        account.add(edit);

        JMenuItem log_out = new JMenuItem("LogOut");
        log_out.addActionListener(e -> {
            frame.dispose();
            Main.start();
        });
        account.add(log_out);

        // check if a user is present
        class check_users {
            public Pharmacy isPresent(String user_val) {
                for (var i : listUsers) {
                    if (i.getUserName().equals(user_val) && i instanceof Pharmacy) {
                        return (Pharmacy) i;
                    }
                }
                return null;
            }
        }

        edit_btn.addActionListener(e -> {
            if ((name.getText().equals("") || pass.getText().equals(""))
                    || !sec_number.getText().matches("-?\\d+(\\.\\d+)?") || !age.getText().matches("-?\\d+(\\.\\d+)?")
                    || !weight.getText().matches("-?\\d+(\\.\\d+)?") || !height.getText().matches("-?\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            } else {
                patient.setName(name.getText());
                patient.setNss(sec_number.getText());
                patient.setPassword(pass.getText());
                patient.setSpecialMentions(special_mentions.getText());
                patient.setAge(parseInt(age.getText()));
                patient.setWeight(parseInt(weight.getText()));
                patient.setHeight(parseInt(height.getText()));
                dataFromMySQL.updatePatientInDB(patient);
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
        });

        send_btn.addActionListener(e -> {
            if (pharmacy.getText().isEmpty() || patient.getPrescriptions_NOT_sent().size() == 0) {
                JOptionPane.showMessageDialog(null, "Wrong Input");
            } else {
                check_users check_user = new check_users();
                Pharmacy temp_pharmacy;
                if ((temp_pharmacy = check_user.isPresent(pharmacy.getText())) != null) {
                    patient.sendPrescription((Prescription) combo_box_prescr.getSelectedItem(), temp_pharmacy);
                    JOptionPane.showMessageDialog(null, "Prescription sent successfully!");

                } else {
                    JOptionPane.showMessageDialog(null, "Couldn't find User!");

                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        frame.add(send_to_pharm_panel);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

}