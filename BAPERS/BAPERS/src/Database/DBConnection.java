package Database;


import java.sql.DriverManager;
import java.sql.*;

public class DBConnection {

    private static Connection conn;
    public static final String DB_NAME = "BAPERS";
    private static final String url = "jdbc:mysql://localhost:3306/"+DB_NAME;
    private static final String user = "root";
    private static final String password = "TeaM27TeaM";
   // String url = "jdbc:mysql://localhost:3306/BAPERS";
  //  String user = "root";
  //  String password = "TeaM27TeaM";
   // public Connection databaselink;

    public Connection getConnection(){
        try {
            conn = DriverManager.getConnection(url,user, password);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return  conn;
    }
}
