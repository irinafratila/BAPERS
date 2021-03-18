package Database;




import java.sql.DriverManager;
import java.sql.*;

// this class is to connect to the databse
public class DBConnection {
    public Connection conn;
    String url = "jdbc:mysql://localhost:3306/BAPERS";
    String user = "root";
    String password = "helloworld12";

    public Connection getConnection(){
        try {
            Connection conn = DriverManager.getConnection(url,user, password);


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return conn;
    }

}