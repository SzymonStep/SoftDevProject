import java.sql.*; // Importing SQL-related classes for database interaction
import java.util.Scanner; // Importing Scanner class to read user input

public class DisplayTable {

    // Method to display all rows from the specified table
    public static void displayTable(String tableName) {
        // Database connection URL for the SalesSystem database
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
        
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

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
            int numberOfColumns = metaData.getColumnCount();

            // Display table name header
            System.out.println("\nDisplayed Table: " + tableName);
            System.out.println("=".repeat(120));

            // Print column headers based on the column count
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i));
            }
            System.out.println();
            System.out.println("=".repeat(120));

            // Loop through the result set and display each row's data
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
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
        }
    }

    public static void main(String[] args) {
        // Create Scanner object to take user input for table name
        Scanner scanner = new Scanner(System.in);


        // Call the displayTable method to display the data
        displayTable("OrderAndEquipment");

        // Close the scanner resource
        scanner.close();
    }
}
