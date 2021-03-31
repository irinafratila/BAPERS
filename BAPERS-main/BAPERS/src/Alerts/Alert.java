package Alerts;

import BapersControl.Main;
import JobTasks.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Alert{
//    private List<String> alertList;
    private List<String> alerts;
    private Main m;

    @FXML
    private Label alertLabel;

    public Alert(){
        this.m = new Main();
        this.alerts = AlertSession.getAlerts();
    }



    public void cancel() throws IOException {
        m.changeScene("dashboard.fxml");
    }
    private void print() {
        for (String i : this.alerts){
            System.out.println("testing big boy alert " +i);
        }
    }



    public void viewAlert(){
       // this.alerts.add("testing this homie");
        System.out.println("view alers");
        String message = "";
        List<String> newTasks = new LinkedList<>();
        StringBuffer output = new StringBuffer(110);
        for (String i : this.alerts){
            newTasks.add(i);
            message = i;
            output.append(message);
            output.append("\n");
            //System.out.println("testing big boy alert22 " +i);
        }
        System.out.println("testin 333"+output.toString());
        alertLabel.setText(output.toString());
    }



}


