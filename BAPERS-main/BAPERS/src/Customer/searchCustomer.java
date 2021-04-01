package Customer;

import BapersControl.Main;
import BapersControl.tempCustomerSession;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class searchCustomer {


    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private TextField id;

    @FXML
    private Label searchCustomerMessageLabel;

//    public String selection = null;
    public String idData = "";


    public searchCustomer(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }

    public void cancelRegister(ActionEvent event) throws IOException {
        //  BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Customer/customer.fxml");

    }

    public void searchCustomerUpdate(ActionEvent event) throws IOException {
        String ID = id.getText();
        this.idData = ID;

        try {
            Boolean result;
            result = DbDriver.searchCustomerAccount(ID);
           // System.out.println("serchuser" + id + result);
            if (result== true){
                searchCustomerMessageLabel.setText("Customer found");
                BapersControl.Main m= new BapersControl.Main();
                new tempCustomerSession(this.idData);
                m.changeScene("/Customer/updateCustomer.fxml");
            }else{
                searchCustomerMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void searchCustomerUpgrade(ActionEvent event) throws IOException {
        String ID = id.getText();
        this.idData = ID;

        try {

            Boolean result;
            result = DbDriver.searchCustomerAccount(ID);
            // System.out.println("serchuser" + id + result);
            if (result== true){
                searchCustomerMessageLabel.setText("Customer found");
                BapersControl.Main m= new BapersControl.Main();
                new tempCustomerSession(this.idData);
                m.changeScene("/Customer/upgradeCustomer.fxml");
            }else{
                searchCustomerMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}