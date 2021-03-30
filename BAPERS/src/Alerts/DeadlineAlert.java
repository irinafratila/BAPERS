package Alerts;

import Database.DbDriver;
import JobTasks.Job;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DeadlineAlert extends  Thread {
    @Override
    public void run() {

        while (true) {

            Timestamp current = new Timestamp(System.currentTimeMillis());
            List<Job> allJobs = DbDriver.searchAllJobs();
            for (Job j : allJobs) {
                Timestamp deadline = j.getDeadlineTimeStamp();
                if (!j.isJobComplete()) {
                    long leftToDeadline = ((deadline.getTime() - current.getTime()) / 1000) / 60 ;
                    if (leftToDeadline < 70) {
                        //TODO create an alert
                        System.out.println("deadline approaching for " + j.getJobId());
                    }
                }


            }try {
                //Will run checks again after 10 minutes.
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
