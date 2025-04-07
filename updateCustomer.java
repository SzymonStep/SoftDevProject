import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateCustomer{

    public static void main (String[] args){

        //database URL
        final String DATABASE_URL ="jdbc:mysql://localhost:3306/SalesSystem?allowPublicKeyRetrieval=true&useSSL=false";

        Connection connection = null;
        PreparedStatement pstat = null;
        String firstname = "firstName";
        String lastname = "lastName";
        String address = "address";
        String email = "email";
        String phoneNumber = "0871234567";
        int customerId = 1;
        int i = 0;

        try{

            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            //create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update Authors SET firstName=?, lastName=?, address=?, email=?, phoneNumber=? WHERE customerId = ?");
            pstat.setString(1, firstname);
            pstat.setString(2, lastname);
            pstat.setString(3, address);
            pstat.setString(4, email);
            pstat.setString(5, phoneNumber);
            pstat.setInt(6, customerId);

            //Update data in the table
            i = pstat.executeUpdate();
            System.out.println( i + " record successfully updated in the dtable");

        }
        catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally{
            try{
                pstat.close();
                connection.close();
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }   //end main
}       //end class
