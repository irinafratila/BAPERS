//package Database;
//
//
//
//
//import java.sql.DriverManager;
//import java.sql.*;
//
//// this class is to connect to the databse
//public class DBConnection {
//    public Connection databaselink;
//
//    public Connection getConnection(){
//        try {
//            databaselink = DriverManager.getConnection("jdbc:sqlite:/Users/tobiadewunmi/Desktop/BAPERS-database/BAPERS.db");
//
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//
//        return databaselink;
//    }
//
//}