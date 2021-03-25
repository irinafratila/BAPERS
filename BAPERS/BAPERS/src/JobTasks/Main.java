package JobTasks;

import BapersControl.tempCustomerSession;
import Customer.CustomerAccount;
import Database.DBConnection;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private BapersControl.Main m;
    private DBConnection conn;
    private Connection connDB;
    @FXML
    private TextField id;

    @FXML
    private Label searchCustomerMessageLabel;

    public String idData = "";
    public Main(){
        this.m = new BapersControl.Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = DbDriver.searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","fixed");

        List<Integer> taskIds = new LinkedList<>();
        System.out.println("Please type the id of tasks you want");
        while (true) {
            int inputValue = sc.nextInt();
            if (inputValue > -1) {
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




    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/BapersControl/dashboard.fxml");
    }

    public void searchCustomer(ActionEvent event) throws IOException {
        String ID = id.getText();
        this.idData = ID;

        try {

            Boolean result;
            result = DbDriver.searchCustomerAccount(ID);
            // System.out.println("serchuser" + id + result);
            if (result== true){
                searchCustomerMessageLabel.setText("Customer found");
//                BapersControl.Main m= new BapersControl.Main();
                new tempCustomerSession(this.idData);
//                m.changeScene("/Customer/createJob.fxml");
                m.changeScene("/Customer/test.fxml");
            }else{
                searchCustomerMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
