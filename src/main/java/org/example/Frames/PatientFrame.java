package org.example.Frames;

import org.example.Main;
import org.example.Users.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Integer.parseInt;

public class PatientFrame {

    public PatientFrame(Patient patient) {

        JFrame frame = new JFrame("Patient");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        //Panel
        JPanel panel1 = new JPanel();

        //Edit tab
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
        sec_number.setText(Integer.toString(patient.getNumeroSec()));
        JTextField special_mentions = new JTextField(8);
        special_mentions.setText(patient.getSpecialMentions());
        JTextField weight = new JTextField(8);
        weight.setText(Integer.toString(patient.getWeight()));
        JTextField height = new JTextField(8);
        height.setText(Integer.toString(patient.getHeight()));
        JTextField age = new JTextField(8);
        age.setText(Integer.toString(patient.getAge()));

        class set_visibility {
            public void set_visibility_edit(boolean check_visibility){
                name_label.setVisible(check_visibility);
                name.setVisible(check_visibility);
                username_label.setVisible(check_visibility);
                username.setVisible(check_visibility);
                pass.setVisible(check_visibility);
                password_label.setVisible(check_visibility);
                special_mentions.setVisible(check_visibility);
                special_mentions_label.setVisible(check_visibility);
                sec_number_label.setVisible(check_visibility);
                sec_number.setVisible(check_visibility);
                weight_label.setVisible(check_visibility);
                weight.setVisible(check_visibility);
                height_label.setVisible(check_visibility);
                height.setVisible(check_visibility);
                age_label.setVisible(check_visibility);
                age.setVisible(check_visibility);
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
        panel1.add(sec_number_label);
        panel1.add(sec_number);
        panel1.add(age_label);
        panel1.add(age);
        panel1.add(weight_label);
        panel1.add(weight);
        panel1.add(height_label);
        panel1.add(height);
        panel1.add(special_mentions_label);
        panel1.add(special_mentions);
        panel1.add(edit_btn);

        //Menu Bar
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

        JMenu sendMenu = new JMenu("Send");
        sendMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(sendMenu);

        JMenuItem viewClientsItem = new JMenuItem("To Pharmacy");
        viewClientsItem.addActionListener(e -> {
            visibility.set_visibility_edit(false);
            JOptionPane.showMessageDialog(null, "Send to Pharmacy");
        });
        sendMenu.add(viewClientsItem);

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
            if((name.getText().equals("") || pass.getText().equals("")) || !sec_number.getText().matches("-?\\d+(\\.\\d+)?") || !age.getText().matches("-?\\d+(\\.\\d+)?") || !weight.getText().matches("-?\\d+(\\.\\d+)?") || !height.getText().matches("-?\\d+(\\.\\d+)?")){
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            }
            else {
                patient.setName(name.getText());
                patient.setNumeroSec(parseInt(sec_number.getText()));
                patient.setPassword(pass.getText());
                patient.setSpecialMentions(special_mentions.getText());
                patient.setAge(parseInt(age.getText()));
                patient.setWeight(parseInt(weight.getText()));
                patient.setHeight(parseInt(height.getText()));
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
        });



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);





        frame.setJMenuBar(menuBar);
        frame.add(panel1);

        frame.setVisible(true);
    }

}