package Admin;

import BapersControl.Main;
import Database.DBConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class User{

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


    public String selection = null;
    public String usernameData = null;

    //    String staffId, String name, String userName, String password, String address, String role, String phoneNumber
    public User(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }



    public void changeSceneHome() throws Exception{
        try {
            // BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/BapersControl/dashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneCreateUser() throws Exception{
        try {
            //  BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/createUser.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneDeleteUser() throws Exception{
        try {
            //  BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/deleteUser.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneUpdateUser() throws Exception{
        try {
            //   BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/searchUser.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void cancelRegister(ActionEvent event) throws IOException{
        //  BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Admin/user.fxml");

    }

}

