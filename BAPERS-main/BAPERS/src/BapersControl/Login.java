package BapersControl;

import Database.DBConnection;
import Database.DbDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


// This class handles the loging in of any user that has their account in the database already
// create user is not in this class or package as its an action that can only be taken after user (admin) has logged in
// this class goes hand in hand with the Login.fxml file which is used to represent the gui

public class Login  {
    public Login(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }
    private Main m;
    private DBConnection conn;
    private Connection connDB;


    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;



    public void cancelLogin(ActionEvent event) throws IOException{
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void userLogin(ActionEvent event) throws IOException{
        if (username.getText().isEmpty() == false && password.getText().isEmpty() == false) {
            validateLogin();
        }else {
            loginMessageLabel.setText("Enter details");
        }
    }

    private void validateLogin() throws IOException{
        String Username = username.getText();
        String Password = password.getText();

        try {
            Boolean result;
            result = DbDriver.verifyLogin(Username,Password);
            if (result == true){
                loginMessageLabel.setText("successful login");
                new currentLoginSession(Username);
                m.changeScene("dashboard.fxml");
            }else {
                loginMessageLabel.setText("wrong credentials!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createAccount(){
        try {
            BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/createUser.fxml");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
