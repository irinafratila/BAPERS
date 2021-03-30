package Reports;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Report {

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public Report(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }


    public void generateCustomerReport() throws FileNotFoundException {

        // job id as input
       // int id = Integer.parseInt(BapersControl.tempCustomerSession.getId());
        DbDriver.generateInvoice(2);
        System.out.println("Generated report 1");
    }

    public void generateStaffReport() throws SQLException, FileNotFoundException {

        // staff id as input 
        DbDriver.generateIndividualStaffReport(2);
        System.out.println("Generated report 2");
    }

    public void generateBiplReport() throws FileNotFoundException {

        DbDriver.generateSummaryReport("2021-03-27","2021-03-29");
        System.out.println("Generated report 3");
    }

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }
}