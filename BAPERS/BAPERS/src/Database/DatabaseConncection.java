package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConncection {

    String url = "jdbc:mysql://localhost:3306/BAPERS";
    String user = "root";
    String password = "TeaM27TeaM";
    public Connection databaselink;

    public Connection getConnection(){
        try {
            databaselink = DriverManager.getConnection(url,user, password);

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return databaselink;
    }
}
