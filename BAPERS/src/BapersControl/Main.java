package BapersControl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
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
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 1000, 769));
        primaryStage.show();


        ChoiceBox<Integer> taskList = new ChoiceBox<>();
        taskList.getItems().add(1);
        taskList.getItems().add(2);
    }

    public void changeScene(String fxml) throws IOException {
        System.out.println(User.class.getResource(fxml));
        System.out.println(getClass().getResource("/Admin/createUser.fxml"));
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    //

    // public static void main(String[] args) {
    //   launch(args);
    //  }
}
