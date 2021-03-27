package BapersControl;

//import Customer.CustomerAccount;
import Database.DBConnection;

import Payment.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button button;

    @FXML
    private Button logout;

    @FXML
    private Label welcomeLabel,roleLabel;

    private Main m;

    public DashboardController(){
        this.m = new Main();
    }



    // change scene to login page
    public void userLogOut(ActionEvent event) throws IOException {
//        Main m = new Main();
        m.changeScene("Login.fxml");

    }

    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneUser() throws  IOException{
//        Main m = new Main();
        m.changeScene("/Admin/user.fxml");
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneCustomer() throws  IOException{
//        Main m = new Main();
        m.changeScene("/Customer/customer.fxml");

    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneJobs() throws  IOException{
//        Main m = new Main();
        m.changeScene("/JobTasks/jobTaskDashboard.fxml");
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeScenePayment() throws  IOException{
//        Main m = new Main();
        m.changeScene("/Payment/paymentType.fxml");
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneBackup() throws  IOException{
//        Main m = new Main();
        m.changeScene("BackupDB.fxml");
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneReport() throws  IOException{
//        Main m = new Main();
        m.changeScene("generateReport.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("");
        String username = currentLoginSession.getUsername();
        String role = currentLoginSession.getRole();
        welcomeLabel.setText("Welcome "+ username+",");
        roleLabel.setText("Role: "+ role+"");
    }
}

//ADDDDD EMAIL
//    ADDDDD EMAIL
//    ADDDDD EMAIL
//            look at list of colums to add to the db and inout their fake data
//backup db should be in database package