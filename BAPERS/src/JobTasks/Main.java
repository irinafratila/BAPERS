package JobTasks;

import Customer.CustomerAccount;
import Database.DbDriver;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    private static DbDriver db = new DbDriver();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter Customer id");
        int searchedId = sc.nextInt();
        CustomerAccount searchedCustomer = searchCustomer(searchedId);
        searchedCustomer.updateCustomerType("valuable","flexi");
//        List<Integer> taskIds = new LinkedList<>();
//        System.out.println("Please type the id of tasks you want");
//        while (true) {
//            int inputValue = sc.nextInt();
//            if (inputValue > -1) {
//                taskIds.add(inputValue);
//            } else break;
//        }
//        for (int i : taskIds) {
//            System.out.println(i);
//        }
//
//        List<Task> newTasks = new LinkedList<>();
//        for (int i : taskIds) {
//            Task searchedTask = searchTask(i);
//            newTasks.add(searchedTask);
//            System.out.println(searchedTask.getDescription());
//        }
//
//        System.out.println("What is the priority?");
//        int priority = sc.nextInt();
//        sc.nextLine();
//        System.out.println("Any special instructions?");
//
//
//        String specialInstructions = sc.nextLine();
//
//        sc.close();
//
//
//
//
//        searchedCustomer.createJob(1,priority,specialInstructions,newTasks);
//
//        searchedCustomer.makePayment(1,6.6f,"card","visa","12/25",1234);
//        searchedCustomer.addTask(1,6);
//        searchedCustomer.removeTask(15);
//
//        printOpenJobs();
//        printOpenTasks(1);
    List<TasksJobs> hello = searchTasksJobs1(4);
    for(TasksJobs h: hello){
        h.startTask("day",1);
//        h.completeTask();
        System.out.println(h.getTimeTaken());
    }
    }


    //Search and print open jobs.
    public static List<Job> searchJob() {
        List<Job> jobs = db.queryJobs();

        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        return jobs;
    }

    public static void printOpenJobs() {
        List<Job> open = searchJob();
        for (Job j : open) {
            if (j.getHours() == 0) {
                System.out.println(j.getJobId());
            }
        }
    }
    //search and print all tasks by selecting a job id.
    public static List<Task> searchTasksJobs(int jobId) {
        List<TasksJobs> jobs = db.queryTasksJobs();
        List<Task> tasks = db.queryTasks();
        List<Task> remaining = new LinkedList<>();


        if (jobs == null) {
            System.out.println("No Tasks");
            return null;
        }
        for (TasksJobs t : jobs)
            if (t.getJobId() == jobId) {
                for (Task task : tasks)
                    if (task.getTaskId() == t.getTaskId()) {
                        remaining.add(task);
                    }
            }
        return remaining;


    }
    public static List<TasksJobs> searchTasksJobs1(int jobId) {
        List<TasksJobs> jobs = db.queryTasksJobs();

        List<TasksJobs> remaining = new LinkedList<>();


        if (jobs == null) {
            System.out.println("No Tasks");
            return null;
        }
        for (TasksJobs t : jobs)
            if (t.getJobId() == jobId) {


                        remaining.add(t);
                    }


        return remaining;


    }

    public static void printOpenTasks(int jobId) {
        List<Task> open = searchTasksJobs(jobId);
        for (Task j : open) {

            System.out.println(j.getTaskId() + " " + j.getDescription());

        }
    }


    public static CustomerAccount searchCustomer(int searchedCustomer) {
        List<CustomerAccount> customers = db.queryCustomers();
        if (customers == null) {
            System.out.println("No customers");
            return null;
        }
        for (CustomerAccount c : customers) {
            if (c.getCustomerId() == searchedCustomer) {
                return c;
            }
        }
        return null;
    }

    public static Task searchTask(int taskId) {
        List<Task> tasks = db.queryTasks();
        if (tasks == null) {
            System.out.println("No Tasks available");
            return null;
        }
        for (Task t : tasks) {
            if (t.getTaskId() == taskId) {
                return t;
            }
        }
        return null;
    }



    public static Job searchJob(int searchedJob){
        List<Job> jobs = db.queryJobs();
        if(jobs == null){
            System.out.println("No Jobs");
            return null;
        }
        for(Job j: jobs){
            if (j.getJobId() == searchedJob){
                return j;
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


