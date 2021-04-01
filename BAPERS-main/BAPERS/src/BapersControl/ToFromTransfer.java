package BapersControl;

import Database.DBConnection;

import java.sql.Connection;

public class ToFromTransfer {

    private Main m;
    private DBConnection conn;
    private Connection connDB;
    private static String to,from;


    public ToFromTransfer(String to, String from){
        setTo(to);
        setFrom(from);
    }

    public ToFromTransfer(String from){
        setFrom(from);
    }


    public static void setTo(String to) {
        ToFromTransfer.to = to;
    }

    public static void setFrom(String from) {
        ToFromTransfer.from = from;
    }

    public static String getTo() {
        return to;
    }

    public static String getFrom() {
        return from;
    }
}
