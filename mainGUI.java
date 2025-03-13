import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class mainGUI extends JFrame {
    // Database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public mainGUI() {
        setTitle("Pharmacy System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with welcome message.
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(0, 128, 128)); // Teal color
        JLabel welcomeLabel = new JLabel("Welcome to the Pharmacy System");
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);

        // Side menu panel with collapsible sections.
        JPanel sideMenu = createSideMenuPanel();
        add(sideMenu, BorderLayout.WEST);

        // Center panel as a placeholder.
        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel placeholderLabel = new JLabel("Select an operation from the side menu", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        centerPanel.add(placeholderLabel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Helper method to create a collapsible panel for each group.
    private JPanel createCollapsiblePanel(String title, List<JButton> buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Header button for the collapsible panel.
        JButton headerButton = new JButton(title);
        headerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerButton.getPreferredSize().height));
        
        // Panel that contains the actual operation buttons.
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Add each button with spacing.
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            contentPanel.add(btn);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        contentPanel.setVisible(false); // Initially collapsed.

        // Toggle the visibility of the content when the header is clicked.
        headerButton.addActionListener(e -> {
            contentPanel.setVisible(!contentPanel.isVisible());
            panel.revalidate();
        });

        panel.add(headerButton);
        panel.add(contentPanel);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }

    // Create the side menu with collapsible panels.
    private JPanel createSideMenuPanel() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(150, 0));
        sideMenu.setBackground(new Color(230, 230, 250)); // Light lavender

        // Customer operations buttons.
        JButton addCustomerBtn = new JButton("Add Customer");
        addCustomerBtn.addActionListener(e -> openAddCustomerWindow());
        JButton updateCustomerBtn = new JButton("Update Customer");
        updateCustomerBtn.addActionListener(e -> openUpdateCustomerWindow());
        JButton deleteCustomerBtn = new JButton("Delete Customer");
        deleteCustomerBtn.addActionListener(e -> openDeleteCustomerWindow());
        JButton viewCustomersBtn = new JButton("View Customers");
        viewCustomersBtn.addActionListener(e -> openViewCustomersWindow());
        List<JButton> customerButtons = Arrays.asList(addCustomerBtn, updateCustomerBtn, deleteCustomerBtn, viewCustomersBtn);
        JPanel customerPanel = createCollapsiblePanel("Customer", customerButtons);

        // Stock operations buttons.
        JButton addStockBtn = new JButton("Add Stock");
        addStockBtn.addActionListener(e -> openAddStockWindow());
        JButton updateStockBtn = new JButton("Update Stock");
        updateStockBtn.addActionListener(e -> openUpdateStockWindow());
        JButton deleteStockBtn = new JButton("Delete Stock");
        deleteStockBtn.addActionListener(e -> openDeleteStockWindow());
        JButton viewStockBtn = new JButton("View Stock");
        viewStockBtn.addActionListener(e -> openViewStockWindow());
        List<JButton> stockButtons = Arrays.asList(addStockBtn, updateStockBtn, deleteStockBtn, viewStockBtn);
        JPanel stockPanel = createCollapsiblePanel("Stock", stockButtons);

        // Faulty Items operations buttons.
        JButton addFaultyItemBtn = new JButton("Add Faulty Item");
        addFaultyItemBtn.addActionListener(e -> openAddFaultyItemWindow());
        JButton updateFaultyItemBtn = new JButton("Update Faulty Item");
        updateFaultyItemBtn.addActionListener(e -> openUpdateFaultyItemWindow());
        JButton deleteFaultyItemBtn = new JButton("Delete Faulty Item");
        deleteFaultyItemBtn.addActionListener(e -> openDeleteFaultyItemWindow());
        JButton viewFaultyItemsBtn = new JButton("View Faulty Items");
        viewFaultyItemsBtn.addActionListener(e -> openViewFaultyItemsWindow());
        List<JButton> faultyItemsButtons = Arrays.asList(addFaultyItemBtn, updateFaultyItemBtn, deleteFaultyItemBtn, viewFaultyItemsBtn);
        JPanel faultyItemsPanel = createCollapsiblePanel("Faulty Items", faultyItemsButtons);

        // Orders operations buttons.
        JButton addOrderBtn = new JButton("Add Order");
        addOrderBtn.addActionListener(e -> openAddOrderWindow());
        JButton updateOrderBtn = new JButton("Update Order");
        updateOrderBtn.addActionListener(e -> openUpdateOrderWindow());
        JButton deleteOrderBtn = new JButton("Delete Order");
        deleteOrderBtn.addActionListener(e -> openDeleteOrderWindow());
        JButton viewOrdersBtn = new JButton("View Orders");
        viewOrdersBtn.addActionListener(e -> openViewOrdersWindow());
        List<JButton> orderButtons = Arrays.asList(addOrderBtn, updateOrderBtn, deleteOrderBtn, viewOrdersBtn);
        JPanel ordersPanel = createCollapsiblePanel("Orders", orderButtons);

        // Add panels and spacing.
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(customerPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(stockPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(faultyItemsPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(ordersPanel);
        sideMenu.add(Box.createVerticalGlue());
        return sideMenu;
    }

    // ---------------- Customer Operation Windows ----------------
    private void openAddCustomerWindow() {
        JFrame frame = new JFrame("Add Customer");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(this);
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

        frame.add(panel);
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("firstName", firstNameField.getText());
            customerData.put("lastName", lastNameField.getText());
            customerData.put("address", addressField.getText());
            customerData.put("email", emailField.getText());
            customerData.put("phoneNumber", phoneField.getText());

            int rowsAffected = insertRecord("Customer", customerData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Customer added successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error adding customer.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openUpdateCustomerWindow() {
        JFrame frame = new JFrame("Update Customer");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(this);
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

        frame.add(panel);
        frame.setVisible(true);

        updateButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Customer ID");
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
                JOptionPane.showMessageDialog(frame, "Customer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating customer.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openDeleteCustomerWindow() {
        JFrame frame = new JFrame("Delete Customer");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Customer ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        deleteButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Customer ID");
                return;
            }
            int rowsAffected = deleteCustomer(customerId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Customer deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error deleting customer.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openViewCustomersWindow() {
        JFrame frame = new JFrame("View Customers");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No customer data available.");
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

    // ---------------- Stock Operation Windows ----------------
    private void openAddStockWindow() {
        JFrame frame = new JFrame("Add Stock");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Add Stock functionality not implemented yet.", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private void openUpdateStockWindow() {
        JFrame frame = new JFrame("Update Stock");
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(this);
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

        frame.add(panel);
        frame.setVisible(true);

        updateButton.addActionListener(e -> {
            int stockId, quantity, price;
            try {
                stockId = Integer.parseInt(idField.getText());
                quantity = Integer.parseInt(quantityField.getText());
                price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid numbers for Stock ID, Quantity, and Price.");
                return;
            }
            int rowsAffected = updateStock(stockId, productField.getText(), quantity, price);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Stock updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating stock.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openDeleteStockWindow() {
        JFrame frame = new JFrame("Delete Stock");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Stock ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        deleteButton.addActionListener(e -> {
            int stockId;
            try {
                stockId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Stock ID");
                return;
            }
            int rowsAffected = deleteStock(stockId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Stock deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error deleting stock.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openViewStockWindow() {
        JFrame frame = new JFrame("View Stock");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("View Stock functionality not implemented yet.", SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    // ---------------- Faulty Items Operation Windows ----------------
    private void openAddFaultyItemWindow() {
        JFrame frame = new JFrame("Add Faulty Item");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Item Name:"));
        JTextField itemNameField = new JTextField();
        panel.add(itemNameField);

        panel.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("Quantity:"));
        JTextField quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("Reported Date (YYYY-MM-DD):"));
        JTextField reportedDateField = new JTextField();
        panel.add(reportedDateField);

        JButton addButton = new JButton("Add");
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            Map<String, Object> faultyItemData = new HashMap<>();
            faultyItemData.put("itemName", itemNameField.getText());
            faultyItemData.put("description", descriptionField.getText());
            try {
                faultyItemData.put("quantity", Integer.parseInt(quantityField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid quantity.");
                return;
            }
            faultyItemData.put("reportedDate", reportedDateField.getText());

            int rowsAffected = insertRecord("FaultyItems", faultyItemData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Faulty item added successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error adding faulty item.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openUpdateFaultyItemWindow() {
        JFrame frame = new JFrame("Update Faulty Item");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Faulty Item ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("New Item Name:"));
        JTextField itemNameField = new JTextField();
        panel.add(itemNameField);

        panel.add(new JLabel("New Description:"));
        JTextField descriptionField = new JTextField();
        panel.add(descriptionField);

        panel.add(new JLabel("New Quantity:"));
        JTextField quantityField = new JTextField();
        panel.add(quantityField);

        panel.add(new JLabel("New Reported Date (YYYY-MM-DD):"));
        JTextField reportedDateField = new JTextField();
        panel.add(reportedDateField);

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        updateButton.addActionListener(e -> {
            int itemId;
            try {
                itemId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Faulty Item ID");
                return;
            }
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid quantity.");
                return;
            }
            int rowsAffected = updateFaultyItem(
                    itemId,
                    itemNameField.getText(),
                    descriptionField.getText(),
                    quantity,
                    reportedDateField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Faulty item updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating faulty item.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openDeleteFaultyItemWindow() {
        JFrame frame = new JFrame("Delete Faulty Item");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Faulty Item ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        deleteButton.addActionListener(e -> {
            int itemId;
            try {
                itemId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Faulty Item ID");
                return;
            }
            int rowsAffected = deleteFaultyItem(itemId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Faulty item deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error deleting faulty item.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openViewFaultyItemsWindow() {
        JFrame frame = new JFrame("View Faulty Items");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        List<Map<String, String>> items = GenericDatabaseManager.fetchData("FaultyItems");
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No faulty items data available.");
            return;
        }
        Set<String> columns = items.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[items.size()][columnNames.length];
        for (int i = 0; i < items.size(); i++) {
            Map<String, String> record = items.get(i);
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

    // ---------------- Orders Operation Windows ----------------
    private void openAddOrderWindow() {
        JFrame frame = new JFrame("Add Order");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Customer ID:"));
        JTextField customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("Order Date (YYYY-MM-DD):"));
        JTextField orderDateField = new JTextField();
        panel.add(orderDateField);

        panel.add(new JLabel("Total Amount:"));
        JTextField totalAmountField = new JTextField();
        panel.add(totalAmountField);

        panel.add(new JLabel("Status:"));
        JTextField statusField = new JTextField();
        panel.add(statusField);

        JButton addButton = new JButton("Add");
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        addButton.addActionListener(e -> {
            Map<String, Object> orderData = new HashMap<>();
            try {
                orderData.put("customerId", Integer.parseInt(customerIdField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Customer ID.");
                return;
            }
            orderData.put("orderDate", orderDateField.getText());
            try {
                orderData.put("totalAmount", Double.parseDouble(totalAmountField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Total Amount.");
                return;
            }
            orderData.put("status", statusField.getText());

            int rowsAffected = insertRecord("Orders", orderData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Order added successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error adding order.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openUpdateOrderWindow() {
        JFrame frame = new JFrame("Update Order");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField();
        panel.add(orderIdField);

        panel.add(new JLabel("New Customer ID:"));
        JTextField customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("New Order Date (YYYY-MM-DD):"));
        JTextField orderDateField = new JTextField();
        panel.add(orderDateField);

        panel.add(new JLabel("New Total Amount:"));
        JTextField totalAmountField = new JTextField();
        panel.add(totalAmountField);

        panel.add(new JLabel("New Status:"));
        JTextField statusField = new JTextField();
        panel.add(statusField);

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        updateButton.addActionListener(e -> {
            int orderId, customerId;
            try {
                orderId = Integer.parseInt(orderIdField.getText());
                customerId = Integer.parseInt(customerIdField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Order ID or Customer ID");
                return;
            }
            double totalAmount;
            try {
                totalAmount = Double.parseDouble(totalAmountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Total Amount");
                return;
            }
            int rowsAffected = updateOrder(
                    orderId,
                    customerId,
                    orderDateField.getText(),
                    totalAmount,
                    statusField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Order updated successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error updating order.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openDeleteOrderWindow() {
        JFrame frame = new JFrame("Delete Order");
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(this);
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField();
        panel.add(orderIdField);

        JButton deleteButton = new JButton("Delete");
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        frame.add(panel);
        frame.setVisible(true);

        deleteButton.addActionListener(e -> {
            int orderId;
            try {
                orderId = Integer.parseInt(orderIdField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Order ID");
                return;
            }
            int rowsAffected = deleteOrder(orderId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Order deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Error deleting order.");
            }
            frame.dispose();
        });

        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void openViewOrdersWindow() {
        JFrame frame = new JFrame("View Orders");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        List<Map<String, String>> orders = GenericDatabaseManager.fetchData("Orders");
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No orders data available.");
            return;
        }
        Set<String> columns = orders.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[orders.size()][columnNames.length];
        for (int i = 0; i < orders.size(); i++) {
            Map<String, String> record = orders.get(i);
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

    // -------------- Database Helper Methods --------------
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

    private int updateFaultyItem(int itemId, String itemName, String description, int quantity, String reportedDate) {
        int rowsAffected = 0;
        String query = "UPDATE FaultyItems SET itemName=?, description=?, quantity=?, reportedDate=? WHERE itemId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setString(1, itemName);
            pstat.setString(2, description);
            pstat.setInt(3, quantity);
            pstat.setString(4, reportedDate);
            pstat.setInt(5, itemId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int deleteFaultyItem(int itemId) {
        int rowsAffected = 0;
        String query = "DELETE FROM FaultyItems WHERE itemId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, itemId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int updateOrder(int orderId, int customerId, String orderDate, double totalAmount, String status) {
        int rowsAffected = 0;
        String query = "UPDATE Orders SET customerId=?, orderDate=?, totalAmount=?, status=? WHERE orderId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, customerId);
            pstat.setString(2, orderDate);
            pstat.setDouble(3, totalAmount);
            pstat.setString(4, status);
            pstat.setInt(5, orderId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int deleteOrder(int orderId) {
        int rowsAffected = 0;
        String query = "DELETE FROM Orders WHERE orderId=?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, orderId);
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
