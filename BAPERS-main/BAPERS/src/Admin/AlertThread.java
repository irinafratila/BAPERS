package Admin;

import Database.DbDriver;
import JobTasks.Job;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AlertThread extends  Thread {
    @Override
    public void run() {

        while (true) {

//           String role =  BapersControl.currentLoginSession.getRole();

            Timestamp current = new Timestamp(System.currentTimeMillis());
            List<Job> allJobs = DbDriver.searchAllJobs();
            for (Job j : allJobs) {
                Timestamp deadline = j.getDeadlineTimeStamp();
                if (!j.isJobComplete()) {
                    long leftToDeadline = ((deadline.getTime() - current.getTime()) / 1000) / 60 ;
                    if (leftToDeadline < 50) {
                        //TODO create an alert
                        System.out.println("deadline approaching for " + j.getJobId());
                    }
                }

            }try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }




}
