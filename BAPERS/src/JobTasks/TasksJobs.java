package JobTasks;

import Database.DbDriver;
import Discount.Discount;

import java.sql.Timestamp;
import java.util.List;
/**
 * @author Muhammad Masum Miah
 */


public class TasksJobs {
    private int taskJobId;
    private int jobId;
    private int taskId;
    private Timestamp startTimeStamp;
    private Timestamp completeTimeStamp;
    private String startTime;
    private String completeTime;
    private long timeTaken;
    private String dayOrNight;
    private String isComplete;
    private String isOverdue;
    private int staffId;
    private String status;
    private Discount discountType; //TODO  this will later be changed to the right discount data type instead of String

    //Constructor for creating and inserting.
    public TasksJobs(int jobId,int taskId) {
        this.status = "Ready to process";
        this.isComplete= "no";
        this.isOverdue = "no";
        this.taskId = taskId;
        this.jobId = jobId;
    }
    //Constructor when querying TasksJobs.
    public TasksJobs(int taskJobId,int taskId, int jobId,int staffId,  String status,long timeTaken, String startTime,  String completeTime,String dayOrNight, String isComplete, String isOverdue) {
        this.taskJobId = taskJobId;
        this.jobId = jobId;
        this.taskId = taskId;
        this.startTime = startTime;
        this.completeTime = completeTime;
        this.timeTaken = timeTaken;
        this.dayOrNight = dayOrNight;
        this.isComplete = isComplete;
        this.isOverdue = isOverdue;
        this.staffId = staffId;
        this.status = status;
    }

    public void startTask(String dayOrNight, int id){
        this.status = "In Progress";
        this.startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.startTime = startTimeStamp.toString();
        this.dayOrNight = dayOrNight;
        this.staffId = id;
        DbDriver.updateStartTask(status,startTime,dayOrNight,staffId);
//TODO update the database once tasks start
    }
    public void completeTask(){
        this.isComplete = "yes";
        this.status = "Complete";
        this.completeTimeStamp = new Timestamp(System.currentTimeMillis());
        this.timeTaken= ((completeTimeStamp.getTime() - startTimeStamp.getTime())/1000)/60/60;
        Task task = searchTask(taskId);
        if (timeTaken > task.getDuration()){
            this.isOverdue = "overdue";
        }else{
            this.isOverdue = "No";
        }DbDriver.updateCompleteTask(status,completeTime,isComplete,isOverdue, (int) timeTaken,getTaskJobId());
    }
    //TODO update the database once tasks start

    public static Task searchTask(int taskId){
        List<Task> tasks = DbDriver.queryTasks();
        if(tasks == null){
            System.out.println("No Tasks available");
            return null;
        }
        for(Task t: tasks){
            if (t.getTaskId() == taskId){
                return t;
            }
        }return null;
    }

    public int getTaskJobId() {
        return taskJobId;
    }

    public void setTaskJobId(int taskJobId) {
        this.taskJobId = taskJobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Timestamp getCompleteTimeStamp() {
        return completeTimeStamp;
    }

    public void setCompleteTimeStamp(Timestamp completeTimeStamp) {
        this.completeTimeStamp = completeTimeStamp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getDayOrNight() {
        return dayOrNight;
    }

    public void setDayOrNight(String dayOrNight) {
        this.dayOrNight = dayOrNight;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Discount getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Discount discountType) {
        this.discountType = discountType;
    }
}
