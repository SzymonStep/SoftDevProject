import java.sql.*; // Import SQL classes for database operations
import java.util.*; // Import utilities like HashMap
import java.sql.Date; // Import SQL Date class
import java.time.LocalDate; // Import LocalDate for handling current date

public class updateTable {

    // Database connection details
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root"; // Change as needed
    private static final String PASSWORD = "password"; // Change as needed

    public static int updateRecord(String tableName, Map<String, Object> data, String primaryKeyColumn, int primaryKeyValue) {
        if (data.isEmpty()) {
            System.out.println("No data provided to update.");
            return 0;
        }

        // Construct SQL UPDATE query dynamically
        String setClause = String.join(" = ?, ", data.keySet()) + " = ?";
        String query = "UPDATE " + tableName + " SET " + setClause + " WHERE " + primaryKeyColumn + " = ?";

        try (
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            PreparedStatement pstat = connection.prepareStatement(query)
        ) {
            // Set column values dynamically
            int index = 1;
            for (Object value : data.values()) {
                pstat.setObject(index++, value);
            }

            // Set the primary key value for the WHERE condition
            pstat.setObject(index, primaryKeyValue);

            // Execute update operation
            int rowsAffected = pstat.executeUpdate();
            System.out.println(rowsAffected + " record(s) successfully updated in " + tableName);
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /** Update a customer record in the Customer table */
    public static void updateCustomer(int customerId) {
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", "John");
        customerData.put("lastName", "Doe");
        customerData.put("address", "456 New St"); // Updated address
        customerData.put("email", "john.doe@newmail.com"); // Updated email
        customerData.put("phoneNumber", "0659876543"); // Updated phone number

        updateRecord("Customer", customerData, "customerId", customerId);
    }

    /** Update a staff record in the Staff table */
    public static void updateStaff(int staffId) {
        Map<String, Object> staffData = new HashMap<>();
        staffData.put("firstName", "Sarah");
        staffData.put("lastName", "Connor");
        staffData.put("staffRole", "Manager"); // Updated role
        staffData.put("email", "sarah.connor@newmail.com"); // Updated email
        staffData.put("phoneNumber", "0859999999"); // Updated phone number

        updateRecord("Staff", staffData, "staffId", staffId);
    }

    /** Update an equipment record in the Equipment table */
    public static void updateEquipment(int equipmentId) {
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("equipmentName", "Updated Equipment");
        equipmentData.put("equipmentType", "Updated Type");
        equipmentData.put("equipmentSpecifications", "Updated Specifications");
        equipmentData.put("quantityAvailable", 150); // Updated quantity
        equipmentData.put("equipmentPrice", 15.99); // Updated price

        updateRecord("Equipment", equipmentData, "equipmentId", equipmentId);
    }

    /** Update an order record in the Orders table */
    public static void updateOrders(int orderId) {
        Map<String, Object> ordersData = new HashMap<>();
        ordersData.put("customerId", 1);
        ordersData.put("orderDate", Date.valueOf(LocalDate.now())); // Updated date
        ordersData.put("totalAmount", 19.99); // Updated amount
        ordersData.put("orderStatus", "Shipped"); // Updated status

        updateRecord("Orders", ordersData, "orderId", orderId);
    }

    /** Update an order return record in the OrderReturns table */
    public static void updateOrderReturns(int returnId) {
        Map<String, Object> orderReturnsData = new HashMap<>();
        orderReturnsData.put("orderId", 1);
        orderReturnsData.put("equipmentId", 1);
        orderReturnsData.put("reason", "Updated Reason");
        orderReturnsData.put("orderReturnStatus", "Resolved"); // Updated status
        orderReturnsData.put("replacementRequested", 0); // Updated replacement request

        updateRecord("OrderReturns", orderReturnsData, "returnId", returnId);
    }

    /** Main method to update predefined records */
    public static void main(String[] args) {
        updateCustomer(1);
        updateStaff(1);
        updateEquipment(1);
        updateOrders(1);
        updateOrderReturns(1);
    }
}
