import java.sql.*; // Importing SQL-related classes for database interaction
import java.util.Scanner; // Importing Scanner class to read user input
 
public class DisplayTable {
    public static void main(String[] args) {
        // Database connection URL for the SalesSystem database
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
        
        // Declare variables for database connection, prepared statement, and result set
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        
        // Create Scanner object to take user input for table name
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the name of the table to display
        System.out.print("Enter table name to display: ");
        String tableName = scanner.nextLine(); // Read the table name input from the user

        try {
            // Establish connection to the MySQL database using the provided URL and credentials
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            // Prepare the SQL query to select all data from the specified table
            String query = "SELECT * FROM " + tableName;
            pstat = connection.prepareStatement(query);
            
            // Execute the query and store the result in resultSet
            resultSet = pstat.executeQuery();

            // Get metadata about the result set (such as column names)
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount(); // Get the number of columns in the table

            // Display table name header
            System.out.println("\nDisplaying Table: " + tableName);
            System.out.println("=".repeat(120)); // Print a separator line

            // Print column headers based on the column count
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i)); // Print each column name with padding
            }
            System.out.println();
            System.out.println("=".repeat(120)); // Print another separator line

            // Loop through the result set and display each row's data
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    // Print each column value with padding
                    System.out.printf("%-20s", resultSet.getObject(i));
                }
                System.out.println(); // Move to the next line after printing each row
            }
        } 
        catch (SQLException sqlException) {
            // Catch and display any SQL exceptions that occur
            System.err.println("Error retrieving data: " + sqlException.getMessage());
        } 
        finally {
            try {
                // Close the resultSet, prepared statement, and connection to release resources
                if (resultSet != null) resultSet.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } 
            catch (SQLException exception) {
                // Catch and display any exceptions that occur while closing resources
                System.err.println("Error closing resources: " + exception.getMessage());
            }
            // Close the scanner resource
            scanner.close();
        }
    }
}