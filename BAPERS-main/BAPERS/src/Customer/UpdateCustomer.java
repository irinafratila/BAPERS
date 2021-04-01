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

public class UpdateCustomer implements Initializable {


    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public UpdateCustomer(){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerName.setText(BapersControl.tempCustomerSession.getCustomerName());
        title.setText(BapersControl.tempCustomerSession.getTitle());
        firstName.setText(BapersControl.tempCustomerSession.getFirstName());
        lastName.setText(BapersControl.tempCustomerSession.getLastName());
        address.setText(BapersControl.tempCustomerSession.getAddress());
        city.setText(BapersControl.tempCustomerSession.getCity());
        postCode.setText(BapersControl.tempCustomerSession.getPostCode());
        email.setText(BapersControl.tempCustomerSession.getEmail());
        number.setText(BapersControl.tempCustomerSession.getNumber());
//        customerType.setText(BapersControl.tempCustomerSession.getCustomerType());
//        discountID.setText(BapersControl.tempCustomerSession.getDiscountID());
    }




    public void updateCustomer() throws IOException{

        String id = BapersControl.tempCustomerSession.getId();
        String CustomerName = customerName.getText();
        String Title = title.getText();
        String FirstName = firstName.getText();
        String LastName = lastName.getText();
        String Address = address.getText();
        String City = city.getText();
        String PostCode = postCode.getText();
        String Email = email.getText();
        String PhoneNumber = number.getText();
        String CustomerType = "normal";
        int DiscountID = 0;

        // System.out.println(id + fullname + userName + password +Address +role+number);


        try {
            DbDriver.updateCustomerAccount(id,CustomerName, Title, FirstName, LastName, Address, City, PostCode, Email, PhoneNumber);
            updateCustomerMessageLabel.setText("User created deleted successfully");
            customerName.setText("");
            title.setText("");
            firstName.setText("");
            lastName.setText("");
            address.setText("");
            city.setText("");
            postCode.setText("");
            email.setText("");
            number.setText("");

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("updated user");

    }
}

