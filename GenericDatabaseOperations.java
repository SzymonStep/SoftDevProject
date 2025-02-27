import java.sql.*;
import java.util.*;

public class GenericDatabaseOperations {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root"; // Change as needed
    private static final String PASSWORD = "password"; // Change as needed

    // ✅ Generic Insert Method using PreparedStatement
    public static int insertRecord(String tableName, Map<String, Object> data) {
        if (data.isEmpty()) {
            System.out.println("No data provided to insert.");
            return 0;
        }

        // 🔹 Generate SQL statement dynamically
        String columns = String.join(", ", data.keySet());
        String placeholders = String.join(", ", Collections.nCopies(data.size(), "?"));
        String query = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            PreparedStatement pstat = connection.prepareStatement(query)
        ) {
            // 🔹 Set values dynamically using PreparedStatement
            int index = 1;
            for (Object value : data.values()) {
                pstat.setObject(index++, value);
            }

            // 🔹 Execute Insert
            int rowsAffected = pstat.executeUpdate();
            System.out.println(rowsAffected + " record(s) successfully added to " + tableName);
            return rowsAffected;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        // ✅ Example: Insert into Customer Table
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", "John");
        customerData.put("lastName", "Doe");
        customerData.put("address", "123 Main St");
        customerData.put("email", "john.doe@example.com");
        customerData.put("phoneNumber", "0651234567");

        insertRecord("Customer", customerData);

        // ✅ Example: Insert into Staff Table
        Map<String, Object> staffData = new HashMap<>();
        staffData.put("firstName", "Sarah");
        staffData.put("lastName", "Connor");
        staffData.put("staffRole", "Manager");
        staffData.put("email", "sarah.connor@example.com");
        staffData.put("phoneNumber", "0851234567");

        insertRecord("Staff", staffData);

        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("equipmentName", "equipmentName1");
        equipmentData.put("equipmentType", "equipmentType");
        equipmentData.put("equipmentSpecifications", "equipmentSpecifications");
        equipmentData.put("quantityAvailable", "101");
        equipmentData.put("equipmentPrice", "9.99");

        insertRecord("Equipment", equipmentData);

        Map<String, Object> ordersData = new HashMap<>();
        ordersData.put("customerId", "customerId");
        ordersData.put("orderDate", "date");        // Date entry??
        ordersData.put("totalAmount", "9.99");     
        ordersData.put("orderStatus", "Processing");

        insertRecord("Orders", ordersData);

        Map<String, Object> OrderReturnsData = new HashMap<>();
        OrderReturnsData.put("orderId", "01");
        OrderReturnsData.put("equipmentId", "123");
        OrderReturnsData.put("reason", "Reason 1");
        OrderReturnsData.put("orderReturnStatus", "In Progress");
        OrderReturnsData.put("replacementRequested", "true"); // Double check logic with foreign keys

        insertRecord("OrderReturns", OrderReturnsData);

        Map<String, Object> orderAndEquipmentData = new HashMap<>();
        orderAndEquipmentData.put("orderId", "123");
        orderAndEquipmentData.put("equipmentID", "456");
        orderAndEquipmentData.put("replacementRequested", "true"); // Double check logic with foreign keys
        orderAndEquipmentData.put("quantityAvailable", "101");
        orderAndEquipmentData.put("equipmentPrice", "$9.99");

        insertRecord("OrderAndEquipment", orderAndEquipmentData);

        Map<String, Object> customerFeedbackData = new HashMap<>();
        customerFeedbackData.put("customerId", "123");
        customerFeedbackData.put("orderId", "456");
        customerFeedbackData.put("comments", "comment"); // Double check logic with foreign keys
        customerFeedbackData.put("rating", "10");

        insertRecord("CustomerFeedback", customerFeedbackData);

        Map<String, Object> deliveryData = new HashMap<>();
        deliveryData.put("orderId", "123");
        deliveryData.put("estimatedDeliveryTime", "In Progress"); 
        deliveryData.put("trackingStatus", "101");

        insertRecord("Delivery", deliveryData);

        Map<String, Object> reportData = new HashMap<>();
        reportData.put("reportType", "");
        reportData.put("reportData", "456");
        reportData.put("generateDate", "date");

        insertRecord("Reports", reportData);

        Map<String, Object> faultyItemData = new HashMap<>();
        faultyItemData.put("equipmentID", "456");
        faultyItemData.put("batchNumber", "123"); 
        faultyItemData.put("faultDescription", "text");
        faultyItemData.put("reportedDate", "date");

        insertRecord("FaultyItems", faultyItemData);






    }
}
