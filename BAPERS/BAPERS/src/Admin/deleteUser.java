package Admin;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;

public class deleteUser{

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private Label deleteUserMessageLabel;

    @FXML
    private TextField id;


    public String selection = null;
    public String idData = null;


    public deleteUser(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();

    }


    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Admin/user.fxml");

    }


    public void deleteUserButtonAction(ActionEvent event) throws IOException {
        String ID = id.getText();
        try {
            Boolean result;
            result = DbDriver.searchStaffAccount(ID);

            if (result == true){
                DbDriver.deleteStaffAccount(ID);
                deleteUserMessageLabel.setText("User Deleted");
            }else{
                deleteUserMessageLabel.setText("User not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}