package Database;




import java.sql.DriverManager;
import java.sql.*;
/**
 * @author Muhammad Masum Miah
 */

// this class is to connect to the databse
public class DBConnection {
    private static Connection conn;
    public static final String DB_NAME = "bapers";
    private static final String url = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static final String user = "root";
    private static final String password = "helloworld12";

    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url,user, password);


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            System.out.println("database not available");
        }

        return conn;
    }

}