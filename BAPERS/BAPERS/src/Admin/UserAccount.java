package Admin;

import BapersControl.Main;
import Database.DBConnection;

import java.sql.Connection;

public class UserAccount {

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public UserAccount(String staffId, String name, String userName, String password, String address, String role, String phoneNumber){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }
}
