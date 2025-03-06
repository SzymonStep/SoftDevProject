import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class guiTest extends JFrame {
    // Database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public guiTest() {
        setTitle("Pharmacy System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with welcome message aligned to the top.
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(0, 128, 128)); // Teal color
        JLabel welcomeLabel = new JLabel("Welcome to the Pharmacy System");
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel to hold the cards.
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(0, 128, 128));
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

        // ---------------- Customer Card ----------------
        JPanel customerCard = new JPanel();
        customerCard.setPreferredSize(new Dimension(300, 250));
        customerCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        customerCard.setLayout(new BoxLayout(customerCard, BoxLayout.Y_AXIS));
        customerCard.setBackground(Color.WHITE);

        JLabel customerLabel = new JLabel("Customer", SwingConstants.CENTER);
        customerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        customerCard.add(Box.createRigidArea(new Dimension(0, 10)));
        customerCard.add(customerLabel);
        customerCard.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addCustomerBtn = new JButton("Add Customer");
        JButton updateCustomerBtn = new JButton("Update Customer");
        JButton deleteCustomerBtn = new JButton("Delete Customer");
        JButton viewCustomersBtn = new JButton("View Customers");
        addCustomerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateCustomerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteCustomerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCustomersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerCard.add(addCustomerBtn);
        customerCard.add(Box.createRigidArea(new Dimension(0, 5)));
        customerCard.add(updateCustomerBtn);
        customerCard.add(Box.createRigidArea(new Dimension(0, 5)));
        customerCard.add(deleteCustomerBtn);
        customerCard.add(Box.createRigidArea(new Dimension(0, 5)));
        customerCard.add(viewCustomersBtn);

        // ---------------- Stock Card ----------------
        JPanel stockCard = new JPanel();
        stockCard.setPreferredSize(new Dimension(300, 250));
        stockCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        stockCard.setLayout(new BoxLayout(stockCard, BoxLayout.Y_AXIS));
        stockCard.setBackground(Color.WHITE);

        JLabel stockLabel = new JLabel("Stock", SwingConstants.CENTER);
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stockLabel.setFont(new Font("Arial", Font.BOLD, 20));
        stockCard.add(Box.createRigidArea(new Dimension(0, 10)));
        stockCard.add(stockLabel);
        stockCard.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addStockBtn = new JButton("Add Stock");
        JButton updateStockBtn = new JButton("Update Stock");
        JButton deleteStockBtn = new JButton("Delete Stock");
        JButton viewStockBtn = new JButton("View Stock");
        addStockBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateStockBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteStockBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewStockBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        stockCard.add(addStockBtn);
        stockCard.add(Box.createRigidArea(new Dimension(0, 5)));
        stockCard.add(updateStockBtn);
        stockCard.add(Box.createRigidArea(new Dimension(0, 5)));
        stockCard.add(deleteStockBtn);
        stockCard.add(Box.createRigidArea(new Dimension(0, 5)));
        stockCard.add(viewStockBtn);

        // Add cards to center panel.
        centerPanel.add(customerCard);
        centerPanel.add(stockCard);
        add(centerPanel, BorderLayout.CENTER);

        // ---------------- Action Listeners ----------------
        addCustomerBtn.addActionListener(e -> openAddCustomerDialog());
        updateCustomerBtn.addActionListener(e -> openUpdateCustomerDialog());
        deleteCustomerBtn.addActionListener(e -> openDeleteCustomerDialog());
        viewCustomersBtn.addActionListener(e -> openViewCustomersPanel());
        
        // For Stock, you can implement the dialogs or show a message if not yet available.
        addStockBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Add Stock not implemented yet.");
        });
        updateStockBtn.addActionListener(e -> openUpdateStockDialog());
        deleteStockBtn.addActionListener(e -> openDeleteStockDialog());
        viewStockBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "View Stock not implemented yet.");
        });
    }

    // Dialog to add a new customer.
    private void openAddCustomerDialog() {
        JDialog dialog = new JDialog(this, "Add Customer", true);
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Phone Number:"));
        JTextField phoneField = new JTextField();
        panel.add(phoneField);

        JButton addButton = new JButton("Add");
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        dialog.add(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("firstName", firstNameField.getText());
            customerData.put("lastName", lastNameField.getText());
            customerData.put("address", addressField.getText());
            customerData.put("email", emailField.getText());
            customerData.put("phoneNumber", phoneField.getText());

            int rowsAffected = insertRecord("Customer", customerData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(dialog, "Customer added successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Error adding customer.");
            }
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Dialog to update an existing customer.
    private void openUpdateCustomerDialog() {
        JDialog dialog = new JDialog(this, "Update Customer", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Customer ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("New First Name:"));
        JTextField firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("New Last Name:"));
        JTextField lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("New Address:"));
        JTextField addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("New Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("New Phone Number:"));
        JTextField phoneField = new JTextField();
        panel.add(phoneField);

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        dialog.add(panel);

        updateButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Customer ID");
                return;
            }
            int rowsAffected = updateCustomer(
                    customerId,
                    firstNameField.getText(),
                    lastNameField.getText(),
                    addressField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(dialog, "Customer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Error updating customer.");
            }
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Dialog to delete a customer.
    private void openDeleteCustomerDialog() {
        JDialog dialog = new JDialog(this, "Delete Customer", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Customer ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        dialog.add(panel);

        deleteButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Customer ID");
                return;
            }
            int rowsAffected = deleteCustomer(customerId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(dialog, "Customer deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Error deleting customer.");
            }
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Panel to view customers using a JTable.
    private void openViewCustomersPanel() {
        JFrame frame = new JFrame("View Customers");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customer data available.");
            return;
        }
        Set<String> columns = customers.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[customers.size()][columnNames.length];
        for (int i = 0; i < customers.size(); i++) {
            Map<String, String> record = customers.get(i);
            int j = 0;
            for (String col : columnNames) {
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Dialog to update stock.
    private void openUpdateStockDialog() {
        JDialog dialog = new JDialog(this, "Update Stock", true);
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Stock ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Product Name:"));
        JTextField productField = new JTextField();
        panel.add(productField);

        panel.add(new JLabel("Quantity:"));
        JTextField quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        panel.add(priceField);

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        dialog.add(panel);

        updateButton.addActionListener(e -> {
            int stockId, quantity, price;
            try {
                stockId = Integer.parseInt(idField.getText());
                quantity = Integer.parseInt(quantityField.getText());
                price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter valid numbers for Stock ID, Quantity, and Price.");
                return;
            }
            int rowsAffected = updateStock(stockId, productField.getText(), quantity, price);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(dialog, "Stock updated successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Error updating stock.");
            }
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Dialog to delete stock.
    private void openDeleteStockDialog() {
        JDialog dialog = new JDialog(this, "Delete Stock", true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Stock ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        dialog.add(panel);

        deleteButton.addActionListener(e -> {
            int stockId;
            try {
                stockId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid Stock ID");
                return;
            }
            int rowsAffected = deleteStock(stockId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(dialog, "Stock deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(dialog, "Error deleting stock.");
            }
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Helper method for generic insert.
    private int insertRecord(String tableName, Map<String, Object> data) {
        int rowsAffected = 0;
        if (data.isEmpty()) {
            System.out.println("No data provided to insert.");
            return 0;
        }
        String columns = String.join(", ", data.keySet());
        String placeholders = String.join(", ", Collections.nCopies(data.size(), "?"));
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : data.values()) {
                pstat.setObject(index++, value);
            }
            rowsAffected = pstat.executeUpdate();
            System.out.println(rowsAffected + " record(s) successfully added to " + tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Helper method to update a customer record.
    private int updateCustomer(int customerId, String firstName, String lastName, String address, String email, String phoneNumber) {
        int rowsAffected = 0;
        String query = "UPDATE Customer SET firstName=?, lastName=?, address=?, email=?, phoneNumber=? WHERE customerId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setString(1, firstName);
            pstat.setString(2, lastName);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, customerId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Helper method to delete a customer record.
    private int deleteCustomer(int customerId) {
        int rowsAffected = 0;
        String query = "DELETE FROM Customer WHERE customerId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, customerId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Helper method to update a stock record.
    private int updateStock(int stockId, String prodName, int quantity, int price) {
        int rowsAffected = 0;
        String query = "UPDATE Stock SET prodName = ?, quantity = ?, price = ? WHERE stockId = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setString(1, prodName);
            pstat.setInt(2, quantity);
            pstat.setInt(3, price);
            pstat.setInt(4, stockId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Helper method to delete a stock record.
    private int deleteStock(int stockId) {
        int rowsAffected = 0;
        String query = "DELETE FROM Stock WHERE stockId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, stockId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new guiTest().setVisible(true));
    }
}
