package Customer;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChangeCustomerType{


    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public ChangeCustomerType(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }

    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label updateCustomerMessageLabel;

    @FXML
    private TextField id,customerName,title,firstName,lastName,address,city,postCode,email,number,customerType,discountID;

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Customer/customer.fxml");
    }
    public void changeSceneFixedDiscount() throws Exception{
        try {
            m.changeScene("/Discount/applyFixedDiscount.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneVarDiscount() throws Exception{
        try {
            m.changeScene("/Discount/applyVarDiscount.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneFlexiDiscount() throws Exception{
        try {
            m.changeScene("/Discount/applyFlexiDiscount.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void downgradeCustomer(ActionEvent actionEvent) throws Exception{
        try {
            m.changeScene("/Discount/downgradeCustomer.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}