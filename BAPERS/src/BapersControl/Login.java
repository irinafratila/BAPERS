//package BapersControl;
////package BAPERS.Database;
//import java.sql.DriverManager;
//
//import Database.DBConnection;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.event.ActionEvent;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.stage.Stage;
//import javafx.*;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import java.util.ResourceBundle;
//import java.net.URL;
//
//// This class handles the loging in of any user that has their account in the database already
//// create user is not in this class or package as its an action that can only be taken after user (admin) has logged in
//// this class goes hand in hand with the Login.xml file which is used to represent the gui
//public class Login  {
//    public Login(){
//
//    }
//
//
//    @FXML
//    private Button loginButton;
//
//    @FXML
//    private Button cancelButton;
//
//    @FXML
//    private Label loginMessageLabel;
//
//    @FXML
//    private TextField username;
//
//    @FXML
//    private PasswordField password;
//
//    @FXML
//    private ImageView brandingImageView;
//
//    @FXML
//    private ImageView lockImageView;
//
////    @Override
////    public void initialize(URL url, ResourceBundle resourceBundle){
////        File brandingFile = new File("img/logo.png");
////        Image brandingImage = new Image(brandingFile.toURI().toString());
////        brandingImageView.setImage(brandingImage);
////
////        File lockFile = new File("img/lock.png");
////        Image lockImage = new Image(lockFile.toURI().toString());
////        lockImageView.setImage(lockImage);
////    }
//
//    public void cancelLogin(ActionEvent event) throws IOException{
//        Stage stage = (Stage) cancelButton.getScene().getWindow();
//        stage.close();
//    }
//
//    public void userLogin(ActionEvent event) throws IOException{
//        if (username.getText().isEmpty() == false && password.getText().isEmpty() == false) {
//            validateLogin();
//        }else {
//            loginMessageLabel.setText("Enter details");
//        }
//    }
//
//    private void validateLogin() throws IOException{
//        Main m= new Main();
//        DatabaseConncection conn = new DatabaseConncection();
//        Connection connDB = conn.getConnection();
//
//        // table doesnt have the right colums yet so im just testing with staffid 1 and name tobs.
//        // table doesnt have the right colums yet so im just testing with staffid 2 and name me.
//        String verifyLogin = "SELECT count(1) FROM STAFF_ACCOUNT WHERE StaffID = '" + username.getText() + "' AND StaffName ='" + password.getText() + "'";
//
//        try {
//            Statement statement = connDB.createStatement();
//            ResultSet result = statement.executeQuery(verifyLogin);
//
//            while (result.next()){
//                if (result.getInt(1) == 1){
//                    loginMessageLabel.setText("successful login");
//                    m.changeScene("afterLogin.fxml");
//                }else {
//                    loginMessageLabel.setText("wrong credentials!");
//                }
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
//    }
//
//}