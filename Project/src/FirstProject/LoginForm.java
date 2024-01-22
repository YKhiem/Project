package FirstProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm {
    private JFrame frame;
    private JLabel accountLabel, passwordLabel, statusLabel;
    private JTextField accountTextField, passwordTextField;
    private JButton confirmButton, cancelButton;

    // Database connection details
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://HP\\SQLEXPRESS:1433;databaseName=ElectricTax;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public LoginForm(String title) {
        frame = new JFrame("Login");
        accountLabel = new JLabel("User");
        passwordLabel = new JLabel("Password");
        accountTextField = new JTextField();
        passwordTextField = new JTextField();
        confirmButton = new JButton("Confirm Login");
        cancelButton = new JButton("Cancel Login");
        statusLabel = new JLabel();

        JPanel logPanel = new JPanel(new GridLayout(2, 2));
        logPanel.add(accountLabel);
        logPanel.add(accountTextField);
        logPanel.add(passwordLabel);
        logPanel.add(passwordTextField);

        JPanel procPanel = new JPanel();
        procPanel.add(confirmButton);
        procPanel.add(cancelButton);

        frame.setLayout(new BorderLayout());
        frame.add(statusLabel, BorderLayout.CENTER);
        frame.add(logPanel, BorderLayout.NORTH);
        frame.add(procPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setLocation(300, 200);
        frame.setSize(300, 150);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
    }

    private void loginActionPerformed(ActionEvent evt) {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM Account WHERE AccName=? AND Pass=?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, accountTextField.getText());
                    ps.setString(2, passwordTextField.getText());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            // Successful login
                            statusLabel.setText("Login successful");
                        } else {
                            // Invalid credentials
                            statusLabel.setText("Invalid Information");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LoginForm("Login Form");
    }
}