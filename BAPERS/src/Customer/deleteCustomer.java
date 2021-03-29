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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class deleteCustomer {
    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public deleteCustomer(){
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
    private TextField id;

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Customer/customer.fxml");
    }


    public void deleteCustomerButtonAction(ActionEvent event) throws IOException {
        String ID = id.getText();
       // int iD =  Integer.parseInt(ID);
        try {
            Boolean result;
            result = DbDriver.searchCustomerAccount(ID);


            if (result == true){
                DbDriver.deleteCustomerAccount(ID);
                deleteUserMessageLabel.setText("Customer Deleted");
                id.setText("");
            }else{
                deleteUserMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //1 city university tobs tobs northampton square 12345
//2 fullname contactname contactname addr 12
//3 fullname contactname email@gmail.com addr 12345
//
//    public void deleteUserButtonAction(ActionEvent event) throws IOException {
//        String FullName = fullName.getText();
//        String phoneNumber = number.getText();
//        String query = "SELECT * FROM CUSTOMER_ACCOUNT WHERE CustomerName = '" + FullName + "' AND CustomerPhoneNumber = '" + phoneNumber +"';";
//        //  insertValue= FullName + "','" + ContactName + "','" + Address + "','" + phoneNumber + "')";
//        try {
//            Statement statement = connDB.createStatement();
//            ResultSet result = statement.executeQuery(query);
//
//            if (result.next()){
//                deleteUserMessageLabel.setText("User Deleted");
//                confirmDeletion();
//
//            }else{
//                deleteUserMessageLabel.setText("User not found in db ");
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//
//    private void confirmDeletion() throws IOException {
//        deleteUser();
//    }
//
//    public void deleteUser() throws IOException{
//
//        String FullName = fullName.getText();
//        String phoneNumber = number.getText();
//        String query = "DELETE FROM CUSTOMER_ACCOUNT WHERE CustomerName = '" + FullName + "' AND CustomerPhoneNumber = '" + phoneNumber +"';";
//        String inserttoDB = query;
//
//        try {
//            Statement statement = connDB.createStatement();
//            statement.executeUpdate(inserttoDB);
//            deleteUserMessageLabel.setText("Customer deleted succesfully ");
//            fullName.setText("");
//
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        System.out.println("deleted customer");
//
//    }





}
//}
