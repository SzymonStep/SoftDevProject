import java.sql.*; // Importing required SQL classes for database connection and operations
import java.util.HashMap; // Importing HashMap to store table names and primary keys
import java.util.Map; // Importing Map interface for HashMap
import java.util.Scanner; // Importing Scanner for user input

public class deleteRecords {
    // Database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USERNAME = "root"; // Change as needed
    private static final String PASSWORD = "password"; // Change as needed

    public static void main(String[] args) {
        // Using try-with-resources to automatically close Scanner
        try (Scanner scanner = new Scanner(System.in)) {

            // 🔹 Storing table names and their primary keys using HashMap
            Map<String, String> tables = new HashMap<>();
            tables.put("Customer", "customerId");
            tables.put("Staff", "staffId");
            tables.put("Equipment", "equipmentId");
            tables.put("Orders", "orderId");
            tables.put("OrderReturns", "returnId");
            tables.put("OrderAndEquipment", "orderId, equipmentId"); // Composite key
            tables.put("CustomerFeedback", "feedbackId");
            tables.put("Delivery", "deliveryId");
            tables.put("Reports", "reportId");
            tables.put("FaultyItems", "faultId");

            // 🔹 Displaying available tables
            System.out.println("Available Tables:");
            int index = 1;
            for (String table : tables.keySet()) {
                System.out.println(index++ + ". " + table);
            }

            // 🔹 Prompting user to select a table
            System.out.print("Enter the number of the table you want to delete from: ");
            int tableChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (tableChoice < 1 || tableChoice > tables.size()) {
                System.out.println("Invalid choice. Exiting...");
                return;
            }

            // 🔹 Getting selected table name and primary key
            String tableName = (String) tables.keySet().toArray()[tableChoice - 1];
            String primaryKey = tables.get(tableName);

            // 🔹 Handling Composite Primary Key (OrderAndEquipment)
            if (primaryKey.contains(",")) {
                System.out.print("Enter orderId: ");
                int orderId = scanner.nextInt();
                System.out.print("Enter equipmentId: ");
                int equipmentId = scanner.nextInt();

                deleteCompositeRecord(tableName, orderId, equipmentId);
            } else {
                System.out.print("Enter " + primaryKey + " to delete: ");
                int primaryKeyValue = scanner.nextInt();

                deleteRecord(tableName, primaryKey, primaryKeyValue);
            }
        } // 🔹 Scanner automatically closed here due to try-with-resources
    }

    /**
     * Deletes a record from a table using a single primary key.
     */
    private static void deleteRecord(String tableName, String primaryKey, int primaryKeyValue) {
        // SQL DELETE query
        String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {

            pstat.setInt(1, primaryKeyValue);

            int rowsAffected = pstat.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully from " + tableName + ".");
            } else {
                System.out.println("No matching record found in " + tableName + ".");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Cannot delete record: Foreign key constraint violation.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a record from OrderAndEquipment, which has a composite primary key.
     */
    private static void deleteCompositeRecord(String tableName, int orderId, int equipmentId) {
        // SQL DELETE query for composite primary key
        String query = "DELETE FROM " + tableName + " WHERE orderId = ? AND equipmentId = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
             PreparedStatement pstat = connection.prepareStatement(query)) {

            pstat.setInt(1, orderId);
            pstat.setInt(2, equipmentId);

            int rowsAffected = pstat.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Record deleted successfully from " + tableName + ".");
            } else {
                System.out.println("No matching record found in " + tableName + ".");
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Cannot delete record: Foreign key constraint violation.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
