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
    private ArrayList<Pharmacy> listPharmacists = new ArrayList<>();

    public DataFromMySQL() {
    }

    public ArrayList<Patient> getListPatients() {
        return listPatients;
    }

    public ArrayList<Doctor> getListDoctors() {
        return listDoctors;
    }

    public ArrayList<Pharmacy> getListPharmacists() {
        return listPharmacists;
    }

    public void addPatient(Patient p) {
        listPatients.add(p);
    }

    public void addDoctor(Doctor d) {
        listDoctors.add(d);
    }

    public void addPharmacy(Pharmacy p) {
        listPharmacists.add(p);
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

}
