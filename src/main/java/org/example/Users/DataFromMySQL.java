package org.example.Users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.example.Prescription;

public class DataFromMySQL {
    private ArrayList<Patient> patients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Pharmacy> pharmacies = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    private String[] connexionSQL = new String[3];

    public DataFromMySQL() {
    }

    public DataFromMySQL(String[] connexionSQL) {
        this.connexionSQL = connexionSQL;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public ArrayList<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    public void addPharmacy(Pharmacy p) {
        pharmacies.add(p);
    }

    public Patient findPatientByNSS(int nss) {
        for (Patient patient : patients) {
            if (patient.getNss() == nss) {
                return patient;
            }
        }
        return null; // Patient not found
    }

    public Doctor findDoctorByRPPS(int rpps) {
        for (Doctor doctor : doctors) {
            if (doctor.getRpps() == rpps) {
                return doctor;
            }
        }
        return null; // Doctor not found
    }

    public Pharmacy findPharmacyByNum(int num) {
        for (Pharmacy pharmacy : pharmacies) {
            if (pharmacy.getId() == num) {
                return pharmacy;
            }
        }
        return null; // Pharmacy not found
    }

    public void initData() {
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Pharmacy> pharmacies = new ArrayList<>();
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        initPatients();
        initDoctors();
        initPharmacies();
        initPrescriptions();
    }

    public void initPatients() {

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
                patients.add(patient);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initDoctors() {

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
                doctors.add(doctor);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initPharmacies() {
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
                pharmacies.add(pharmacy);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initPrescriptions() {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM prescriptions";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String medicines = resultSet.getString("medicines");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                int rpps = resultSet.getInt("rpps");
                int numPharmacy = resultSet.getInt("num_pharmacy");
                int nss = resultSet.getInt("nss");
                String instructions = resultSet.getString("instructions");
                boolean isValidate = resultSet.getBoolean("is_validate");

                // Retrieve the associated patient, doctor, and pharmacy based on foreign keys
                Patient patient = findPatientByNSS(nss);
                Doctor doctor = findDoctorByRPPS(rpps);
                Pharmacy pharmacy = findPharmacyByNum(numPharmacy);

                // Create a new Prescription object with the retrieved data
                Prescription prescription = new Prescription(id, medicines, date, patient, doctor, pharmacy,
                        instructions,
                        isValidate);

                // Add the prescription to the listPrescriptions
                prescriptions.add(prescription);
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
            String special_mentions) {
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
                patients.add(patient);
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

    public void addDoctorToDB(String name, String password, int rpps) {
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
                doctors.add(doctor);
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

    public void addPharmacyToDB(int id, String name, String password, String address) {
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
                pharmacies.add(pharmacy);
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

    public void addPrescriptionToDB(int id, String medicines, LocalDate date, int rpps, int numPharmacy, int nss,
            String instructions, boolean isValidate) {
        try {
            Connection connection = DriverManager.getConnection(connexionSQL[0], connexionSQL[1], connexionSQL[2]);
            String sql = "INSERT INTO prescriptions (id, medicines, date, rpps, num_pharmacy, nss, instructions, is_validate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, medicines);
            statement.setDate(3, java.sql.Date.valueOf(date));
            statement.setInt(4, rpps);
            statement.setInt(5, numPharmacy);
            statement.setInt(6, nss);
            statement.setString(7, instructions);
            statement.setBoolean(8, isValidate);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Prescription added successfully!");
                // Create a new Prescription object
                Prescription prescription = new Prescription(id, medicines, date, findPatientByNSS(nss),
                        findDoctorByRPPS(rpps), findPharmacyByNum(numPharmacy), instructions, isValidate);
                // Add the prescription to the local list
                prescriptions.add(prescription);
            } else {
                System.out.println("Failed to add prescription.");
            }

            // Close the statement and connection
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
