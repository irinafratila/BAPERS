package JobTasks;

import Customer.CustomerAccount;
import Database.DbDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static DbDriver db = new DbDriver();

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Customer id");
        int searchedCustomer = sc.nextInt();
        CustomerAccount searched = searchCustomer(searchedCustomer);
        List<Integer> taskIds = new LinkedList<>();
        System.out.println("Please type the id of tasks you want");
        while (true) {
            int inputValue = sc.nextInt();
            if (inputValue >-1) {
                taskIds.add(inputValue);
            }
            else break;
            }
        for(int i: taskIds){
            System.out.println(i);
        }

        List<Task> newTasks = new LinkedList<>();
        for (int i : taskIds) {
            Task searchedTask = searchTask(i);
            newTasks.add(searchedTask);
            System.out.println(searchedTask.getDescription());
        }

        System.out.println("What is the priority?");
        int priority = sc.nextInt();
        System.out.println("Any special instructions?");
        String instructions = sc.nextLine();

        Job job = new Job(priority, instructions, newTasks);


        searched.createJob(job);
    }




    public static CustomerAccount searchCustomer(int searchedCustomer){
        List<CustomerAccount> customers = db.queryCustomers();
        if(customers == null){
            System.out.println("No customers");
            return null;
        }
        for(CustomerAccount c: customers){
            if (c.getCustomerId() == searchedCustomer){
                return c;
            }
        }return null;
    }
    public static Task searchTask(int taskId){
        List<Task> tasks = db.queryTasks();
        if(tasks == null){
            System.out.println("No Tasks available");
            return null;
        }
        for(Task t: tasks){
            if (t.getTaskId() == taskId){
                return t;
            }
        }return null;
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


