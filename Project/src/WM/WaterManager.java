package WM;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class WaterManager {
    private JFrame frame;
    private JLabel consumerLabel, dateLabel, amountLabel, costLabel;
    private JTextField consumerTextField, dateTextField, amountTextField, costTextField;
    private JPanel managePanel, infoPanel;
    private JButton addButton, deleteButton, updateButton, searchButton;
    private JTable displayTable;
    private DefaultTableModel tableModel;

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://HP\\SQLEXPRESS:1433;databaseName=WaterTax;user=sa;password=123456789;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public WaterManager(String title) {
        frame = new JFrame("Electric Manager");
        consumerLabel = new JLabel("Consumer");
        consumerTextField = new JTextField();
        dateLabel = new JLabel("Date");
        dateTextField = new JTextField();
        amountLabel = new JLabel("Amount");
        amountTextField = new JTextField();
        costLabel = new JLabel("Cost");
        costTextField = new JTextField();
        managePanel = new JPanel();
        managePanel.setLayout(new GridLayout(4, 3));
        managePanel.add(consumerLabel);
        managePanel.add(consumerTextField);
        managePanel.add(dateLabel);
        managePanel.add(dateTextField);
        managePanel.add(amountLabel);
        managePanel.add(amountTextField);
        managePanel.add(costLabel);
        managePanel.add(costTextField);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        searchButton = new JButton("Search");
        tableModel = new DefaultTableModel();
        displayTable = new JTable(tableModel);
        displayTable.setEnabled(false);

        infoPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);

        infoPanel.add(buttonPanel, BorderLayout.NORTH);
        infoPanel.add(new JScrollPane(displayTable), BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(managePanel, BorderLayout.NORTH);
        frame.add(infoPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setLocation(300, 200);
        frame.setSize(400, 300);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addActionPerformed(e);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteActionPerformed(e);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateActionPerformed(e);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchActionPerformed(e);
            }
        });
    }
//Add performed
    private void addActionPerformed(ActionEvent evt) {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "INSERT INTO WaterManager (Consumer, DateAnnounment, Amount, Cost) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, consumerTextField.getText());
                    ps.setString(2, dateTextField.getText());
                    ps.setString(3, amountTextField.getText()); 
                    ps.setString(4, costTextField.getText());    
                    ps.executeUpdate(); 
                }
            }
            clearFields();
            searchData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//Delete performed
    private void deleteActionPerformed(ActionEvent evt) {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "DELETE FROM WaterManager WHERE Consumer = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, consumerTextField.getText());
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        searchData();
                        JOptionPane.showMessageDialog(frame, "Costumer deleted from the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Costumer not found in the database.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//Update performed
    private void updateActionPerformed(ActionEvent evt) {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "UPDATE WaterManager SET DateAnnounment = ?, Amount = ?, Cost = ? WHERE Consumer = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, dateTextField.getText());
                    ps.setString(2, costTextField.getText());
                    ps.setString(3, consumerTextField.getText());
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                       searchData();
                        JOptionPane.showMessageDialog(frame, "Costumer updated in the database.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Costumer not found in the database.", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//Search performed
    private void searchActionPerformed(ActionEvent evt) {
        searchData();
    }

    private void searchData() {
        try {
            Class.forName(DRIVER);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT * FROM WaterManager";
                try (PreparedStatement ps = connection.prepareStatement(sql);
                     ResultSet rs = ps.executeQuery()) {

                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    String[] columnNames = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames[i - 1] = metaData.getColumnName(i);
                    }
                    tableModel.setColumnIdentifiers(columnNames);
             
                    tableModel.setRowCount(0);

                    while (rs.next()) {
                        Object[] rowData = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            rowData[i - 1] = rs.getObject(i);
                        }
                        tableModel.addRow(rowData);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        consumerTextField.setText("");
        dateTextField.setText("");
        costTextField.setText("");
    }

    public static void main(String[] args) {
        new WaterManager("Electric Manager");
    }
}