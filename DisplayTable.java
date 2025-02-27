import java.sql.*;
import java.util.Scanner;

public class DisplayTable {
    public static void main(String[] args) {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";
        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter table name to display: ");
        String tableName = scanner.nextLine();

        try {
            // Establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            // Prepare SQL query to select all data from the given table
            String query = "SELECT * FROM " + tableName;
            pstat = connection.prepareStatement(query);
            resultSet = pstat.executeQuery();

            // Get column metadata
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.println("\nDisplaying Table: " + tableName);
            System.out.println("=".repeat(50));

            // Print column headers
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-20s", metaData.getColumnName(i));
            }
            System.out.println();
            System.out.println("=".repeat(50));

            // Print row data
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-20s", resultSet.getObject(i));
                }
                System.out.println();
            }
        } 
        catch (SQLException sqlException) {
            System.err.println("Error retrieving data: " + sqlException.getMessage());
        } 
        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (pstat != null) pstat.close();
                if (connection != null) connection.close();
            } 
            catch (SQLException exception) {
                System.err.println("Error closing resources: " + exception.getMessage());
            }
            scanner.close();
        }
    }
}
