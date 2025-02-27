import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;

public class insertTable {
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

    public static void insertCustomer() 
    {
        // Insert into Customer Table
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", "John");
        customerData.put("lastName", "Doe");
        customerData.put("address", "123 Main St");
        customerData.put("email", "john.doe@example.com");
        customerData.put("phoneNumber", "0651234567");

        insertRecord("Customer", customerData);
    }

    public static void insertStaff()
    {
        // Insert into Staff Table
        Map<String, Object> staffData = new HashMap<>();
        staffData.put("firstName", "Sarah");
        staffData.put("lastName", "Connor");
        staffData.put("staffRole", "Manager");
        staffData.put("email", "sarah.connor@example.com");
        staffData.put("phoneNumber", "0851234567");

        insertRecord("Staff", staffData);
    }

    public static void insertEquipmentData()
    {
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("equipmentName", "equipmentName1");
        equipmentData.put("equipmentType", "equipmentType");
        equipmentData.put("equipmentSpecifications", "equipmentSpecifications");
        equipmentData.put("quantityAvailable", 101);
        equipmentData.put("equipmentPrice", 9.99);

        insertRecord("Equipment", equipmentData);
    }

    public static void insertOrdersData()
    {
        Map<String, Object> ordersData = new HashMap<>();
        ordersData.put("customerId", "1");
        ordersData.put("orderDate", Date.valueOf(LocalDate.now()));        // Date entry??
        ordersData.put("totalAmount", 9.99);     
        ordersData.put("orderStatus", "Processing");

        insertRecord("Orders", ordersData);
    }
    public static void insertOrderReturnsData()
    {
        Map<String, Object> OrderReturnsData = new HashMap<>();
        OrderReturnsData.put("orderId", "01");
        OrderReturnsData.put("equipmentId", "1");
        OrderReturnsData.put("reason", "Reason 1");
        OrderReturnsData.put("orderReturnStatus", "In Progress");
        OrderReturnsData.put("replacementRequested", "1"); // Double check logic with foreign keys

        insertRecord("OrderReturns", OrderReturnsData);
    }

    public static void insertOrderAndEquipment()
    {
        Map<String, Object> orderAndEquipmentData = new HashMap<>();
        orderAndEquipmentData.put("orderId", 2);
        orderAndEquipmentData.put("equipmentId", 1);

        insertRecord("OrderAndEquipment", orderAndEquipmentData);
    }

    public static void insertCustomerFeedbackData()
    {
        Map<String, Object> customerFeedbackData = new HashMap<>();
        customerFeedbackData.put("customerId", "1");
        customerFeedbackData.put("orderId", "1");
        customerFeedbackData.put("comments", "comment"); // Double check logic with foreign keys
        customerFeedbackData.put("rating", 5);

        insertRecord("CustomerFeedback", customerFeedbackData);
    }

    public static void insertDeliveryData()
    {
        Map<String, Object> deliveryData = new HashMap<>();
        deliveryData.put("orderId", "1");
        deliveryData.put("estimatedDeliveryTime", Date.valueOf(LocalDate.now())); 
        deliveryData.put("trackingStatus", "Processing");

        insertRecord("Delivery", deliveryData);
    }

    public static void insertReportData()
    {
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("reportType", "Report1");
        reportData.put("reportData", "1");
        reportData.put("generateDate", Date.valueOf(LocalDate.now()));

        insertRecord("Reports", reportData);
    }

    public static void insertFaultyItemData()
    {
        Map<String, Object> faultyItemData = new HashMap<>();
        faultyItemData.put("equipmentID", "1");
        faultyItemData.put("batchNumber", "1"); 
        faultyItemData.put("faultDescription", "text");
        faultyItemData.put("reportedDate", Date.valueOf(LocalDate.now()));

        insertRecord("FaultyItems", faultyItemData);
    }
    public static void main(String[] args) 
    {
        // Call each method to insert records into respective tables
        insertCustomer();
        insertStaff();
        insertEquipmentData();
        insertOrdersData();
        insertOrderReturnsData();
        insertOrderAndEquipment();
        insertCustomerFeedbackData();
        insertDeliveryData();
        insertReportData();
        insertFaultyItemData();
    }
}
