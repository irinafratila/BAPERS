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

import Database.DbDriver;

public class User {

    public User(){
    }

    @FXML
    private Button createAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label createUserMessageLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField fullName;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password1;

    @FXML
    private PasswordField password2;

    @FXML
    private RadioButton officeManager, shiftManager, technician, receptionist;

    @FXML
    private ImageView brandingImageView;

    public String selection = null;


//    public static void changeScene(FXML path) throws Exception{
//        try {
//
//            Parent root = FXMLLoader.load(user.class.getResource("/Admin/createUser.fxml"));
//            Stage register = new Stage();
//            register.initStyle(StageStyle.UNDECORATED);
//            register.setScene(new Scene(root, 1000, 769));
//            register.show();
////           BapersControl.Main m= new BapersControl.Main();
////           m.changeScene("/Admin/createUser.fxml");
//        }catch (Exception e){
//            e.printStackTrace();
//            e.getCause();
//        }
//    }

    public void changeSceneHome() throws Exception{
        try {
            BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/BapersControl/dashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneCreateUser() throws Exception{
        try {
            BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/createUser.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneDeleteUser() throws Exception{
        try {
            BapersControl.Main m= new BapersControl.Main();
            m.changeScene("/Admin/deleteUser.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    public void cancelRegister(ActionEvent event) throws IOException{
        //Stage stage = (Stage) closeButton.getScene().getWindow();
        //stage.close();
        // Platform.exit();
        BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Admin/user.fxml");

    }

    public void registerButtonAction(ActionEvent event) throws IOException {
        if (password1.getText().equals(password2.getText()) && !password1.getText().isEmpty()) {
            createUserMessageLabel.setText("looks good");
            // confirmPasswordLabel.setText("Passwords match");
            registerUser();
        }else {
            createUserMessageLabel.setText("passwords do not match");
            // confirmPasswordLabel.setText("Passwords do not match");
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
//test();
    }

//    public void test() {
//        System.out.println("testinggggg"+this.selection);
//    }


    //1 john doe doeeee 123 null
    public void registerUser() throws IOException{
        Main m= new Main();
        DBConnection conn = new DBConnection();
        Connection connDB = conn.getConnection();

        String fullname = fullName.getText();
        String userName = username.getText();
        String password = password1.getText();
        String role = this.selection;
        String insertField= "INSERT INTO " + DbDriver.TABLE_STAFF_ACCOUNT + " ( " +DbDriver.COLUMN_STAFF_NAME + ',' + DbDriver.COLUMN_USER_NAME + ',' + DbDriver.COLUMN_STAFF_PASSWORD +',' + DbDriver.COLUMN_STAFF_ROLE + ") VALUES(";
        String insertValue= fullname + "','" + userName + "','" + password + "','" + role + ")";
        String inserttoDB = insertField + insertValue;

        try {
            Statement statement = connDB.createStatement();
            statement.executeUpdate(inserttoDB);
            createUserMessageLabel.setText("User created succesfully");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }


    public void deleteUser() throws IOException{
//        Main m= new Main();
//        DatabaseConncection conn = new DatabaseConncection();
//        Connection connDB = conn.getConnection();
//
//        String fullname = fullName.getText();
//        String userName = username.getText();
//        String password = password1.getText();
//        String role = this.selection;
//        String insertField= "INSERT INTO STAFF_ACCOUNT(StaffName,UserName,Password,StaffRole) VALUES(' ";
//        String insertValue= fullname + "','" + userName + "','" + password + "','" + role + "')";
//        String inserttoDB = insertField + insertValue;
//
//        try {
//            Statement statement = connDB.createStatement();
//            statement.executeUpdate(inserttoDB);
//            createUserMessageLabel.setText("User created succesfully");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        System.out.println("deleted user");

    }






}