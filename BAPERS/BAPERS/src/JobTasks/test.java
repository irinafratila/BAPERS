package JobTasks;

import BapersControl.Main;
import Customer.CustomerAccount;
import Database.DBConnection;
import Database.DbDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class test implements Initializable {
    private Main m;
    private DBConnection conn;
    private Connection connDB;
    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label taskLabel;

    @FXML
    private TextField id,jobPriority,notes;

    @FXML
    private RadioButton task1,task2,task3,task4;

    @FXML
    private ChoiceBox tasks;

    private List<Task> tasklist;

    private static List<Integer> taskIds;

    private boolean listening;

    public test(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
//        List<Integer> taskIds = new LinkedList<>();
        this.listening = true;
      //  taskIds = new LinkedList<>();
        this.taskIds = new LinkedList<>();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tasklist = DbDriver.queryTasks();
        List<Integer> st = new LinkedList<>();
        for (Task t : tasklist){
            int id = t.getTaskId();
            st.add(id);
            double price = t.getPrice();
            System.out.println(id +"-"+ price);
        }

        ObservableList<Integer> tasksList = FXCollections.observableList(st);
      //  tasks.setValue("1");
        tasks.setItems(tasksList);
    }



    public void TaskActionAdd(){
        Integer value = Integer.valueOf(String.valueOf(tasks.getValue()));
        taskIds.add(value);

    }
    public void TaskActionRemove(){
        Integer value = Integer.valueOf(String.valueOf(tasks.getValue()));
        taskIds.remove(value);
    }

    public void confirmTasks(){
        for (int i : this.taskIds) System.out.println(i);
        List<Task> newTasks = new LinkedList<>();
        String message = "";
        StringBuffer output = new StringBuffer(110);
        for (int i : this.taskIds) {
            Task searchedTask = DbDriver.searchTask(i);
            newTasks.add(searchedTask);
            message = searchedTask.getDescription();
            output.append(message);
            output.append("\n");
        }
        System.out.println(output.toString());
        taskLabel.setText(output.toString());

    }



    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/BapersControl/dashboard.fxml");
    }

    public void radioButtonHandler(){
        System.out.println("handling radio button");
    }


    public void createJob(ActionEvent event) throws IOException {

        //int searchedId = 1;
//        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
//
//        List<Task> newTasks = new LinkedList<>();
//        for (int i : this.taskIds) {
//            Task searchedTask = DbDriver.searchTask(i);
//            newTasks.add(searchedTask);
//            System.out.println(searchedTask.getDescription());
//        }
//
//        int priority = Integer.valueOf(jobPriority.getText());
//
//        String specialInstructions = notes.getText();
//
//
//        searchedCustomer.createJob(1,priority,specialInstructions,newTasks);
    }

}