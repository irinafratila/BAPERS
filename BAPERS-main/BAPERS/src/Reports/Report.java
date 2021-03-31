package Reports;

import BapersControl.Main;
import BapersControl.currentSessionStaff;
import BapersControl.tempCustomerSession;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Report {

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private TextField id;

    @FXML
    private Label searchJobMessageLabel,searchStaffMessageLabel;

    public int idData = 0;

    public Report(){
        this.m = new Main();


    }


    public void changeSceneSearchUser() throws IOException {
        m.changeScene("/Reports/searchUser.fxml");
    }
    public void changeSceneSearchCustomer() throws IOException {
        m.changeScene("/Reports/searchCustomer.fxml");
    }
    public void changeSceneViewBiplReport() throws IOException {
        m.changeScene("/Reports/viewBiplReport.fxml");
    }



    public void searchCustomer() throws IOException {
        int ID = Integer.parseInt(id.getText());
        this.idData = ID;

        //String x = ;
        try {

            Boolean result;
            result = DbDriver.searchCustomerAccount(String.valueOf(ID));
            // System.out.println("serchuser" + id + result);
            if (result== true){
                searchJobMessageLabel.setText("Customer has not jobs");
//                BapersControl.Main m= new BapersControl.Main();
                new tempCustomerSession(String.valueOf(ID));
//                m.changeScene("/Customer/createJob.fxml");
//                generateCustomerReport();

                try {
                    DbDriver.generateCustomerReport(ID,"2021-03-25","2021-04-01");
                    m.changeScene("/Reports/viewCustomerReport.fxml");
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("The index you have entered is invalid");
                }


            }else{
                searchJobMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchUserReport(ActionEvent event) throws IOException {
        int ID = Integer.parseInt(id.getText());
        this.idData = ID;

        try {
            Boolean result;
            result = DbDriver.searchStaffAccount(String.valueOf(ID));

            if (result== true){
                searchStaffMessageLabel.setText("Staff found");
               // BapersControl.Main m= new BapersControl.Main();
                new currentSessionStaff(String.valueOf(ID));
                 Report.generateStaffReport(ID);
                m.changeScene("/Reports/viewStaffReport.fxml");
            }else{
                searchStaffMessageLabel.setText("Staff member not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void generateCustomerReport() throws FileNotFoundException {

        // job id as input
        int id = BapersControl.tempJobSession.getId();
        System.out.println("genearting cust report for id : " + id);
        try {
            DbDriver.generateCustomerReport(id,"2021-03-25","2021-04-01");

        }catch (Exception e){
            e.printStackTrace();
            /// print an out of range excpetion
        };
        System.out.println("Generated report 1");
    }

    public static void generateStaffReport(int id) throws SQLException, FileNotFoundException {

        // staff id as input
       // int ID = Integer.parseInt(BapersControl.currentSessionStaff.getId());
        DbDriver.generateIndividualStaffReport(id);
        System.out.println("Generated report 2");
    }

    public void generateBiplReport() throws FileNotFoundException {

        DbDriver.generateSummaryReport("2021-03-27","2021-03-31");
        System.out.println("Generated report 3");
        try {
            m.changeScene("/Reports/viewBiplReport.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }



}