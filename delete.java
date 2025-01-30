import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class delete
{
    public static void main(String[] args)
    {
    //database URL
    final String DATABASE_URL = "jdbc:mysql://localhost:3306/SalesSystem?useSSL=false";

    Connection connection = null;
    PreparedStatement pstat = null;
    int i =0;
    int authorID = 6;

    try
        {
            //establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");

            pstat = connection.prepareStatement("Delete From Authors Where AuthorID=?");
            pstat.setInt(1, authorID);

            // delete data from the table
            i = pstat.executeUpdate();
            System.out.print(i + " record successfully removed from the table.");
        }
    catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    finally
        {
            try
                {
                    pstat.close();
                    connection.close();
                }
            catch(Exception exception){
                exception.printStackTrace();
            }
        }
    }   //end main
}       //end class
