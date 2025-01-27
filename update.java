import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class update{

    public static void main (String[] args){

        //database URL
        final String DATABASE_URL ="";

        String firstname = "firtsname2";
        String lastname ="lastname2";
        Connection connection = null;
        PreparedStatement pstat = null;
        int i = 0;

        try{

            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            //create Prepared Statement for updating data in the table
            pstat = connection.prepareStatement("Update Authors SET LastName=? Where FirstName=?" );
            pstat.setString(1, lastname);
            pstat.setString(2, firstname);

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
