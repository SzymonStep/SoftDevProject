import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionExample {
    public static void main(String[] args) {
        // JDBC URL, username, and password
        String url = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "password";  // replace with your actual password

        // Declare the connection object
        Connection connection = null;

        try {
            // Step 1: Register the MySQL driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Open a connection using DriverManager
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established successfully.");

        } catch (SQLException e) {
            // Handle database connection errors
            System.out.println("Connection failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle driver class not found
            System.out.println("MySQL Driver not found: " + e.getMessage());
        } finally {
            try {
                // Close the connection if it's open
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
