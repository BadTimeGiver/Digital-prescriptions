package org.example.Frames;

import org.example.Main;
import org.example.Prescription;
import org.example.Users.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;
import static org.example.Main.dataFromMySQL;

public class PharmaFrame {

    public PharmaFrame(Pharmacy pharmacy) {

        JFrame frame = new JFrame("Pharmacy");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        // Panel
        JPanel edit_panel = new JPanel();
        JPanel history_presc_panel = new JPanel();
        JPanel new_presc_panel = new JPanel();
        // JPanel send_to_pharm_panel = new JPanel();

        // new prescriptions tab
        JLabel prescriptions_not_sent_label = new JLabel("New Prescriptions Received:");
        JComboBox<Prescription> combo_box_prescr = new JComboBox<>();
        for (var i : dataFromMySQL.PrescriptionNotValidated(pharmacy)) {
            combo_box_prescr.addItem(i);
        }
        JButton confirm_btn = new JButton("Confirm Prescription");
        JButton not_confirm_btn = new JButton("Don't Confirm Prescription");

        // adding elements to new prescriptions panel
        new_presc_panel.add(prescriptions_not_sent_label);
        new_presc_panel.add(combo_box_prescr);
        new_presc_panel.add(confirm_btn);
        new_presc_panel.add(not_confirm_btn);

        // history prescriptions tab
        JLabel prescriptions_sent_label = new JLabel("Prescriptions Confirmed:");
        JTextArea prescriptions_sent = new JTextArea(4, 8);

        // adding elements to history prescriptions panel
        history_presc_panel.add(prescriptions_sent_label);
        history_presc_panel.add(prescriptions_sent);

        // Edit tab
        JLabel name_label = new JLabel("Name:");
        JLabel username_label = new JLabel("UserName:");
        JLabel password_label = new JLabel("Password:");
        JLabel pharmacy_number_label = new JLabel("Pharmacy Num:");
        JLabel address_label = new JLabel("Address:");
        JButton edit_btn = new JButton("Save Changes");

        JTextField name = new JTextField(8);
        name.setText(pharmacy.getName());
        JPasswordField pass = new JPasswordField(8);
        pass.setText(pharmacy.getPassword());
        JTextField username = new JTextField(8);
        username.setText(pharmacy.getUserName());
        username.setEnabled(false);
        JTextField pharmacy_number = new JTextField(8);
        pharmacy_number.setText(pharmacy.getId());
        JTextField address = new JTextField(8);
        address.setText(pharmacy.getAddress());

        // adding elements to panel
        edit_panel.add(name_label);
        edit_panel.add(name);
        edit_panel.add(username_label);
        edit_panel.add(username);
        edit_panel.add(password_label);
        edit_panel.add(pass);
        edit_panel.add(pharmacy_number_label);
        edit_panel.add(pharmacy_number);
        edit_panel.add(address_label);
        edit_panel.add(address);
        edit_panel.add(edit_btn);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);

        JMenu prescrMenu = new JMenu("Prescriptions");
        prescrMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(prescrMenu);

        JMenuItem newPrescrItem = new JMenuItem("New", KeyEvent.VK_N);
        newPrescrItem.addActionListener(e -> {
            combo_box_prescr.removeAllItems();
            for (var i : dataFromMySQL.PrescriptionNotValidated(pharmacy)) {
                combo_box_prescr.addItem(i);
            }
            frame.remove(edit_panel);
            frame.add(new_presc_panel);
            frame.remove(history_presc_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(newPrescrItem);

        JMenuItem historyItem = new JMenuItem("History", KeyEvent.VK_H);
        historyItem.addActionListener(e -> {
            String result = "";
            for (var i : dataFromMySQL.PrescriptionValidated(pharmacy)) {
                result += i + "\n------------------\n";
            }
            prescriptions_sent.setText(result);
            frame.remove(edit_panel);
            frame.remove(new_presc_panel);
            frame.add(history_presc_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        prescrMenu.add(historyItem);

        JMenu account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_A);
        menuBar.add(account);

        JMenuItem edit = new JMenuItem("Edit");
        edit.addActionListener(e -> {
            frame.add(edit_panel);
            frame.remove(new_presc_panel);
            frame.remove(history_presc_panel);
            SwingUtilities.updateComponentTreeUI(frame);
        });
        account.add(edit);

        JMenuItem log_out = new JMenuItem("LogOut");
        log_out.addActionListener(e -> {
            frame.dispose();
            Main.start();
        });
        account.add(log_out);

        edit_btn.addActionListener(e -> {
            if ((name.getText().equals("") || pass.getText().equals(""))
                    || !pharmacy_number.getText().matches("-?\\d+(\\.\\d+)?") || address.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            } else {
                pharmacy.setName(name.getText());
                pharmacy.setId(pharmacy_number.getText());
                pharmacy.setPassword(pass.getText());
                pharmacy.setAddress(address.getText());
                dataFromMySQL.updatePharmacyInDB(pharmacy);
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
        });

        confirm_btn.addActionListener(e -> {
            if (combo_box_prescr.getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Update Successful");
                pharmacy.validate_prescription((Prescription) combo_box_prescr.getSelectedItem(), true);
                ((Prescription) combo_box_prescr.getSelectedItem()).setValidate(true);
                dataFromMySQL.updatePrescriptionInDB(dataFromMySQL
                        .findPrescriptionByNum(((Prescription) combo_box_prescr.getSelectedItem()).getId()));
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Inputs");
            }
        });

        not_confirm_btn.addActionListener(e -> {
            if (combo_box_prescr.getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Update Successful");
                pharmacy.validate_prescription((Prescription) combo_box_prescr.getSelectedItem(), false);
                ((Prescription) combo_box_prescr.getSelectedItem()).setValidate(false);
                dataFromMySQL.updatePrescriptionInDB(dataFromMySQL
                        .findPrescriptionByNum(((Prescription) combo_box_prescr.getSelectedItem()).getId()));

            } else {
                JOptionPane.showMessageDialog(null, "Wrong Inputs");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar);

        frame.add(new_presc_panel);

        frame.setVisible(true);
    }

}
