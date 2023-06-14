package org.example.Frames;

import org.example.Main;
import org.example.Users.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;

import static java.lang.Integer.parseInt;

public class DrFrame {

    public DrFrame(Doctor doctor) {



        JFrame frame = new JFrame("Doctor");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        //Panel
        JPanel panel1 = new JPanel();


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

        class set_visibility {
            public void set_visibility_edit(boolean check_visibility){
                name_label.setVisible(check_visibility);
                name.setVisible(check_visibility);
                username_label.setVisible(check_visibility);
                username.setVisible(check_visibility);
                pass.setVisible(check_visibility);
                password_label.setVisible(check_visibility);
                rpps_label.setVisible(check_visibility);
                rpps.setVisible(check_visibility);
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
        panel1.add(rpps_label);
        panel1.add(rpps);
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

        JMenu clientMenu = new JMenu("Patients");
        clientMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(clientMenu);

        JMenuItem viewClientsItem = new JMenuItem("View Clients");
        viewClientsItem.addActionListener(e -> {
            visibility.set_visibility_edit(false);
            JOptionPane.showMessageDialog(null, "View Clients");
        });
        clientMenu.add(viewClientsItem);

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
            if((name.getText().equals("") || pass.getText().equals(""))){
                JOptionPane.showMessageDialog(null, "Wrong Inputs!");
            }
            else if (rpps.getText().matches("-?\\d+(\\.\\d+)?")) {
                doctor.setName(name.getText());
                doctor.setRppsNumber(parseInt(rpps.getText()));
                doctor.setPassword(pass.getText());
                JOptionPane.showMessageDialog(null, "Update Successful");
            }
            else{
                JOptionPane.showMessageDialog(null, "RPPS should be a number");
            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);


        frame.setJMenuBar(menuBar);
        frame.add(panel1);
        frame.setVisible(true);
    }
}
