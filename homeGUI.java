import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class homeGUI extends JFrame {
    // Constant for uniform CRUD button size.
    private static final Dimension BUTTON_SIZE = new Dimension(150, 30);

    // Center panel where all operation panels (forms, tables, etc.) are loaded.
    private final JPanel centerPanel;

    public homeGUI() {
        setTitle("Pharmacy System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel with welcome message.
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(0, 128, 128));
        JLabel welcomeLabel = new JLabel("Welcome to the Pharmacy System");
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel);
        add(topPanel, BorderLayout.NORTH);

        // Side menu panel.
        JPanel sideMenu = createSideMenuPanel();
        add(sideMenu, BorderLayout.WEST);

        // Center panel.
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(createPlaceholderPanel(), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Helper to replace null or "null" values with an empty string.
    private String sanitize(String s) {
        if (s == null || s.equalsIgnoreCase("null")) {
            return "";
        }
        return s;
    }

    // Creates a placeholder panel.
    private JPanel createPlaceholderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel placeholderLabel = new JLabel("Select an operation from the side menu", SwingConstants.CENTER);
        placeholderLabel.setFont(new Font("Arial", Font.ITALIC, 24));
        panel.add(placeholderLabel, BorderLayout.CENTER);
        return panel;
    }

    // Updates the center panel for form views (fixed size).
    private void setCenterPanel(JPanel newPanel) {
        centerPanel.removeAll();
        JPanel container = new JPanel(new GridBagLayout());
        newPanel.setPreferredSize(new Dimension(600, 400));
        container.add(newPanel);
        centerPanel.add(container, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // Updates the center panel for view displays.
    private void setCenterPanelView(JPanel newPanel) {
        centerPanel.removeAll();
        JPanel container = new JPanel(new GridBagLayout());
        newPanel.setPreferredSize(new Dimension(700, 300));
        container.add(newPanel);
        centerPanel.add(container, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    // Creates a collapsible panel for a category.
    private JPanel createCollapsiblePanel(String title, List<JButton> buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton headerButton = new JButton(title);
        headerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, headerButton.getPreferredSize().height));

        JPopupMenu popupMenu = new JPopupMenu();
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setPreferredSize(BUTTON_SIZE);
            btn.setMaximumSize(BUTTON_SIZE);
            popupMenu.add(btn);
            popupMenu.addSeparator();
        }
        headerButton.addActionListener(e -> popupMenu.show(headerButton, headerButton.getWidth(), 0));
        panel.add(headerButton);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }

    // Creates the side menu panel.
    private JPanel createSideMenuPanel() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(175, 0));
        sideMenu.setBackground(new Color(230, 230, 250));

        // Customer Operations.
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

        // Faulty Items Operations (without update).
        JButton addFaultyItemBtn = new JButton("Add Faulty Item");
        addFaultyItemBtn.addActionListener(e -> openAddFaultyItemWindow());
        JButton deleteFaultyItemBtn = new JButton("Delete Faulty Item");
        deleteFaultyItemBtn.addActionListener(e -> openDeleteFaultyItemWindow());
        JButton viewFaultyItemsBtn = new JButton("View Faulty Items");
        viewFaultyItemsBtn.addActionListener(e -> openViewFaultyItemsWindow());
        List<JButton> faultyItemsButtons = Arrays.asList(addFaultyItemBtn, deleteFaultyItemBtn, viewFaultyItemsBtn);
        JPanel faultyItemsPanel = createCollapsiblePanel("Faulty Items", faultyItemsButtons);

        // Orders Operations.
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

        // Equipment Operations.
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

        // Staff Operations.
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

        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(customerPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        // Stock operations have been removed.
        sideMenu.add(faultyItemsPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(ordersPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(equipmentPanel);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 20)));
        sideMenu.add(staffPanel);
        sideMenu.add(Box.createVerticalGlue());

        JButton logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        logoutButton.setPreferredSize(BUTTON_SIZE);
        logoutButton.setMaximumSize(BUTTON_SIZE);
        logoutButton.addActionListener(e -> {
            this.dispose();
            new loginPage().setVisible(true);
        });
        sideMenu.add(Box.createRigidArea(new Dimension(0, 10)));
        sideMenu.add(logoutButton);
        sideMenu.add(Box.createRigidArea(new Dimension(0, 10)));

        return sideMenu;
    }

    // ========================= Customer Operations =========================
    // --- Add Customer ---
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
            int rowsAffected = Operations.addCustomer(customerData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Update Customer with Dropdown ---
    private void openUpdateCustomerWindow() {
        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customer data available.");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Select Customer:"));
        JComboBox<String> customerDropdown = new JComboBox<>();
        customerDropdown.setBackground(Color.WHITE);
        customerDropdown.addItem("-- Select Customer --");
        for (Map<String, String> rec : customers) {
            String display = "ID: " + rec.get("customerId") + " - " +
                    sanitize(rec.get("firstName")) + " " + sanitize(rec.get("lastName"));
            customerDropdown.addItem(display);
        }
        panel.add(customerDropdown);

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

        customerDropdown.addActionListener(e -> {
            int index = customerDropdown.getSelectedIndex();
            if (index <= 0) {
                firstNameField.setText("");
                lastNameField.setText("");
                addressField.setText("");
                emailField.setText("");
                phoneField.setText("");
            } else {
                Map<String, String> rec = customers.get(index - 1);
                firstNameField.setText(sanitize(rec.get("firstName")));
                lastNameField.setText(sanitize(rec.get("lastName")));
                addressField.setText(sanitize(rec.get("address")));
                emailField.setText(sanitize(rec.get("email")));
                phoneField.setText(sanitize(rec.get("phoneNumber")));
            }
        });

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int index = customerDropdown.getSelectedIndex();
            if (index <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid customer.");
                return;
            }
            if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "First Name and Last Name cannot be empty.");
                return;
            }
            Map<String, String> selectedRec = customers.get(index - 1);
            int customerId = Integer.parseInt(selectedRec.get("customerId"));
            int rowsAffected = Operations.updateCustomer(
                    customerId,
                    firstNameField.getText(),
                    lastNameField.getText(),
                    addressField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Delete Customer with Disabled Detail Fields ---
    private void openDeleteCustomerWindow() {
        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customer data available.");
            return;
        }
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top: Dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dropdownLabel = new JLabel("Select Customer to Delete:");
        topPanel.add(dropdownLabel);
        JComboBox<String> customerDropdown = new JComboBox<>();
        customerDropdown.setBackground(Color.WHITE);
        customerDropdown.addItem("-- Select Customer --");
        for (Map<String, String> rec : customers) {
            String display = "ID: " + rec.get("customerId") + " - " +
                    sanitize(rec.get("firstName")) + " " + sanitize(rec.get("lastName"));
            customerDropdown.addItem(display);
        }
        topPanel.add(customerDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel lblFirstName = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        firstNameField.setEditable(false);
        firstNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblFirstName);
        detailsPanel.add(firstNameField);

        JLabel lblLastName = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        lastNameField.setEditable(false);
        lastNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblLastName);
        detailsPanel.add(lastNameField);

        JLabel lblAddress = new JLabel("Address:");
        JTextField addressField = new JTextField();
        addressField.setEditable(false);
        addressField.setBackground(Color.WHITE);
        detailsPanel.add(lblAddress);
        detailsPanel.add(addressField);

        JLabel lblEmail = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailField.setEditable(false);
        emailField.setBackground(Color.WHITE);
        detailsPanel.add(lblEmail);
        detailsPanel.add(emailField);

        JLabel lblPhone = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        phoneField.setEditable(false);
        phoneField.setBackground(Color.WHITE);
        detailsPanel.add(lblPhone);
        detailsPanel.add(phoneField);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Bottom: Delete and Cancel buttons.
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setCenterPanel(panel);

        // Populate details when a record is selected.
        customerDropdown.addActionListener(e -> {
            int idx = customerDropdown.getSelectedIndex();
            if (idx <= 0) {
                firstNameField.setText("");
                lastNameField.setText("");
                addressField.setText("");
                emailField.setText("");
                phoneField.setText("");
            } else {
                Map<String, String> rec = customers.get(idx - 1);
                firstNameField.setText(sanitize(rec.get("firstName")));
                lastNameField.setText(sanitize(rec.get("lastName")));
                addressField.setText(sanitize(rec.get("address")));
                emailField.setText(sanitize(rec.get("email")));
                phoneField.setText(sanitize(rec.get("phoneNumber")));
            }
        });

        deleteButton.addActionListener(e -> {
            int idx = customerDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid customer.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            Map<String, String> rec = customers.get(idx - 1);
            int customerId = Integer.parseInt(rec.get("customerId"));
            int rowsAffected = Operations.deleteCustomer(customerId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting customer.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- View Customers ---
    private void openViewCustomersWindow() {
        List<Map<String, String>> customers = GenericDatabaseManager.fetchData("Customer");
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No customer data available.");
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
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanelView(panel);
    }

    // ========================= Faulty Items Operations =========================
    // --- Add Faulty Item ---
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
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity.");
                return;
            }
            faultyItemData.put("reportedDate", reportedDateField.getText());
            int rowsAffected = Operations.addFaultyItem(faultyItemData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Faulty item added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding faulty item.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Delete Faulty Item with Disabled Detail Fields ---
    private void openDeleteFaultyItemWindow() {
        List<Map<String, String>> items = GenericDatabaseManager.fetchData("FaultyItems");
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No faulty items data available.");
            return;
        }
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top: Dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dropdownLabel = new JLabel("Select Faulty Item to Delete:");
        topPanel.add(dropdownLabel);
        JComboBox<String> itemDropdown = new JComboBox<>();
        itemDropdown.setBackground(Color.WHITE);
        itemDropdown.addItem("-- Select Faulty Item --");
        for (Map<String, String> rec : items) {
            String display = "ID: " + rec.get("itemId") + " - " + sanitize(rec.get("itemName"));
            itemDropdown.addItem(display);
        }
        topPanel.add(itemDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel lblItemName = new JLabel("Item Name:");
        JTextField itemNameField = new JTextField();
        itemNameField.setEditable(false);
        itemNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblItemName);
        detailsPanel.add(itemNameField);

        JLabel lblDescription = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        descriptionField.setEditable(false);
        descriptionField.setBackground(Color.WHITE);
        detailsPanel.add(lblDescription);
        detailsPanel.add(descriptionField);

        JLabel lblQuantity = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        quantityField.setEditable(false);
        quantityField.setBackground(Color.WHITE);
        detailsPanel.add(lblQuantity);
        detailsPanel.add(quantityField);

        JLabel lblReportedDate = new JLabel("Reported Date:");
        JTextField reportedDateField = new JTextField();
        reportedDateField.setEditable(false);
        reportedDateField.setBackground(Color.WHITE);
        detailsPanel.add(lblReportedDate);
        detailsPanel.add(reportedDateField);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Bottom: Delete and Cancel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setCenterPanel(panel);

        // Populate details when selected.
        itemDropdown.addActionListener(e -> {
            int idx = itemDropdown.getSelectedIndex();
            if (idx <= 0) {
                itemNameField.setText("");
                descriptionField.setText("");
                quantityField.setText("");
                reportedDateField.setText("");
            } else {
                Map<String, String> rec = items.get(idx - 1);
                itemNameField.setText(sanitize(rec.get("itemName")));
                descriptionField.setText(sanitize(rec.get("description")));
                quantityField.setText(sanitize(rec.get("quantity")));
                reportedDateField.setText(sanitize(rec.get("reportedDate")));
            }
        });

        deleteButton.addActionListener(e -> {
            int idx = itemDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid faulty item.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this faulty item?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            Map<String, String> rec = items.get(idx - 1);
            int itemId = Integer.parseInt(rec.get("itemId"));
            int rowsAffected = Operations.deleteFaultyItem(itemId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Faulty item deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting faulty item.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- View Faulty Items ---
    private void openViewFaultyItemsWindow() {
        List<Map<String, String>> items = GenericDatabaseManager.fetchData("FaultyItems");
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No faulty items data available.");
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
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanelView(panel);
    }

    // ========================= Orders Operations =========================
    // --- Add Order ---
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
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Customer ID.");
                return;
            }
            orderData.put("orderDate", orderDateField.getText());
            try {
                orderData.put("totalAmount", Double.parseDouble(totalAmountField.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Total Amount.");
                return;
            }
            orderData.put("status", statusField.getText());
            int rowsAffected = Operations.addOrder(orderData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Order added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Update Order with Dropdown ---
    private void openUpdateOrderWindow() {
        List<Map<String, String>> orders = GenericDatabaseManager.fetchData("Orders");
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders data available.");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Select Order:"));
        JComboBox<String> orderDropdown = new JComboBox<>();
        orderDropdown.setBackground(Color.WHITE);
        orderDropdown.addItem("-- Select Order --");
        for (Map<String, String> rec : orders) {
            String display = "ID: " + rec.get("orderId") + " - " + sanitize(rec.get("orderDate"));
            orderDropdown.addItem(display);
        }
        panel.add(orderDropdown);
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

        orderDropdown.addActionListener(e -> {
            int idx = orderDropdown.getSelectedIndex();
            if (idx <= 0) {
                customerIdField.setText("");
                orderDateField.setText("");
                totalAmountField.setText("");
                statusField.setText("");
            } else {
                Map<String, String> rec = orders.get(idx - 1);
                customerIdField.setText(sanitize(rec.get("customerId")));
                orderDateField.setText(sanitize(rec.get("orderDate")));
                totalAmountField.setText(sanitize(rec.get("totalAmount")));
                statusField.setText(sanitize(rec.get("status")));
            }
        });

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int idx = orderDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid order.");
                return;
            }
            Map<String, String> rec = orders.get(idx - 1);
            int orderId = Integer.parseInt(rec.get("orderId"));
            int rowsAffected;
            try {
                rowsAffected = Operations.updateOrder(
                        orderId,
                        Integer.parseInt(customerIdField.getText()),
                        orderDateField.getText(),
                        Double.parseDouble(totalAmountField.getText()),
                        statusField.getText()
                );
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid numeric input.");
                return;
            }
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Order updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Delete Order with Disabled Detail Fields ---
    private void openDeleteOrderWindow() {
        List<Map<String, String>> orders = GenericDatabaseManager.fetchData("Orders");
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders data available.");
            return;
        }
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top: Dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dropdownLabel = new JLabel("Select Order to Delete:");
        topPanel.add(dropdownLabel);
        JComboBox<String> orderDropdown = new JComboBox<>();
        orderDropdown.setBackground(Color.WHITE);
        orderDropdown.addItem("-- Select Order --");
        for (Map<String, String> rec : orders) {
            String display = "ID: " + rec.get("orderId") + " - " + sanitize(rec.get("orderDate"));
            orderDropdown.addItem(display);
        }
        topPanel.add(orderDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel lblCustomerId = new JLabel("Customer ID:");
        JTextField customerIdField = new JTextField();
        customerIdField.setEditable(false);
        customerIdField.setBackground(Color.WHITE);
        detailsPanel.add(lblCustomerId);
        detailsPanel.add(customerIdField);

        JLabel lblOrderDate = new JLabel("Order Date:");
        JTextField orderDateField = new JTextField();
        orderDateField.setEditable(false);
        orderDateField.setBackground(Color.WHITE);
        detailsPanel.add(lblOrderDate);
        detailsPanel.add(orderDateField);

        JLabel lblTotalAmount = new JLabel("Total Amount:");
        JTextField totalAmountField = new JTextField();
        totalAmountField.setEditable(false);
        totalAmountField.setBackground(Color.WHITE);
        detailsPanel.add(lblTotalAmount);
        detailsPanel.add(totalAmountField);

        JLabel lblStatus = new JLabel("Status:");
        JTextField statusField = new JTextField();
        statusField.setEditable(false);
        statusField.setBackground(Color.WHITE);
        detailsPanel.add(lblStatus);
        detailsPanel.add(statusField);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Bottom: Delete and Cancel buttons.
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setCenterPanel(panel);

        // Populate details on selection.
        orderDropdown.addActionListener(e -> {
            int idx = orderDropdown.getSelectedIndex();
            if (idx <= 0) {
                customerIdField.setText("");
                orderDateField.setText("");
                totalAmountField.setText("");
                statusField.setText("");
            } else {
                Map<String, String> rec = orders.get(idx - 1);
                customerIdField.setText(sanitize(rec.get("customerId")));
                orderDateField.setText(sanitize(rec.get("orderDate")));
                totalAmountField.setText(sanitize(rec.get("totalAmount")));
                statusField.setText(sanitize(rec.get("status")));
            }
        });

        deleteButton.addActionListener(e -> {
            int idx = orderDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid order.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this order?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            Map<String, String> rec = orders.get(idx - 1);
            int orderId = Integer.parseInt(rec.get("orderId"));
            int rowsAffected = Operations.deleteOrder(orderId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Order deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting order.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- View Orders ---
    private void openViewOrdersWindow() {
        List<Map<String, String>> orders = GenericDatabaseManager.fetchData("Orders");
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No orders data available.");
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
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanelView(panel);
    }

    // ========================= Equipment Operations =========================
    // --- Add Equipment ---
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
            int rowsAffected = Operations.addEquipment(equipmentData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Equipment added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Update Equipment with Dropdown ---
    private void openUpdateEquipmentWindow() {
        List<Map<String, String>> equipmentList = GenericDatabaseManager.fetchData("Equipment");
        if (equipmentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No equipment data available.");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Select Equipment:"));
        JComboBox<String> equipmentDropdown = new JComboBox<>();
        equipmentDropdown.setBackground(Color.WHITE);
        equipmentDropdown.addItem("-- Select Equipment --");
        for (Map<String, String> rec : equipmentList) {
            String display = "ID: " + rec.get("equipmentId") + " - " + sanitize(rec.get("equipmentName"));
            equipmentDropdown.addItem(display);
        }
        panel.add(equipmentDropdown);
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

        equipmentDropdown.addActionListener(e -> {
            int idx = equipmentDropdown.getSelectedIndex();
            if (idx <= 0) {
                equipmentNameField.setText("");
                equipmentTypeField.setText("");
                purchaseDateField.setText("");
                statusField.setText("");
            } else {
                Map<String, String> rec = equipmentList.get(idx - 1);
                equipmentNameField.setText(sanitize(rec.get("equipmentName")));
                equipmentTypeField.setText(sanitize(rec.get("equipmentType")));
                purchaseDateField.setText(sanitize(rec.get("purchaseDate")));
                statusField.setText(sanitize(rec.get("status")));
            }
        });

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int idx = equipmentDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid equipment record.");
                return;
            }
            Map<String, String> rec = equipmentList.get(idx - 1);
            int equipmentId = Integer.parseInt(rec.get("equipmentId"));
            int rowsAffected = Operations.updateEquipment(
                    equipmentId,
                    equipmentNameField.getText(),
                    equipmentTypeField.getText(),
                    purchaseDateField.getText(),
                    statusField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Equipment updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Delete Equipment with Disabled Detail Fields ---
    private void openDeleteEquipmentWindow() {
        List<Map<String, String>> equipmentList = GenericDatabaseManager.fetchData("Equipment");
        if (equipmentList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No equipment data available.");
            return;
        }
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top: Dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dropdownLabel = new JLabel("Select Equipment to Delete:");
        topPanel.add(dropdownLabel);
        JComboBox<String> equipmentDropdown = new JComboBox<>();
        equipmentDropdown.setBackground(Color.WHITE);
        equipmentDropdown.addItem("-- Select Equipment --");
        for (Map<String, String> rec : equipmentList) {
            String display = "ID: " + rec.get("equipmentId") + " - " + sanitize(rec.get("equipmentName"));
            equipmentDropdown.addItem(display);
        }
        topPanel.add(equipmentDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JLabel lblEquipName = new JLabel("Equipment Name:");
        JTextField equipmentNameField = new JTextField();
        equipmentNameField.setEditable(false);
        equipmentNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblEquipName);
        detailsPanel.add(equipmentNameField);

        JLabel lblEquipType = new JLabel("Equipment Type:");
        JTextField equipmentTypeField = new JTextField();
        equipmentTypeField.setEditable(false);
        equipmentTypeField.setBackground(Color.WHITE);
        detailsPanel.add(lblEquipType);
        detailsPanel.add(equipmentTypeField);

        JLabel lblPurchaseDate = new JLabel("Purchase Date:");
        JTextField purchaseDateField = new JTextField();
        purchaseDateField.setEditable(false);
        purchaseDateField.setBackground(Color.WHITE);
        detailsPanel.add(lblPurchaseDate);
        detailsPanel.add(purchaseDateField);

        JLabel lblStatus = new JLabel("Status:");
        JTextField statusField = new JTextField();
        statusField.setEditable(false);
        statusField.setBackground(Color.WHITE);
        detailsPanel.add(lblStatus);
        detailsPanel.add(statusField);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Bottom: Delete and Cancel buttons.
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setCenterPanel(panel);

        equipmentDropdown.addActionListener(e -> {
            int idx = equipmentDropdown.getSelectedIndex();
            if (idx <= 0) {
                equipmentNameField.setText("");
                equipmentTypeField.setText("");
                purchaseDateField.setText("");
                statusField.setText("");
            } else {
                Map<String, String> rec = equipmentList.get(idx - 1);
                equipmentNameField.setText(sanitize(rec.get("equipmentName")));
                equipmentTypeField.setText(sanitize(rec.get("equipmentType")));
                purchaseDateField.setText(sanitize(rec.get("purchaseDate")));
                statusField.setText(sanitize(rec.get("status")));
            }
        });

        deleteButton.addActionListener(e -> {
            int idx = equipmentDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid equipment record.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this equipment record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            Map<String, String> rec = equipmentList.get(idx - 1);
            int equipmentId = Integer.parseInt(rec.get("equipmentId"));
            int rowsAffected = Operations.deleteEquipment(equipmentId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Equipment deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting equipment.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- View Equipment ---
    private void openViewEquipmentWindow() {
        List<Map<String, String>> equipment = GenericDatabaseManager.fetchData("Equipment");
        if (equipment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No equipment data available.");
            setCenterPanel(createPlaceholderPanel());
            return;
        }
        Set<String> columns = equipment.get(0).keySet();
        String[] columnNames = columns.toArray(new String[0]);
        Object[][] data = new Object[equipment.size()][columnNames.length];
        for (int i = 0; i < equipment.size(); i++) {
            Map<String, String> record = equipment.get(i);
            int j = 0;
            for (String col : columnNames) {
                data[i][j++] = record.get(col);
            }
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    // ========================= Staff Operations =========================
    // --- Add Staff ---
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
            int rowsAffected = Operations.addStaff(staffData);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Staff added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error adding staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Update Staff with Dropdown ---
    private void openUpdateStaffWindow() {
        List<Map<String, String>> staffList = GenericDatabaseManager.fetchData("Staff");
        if (staffList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No staff data available.");
            return;
        }
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.add(new JLabel("Select Staff:"));
        JComboBox<String> staffDropdown = new JComboBox<>();
        staffDropdown.setBackground(Color.WHITE);
        staffDropdown.addItem("-- Select Staff --");
        for (Map<String, String> rec : staffList) {
            String display = "ID: " + rec.get("staffId") + " - " +
                    sanitize(rec.get("firstName")) + " " + sanitize(rec.get("lastName"));
            staffDropdown.addItem(display);
        }
        panel.add(staffDropdown);
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

        staffDropdown.addActionListener(e -> {
            int idx = staffDropdown.getSelectedIndex();
            if (idx <= 0) {
                firstNameField.setText("");
                lastNameField.setText("");
                roleField.setText("");
                emailField.setText("");
                phoneField.setText("");
            } else {
                Map<String, String> rec = staffList.get(idx - 1);
                firstNameField.setText(sanitize(rec.get("firstName")));
                lastNameField.setText(sanitize(rec.get("lastName")));
                roleField.setText(sanitize(rec.get("role")));
                emailField.setText(sanitize(rec.get("email")));
                phoneField.setText(sanitize(rec.get("phoneNumber")));
            }
        });

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        setCenterPanel(panel);

        updateButton.addActionListener(e -> {
            int idx = staffDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid staff record.");
                return;
            }
            Map<String, String> rec = staffList.get(idx - 1);
            int staffId = Integer.parseInt(rec.get("staffId"));
            int rowsAffected = Operations.updateStaff(
                    staffId,
                    firstNameField.getText(),
                    lastNameField.getText(),
                    roleField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Staff updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- Delete Staff with Disabled Detail Fields ---
    private void openDeleteStaffWindow() {
        List<Map<String, String>> staffList = GenericDatabaseManager.fetchData("Staff");
        if (staffList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No staff data available.");
            return;
        }
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        // Top: Dropdown
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dropdownLabel = new JLabel("Select Staff to Delete:");
        topPanel.add(dropdownLabel);
        JComboBox<String> staffDropdown = new JComboBox<>();
        staffDropdown.setBackground(Color.WHITE);
        staffDropdown.addItem("-- Select Staff --");
        for (Map<String, String> rec : staffList) {
            String display = "ID: " + rec.get("staffId") + " - " +
                    sanitize(rec.get("firstName")) + " " + sanitize(rec.get("lastName"));
            staffDropdown.addItem(display);
        }
        topPanel.add(staffDropdown);
        panel.add(topPanel, BorderLayout.NORTH);

        // Center: Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel lblFirstName = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        firstNameField.setEditable(false);
        firstNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblFirstName);
        detailsPanel.add(firstNameField);

        JLabel lblLastName = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        lastNameField.setEditable(false);
        lastNameField.setBackground(Color.WHITE);
        detailsPanel.add(lblLastName);
        detailsPanel.add(lastNameField);

        JLabel lblRole = new JLabel("Role:");
        JTextField roleField = new JTextField();
        roleField.setEditable(false);
        roleField.setBackground(Color.WHITE);
        detailsPanel.add(lblRole);
        detailsPanel.add(roleField);

        JLabel lblEmail = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailField.setEditable(false);
        emailField.setBackground(Color.WHITE);
        detailsPanel.add(lblEmail);
        detailsPanel.add(emailField);

        JLabel lblPhone = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();
        phoneField.setEditable(false);
        phoneField.setBackground(Color.WHITE);
        detailsPanel.add(lblPhone);
        detailsPanel.add(phoneField);

        panel.add(detailsPanel, BorderLayout.CENTER);

        // Bottom: Delete and Cancel buttons.
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        bottomPanel.add(deleteButton);
        bottomPanel.add(cancelButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setCenterPanel(panel);

        staffDropdown.addActionListener(e -> {
            int idx = staffDropdown.getSelectedIndex();
            if (idx <= 0) {
                firstNameField.setText("");
                lastNameField.setText("");
                roleField.setText("");
                emailField.setText("");
                phoneField.setText("");
            } else {
                Map<String, String> rec = staffList.get(idx - 1);
                firstNameField.setText(sanitize(rec.get("firstName")));
                lastNameField.setText(sanitize(rec.get("lastName")));
                roleField.setText(sanitize(rec.get("role")));
                emailField.setText(sanitize(rec.get("email")));
                phoneField.setText(sanitize(rec.get("phoneNumber")));
            }
        });

        deleteButton.addActionListener(e -> {
            int idx = staffDropdown.getSelectedIndex();
            if (idx <= 0) {
                JOptionPane.showMessageDialog(this, "Please select a valid staff record.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this staff record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;
            Map<String, String> rec = staffList.get(idx - 1);
            int staffId = Integer.parseInt(rec.get("staffId"));
            int rowsAffected = Operations.deleteStaff(staffId);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Staff deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting staff.");
            }
            setCenterPanel(createPlaceholderPanel());
        });
        cancelButton.addActionListener(e -> setCenterPanel(createPlaceholderPanel()));
    }

    // --- View Staff ---
    private void openViewStaffWindow() {
        List<Map<String, String>> staffList = GenericDatabaseManager.fetchData("Staff");
        if (staffList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No staff data available.");
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
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        setCenterPanel(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new homeGUI().setVisible(true));
    }
}
