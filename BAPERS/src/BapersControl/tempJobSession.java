package BapersControl;

import Database.DBConnection;
import Database.DbDriver;
import JobTasks.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class tempJobSession {


    private Main m;
    private DBConnection conn;
    private Connection connDB;
    private static int id;


    public tempJobSession(int id){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
        this.id = id;
        getCustomerDetails(id);



    }

    public void getCustomerDetails(int id){


//        System.out.println(id + "first check temp");
        try {
            Job searchedJob = DbDriver.searchJobs(id);
            setId(searchedJob.getJobId());
            System.out.println("testing Job session"+ id );

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setId(int id) { tempJobSession.id = id; }

    public static int getId() { return id; }

}
