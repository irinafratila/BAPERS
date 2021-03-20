package JobTasks;

import Admin.User;
import Customer.CustomerAccount;
import Discount.FixedDiscount;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        List<Task> list = new LinkedList<>();
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        list.add(new Task(Department.CopyRoom, "Print work", 19.5));
        Job job = new Job(5, "nothing", list);
        Job job1 = new Job(5, "nothing", list);
        Job job2 = new Job(5, "nothing", list);

        CustomerAccount Bill = new CustomerAccount("Mr","Bill","ALlen","224asf","e153re","London","0488458409284","bill@email.com",true,new FixedDiscount());
        Bill.createJob(job);
        Bill.createJob(job1);
        Bill.createJob(job2);

      for (int i = 0; i< Bill.getJobs().size();i++){
          System.out.println(Bill.getJobs().get(i).getJobId());
          System.out.println(Bill.getJobs().get(i).getStartTime());
      }

        System.out.println(job.getPrice());
        for (int i =0; i<list.size();i++){
            job.getTasks().get(i).startTask(true,5);
            job.getTasks().get(i).completeTask();
        }
//        job.completeJob(user);
//        System.out.println(job.getStartTime());
//        System.out.println(job.getDeadline());
//        System.out.println(job.getTimeTaken());

    }
}
//j
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


