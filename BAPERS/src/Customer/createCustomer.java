package Customer;


import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class createCustomer {
    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public createCustomer(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }

    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label createCustomerMessageLabel;

    @FXML
    private TextField customerName,title,firstName,lastName,address,city,postCode,email,number;

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Customer/customer.fxml");
    }

    public void registerButtonAction(ActionEvent event) throws IOException {
        if ( !customerName.getText().isEmpty() && !title.getText().isEmpty() &&  !firstName.getText().isEmpty() &&  !lastName.getText().isEmpty()
                &&  !address.getText().isEmpty() &&  !city.getText().isEmpty()&&  !postCode.getText().isEmpty()&&  !email.getText().isEmpty()&&
                !number.getText().isEmpty()  )  {
            createCustomerMessageLabel.setText("");
            createCustomerMessageLabel.setText("looks good");
            registerUser();
        }else {
            createCustomerMessageLabel.setText("");
            createCustomerMessageLabel.setText("fill all text fields");
        }
    }
    public void registerUser() throws IOException{

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

//        String insertField= "INSERT INTO CUSTOMER_ACCOUNT(CustomerName,ContactName,EmailContact,CustomerAddress,CustomerPhoneNumber) VALUES('";
//        String insertValue= FullName + "','" + ContactName + "','" +Email+ "','"+ Address + "','" + phoneNumber + "')";
//        String inserttoDB = insertField + insertValue;

        try {
            DbDriver.insertCustomer(CustomerName, Title, FirstName, LastName, Address, City, PostCode, Email, PhoneNumber, CustomerType);
          //  System.out.println(CustomerName + Title+ FirstName+ LastName+ Address+ City+ PostCode+ Email+ PhoneNumber);
            createCustomerMessageLabel.setText("Customer created succesfully");
            customerName.setText("");
            title.setText("");
            firstName.setText("");
            lastName.setText("");
            address.setText("");
            city.setText("");
            postCode.setText("");
            email.setText("");
            number.setText("");

        }catch ( Exception e){
            e.printStackTrace();

        }

    }

}
