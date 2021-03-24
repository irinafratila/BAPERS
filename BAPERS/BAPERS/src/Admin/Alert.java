package Admin;

import BapersControl.Main;
import Database.DBConnection;
import javafx.beans.binding.When;

import java.sql.Connection;

public class Alert {


    private Main m;
    private DBConnection conn;
    private Connection connDB;


    public Alert(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }


    public void saveAlert(String message){
        //save to db
    }

    public Object getAlert(){
        // get from db

       // while (result.next()) then blah blah
        return null;
    }
}
