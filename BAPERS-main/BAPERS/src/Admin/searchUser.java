package Admin;

import BapersControl.Main;
import BapersControl.currentSessionStaff;
import Database.DBConnection;
import Database.DbDriver;
import Reports.Report;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class searchUser {


    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private TextField id;

    @FXML
    private Label searchUserMessageLabel;

    public String selection = null;
    public String idData = "";


    public searchUser(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }

    public void cancelRegister(ActionEvent event) throws IOException {
        //  BapersControl.Main m= new BapersControl.Main();
        m.changeScene("/Admin/user.fxml");

    }

    public void searchUser(ActionEvent event) throws IOException {
        String ID = id.getText();
        this.idData = ID;

        try {
            Boolean result;
            result = DbDriver.searchStaffAccount(ID);

            if (result== true){
                searchUserMessageLabel.setText("User found");
                BapersControl.Main m= new BapersControl.Main();
                new currentSessionStaff(this.idData);
                m.changeScene("/Admin/updateUser.fxml");
            }else{
                searchUserMessageLabel.setText("User not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}