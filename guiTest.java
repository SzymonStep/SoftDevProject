import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class guiTest extends JFrame {
    /*public Color(int r, int b, int g){
        int r =0;
        int b = 128;
        int g = 128; 
    }*/
    // Database connection details (same as used in the provided files)
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public guiTest() {
        setTitle("Pharmacy System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        // Create menu bar with Customer operations.
        JMenuBar menuBar = new JMenuBar();
        JMenu customerMenu = new JMenu("Customer");
        JMenuItem addCustomerItem = new JMenuItem("Add Customer");
        JMenuItem updateCustomerItem = new JMenuItem("Update Customer");
        JMenuItem deleteCustomerItem = new JMenuItem("Delete Customer");
        JMenuItem viewCustomersItem = new JMenuItem("View Customers");
        JMenu stockMenu = new JMenu("Stock");
        JMenuItem addStockItem = new JMenuItem("Add Stock");
        JMenuItem updateStockItem = new JMenuItem("Update Stock");
        JMenuItem deleteStockItem = new JMenuItem("Delete Stock");
        JMenuItem viewStockItem = new JMenuItem("View Stock");

        customerMenu.add(addCustomerItem);
        customerMenu.add(updateCustomerItem);
        customerMenu.add(deleteCustomerItem);
        customerMenu.add(viewCustomersItem);
        stockMenu.add(addStockItem);
        stockMenu.add(deleteStockItem);
        stockMenu.add(updateStockItem);
        stockMenu.add(viewStockItem);
        menuBar.add(stockMenu);
        menuBar.add(customerMenu);
        setJMenuBar(menuBar);

        // Setup actions for menu items
        addCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddCustomerDialog();
            }
        });
        updateCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUpdateCustomerDialog();
            }
        });
        deleteCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteCustomerDialog();
            }
        });
        viewCustomersItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openViewCustomersPanel();
            }
        });
        addStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //openAddStockDialog();
            }
        });
        updateStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openUpdateStockDialog();
            }
        });
        deleteStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openDeleteStockDialog();
            }
        });
        viewStockItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //openViewStockPanel();
            }
        });

        // Main panel with welcome message.
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Welcome to the Pharmacy System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans", Font.BOLD, 32));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(mainPanel);
    }   

    // Dialog to add a new customer (based on insertCustomer and insertTable logic :contentReference[oaicite:3]{index=3}, :contentReference[oaicite:4]{index=4})
    private void openAddCustomerDialog() {
        JDialog dialog = new JDialog(this, "Add Customer", true);
        dialog.setSize(400, 300);
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

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<String, Object> customerData = new HashMap<>();
                customerData.put("firstName", firstNameField.getText());
                customerData.put("lastName", lastNameField.getText());
                customerData.put("address", addressField.getText());
                customerData.put("email", emailField.getText());
                customerData.put("phoneNumber", phoneField.getText());

                int rowsAffected = insertRecord("Customer", customerData);
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(dialog, "Customer added successfully.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error adding customer.");
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    // Dialog to update an existing customer (inspired by updateCustomer.java :contentReference[oaicite:5]{index=5})
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

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int customerId;
                try {
                    customerId = Integer.parseInt(idField.getText());
                } catch(NumberFormatException ex) {
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
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(dialog, "Customer updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error updating customer.");
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    // Dialog to delete a customer (based on deleteCustomer.java :contentReference[oaicite:6]{index=6})
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

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int customerId;
                try {
                    customerId = Integer.parseInt(idField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Customer ID");
                    return;
                }
                int rowsAffected = deleteCustomer(customerId);
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(dialog, "Customer deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error deleting customer.");
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    // Panel to view customers using a JTable (leveraging GenericDatabaseManager from :contentReference[oaicite:7]{index=7})
    private void openViewCustomersPanel() {
        JFrame frame = new JFrame("View Customers");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if(customers.isEmpty()){
            JOptionPane.showMessageDialog(this, "No customer data available.");
            return;
        }
        Set<String> columns = customers.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[customers.size()][columnNames.length];
        for(int i = 0; i < customers.size(); i++){
            Map<String, String> record = customers.get(i);
            int j = 0;
            for(String col : columnNames){
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // Helper method for generic insert (inspired by insertTable.java :contentReference[oaicite:8]{index=8})
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
    
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int stockId;
                try {
                    stockId = Integer.parseInt(idField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid Stock ID");
                    return;
                }
                int rowsAffected = deleteStock(stockId);
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(dialog, "Stock deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error deleting stock.");
                }
                dialog.dispose();
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    
        dialog.setVisible(true);
    }
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
    
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int stockId, quantity, price;
                try {
                    stockId = Integer.parseInt(idField.getText());
                    quantity = Integer.parseInt(quantityField.getText());
                    price = Integer.parseInt(priceField.getText());
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter valid numbers for Stock ID, Quantity, and Price.");
                    return;
                }
                int rowsAffected = updateStock(stockId, productField.getText(), quantity, price);
                if(rowsAffected > 0) {
                    JOptionPane.showMessageDialog(dialog, "Stock updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error updating stock.");
                }
                dialog.dispose();
            }
        });
    
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
    
        dialog.setVisible(true);
    }
    
    // Helper method to update a stock record.
    private int updateStock(int stockId, String prodName, int quantity, int price) {
        int rowsAffected = 0;
        // Corrected query: update the Stock table without an extra comma.
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
    

    // Helper method to delete a customer record.
    private int deleteStock(int stockId) {
        int rowsAffected = 0;
        // Corrected query: delete from Stock table using the stockId column.
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
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new guiTest().setVisible(true);
            }
        });
    }
}
