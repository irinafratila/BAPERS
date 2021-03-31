package JobTasks;

import Database.DbDriver;
import Discount.Discount;
import java.sql.Timestamp;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Muhammad Masum Miah
 */


public class TasksJobs {
    private int taskJobId;
    private int jobId;
    private int taskId;
    private Timestamp startTimeStamp;
    private Timestamp completeTimeStamp;
    private Timestamp startTime;
    private Timestamp completeTime;
    private long timeTaken;
    private String dayOrNight;
    private String isComplete;
    private String isOverdue;
    private int staffId;
    private String status;
    private Discount discountType;


    //Constructor when querying TasksJobs.
    public TasksJobs(int taskJobId, int taskId, int jobId, int staffId, String status, long timeTaken, Timestamp startTime, Timestamp completeTime, String dayOrNight, String isComplete, String isOverdue) {
        this.taskJobId = taskJobId;
        this.jobId = jobId;
        this.taskId = taskId;
        this.startTimeStamp = startTime;
        this.completeTimeStamp = completeTime;
        this.timeTaken = timeTaken;
        this.dayOrNight = dayOrNight;
        this.isComplete = isComplete;
        this.isOverdue = isOverdue;
        this.staffId = staffId;
        this.status = status;
    }

    public void startTask(String dayOrNight, int id){
        setStatus("In Progress");
        setStartTimeStamp(new Timestamp(System.currentTimeMillis()));
        setDayOrNight(dayOrNight);
        setStaffId(id);
        DbDriver.updateStartTask(status,id,startTimeStamp,dayOrNight,taskJobId);

//TODO update the database once tasks start
    }
    public void completeTask() {

         setIsComplete("yes");
            setStatus("Complete");
            setCompleteTimeStamp(new Timestamp(System.currentTimeMillis()));
            setTimeTaken(((completeTimeStamp.getTime() -startTimeStamp.getTime()) / 1000) / 60 / 60);
            Task task = DbDriver.searchTask(taskId);
            if(task != null)
            if ((double) timeTaken > task.getDuration()) {
                setIsOverdue("Overdue");
            } else {
                setIsOverdue("No");
            }
            DbDriver.updateCompleteTask("Complete", completeTimeStamp, isComplete, isOverdue, (int) timeTaken, getTaskJobId());

    }
    //TODO update the database once tasks start


    //Sets the current task in operation.
    public boolean setCurrentOperation(String dayOrNight, int id) {
        List<TasksJobs>  tasksJobs = DbDriver.queryTasksJobs();
        ListIterator<TasksJobs> tasksList = tasksJobs.listIterator();
        while (tasksList.hasNext()) {
            if (!tasksList.next().getIsComplete().equals(("yes"))) {
                tasksList.next().startTask(dayOrNight,id);
                return true;
            }
        }
        System.out.println("All jobs are complete!");
        return false;

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Timestamp completeTime) {
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
