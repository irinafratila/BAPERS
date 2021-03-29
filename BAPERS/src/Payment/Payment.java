package Payment;

import BapersControl.Main;
import Database.DbDriver;
import JobTasks.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Payment {

    private BapersControl.Main m;

    @FXML
    private TextField id;

    public Payment(){
        this.m = new Main();
    }

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/BapersControl/dashboard.fxml");
    }

    public void changeSceneCardPayment() throws Exception{
        try {
            m.changeScene("/Payment/searchJobCard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneCashPayment() throws Exception{
        try {
            m.changeScene("/Payment/searchJobCash.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    public void searchJobCash() throws Exception{
        try {
            int JobID = Integer.parseInt(id.getText());
            Job searchedJob = DbDriver.searchJobs(JobID);
            new BapersControl.tempJobSession(searchedJob.getJobId());
            m.changeScene("/Payment/cashPayment.fxml");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void searchJobCard() throws Exception{
        try {
            int JobID = Integer.parseInt(id.getText());
            Job searchedJob = DbDriver.searchJobs(JobID);
            new BapersControl.tempJobSession(JobID);
            m.changeScene("/Payment/cardPayment.fxml");

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
