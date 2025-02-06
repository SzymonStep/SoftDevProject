import java.sql.*;
import java.util.*;

public class GenericDatabaseOperations {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?useSSL=false";
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
        staffData.put("role", "Manager");
        staffData.put("email", "sarah.connor@example.com");
        staffData.put("phoneNumber", "0851234567");

        insertRecord("Staff", staffData);
    }
}
