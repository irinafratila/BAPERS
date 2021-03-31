package Alerts;


import Database.DbDriver;
import JobTasks.Job;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PaymentAlert extends  Thread {
    private int job_id =-1;
    private int account = -1;


    private String cname =null;
    @Override
    public void run() {

        while (true) {

            System.out.println("job ID: " + job_id + " Customer ID: " + getAccount() + " Customer Name: " + getCname());


            try {
                //Will run checks again after 15 minutes.
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



}