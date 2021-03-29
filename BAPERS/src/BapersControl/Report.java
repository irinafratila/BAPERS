package BapersControl;

import Database.DBConnection;

import java.io.IOException;
import java.sql.Connection;

public class Report {

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public Report(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }


    public void generateCustomerReport(){
        System.out.println("Generated report 1");
    }

    public void generateStaffReport(){
        System.out.println("Generated report 2");
    }

    public void generateBiplReport(){
        System.out.println("Generated report 3");
    }

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }
}