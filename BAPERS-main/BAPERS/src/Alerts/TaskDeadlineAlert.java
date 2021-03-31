package Alerts;


public class TaskDeadlineAlert extends Thread{

    int taskId, jobTaskId;
    @Override
    public void run() {
        boolean flag = true;

        while (flag) {


            System.out.println("job task ID: " + getJobTaskId() + " task ID: " + getTaskId() + " has passed their deadine" );


            flag = false;

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