package JobTasks;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Job {
    private int priority;
    private Timestamp deadline;
    private String status;
    private Date startDate;
    private Timestamp startTime;
    private int timeTaken;
    private User completedBy;
    private List<Task> tasks;
    private  double price;

    public boolean addTask(Task t){
        tasks.add(t);
        return true;
    }
    public boolean removeTask(Task t){
        return true;
    }
    public boolean updateTask(Task t){
        return true;
    }
    public boolean retrieveTask(Task t){
        return true;
    }
    public boolean setCurrentOperation(int taskId){
        return true;
    }
    public String inspectTask(int taskId){
        return null;
    }
    public int assignJobId(){

    }
    public Timestamp operationDeadline(){

    }
    public String retrieveJobStatus(int jobId){
        return status;
    }
    public String updateStatus(String message){
        status = message;
        return status;
    }
    public boolean changePriority(int priority){
        this.priority = priority;
        return true;
    }
    public double calculatePrice(){
        return price;
    }
    public double getPrice(){
        return price;
    }

    public int getPriority() {
        return priority;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
