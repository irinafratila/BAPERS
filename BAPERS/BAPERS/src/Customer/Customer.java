package Customer;

import BapersControl.Main;
import Database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;

public class Customer {


    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public Customer(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }

    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label createCustomerMessageLabel,deleteUserMessageLabel,confirmPasswordLabel,actionMessage;

    @FXML
    private TextField fullName,email,contactName,address,number;


        public void changeSceneHome() throws Exception{
        try {
         //   BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/BapersControl/dashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void changeSceneCreateCustomer() throws Exception{
        try {
            m.changeScene("/Customer/createCustomer.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneDeleteCustomer() throws Exception{
        try {
            m.changeScene("/Customer/deleteCustomer.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneUpdateCustomer() throws Exception{
        try {
            m.changeScene("/Customer/searchCustomerUpdate.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneUpgradeCustomer() throws Exception{
        try {
            m.changeScene("/Customer/searchCustomerUpgrade.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelRegister(ActionEvent event) throws IOException{
        m.changeScene("/Customer/customer.fxml");
    }

}
