package Alerts;

import Database.DbDriver;
import JobTasks.Job;
import java.sql.Timestamp;
import java.util.List;

public class JobDeadlineAlert extends  Thread {
    @Override
    public void run() {

        while (true) {

            Timestamp current = new Timestamp(System.currentTimeMillis());
            List<Job> allJobs = DbDriver.searchAllJobs();
            for (Job j : allJobs) {
                Timestamp deadline = j.getDeadlineTimeStamp();
                if (!j.isJobComplete()) {
                    long leftToDeadline = ((deadline.getTime() - current.getTime()) / 1000) / 60 ;
                    if (leftToDeadline < 70 && leftToDeadline >=0) {
                        //TODO create an alert
                        System.out.println("deadline approaching for job number " + j.getJobId());
                    }
                    else if ((leftToDeadline < 0)){
                        System.out.println("deadline passed for job number " + j.getJobId());

                    }
                }


            }try {
                //Will run checks again after 10 minutes.
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
