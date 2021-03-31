package BapersControl;

import Database.DbDriver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Admin.User;


import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stg = primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1000, 769));
        primaryStage.show();
//        DbDriver.printCust();
//        DbDriver.printStaff();
//        DbDriver.printJobs();
        DbDriver.searchOpenJobs();
//        DbDriver.printTasks();
//        DbDriver.generateSummaryReport("2021-03-27","2021-03-29");
    }

    public void changeScene(String fxml) throws IOException {
//        System.out.println(User.class.getResource(fxml));
//        System.out.println(getClass().getResource("/Admin/createUser.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }



   // public static void main(String[] args) {
     //   launch(args);
  //  }
}
