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
    public static void updateCustomer(int customerId, String firstName, String lastName, String address, String email, int phoneNumber) {
        Map<String, Object> customerData = new HashMap<>();
        customerData.put("firstName", firstName);
        customerData.put("lastName", lastName);
        customerData.put("address", address); // Updated address
        customerData.put("email", email); // Updated email
        customerData.put("phoneNumber", phoneNumber); // Updated phone number

        updateRecord("Customer", customerData, "customerId", customerId);
    }

    /** Update a staff record in the Staff table */
    public static void updateStaff(int staffId, String firstName, String lastName, String staffRole, String email, int phoneNumber) {
        Map<String, Object> staffData = new HashMap<>();
        staffData.put("firstName", firstName);
        staffData.put("lastName", lastName);
        staffData.put("staffRole", staffRole); // Updated role
        staffData.put("email", email); // Updated email
        staffData.put("phoneNumber", phoneNumber); // Updated phone number

        updateRecord("Staff", staffData, "staffId", staffId);
    }

    /** Update an equipment record in the Equipment table */
    public static void updateEquipment(int equipmentId, String equipmentName, String equipmentType, String equipmentSpecifications, int quantityAvailable, int equipmentPrice ) {
        Map<String, Object> equipmentData = new HashMap<>();
        equipmentData.put("equipmentName", equipmentName);
        equipmentData.put("equipmentType", equipmentType);
        equipmentData.put("equipmentSpecifications", equipmentSpecifications);
        equipmentData.put("quantityAvailable", quantityAvailable); // Updated quantity
        equipmentData.put("equipmentPrice", equipmentPrice); // Updated price

        updateRecord("Equipment", equipmentData, "equipmentId", equipmentId);
    }

    /** Update an order record in the Orders table */
    public static void updateOrders(int orderId, int customerId, double totalAmount, String orderStatus) {
        Map<String, Object> ordersData = new HashMap<>();
        ordersData.put("customerId", orderId);
        ordersData.put("orderDate", Date.valueOf(LocalDate.now())); // Updated date
        ordersData.put("totalAmount", totalAmount); // Updated amount
        ordersData.put("orderStatus", orderStatus); // Updated status

        updateRecord("Orders", ordersData, "orderId", orderId);
    }

    /** Update an order return record in the OrderReturns table */
    public static void updateOrderReturns(int returnId, int orderId, int equipmentId, String reason, String orderReturnStatus, Boolean replacementRequested) {
        Map<String, Object> orderReturnsData = new HashMap<>();
        orderReturnsData.put("orderId", orderId);
        orderReturnsData.put("equipmentId", equipmentId);
        orderReturnsData.put("reason", reason);
        orderReturnsData.put("orderReturnStatus", orderReturnStatus); // Updated status
        orderReturnsData.put("replacementRequested", replacementRequested); // Updated replacement request

        updateRecord("OrderReturns", orderReturnsData, "returnId", returnId);
    }

    public static void updateOrderAndEquipment(int orderId, int equipmentId) {
        Map<String, Object> OrderAndEquipmentData = new HashMap<>();
        OrderAndEquipmentData.put("orderId", orderId );
        OrderAndEquipmentData.put("equipmentId", equipmentId );

        //updateRecord("OrderAndEquipment", OrderAndEquipmentData);
    }
    public static void insertCustomerFeedback(int customerId, int orderId, String comments, int rating) {
        Map<String, Object> customerFeedbackData = new HashMap<>();
        customerFeedbackData.put("customerId", customerId );
        customerFeedbackData.put("orderId", orderId );
        customerFeedbackData.put("comments", comments );
        customerFeedbackData.put("rating", rating );

        //updateRecord("CustomerFeedback", customerFeedbackData);
    }

    public static void insertDelivery(int orderId, Timestamp estimatedDeliveryTime, String trackingStatus ) {
        Map<String, Object> deliveryData = new HashMap<>();
        deliveryData.put("orderid", orderId );
        //deliveryData.put("estimatedDeliveryTime", getEstimatedDeliveryTime());
        deliveryData.put("trackingStatus", trackingStatus);

        ///updateRecord("Delivery", deliveryData);
    }
    public static void insertReports(String reportType, String reportData) {
        Map<String, Object> reportsData = new HashMap<>();
        reportsData.put("reportType", reportType);
        reportsData.put("reportData", reportData);

        //updateRecord("Reports", reportsData);
    }
    public static void insertFaultyItems(int equipmentId, int batchNumber, String faultDescription) {
        Map<String, Object> faultyItemData = new HashMap<>();
        faultyItemData.put("equipmentId", equipmentId);
        faultyItemData.put("batchNumber", batchNumber);
        faultyItemData.put("faultDescription", faultDescription);
        //faultyItemData.put("reportedDate", getCurrentTime());

        //updateRecord("FaultyItems", faultyItemData);
    }

    /** Main method to update predefined records */
    //public static void main(String[] args) {
        //updateCustomer(1);
        //updateStaff(1);
        //updateEquipment(1);
        //updateOrders(1);
        //updateOrderReturns(1);
    //}
}
