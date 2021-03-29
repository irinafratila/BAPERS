package BapersControl;

import Database.DBConnection;

import java.io.IOException;
import java.sql.Connection;

public class BackupDB {

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    public BackupDB(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }


    public void backup(){
        System.out.println("Backed up");
    }

    public void restoreBackup(){
        System.out.println("Backup restored");
    }

    public void cancel() throws IOException {
       m.changeScene("dashboard.fxml");
    }
}
