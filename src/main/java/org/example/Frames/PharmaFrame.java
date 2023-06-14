package org.example.Frames;

import org.example.Main;
import org.example.Users.Pharmacy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;

public class PharmaFrame {

    public PharmaFrame(Pharmacy pharmacy) {

        JFrame frame = new JFrame("Pharmacy");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        //Panel
        JPanel panel1 = new JPanel();

        //Edit tab
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
        pharmacy_number.setText(Integer.toString(pharmacy.getPharamcyNbr()));
        JTextField address = new JTextField(8);
        address.setText(pharmacy.getAddress());

        class set_visibility {
            public void set_visibility_edit(boolean check_visibility){
                name_label.setVisible(check_visibility);
                name.setVisible(check_visibility);
                username_label.setVisible(check_visibility);
                username.setVisible(check_visibility);
                pass.setVisible(check_visibility);
                password_label.setVisible(check_visibility);
                pharmacy_number.setVisible(check_visibility);
                pharmacy_number_label.setVisible(check_visibility);
                address.setVisible(check_visibility);
                address_label.setVisible(check_visibility);
                edit_btn.setVisible(check_visibility);

            }
        }

        set_visibility visibility = new set_visibility();

        //adding elements to panel
        visibility.set_visibility_edit(false);
        panel1.add(name_label);
        panel1.add(name);
        panel1.add(username_label);
        panel1.add(username);
        panel1.add(password_label);
        panel1.add(pass);
        panel1.add(pharmacy_number_label);
        panel1.add(pharmacy_number);
        panel1.add(address_label);
        panel1.add(address);
        panel1.add(edit_btn);


        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);

        JMenu prescrMenu = new JMenu("Prescriptions");
        prescrMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(prescrMenu);

        JMenuItem newPrescrItem = new JMenuItem("New", KeyEvent.VK_N);
        newPrescrItem.addActionListener(e -> {
            visibility.set_visibility_edit(false);
            JOptionPane.showMessageDialog(null, "New Prescription");
        });
        prescrMenu.add(newPrescrItem);

        JMenuItem historyItem = new JMenuItem("History", KeyEvent.VK_H);
        historyItem.addActionListener(e -> {
            visibility.set_visibility_edit(false);
            JOptionPane.showMessageDialog(null, "Prescription History");
        });
        prescrMenu.add(historyItem);

        JMenu account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_A);
        menuBar.add(account);

        JMenuItem edit = new JMenuItem("Edit");
        edit.addActionListener(e -> {
            visibility.set_visibility_edit(true);
        });
        account.add(edit);

        JMenuItem log_out = new JMenuItem("LogOut");
        log_out.addActionListener(e -> {
            frame.dispose();
            Main.start();
        });
        account.add(log_out);



        edit_btn.addActionListener(e -> {
            if((name.getText().equals("") || pass.getText().equals("")) || !pharmacy_number.getText().matches("-?\\d+(\\.\\d+)?") || address.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            }
            else {
                pharmacy.setName(name.getText());
                pharmacy.setPharamcyNbr(parseInt(pharmacy_number.getText()));
                pharmacy.setPassword(pass.getText());
                pharmacy.setAddress(address.getText());
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(menuBar);

        frame.add(panel1);

        frame.setVisible(true);
    }

}
