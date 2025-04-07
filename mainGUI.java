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
    
    // Constant for uniform CRUD button size (smaller size: 150x30)
    private static final Dimension BUTTON_SIZE = new Dimension(150, 30);

    // Center panel where all operation panels will be loaded.
    private JPanel centerPanel;

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

        // Side menu panel with dropdown (popup) categories.
        JPanel sideMenu = createSideMenuPanel();
        add(sideMenu, BorderLayout.WEST);

        // Center panel (initially showing a placeholder).
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(createPlaceholderPanel(), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Helper method to create the placeholder panel.
    private JPanel createPlaceholderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel placeholderLabel = new JLabel("Select an operation from the side menu", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        panel.add(placeholderLabel, BorderLayout.CENTER);
        return panel;
    }

    // Helper method to update the center panel.
    private void setCenterPanel(JPanel newPanel) {
        centerPanel.removeAll();
        centerPanel.add(newPanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // Create a collapsible panel with a header button that shows a popup menu.
    private JPanel createCollapsiblePanel(String title, List<JButton> buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Header button for the popup menu.
        JButton headerButton = new JButton(title);
        headerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerButton.getPreferredSize().height));
        
        // Create a popup menu to hold the operation buttons.
        JPopupMenu popupMenu = new JPopupMenu();
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setPreferredSize(BUTTON_SIZE);
            btn.setMaximumSize(BUTTON_SIZE);
            popupMenu.add(btn);
            popupMenu.addSeparator();
        }
        // Show the popup menu to the right of the header button when clicked.
        headerButton.addActionListener(e -> {
            popupMenu.show(headerButton, headerButton.getWidth(), 0);
        });

        panel.add(headerButton);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }

    // Create the side menu using collapsible panels.
    private JPanel createSideMenuPanel() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(175, 0));
        sideMenu.setBackground(new Color(230, 230, 250)); // Light lavender

        // Customer operations.
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

        // Stock operations.
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

        // Faulty Items operations.
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

        // Orders operations.
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

        // Equipment operations.
        JButton addEquipmentBtn = new JButton("Add Equipment");
        addEquipmentBtn.addActionListener(e -> openAddEquipmentWindow());
        JButton updateEquipmentBtn = new JButton("Update Equipment");
        updateEquipmentBtn.addActionListener(e -> openUpdateEquipmentWindow());
        JButton deleteEquipmentBtn = new JButton("Delete Equipment");
        deleteEquipmentBtn.addActionListener(e -> openDeleteEquipmentWindow());
        JButton viewEquipmentBtn = new JButton("View Equipment");
        viewEquipmentBtn.addActionListener(e -> openViewEquipmentWindow());
        List<JButton> equipmentButtons = Arrays.asList(addEquipmentBtn, updateEquipmentBtn, deleteEquipmentBtn, viewEquipmentBtn);
        JPanel equipmentPanel = createCollapsiblePanel("Equipment", equipmentButtons);

        // Staff operations.
        JButton addStaffBtn = new JButton("Add Staff");
        addStaffBtn.addActionListener(e -> openAddStaffWindow());
        JButton updateStaffBtn = new JButton("Update Staff");
        updateStaffBtn.addActionListener(e -> openUpdateStaffWindow());
        JButton deleteStaffBtn = new JButton("Delete Staff");
        deleteStaffBtn.addActionListener(e -> openDeleteStaffWindow());
        JButton viewStaffBtn = new JButton("View Staff");
        viewStaffBtn.addActionListener(e -> openViewStaffWindow());
        List<JButton> staffButtons = Arrays.asList(addStaffBtn, updateStaffBtn, deleteStaffBtn, viewStaffBtn);
        JPanel staffPanel = createCollapsiblePanel("Staff", staffButtons);

        // Add sections with spacing.
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(customerPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(stockPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(faultyItemsPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(ordersPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(equipmentPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(staffPanel);
        sideMenu.add(Box.createVerticalGlue());
        return sideMenu;
    }

    // ---------------- Operation Panels ----------------
    // All operations now update the center panel.

    // Customer Operations
    private void openAddCustomerWindow() {
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
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("firstName", firstNameField.getText());
            customerData.put("lastName", lastNameField.getText());
            customerData.put("address", addressField.getText());
            customerData.put("email", emailField.getText());
            customerData.put("phoneNumber", phoneField.getText());
            int rowsAffected = insertRecord("Customer", customerData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Customer added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateCustomerWindow() {
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
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Customer ID");
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
                JOptionPane.showMessageDialog(mainGUI.this, "Customer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteCustomerWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Customer ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int customerId;
            try {
                customerId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Customer ID");
                return;
            }
            int rowsAffected = deleteCustomer(customerId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Customer deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewCustomersWindow() {
        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(mainGUI.this, "No customer data available.");
            setCenterPanel(createPlaceholderPanel());
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // Stock Operations
    private void openAddStockWindow() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Product Name:"));
        JTextField productField = new JTextField();
        panel.add(productField);
        panel.add(new JLabel("Quantity:"));
        JTextField quantityField = new JTextField();
        panel.add(quantityField);
        panel.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        panel.add(priceField);
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> stockData = new HashMap<>();
            stockData.put("prodName", productField.getText());
            try {
                stockData.put("quantity", Integer.parseInt(quantityField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid quantity.");
                return;
            }
            try {
                stockData.put("price", Integer.parseInt(priceField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid price.");
                return;
            }
            int rowsAffected = insertRecord("Stock", stockData);
            if(rowsAffected > 0){
                JOptionPane.showMessageDialog(mainGUI.this, "Stock added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding stock.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateStockWindow() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
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
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int stockId, quantity, price;
            try {
                stockId = Integer.parseInt(idField.getText());
                quantity = Integer.parseInt(quantityField.getText());
                price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid input. Please enter valid numbers for Stock ID, Quantity, and Price.");
                return;
            }
            int rowsAffected = updateStock(stockId, productField.getText(), quantity, price);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Stock updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating stock.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteStockWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Stock ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int stockId;
            try {
                stockId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Stock ID");
                return;
            }
            int rowsAffected = deleteStock(stockId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Stock deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting stock.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewStockWindow() {
        List<Map<String, String>> stock = GenericDatabaseManager.fetchData("Stock");
        if(stock.isEmpty()){
            JOptionPane.showMessageDialog(mainGUI.this, "No stock data available.");
            setCenterPanel(createPlaceholderPanel());
            return;
        }
        Set<String> columns = stock.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[stock.size()][columnNames.length];
        for (int i = 0; i < stock.size(); i++) {
            Map<String, String> record = stock.get(i);
            int j = 0;
            for (String col : columnNames) {
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // Faulty Items Operations
    private void openAddFaultyItemWindow() {
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
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> faultyItemData = new HashMap<>();
            faultyItemData.put("itemName", itemNameField.getText());
            faultyItemData.put("description", descriptionField.getText());
            try {
                faultyItemData.put("quantity", Integer.parseInt(quantityField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid quantity.");
                return;
            }
            faultyItemData.put("reportedDate", reportedDateField.getText());
            int rowsAffected = insertRecord("FaultyItems", faultyItemData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Faulty item added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding faulty item.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateFaultyItemWindow() {
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
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int itemId;
            try {
                itemId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Faulty Item ID");
                return;
            }
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid quantity.");
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
                JOptionPane.showMessageDialog(mainGUI.this, "Faulty item updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating faulty item.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteFaultyItemWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Faulty Item ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int itemId;
            try {
                itemId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Faulty Item ID");
                return;
            }
            int rowsAffected = deleteFaultyItem(itemId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Faulty item deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting faulty item.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewFaultyItemsWindow() {
        List<Map<String, String>> items = GenericDatabaseManager.fetchData("FaultyItems");
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(mainGUI.this, "No faulty items data available.");
            setCenterPanel(createPlaceholderPanel());
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // Orders Operations
    private void openAddOrderWindow() {
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
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> orderData = new HashMap<>();
            try {
                orderData.put("customerId", Integer.parseInt(customerIdField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Customer ID.");
                return;
            }
            orderData.put("orderDate", orderDateField.getText());
            try {
                orderData.put("totalAmount", Double.parseDouble(totalAmountField.getText()));
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Total Amount.");
                return;
            }
            orderData.put("status", statusField.getText());
            int rowsAffected = insertRecord("Orders", orderData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Order added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateOrderWindow() {
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
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int orderId, customerId;
            try {
                orderId = Integer.parseInt(orderIdField.getText());
                customerId = Integer.parseInt(customerIdField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Order ID or Customer ID");
                return;
            }
            double totalAmount;
            try {
                totalAmount = Double.parseDouble(totalAmountField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Total Amount");
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
                JOptionPane.showMessageDialog(mainGUI.this, "Order updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteOrderWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField();
        panel.add(orderIdField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int orderId;
            try {
                orderId = Integer.parseInt(orderIdField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Order ID");
                return;
            }
            int rowsAffected = deleteOrder(orderId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Order deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewOrdersWindow() {
        List<Map<String, String>> orders = GenericDatabaseManager.fetchData("Orders");
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(mainGUI.this, "No orders data available.");
            setCenterPanel(createPlaceholderPanel());
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // Equipment Operations
    private void openAddEquipmentWindow() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Equipment Name:"));
        JTextField equipmentNameField = new JTextField();
        panel.add(equipmentNameField);
        panel.add(new JLabel("Equipment Type:"));
        JTextField equipmentTypeField = new JTextField();
        panel.add(equipmentTypeField);
        panel.add(new JLabel("Purchase Date (YYYY-MM-DD):"));
        JTextField purchaseDateField = new JTextField();
        panel.add(purchaseDateField);
        panel.add(new JLabel("Status:"));
        JTextField statusField = new JTextField();
        panel.add(statusField);
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> equipmentData = new HashMap<>();
            equipmentData.put("equipmentName", equipmentNameField.getText());
            equipmentData.put("equipmentType", equipmentTypeField.getText());
            equipmentData.put("purchaseDate", purchaseDateField.getText());
            equipmentData.put("status", statusField.getText());
            int rowsAffected = insertRecord("Equipment", equipmentData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Equipment added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateEquipmentWindow() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Equipment ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("New Equipment Name:"));
        JTextField equipmentNameField = new JTextField();
        panel.add(equipmentNameField);
        panel.add(new JLabel("New Equipment Type:"));
        JTextField equipmentTypeField = new JTextField();
        panel.add(equipmentTypeField);
        panel.add(new JLabel("New Purchase Date (YYYY-MM-DD):"));
        JTextField purchaseDateField = new JTextField();
        panel.add(purchaseDateField);
        panel.add(new JLabel("New Status:"));
        JTextField statusField = new JTextField();
        panel.add(statusField);
        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int equipmentId;
            try {
                equipmentId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Equipment ID");
                return;
            }
            int rowsAffected = updateEquipment(equipmentId, equipmentNameField.getText(), equipmentTypeField.getText(), purchaseDateField.getText(), statusField.getText());
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Equipment updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteEquipmentWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Equipment ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int equipmentId;
            try {
                equipmentId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Equipment ID");
                return;
            }
            int rowsAffected = deleteEquipment(equipmentId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Equipment deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewEquipmentWindow() {
        List<Map<String, String>> equipmentList = GenericDatabaseManager.fetchData("Equipment");
        if (equipmentList.isEmpty()) {
            JOptionPane.showMessageDialog(mainGUI.this, "No equipment data available.");
            setCenterPanel(createPlaceholderPanel());
            return;
        }
        Set<String> columns = equipmentList.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[equipmentList.size()][columnNames.length];
        for (int i = 0; i < equipmentList.size(); i++) {
            Map<String, String> record = equipmentList.get(i);
            int j = 0;
            for (String col : columnNames) {
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // Staff Operations
    private void openAddStaffWindow() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField();
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField();
        panel.add(lastNameField);
        panel.add(new JLabel("Role:"));
        JTextField roleField = new JTextField();
        panel.add(roleField);
        panel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        panel.add(emailField);
        panel.add(new JLabel("Phone Number:"));
        JTextField phoneField = new JTextField();
        panel.add(phoneField);
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(BUTTON_SIZE);
        panel.add(addButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        addButton.addActionListener(e -> {
            Map<String, Object> staffData = new HashMap<>();
            staffData.put("firstName", firstNameField.getText());
            staffData.put("lastName", lastNameField.getText());
            staffData.put("role", roleField.getText());
            staffData.put("email", emailField.getText());
            staffData.put("phoneNumber", phoneField.getText());
            int rowsAffected = insertRecord("Staff", staffData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Staff added successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error adding staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openUpdateStaffWindow() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Staff ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("New First Name:"));
        JTextField firstNameField = new JTextField();
        panel.add(firstNameField);
        panel.add(new JLabel("New Last Name:"));
        JTextField lastNameField = new JTextField();
        panel.add(lastNameField);
        panel.add(new JLabel("New Role:"));
        JTextField roleField = new JTextField();
        panel.add(roleField);
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
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int staffId;
            try {
                staffId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Staff ID");
                return;
            }
            int rowsAffected = updateStaff(staffId, firstNameField.getText(), lastNameField.getText(), roleField.getText(), emailField.getText(), phoneField.getText());
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Staff updated successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error updating staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openDeleteStaffWindow() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(new JLabel("Staff ID:"));
        JTextField idField = new JTextField();
        panel.add(idField);
        JButton deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(BUTTON_SIZE);
        panel.add(deleteButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        deleteButton.addActionListener(e -> {
            int staffId;
            try {
                staffId = Integer.parseInt(idField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainGUI.this, "Invalid Staff ID");
                return;
            }
            int rowsAffected = deleteStaff(staffId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(mainGUI.this, "Staff deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(mainGUI.this, "Error deleting staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    private void openViewStaffWindow() {
        List<Map<String, String>> staffList = GenericDatabaseManager.fetchData("Staff");
        if (staffList.isEmpty()) {
            JOptionPane.showMessageDialog(mainGUI.this, "No staff data available.");
            setCenterPanel(createPlaceholderPanel());
            return;
        }
        Set<String> columns = staffList.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[staffList.size()][columnNames.length];
        for (int i = 0; i < staffList.size(); i++) {
            Map<String, String> record = staffList.get(i);
            int j = 0;
            for (String col : columnNames) {
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
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

    private int updateEquipment(int equipmentId, String equipmentName, String equipmentType, String purchaseDate, String status) {
        int rowsAffected = 0;
        String query = "UPDATE Equipment SET equipmentName = ?, equipmentType = ?, purchaseDate = ?, status = ? WHERE equipmentId = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setString(1, equipmentName);
            pstat.setString(2, equipmentType);
            pstat.setString(3, purchaseDate);
            pstat.setString(4, status);
            pstat.setInt(5, equipmentId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int deleteEquipment(int equipmentId) {
        int rowsAffected = 0;
        String query = "DELETE FROM Equipment WHERE equipmentId = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, equipmentId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int updateStaff(int staffId, String firstName, String lastName, String role, String email, String phoneNumber) {
        int rowsAffected = 0;
        String query = "UPDATE Staff SET firstName = ?, lastName = ?, role = ?, email = ?, phoneNumber = ? WHERE staffId = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setString(1, firstName);
            pstat.setString(2, lastName);
            pstat.setString(3, role);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, staffId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    private int deleteStaff(int staffId) {
        int rowsAffected = 0;
        String query = "DELETE FROM Staff WHERE staffId = ?";
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {
            pstat.setInt(1, staffId);
            rowsAffected = pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new mainGUI().setVisible(true));
    }
}
