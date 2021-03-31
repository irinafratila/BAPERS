package Admin;

import BapersControl.Main;
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

public class createUser{

    private Main m;
    private DBConnection conn;
    private Connection connDB;
    @FXML
    private Button createAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label createUserMessageLabel,deleteUserMessageLabel,updateUserMessageLabel,confirmPasswordLabel,actionMessage;

    @FXML
    private TextField fullName,username,email,phoneNumber,address;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private RadioButton officeManager, shiftManager, technician, receptionist;

    @FXML
    private ImageView brandingImageView;

    public String selection = null;
    public String usernameData = null;


    public createUser(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }


    public void cancelRegister(ActionEvent event) throws IOException {
        //  BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Admin/user.fxml");

    }

    public void registerButtonAction(ActionEvent event) throws IOException {
        if (password1.getText().equals(password2.getText()) && !password1.getText().isEmpty()) {
            createUserMessageLabel.setText("looks good");
            registerUser();
        }else {
            createUserMessageLabel.setText("passwords do not match");
        }
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

    //1 john doe doeeee 123 null
    //2 testuser test 123null
    public void registerUser() throws IOException{


        String fullname = fullName.getText();
        String userName = username.getText();
//            String Email = email.getText();
        String password = password1.getText();
        String role = this.selection;

        try {
            Boolean result;
            result = DbDriver.insertStaffAccount(fullname,userName,password,"",role,"");
            if (result == true){
                createUserMessageLabel.setText("User created succesfully");
                fullName.setText("");
                username.setText("");
                password1.setText("");
                password2.setText("");
                shiftManager.setSelected(false);
                officeManager.setSelected(false);
                receptionist.setSelected(false);
                technician.setSelected(false);
            }else {
                createUserMessageLabel.setText("User creation failed ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}