package org.example.Frames;

import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.Key;

public class DrFrame {

    public DrFrame() {

        JFrame frame = new JFrame("Doctor");
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        UIManager.put("MenuBar.background", Color.ORANGE);

        JMenu prescrMenu = new JMenu("Prescriptions");
        prescrMenu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(prescrMenu);

        JMenuItem newPrescrItem = new JMenuItem("New", KeyEvent.VK_N);
        newPrescrItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "New Prescription");
        });
        prescrMenu.add(newPrescrItem);

        JMenuItem historyItem = new JMenuItem("History", KeyEvent.VK_H);
        historyItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Prescription History");
        });
        prescrMenu.add(historyItem);

        JMenu clientMenu = new JMenu("Patients");
        clientMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(clientMenu);

        JMenuItem viewClientsItem = new JMenuItem("View Clients");
        viewClientsItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "View Clients");
        });
        clientMenu.add(viewClientsItem);

        JMenu account = new JMenu("Account");
        account.setMnemonic(KeyEvent.VK_A);
        menuBar.add(account);

        JMenuItem edit = new JMenuItem("Edit");
        edit.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Edit Account");
        });
        account.add(edit);

        JMenuItem log_out = new JMenuItem("LogOut");
        log_out.addActionListener(e -> {
            frame.dispose();
            Main.start();
        });
        account.add(log_out);

        //JPanel panel1 = new JPanel();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);


        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
}
