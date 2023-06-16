import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
    private JTextField textField;
    private JButton button;

    public Test() {
        // Set JFrame properties
        setTitle("Text Field and Button Example");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Create and configure the JTextField
        textField = new JTextField();
        textField.setBounds(20, 20, 200, 30);
        add(textField);

        // Create and configure the JButton
        button = new JButton("Submit");
        button.setBounds(20, 60, 100, 30);
        add(button);

        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                System.out.println("Text entered: " + text);
                // Perform any actions with the entered text here
            }
        });
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/ordonnancement";
        String username = "root";
        String password = "root";
        String column1Value = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            Statement statement1 = connection.createStatement();
            String sqlQuery = "SELECT * FROM patients";
            ResultSet resultSet = statement1.executeQuery(sqlQuery);
            String sql = "INSERT INTO patients (name, nss) VALUES (?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(sql);

            // Set the values for the parameters
            statement2.setString(1, "John Doe");
            statement2.setInt(2, 123456789);

            // Execute the query
            int rowsInserted = statement2.executeUpdate();

            while (resultSet.next()) {
                // Retrieve data from the result set
                column1Value = resultSet.getString("name");
                int column2Value = resultSet.getInt("nss");
                // ...
                // Process the retrieved data as needed
                // ...
            }
            // Close the result set, statement, and connection
            resultSet.close();
            statement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(column1Value);
        Test frame = new Test();
        frame.setVisible(true);
    }
}
