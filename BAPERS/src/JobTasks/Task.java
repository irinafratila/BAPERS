
package JobTasks;

import Discount.Discount;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Muhammad Masum Miah
 */


public class Task {
    private static int count;
    private int taskId;
    private String description;
    private String status;
    private int department;
    private Timestamp startTimeStamp;
    private String startTime;
    private Timestamp completeTimeStamp;
    private String completeTime;
    private long timeTaken;
    private boolean dayShift;
    private double price;
    private boolean isComplete;
    private boolean isOverdue = false;
    private double duration;
    private int staffId;
    private Discount discountType; //TODO  this will later be changed to the right discount data type instead of String


    public Task(int id, String description,int d,float price, int duration) {
        this.department = d;
        this.description = description;
        this.status = "Ready to process";
        this.taskId = id;
        this.isComplete=false;
        this.isOverdue = false;
        this.price = price;
        this.duration = duration;

        //TODO: if statements for discounts and to calculate price.
        this.price = price;
    }

    public void startTask(boolean dayShift, int id){
        this.status = "In Progress";
        this.startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.startTime = startTimeStamp.toString();
        this.dayShift = dayShift;
        this.staffId = id;
        //TODO: Update database with new info.

    }

    public void completeTask(){
        this.isComplete = true;
        this.status = "Complete";
        this.completeTimeStamp = new Timestamp(System.currentTimeMillis());
        this.completeTime = completeTimeStamp.toString();
        this.timeTaken= ((completeTimeStamp.getTime() - startTimeStamp.getTime())/1000)/60/60;
        if (timeTaken > duration){
            this.isOverdue = true;
        }
    }


    //getters and setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public Timestamp getStartTime() {
        return startTimeStamp;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTimeStamp = startTime;
    }

    public Timestamp getCompleteTime() {
        return completeTimeStamp;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTimeStamp = completeTime;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public boolean isDayShift() {
        return dayShift;
    }

    public void setDayShift(boolean dayShift) {
        this.dayShift = dayShift;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Discount getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Discount discountType) {
        this.discountType = discountType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }
}
