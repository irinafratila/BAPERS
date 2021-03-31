package JobTasks;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateTask implements Initializable {

    private BapersControl.Main m;
    private DBConnection conn;
    private Connection connDB;
    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label taskLabel;

    @FXML
    private TextField desc,did,price,duration;

    @FXML
    private ChoiceBox tasks;

    private List<Task> tasklist;

    private static List<Integer> taskIds;

    public CreateTask(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
        this.taskIds = new LinkedList<>();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/taskDashboard.fxml");
    }

    public void createTask() throws SQLException {
        String TaskDescription = desc.getText();
        int DepartmentID = Integer.parseInt(did.getText());
        float Price = Float.parseFloat(price.getText());
        int Duration = Integer.parseInt(duration.getText());

        DbDriver.insertTasks(TaskDescription,DepartmentID,Price,Duration);
        desc.setText("");
        did.setText("");
        price.setText("");
        duration.setText("");
        taskLabel.setText("Task created succesfully");

    }
}
