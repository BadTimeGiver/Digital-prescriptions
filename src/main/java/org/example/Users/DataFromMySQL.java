package org.example.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.example.Prescription;

public class DataFromMySQL {
    private ArrayList<Patient> listPatients = new ArrayList<>();
    private ArrayList<Doctor> listDoctors = new ArrayList<>();
    private ArrayList<Pharmacy> listPharmacies = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

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

    public void addPatientToDB(int nss, String name, String password, int age, int weight, int height,
            String special_mentions, String[] connexionSQL) {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            String sql = "INSERT INTO patients (nss, name, password, age, weight, height, special_mentions) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, nss);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setInt(4, age);
            statement.setInt(5, weight);
            statement.setInt(6, height);
            statement.setString(7, special_mentions);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Patient added successfully!");
                // Create a new Patient object
                Patient patient = new Patient(name, password, nss, age, weight, height, special_mentions);
                // Add the patient to the local list
                listPatients.add(patient);
            } else {
                System.out.println("Failed to add patient.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDoctorToDB(String name, String password, int rpps, String[] connexionSQL) {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            String sql = "INSERT INTO doctors (rpps, name, password) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, rpps);
            statement.setString(2, name);
            statement.setString(3, password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Doctor added successfully!");
                // Create a new Doctor object
                Doctor doctor = new Doctor(name, password, rpps);
                // Add the doctor to the local list
                listDoctors.add(doctor);
            } else {
                System.out.println("Failed to add doctor.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPharmacyToDB(int id, String name, String password, String address, String[] connexionSQL) {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            String sql = "INSERT INTO pharmacies (id, name, password, address) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, address);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Pharmacy added successfully!");
                // Create a new Pharmacy object
                Pharmacy pharmacy = new Pharmacy(name, password, id, address);
                // Add the pharmacy to the local list
                listPharmacies.add(pharmacy);
            } else {
                System.out.println("Failed to add pharmacy.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
