package JobTasks;


import BapersControl.tempJobSession;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SearchJob {

    private BapersControl.Main m;

    @FXML
    private TextField id;

    @FXML
    private Label searchJobMessageLabel;

    public String idData = "";

    public SearchJob(){
        this.m = new BapersControl.Main();
    }

    public void cancel(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/jobDashboard.fxml");
    }

    public void searchJob(ActionEvent event) throws IOException {
        int ID = Integer.parseInt(id.getText());
        this.idData = String.valueOf(ID);

        try {
            Boolean result = DbDriver.searchJobsBool(ID);

            if (result== true){
                searchJobMessageLabel.setText("Job found");
                // BapersControl.Main m= new BapersControl.Main();
                new tempJobSession(ID);
                //Report.generateStaffReport(ID);
                m.changeScene("/JobTasks/updateJob.fxml");
            }else{
                searchJobMessageLabel.setText("Job ID not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
