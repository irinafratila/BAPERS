package Alerts;

import Database.DbDriver;
import JobTasks.Job;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class JobDeadlineAlert extends  Thread {

    private List<String> alertList;
    public JobDeadlineAlert(){
       // this.alertList = new LinkedList<>();
    }

    @Override
    public void run() {

        while (true) {

            Timestamp current = new Timestamp(System.currentTimeMillis());
            List<Job> allJobs = DbDriver.searchAllJobs();
            for (Job j : allJobs) {
                Timestamp deadline = j.getDeadlineTimeStamp();
                if (!j.getStatus().equalsIgnoreCase("Completed")) {
                    long leftToDeadline = ((deadline.getTime() - current.getTime()) / 1000) / 60 ;
                    if (leftToDeadline < 70 && leftToDeadline >=0) {
                        //TODO create an alert
                        System.out.println("deadline approaching for job number " + j.getJobId());

                        String x = "deadline approaching for job number " + j.getJobId();
                        System.out.println("i am calling alert method");
                        new AlertSession(x);
                    }
                    else if ((leftToDeadline < 0)){
                        System.out.println("not test deadline passed for job number " + j.getJobId());

                            String x = "test deadline passed for job number " + j.getJobId();
                            System.out.println("i am calling alert method");
                             new AlertSession(x);

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
