import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertCustomer
{
    public static void main(String[] args)
        {
            //database URL
            final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?useSSL=false";
            Connection connection = null;
            PreparedStatement pstat = null;
            String firstname = "firstName1";
            String lastname = "lastName1";
            String address = "address";
            String email = "email";
            String phoneNumber = "0871234567";

            int i =0;

            try {
                //establish connection to database
                connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

                //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Customer (firstName, lastName, address, email, phoneNumber ) VALUES (?,?,?,?,?)");
                pstat.setString(1, firstname);
                pstat.setString(2, lastname);
                pstat.setString(3, address);
                pstat.setString(4, email);
                pstat.setString(5, phoneNumber);

                //insert data into table
                i = pstat.executeUpdate();
                System.out.println(i + " record successfully added to the table.");
            }

            catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
            finally {
                try{
                    pstat.close();
                    connection.close();
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        } //end main
}         //end class