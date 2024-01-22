package WM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterForm {
    JFrame frame;
    JLabel accountLabel, passwordLabel, confirmPassLabel, gmailLabel, statusLabel;
    JTextField accountTextField, passwordTextField, confirmPassTextField, gmailTextField;
    JPanel resPanel, procPanel;
    JButton confirmButton, cancelButton;

    // Database connection details
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://HP\\SQLEXPRESS:1433;databaseName=WaterTax;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public RegisterForm(String title) {
        frame = new JFrame("Register");
        accountLabel = new JLabel("User");
        passwordLabel = new JLabel("Password");
        confirmPassLabel = new JLabel("Confirm Password");
        gmailLabel = new JLabel("Gmail");
        accountTextField = new JTextField();
        passwordTextField = new JTextField();
        confirmPassTextField = new JTextField();
        gmailTextField = new JTextField();
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        statusLabel = new JLabel();

        resPanel = new JPanel(new GridLayout(4, 2));
        resPanel.add(accountLabel);
        resPanel.add(accountTextField);
        resPanel.add(passwordLabel);
        resPanel.add(passwordTextField);
        resPanel.add(confirmPassLabel);
        resPanel.add(confirmPassTextField);
        resPanel.add(gmailLabel);
        resPanel.add(gmailTextField);

        procPanel = new JPanel();
        procPanel.add(cancelButton);
        procPanel.add(confirmButton);
       

        frame.setLayout(new BorderLayout());
        frame.add(statusLabel, BorderLayout.CENTER);
        frame.add(resPanel, BorderLayout.NORTH);
        frame.add(procPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocation(300, 200);
        frame.setSize(300, 200);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmActionPerformed(e);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelActionPerformed(e);
            }
        });

        // Set the default close operation to exit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void confirmActionPerformed(ActionEvent evt) {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO Account (AccName, Pass, ConfrimPass, Mail) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, accountTextField.getText());
                    ps.setString(2, passwordTextField.getText());
                    ps.setString(3, confirmPassTextField.getText());
                    ps.setString(4, gmailTextField.getText());

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        statusLabel.setText("Registration successful");
                    } else {
                        statusLabel.setText("Registration failed");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    private void cancelActionPerformed(ActionEvent evt) {
        new Main("");
        frame.dispose();
    }

    public static void main(String[] args) {
         new RegisterForm("Registration Form");
    }
}