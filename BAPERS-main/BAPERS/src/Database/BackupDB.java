package Database;


import BapersControl.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class BackupDB {

    private Main m;


    @FXML
    private Label dbBackupMessageLabel;

    @FXML
    private TextField date;


    public BackupDB(){
        this.m = new Main();

    }

    public void backup(){
        try {
            Boolean result = DBBackupRestore.dbBackup("root", "TeaM27TeaM", "bapers");
            dbBackupMessageLabel.setText("");
            if (result == true){
                dbBackupMessageLabel.setText("Back up Successful");
            }else {
                dbBackupMessageLabel.setText("Back up Failed");
            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    public void changeSceneSearchBackup() throws IOException {
        m.changeScene("/Database/searchBackup.fxml");
    }

    public void searchBackup(){
        try {
            String From = date.getText();
//            System.out.println(From);
            new BapersControl.ToFromTransfer(From);
//            System.out.println( "from where "+From);
            restoreBackup();
            date.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void restoreBackup(){

        try {
            String date = BapersControl.ToFromTransfer.getFrom();
            Boolean result = DBBackupRestore.restoreDB("bapers", "root", "TeaM27TeaM",date );
//            "2021-03-31"
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
