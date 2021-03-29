package JobTasks;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;

public class DeleteJob{

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private Label deleteJobMessageLabel;

    @FXML
    private TextField id;


    public String selection = null;
    public String idData = null;


    public DeleteJob(){
        this.m = new Main();


    }


    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/jobDashboard.fxml");

    }


    public void deleteJobButtonAction(ActionEvent event) throws IOException {
        int ID = Integer.parseInt(id.getText());
        try {

            Boolean result;
            result = DbDriver.searchJob(ID);


            if (result == true ){
                DbDriver.removeTasksByJob(ID);
                DbDriver.removeJob(ID);
                this.deleteJobMessageLabel.setText("Job Deleted");
                id.setText("");
            }else{
                deleteJobMessageLabel.setText("Job not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}