
package Admin;

import BapersControl.Main;
import BapersControl.currentSessionStaff;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class updateUser implements Initializable{

    private Main m;
    private DBConnection conn;
    private Connection connDB;
    @FXML
    private Button createAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label updateUserMessageLabel,updateUserRoleLabel;

    @FXML
    private TextField fullName,username,phoneNumber,address;

    @FXML
    private PasswordField password1, password2;

    @FXML
    private RadioButton officeManager, shiftManager, technician, receptionist;

    public String selection = null;
    public String usernameData = "";


    public updateUser(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }
    public void cancelRegister(ActionEvent event) throws IOException {
        //  BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Admin/user.fxml");

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fullName.setText(BapersControl.currentSessionStaff.getFullname());
        username.setText(BapersControl.currentSessionStaff.getUsername());
        password1.setText(BapersControl.currentSessionStaff.getPassword());
        password2.setText(BapersControl.currentSessionStaff.getPassword());
        address.setText(BapersControl.currentSessionStaff.getAddress());
        phoneNumber.setText(BapersControl.currentSessionStaff.getPhoneNumber());
        updateUserRoleLabel.setText("Current role = " +BapersControl.currentSessionStaff.getRole());
    }

    public void radioButtonHandler(ActionEvent event) {


        if (officeManager.isSelected()){
            this.selection = "Office Manager";
            shiftManager.setSelected(false);
            technician.setSelected(false);
            receptionist.setSelected(false);
        } else if (shiftManager.isSelected()){
            this.selection = "Shift Manager";
            officeManager.setSelected(false);
            technician.setSelected(false);
            receptionist.setSelected(false);
        }else if (technician.isSelected()){
            this.selection = "Technician";
            officeManager.setSelected(false);
            shiftManager.setSelected(false);
            receptionist.setSelected(false);
        }else if(receptionist.isSelected()){
            this.selection = "Receptionist";
            officeManager.setSelected(false);
            shiftManager.setSelected(false);
            technician.setSelected(false);
        }

        System.out.println(this.selection);
    }

    public void updateUser() throws IOException{
//        fullName.setText(BapersControl.currentSessionStaff.getUsername());

        String id = BapersControl.currentSessionStaff.getId();
        String fullname = fullName.getText();
        String userName = username.getText();
        String password = password1.getText();
        String Address = address.getText();
        String number = phoneNumber.getText();
        String role = this.selection;

//        String insertField= "UPDATE STAFF_ACCOUNT SET FullName = '" + userName + "', WHERE UserName = '" +userName +"';";
//        String inserttoDB = insertField;


        // "SELECT * FROM CUSTOMER_ACCOUNT WHERE CustomerName = '" + FullName + "' AND CustomerPhoneNumber = '" + phoneNumber +"';";
        try {
            System.out.println(id + fullname + userName + password +Address +role+number);
            DbDriver.updateStaffAccount(id,fullname,userName,password, Address,role,number);
            updateUserMessageLabel.setText("User updated successfully");
            fullName.setText("");
            username.setText("");
            password1.setText("");
            password2.setText("");
            address.setText("");
            phoneNumber.setText("");
            shiftManager.setSelected(false);
            officeManager.setSelected(false);
            receptionist.setSelected(false);
            technician.setSelected(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("updated user");

    }

}

//        select * from STAFF_ACCOUNT
//        9 fullname username 123 null Shift Manager
//        10 f2 user2 123 null Technician