package JobTasks;

import Admin.AlertThread;
import Customer.CustomerAccount;
import Database.DbDriver;
import Reports.Invoice;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException {

//        Thread thread = new AlertThread();
//        thread.start();
//        DbDriver.generateIndividualStaffReport(2);
////        System.out.println(DbDriver.createSummaryReport);
////
//        //start tasks
//        List<TasksJobs> tasksToStart = DbDriver.queryTasksJobs();
//        for(TasksJobs t :tasksToStart){
//            if(t.getTaskJobId() <15){
//                t.startTask("day",1);
//                t.completeTask();
//            }
//        }
//DbDriver.generateIndividualStaffReport(5);
//
//////
        DbDriver.generateSummaryReport("2021-03-27","2021-03-29");
//         DbDriver.insertStaffAccount("hfhf", "dd", "ds", "dd", "ddd", "242323");

//        List<Job> open = DbDriver.getOpenJobs();
//        DbDriver.printJobs(open);


        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","variable");
        List<Task> tasks = DbDriver.queryTasks();
        int tasksSize = tasks.size();
        List<Integer> taskIds = new LinkedList<>();
        System.out.println("Please type the id of tasks you want");
        while (true) {
            int inputValue = sc.nextInt();
            if (inputValue < tasksSize && inputValue > 0) {

                    taskIds.add(inputValue);
                } else break;
            }

        for (int i : taskIds) System.out.println(i);

        List<Task> newTasks = new LinkedList<>();
        for (int i : taskIds) {
            Task searchedTask = DbDriver.searchTask(i);
            newTasks.add(searchedTask);
            System.out.println(searchedTask.getDescription());
        }

        System.out.println("What is the priority?");
        int priority = sc.nextInt();
        sc.nextLine();
        System.out.println("Any special instructions?");


        String specialInstructions = sc.nextLine();

        sc.close();




        searchedCustomer.createJob(1,priority,specialInstructions,newTasks);
//        Job j = DbDriver.searchJobs(1);
//        j.completeJob(1);
//        searchedCustomer.makeCashPayment(1,59.4);
//


//        searchedCustomer.makePayment(1,6.6f,"card","visa","12/25",1234);
//        searchedCustomer.addTask(1,6);
//        searchedCustomer.removeTask(15);
//        printOpenJobs();
//        printOpenTasks(1);
//        List<TasksJobs> hello = searchTasksJobs1(4);
//        for(TasksJobs h: hello){
//            h.startTask("day",1);
////        h.completeTask();
//            System.out.println(h.getTimeTaken());
//        }
    }

}




//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
//import javafx.scene.control.ChoiceBox;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//
//
//import java.io.IOException;
//
//public class Main extends Application {
//    private static Stage stg;
//    Button button;
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        stg = primaryStage;
//        stg.setTitle("Tasks");
//        button = new Button("Click me");
//
//
//
//        ChoiceBox<Integer> taskList = new ChoiceBox<>();
//        taskList.getItems().add(1);
//        taskList.getItems().add(2);
//
//        button.setOnAction(e -> getChoice(taskList));
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout,3000,250);
//        stg.setScene(scene);
//        stg.show();
//    }
//
//    private void getChoice(ChoiceBox<Integer> taskList){
//        int food = taskList.getValue();
//        System.out.println(food);
//    }
//
//    //
//
//    // public static void main(String[] args) {
//    //   launch(args);
//    //  }
//}


