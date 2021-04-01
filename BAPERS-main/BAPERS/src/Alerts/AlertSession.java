package Alerts;

import BapersControl.Main;
import Database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class AlertSession {


    private Main m;
    private static String message;
    private static List<String> messages = new LinkedList<>();

    public AlertSession(String x){
        this.m = new Main();
        this.messages.add(x);

    }

    public static List<String> getAlerts() {
        return messages;
    }

    public static Boolean checkForAlert(){
        if (messages.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

//    public static void setMessage(String message) {
//        Alertsession.message = message;
//    }
}
