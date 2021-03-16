package JobTasks;

import Admin.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class Job {
    private static int count;
    private int priority;
    private Timestamp deadline;
    private String status;
    private Date startDate;
    private Timestamp startTime;
    private int timeTaken;
    private User completedBy;
    private List<Task> tasks;
    private  double price;
    private int JobId;


    public Job() {
    }

    public int getJobId() {
        return JobId;
    }

    public static void setCount(int count) {
        Job.count = count;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setCompletedBy(User completedBy) {
        this.completedBy = completedBy;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean addTask(Task t){
        tasks.add(t);
        return true;
    }
    public void removeTask(Task t){
        ListIterator<Task> tasksList = tasks.listIterator();
        while( tasksList.hasNext()){
            if (tasksList.next() == t){
                tasksList.remove();
            }

        }

    }
    public boolean updateTask(Task t){
        return true;
    }
    public Task retrieveTask(int taskID) {
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            if (tasksList.next().getTaskId() == taskID) {
                return tasksList.next();
            }
        } return null;
    }
    public boolean setCurrentOperation(int taskId){
        return true;
    }
    public String inspectTask(int taskId){
        return null;
    }

    public int setJobId() {
        JobId = count++;
        return JobId;
    }

//    public Timestamp operationDeadline(){
//
//    }
    public String retrieveJobStatus(){
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
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            price += tasksList.next().getPrice();

            }return price;


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

    public double getTimeTaken() {
        return timeTaken;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
