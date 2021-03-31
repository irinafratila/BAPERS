package Database;


import BapersControl.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class BackupDB {

    private Main m;


    @FXML
    private Label dbBackupMessageLabel;

    public BackupDB(){
        this.m = new Main();

    }


    public void backup(){
        try {

            Boolean result = DBBackupRestore.dbBackup("root", "TeaM27TeaM", "bapers");
            dbBackupMessageLabel.setText("");
            if (result == true){
                dbBackupMessageLabel.setText("Back up Succesful");
            }else {
                dbBackupMessageLabel.setText("Back up Failed");
            }

        }catch (Exception e){

            e.printStackTrace();
        }

//        System.out.println("Backed up");
    }

    public void restoreBackup(){

        try {
            Boolean result = DBBackupRestore.restoreDB("bapers", "root", "TeaM27TeaM", "2021-03-31");
            dbBackupMessageLabel.setText("");
            if (result == true){
                dbBackupMessageLabel.setText("Back up Restore Successful");
            }else {
                dbBackupMessageLabel.setText("Back up Restore Failed");
            }


        }catch (Exception e){
            e.printStackTrace();
        }


//        System.out.println("Backup restored");
    }

    public void cancel() throws IOException {
       m.changeScene("dashboard.fxml");
    }
}
