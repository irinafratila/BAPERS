package JobTasks;


import BapersControl.Main;
import Database.DbDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateJob  implements Initializable {

    private Main m;

    @FXML
    private Label taskLabel;

    @FXML
    private ChoiceBox tasks;

    private List<TasksJobs> tasklist;

    private static List<Integer> taskIds;


    public UpdateJob(){

        this.m = new BapersControl.Main();
        this.taskIds = new LinkedList<>();
    }

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/searchJob.fxml");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int iD = BapersControl.tempJobSession.getId();
//        Job searchedJob = DbDriver.searchJobs(id);
//        searchedJob.getJobId();
        tasklist = DbDriver.getAllTaskInfoOnAJob(iD);

        List<Integer> st = new LinkedList<>();
        for (TasksJobs t : tasklist){
            int id = t.getTaskId();
            st.add(id);
            taskIds.add(id);
//            double price = t.;
            String stats = t.getStatus();
            System.out.println(id +"-"+stats);
        }

        ObservableList<Integer> tasksList = FXCollections.observableList(st);
        tasks.setItems(tasksList);
    }


    public void start(){
        int taskID = Integer.valueOf(String.valueOf(tasks.getValue()));
//        taskIds.add(taskID);
//        String status = "Started";
//        int staff_id = Integer.parseInt(BapersControl.currentLoginSession.getId());;
//        Timestamp start = Timestamp.valueOf(LocalDateTime.now());
//        String shift = "day";
//        Job searchedJob = DbDriver.searchJobs(id);
//
//        int jobTaskID = 1;

    //DbDriver.updateStartTask(status,staff_id,start,shift,jobTaskID);

       // start tasks
        int iD = BapersControl.tempJobSession.getId();
        List<TasksJobs> tasksToStart = DbDriver.getAllTaskInfoOnAJob(iD);
        for(TasksJobs t :tasksToStart){
            if(t.getTaskId() == taskID){
                System.out.println("i can start the task now");
                t.startTask("day",iD);
                int JID = t.getJobId();
                Boolean result = DbDriver.updateJobStart(JID,"Started",Timestamp.valueOf(LocalDateTime.now()));
//                Job j = DbDriver.searchJobs(JID);
//                j.setStatus("Started");
            }
        }
    }



    public void complete(){
        int taskID = Integer.valueOf(String.valueOf(tasks.getValue()));
        int iD = BapersControl.tempJobSession.getId();
        List<TasksJobs> tasksToStart = DbDriver.getAllTaskInfoOnAJob(iD);
        for(TasksJobs t :tasksToStart){
            if(t.getTaskId() == taskID){
                System.out.println("i can start the task now");
                t.completeTask();
//                Job j = DbDriver.searchJobs(JID);
//                j.setStatus("Completed");
            }
        }



       // for (int i : this.taskIds) System.out.println(i);
//        List<Task> newTasks = new LinkedList<>();
//        String message = "";
//        StringBuffer output = new StringBuffer(110);
//        for (int i : this.taskIds) {
//            Task searchedTask = DbDriver.searchTask(i);
//            newTasks.add(searchedTask);
//            message = searchedTask.getDescription();
//            output.append(message);
//            output.append("\n");
////            DbDriver.updateCompleteTask();
//        }
//        System.out.println(output.toString());
//        taskLabel.setText(output.toString());


//        for (int i : this.taskIds) System.out.println(i);
//        List<Task> newTasks = new LinkedList<>();
//        String message = "";
//        StringBuffer output = new StringBuffer(110);
//        for (int i : this.taskIds) {
//            Task searchedTask = DbDriver.searchTask(i);
//            newTasks.add(searchedTask);
//            message = searchedTask.getDescription();
//            output.append(message);
//            output.append("\n");
////            DbDriver.updateCompleteTask();
//        }
//        System.out.println(output.toString());
//        taskLabel.setText(output.toString());

    }

    public void show(){
        for (TasksJobs t : this.tasklist) System.out.println(t.getTaskId());
        List<Task> newTasks = new LinkedList<>();
        String message = "";
        StringBuffer output = new StringBuffer(110);
        int i = 1;
        for (TasksJobs t : this.tasklist) {
            Task searchedTask = DbDriver.searchTask(t.getTaskId());
            newTasks.add(searchedTask);
            message = i+" : " +searchedTask.getDescription() +"  "+ t.getStatus()+ "  "+ t.getTimeTaken() + "  " + t.getTaskId();
            output.append(message);
            output.append("\n");
//            DbDriver.updateCompleteTask();
            i++;
        }
        System.out.println(output.toString());
        taskLabel.setText(output.toString());
    }


    public void Update(){

        for (TasksJobs t : tasklist) {
            //Task searchedTask = DbDriver.searchTask(t.getTaskId());
            if((!t.getStatus().equalsIgnoreCase("complete")) ){
                System.out.println("Job still has tasks that are not complete"+ t.getStatus());
            }else {
                int JID = t.getJobId();
                Boolean result = DbDriver.updateJobComplete(JID,"Completed",Timestamp.valueOf(LocalDateTime.now()));
                System.out.println("Job is now complete" + t.getStatus());
            }
        }

        taskLabel.setText("");
        tasks.setValue("");

        // check if all the tastks are ready if so then
       // System.out.println("i am going to change the jobs table");
    }
}
