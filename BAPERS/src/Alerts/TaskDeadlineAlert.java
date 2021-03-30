package Alerts;

import Database.DbDriver;
import JobTasks.Job;

import java.sql.Timestamp;
import java.util.List;

public class TaskDeadlineAlert extends Thread{

    int taskId, jobTaskId;
    @Override
    public void run() {

        while (true) {


            System.out.println("job task ID: " + getJobTaskId() + " task ID: " + getTaskId() );


            try {
                //Will run checks again after 15 minutes.
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getJobTaskId() {
        return jobTaskId;
    }

    public void setJobTaskId(int jobTaskId) {
        this.jobTaskId = jobTaskId;
    }
}
