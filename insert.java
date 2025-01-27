import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insert
{
    public static void main(String[] args)
        {
            //database URL
            final String DATABASE_URL = "";
            Connection connection = null;
            PreparedStatement pstat = null;
            String firstname = "firstname";
            String lastname = "lastname";
            int i =0;

            try {
                //establish connection to database
                connection = DriverManager.getconnection(DATABASE_URL, "root", "password");

                //create Prepared Statement for inserting into table
                pstat = connection.prepareStatement("INSERT INTO Authors (FirstName, LastName) VALUES (?,?)");
                pstat.setString(1, firstname);
                pstat.setString(2, lastname);

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