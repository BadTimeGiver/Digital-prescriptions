package org.example.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataFromMySQL {
    private ArrayList<Patient> listPatients = new ArrayList<>();
    private ArrayList<Doctor> listDoctors = new ArrayList<>();
    private ArrayList<Pharmacy> listPharmacies = new ArrayList<>();

    public DataFromMySQL() {
    }

    public ArrayList<Patient> getListPatients() {
        return listPatients;
    }

    public ArrayList<Doctor> getListDoctors() {
        return listDoctors;
    }

    public ArrayList<Pharmacy> getListPharmacies() {
        return listPharmacies;
    }

    public void addPatient(Patient p) {
        listPatients.add(p);
    }

    public void addDoctor(Doctor d) {
        listDoctors.add(d);
    }

    public void addPharmacy(Pharmacy p) {
        listPharmacies.add(p);
    }

    public void initData(String[] connexionSQL) {
        initPatients(connexionSQL);
        initDoctors(connexionSQL);
        initPharmacies(connexionSQL);
    }

    public void initPatients(String[] connexionSQL) {

        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM patients";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int nss = resultSet.getInt("nss");
                String name = resultSet.getString("name");
                String pwd = resultSet.getString("password");
                int age = resultSet.getInt("age");
                int weight = resultSet.getInt("weight");
                int height = resultSet.getInt("height");
                String special_mentions = resultSet.getString("special_mentions");

                // Create a new Patient object with the retrieved data
                Patient patient = new Patient(name, pwd, nss, age, weight, height, special_mentions);

                // Add the patient to the listPatients
                listPatients.add(patient);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDoctors(String[] connexionSQL) {

        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM doctors";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int rpps = resultSet.getInt("rpps");
                String name = resultSet.getString("name");
                String pwd = resultSet.getString("password");

                // Create a new Patient object with the retrieved data
                Doctor doctor = new Doctor(name, pwd, rpps);

                // Add the patient to the listPatients
                listDoctors.add(doctor);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initPharmacies(String[] connexionSQL) {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM pharmacies";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String pwd = resultSet.getString("password");
                String adress = resultSet.getString("address");

                // Create a new Patient object with the retrieved data
                Pharmacy pharmacy = new Pharmacy(name, pwd, id, adress);

                // Add the patient to the listPatients
                listPharmacies.add(pharmacy);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
