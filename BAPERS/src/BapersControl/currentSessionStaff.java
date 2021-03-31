package BapersControl;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class currentSessionStaff {


    private Main m;
    private DBConnection conn;
    private Connection connDB;
    private static String id;
    private static String fullname;
    private static String username;
    private static String password;
    private static String address;
    private static String role;
    private static String phoneNumber;

    public currentSessionStaff(String id){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
        this.id = id;
        this.fullname = "";
        this.username = "";
        this.password = "";
        this.address = "";
        this.role = "";
        this.phoneNumber = "";
        getStaffDetails(id);


    }

    public void getStaffDetails(String username){
//        String userName = username;
        String query = "SELECT * FROM STAFF_ACCOUNT WHERE STAFF_ID = '" + id + "';";

        System.out.println(id + "first check");
        try {
            Statement statement = connDB.createStatement();
            ResultSet result = statement.executeQuery(query);

            if (result.next()){
                id = result.getString(1);
                setId(id);
                fullname = result.getString(2);
                setFullname(fullname);
                username = result.getString(3);
                setUsername(username);
                password = result.getString(4);
                setPassword(password);
                address = result.getString(5);
                setAddress(address);
                role = result.getString(6);
                setRole(role);
                phoneNumber = result.getString(7);
                setPhoneNumber(phoneNumber);
                System.out.println(id + fullname + username + password  +
                        address + role +phoneNumber);
            }else{
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void setId(String id) { currentSessionStaff.id = id; }

    public static String getId() { return id; }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) { currentSessionStaff.username = username; }

    public static void setFullname(String fullname) {
        currentSessionStaff.fullname = fullname;
    }

    public static String getFullname() {
        return fullname;
    }

    public static void setPassword(String password) {
        currentSessionStaff.password = password;
    }

    public static String getPassword() {
        return password;
    }

    public static void setAddress(String address) {
        currentSessionStaff.address = address;
    }

    public static String getAddress() {
        return address;
    }

    public static void setPhoneNumber(String phoneNumber) {
        currentSessionStaff.phoneNumber = phoneNumber;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setRole(String role) {
        currentSessionStaff.role = role;
    }

    public static String getRole() {
        return role;
    }
}



