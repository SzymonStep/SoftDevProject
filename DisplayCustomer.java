import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet ;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DisplayCustomer
{
    public static void main(String args[])
    {
        //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet resultSet = null;

        try
        {
            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            // create Prepared Statement for querying data in the table
            pstat = connection.prepareStatement("SELECT customerId, firstName, lastName, address, email, phoneNumber FROM Customer");

            // query data in the table
            resultSet = pstat.executeQuery();

            // process query results
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("Customer Table from Sales System Database");

            for (int i = 1; i <= numberOfColumns; i++)
                {
                    System.out.print(metaData.getColumnName(i)+ "\t");
                }
                System.out.println();

            while (resultSet.next() ){
                    for (int i = 1; i <= numberOfColumns; i++)
                    {
                        System.out.print(resultSet.getObject(i) + "\t" );        
                    }
                    System.out.println();
                }
        }
        catch(SQLException sqlException)
                {
                    sqlException.printStackTrace();
                }
        finally
            {
                try{
                        if (resultSet != null) resultSet.close();
                        if (pstat != null) pstat.close();
                        if (connection != null) connection.close();
                    }
                catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }
            }
    }
}