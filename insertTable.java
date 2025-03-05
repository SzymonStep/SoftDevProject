import java.sql.*; // Import SQL classes for database connection and operations
import java.util.*; // Import utilities like HashMap
import java.sql.Date; // Import Date class for handling SQL date types
import java.time.LocalDate; // Import LocalDate for handling current date

public class insertTable {

    // Database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root"; // Change as needed
    private static final String PASSWORD = "password"; // Change as needed

    public static int insertRecord(String tableName, Map<String, Object> data) {
        if (data.isEmpty()) {
            System.out.println("No data provided to insert.");
            return 0;
        }

        // Construct SQL query dynamically
        String columns = String.join(", ", data.keySet());
        String placeholders = String.join(", ", Collections.nCopies(data.size(), "?"));
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            PreparedStatement pstat = connection.prepareStatement(query)
        ) {
            // Set values dynamically for PreparedStatement
            int index = 1;
            for (Object value : data.values()) {
                pstat.setObject(index++, value);
            }

            // Execute insert operation
            int rowsAffected = pstat.executeUpdate();
            System.out.println(rowsAffected + " record(s) successfully added to " + tableName);
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** Insert a customer record into the Customer table */
    public static void insertCustomer() {
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", "John");
        customerData.put("lastName", "Doe");
        customerData.put("address", "123 Main St");
        customerData.put("email", "john.doe@example.com");
        customerData.put("phoneNumber", "0651234567");

        insertRecord("Customer", customerData);
    }

    /** Insert a staff record into the Staff table */
    public static void insertStaff() {
        Map<String, Object> staffData = new HashMap<>();
        staffData.put("firstName", "Sarah");
        staffData.put("lastName", "Connor");
        staffData.put("staffRole", "Manager");
        staffData.put("email", "sarah.connor@example.com");
        staffData.put("phoneNumber", "0851234567");

        insertRecord("Staff", staffData);
    }

    /** Insert an equipment record into the Equipment table */
    public static void insertEquipmentData() {
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("equipmentName", "equipmentName1");
        equipmentData.put("equipmentType", "equipmentType");
        equipmentData.put("equipmentSpecifications", "equipmentSpecifications");
        equipmentData.put("quantityAvailable", 101);
        equipmentData.put("equipmentPrice", 9.99);

        insertRecord("Equipment", equipmentData);
    }

    /** Insert an order record into the Orders table */
    public static void insertOrdersData() {
        Map<String, Object> ordersData = new HashMap<>();
        ordersData.put("customerId", 1);
        ordersData.put("orderDate", Date.valueOf(LocalDate.now())); // Get current date
        ordersData.put("totalAmount", 9.99);
        ordersData.put("orderStatus", "Processing");

        insertRecord("Orders", ordersData);
    }

    /** Insert an order return record into the OrderReturns table */
    public static void insertOrderReturnsData() {
        Map<String, Object> orderReturnsData = new HashMap<>();
        orderReturnsData.put("orderId", 1);
        orderReturnsData.put("equipmentId", 1);
        orderReturnsData.put("reason", "Reason 1");
        orderReturnsData.put("orderReturnStatus", "In Progress");
        orderReturnsData.put("replacementRequested", 1);

        insertRecord("OrderReturns", orderReturnsData);
    }

    /** Main method to insert all predefined records into respective tables */
    public static void main(String[] args) {
        insertCustomer();
        insertStaff();
        insertEquipmentData();
        insertOrdersData();
        insertOrderReturnsData();
    }
}
