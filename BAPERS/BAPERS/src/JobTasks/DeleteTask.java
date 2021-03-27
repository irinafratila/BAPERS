package JobTasks;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.sql.Connection;

public class DeleteTask{

    private Main m;
    private DBConnection conn;
    private Connection connDB;

    @FXML
    private Label deleteJobMessageLabel;

    @FXML
    private TextField id;


    public String selection = null;
    public String idData = null;


    public DeleteTask(){
        this.m = new Main();


    }


    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/TaskDashboard.fxml");

    }


    public void deleteTaskButtonAction(ActionEvent event) throws IOException {
        int ID = Integer.parseInt(id.getText());
        try {

            Boolean result;
            result = DbDriver.deleteTask(ID);

            if (result == true ){
                DbDriver.removeTasks(ID);
                this.deleteJobMessageLabel.setText("Task Deleted");
                id.setText("");
            }else{
                deleteJobMessageLabel.setText("Task not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}