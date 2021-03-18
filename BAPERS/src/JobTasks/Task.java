
package JobTasks;

import Discount.Discount;

import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private static int count;
    private String status;
    private int taskId;
    private Department department;
    private Timestamp startTime;
    private Timestamp completeTime;
    private long timeTaken;
    private boolean dayShift;
    private double price;
    private boolean isComplete;
    private boolean isOverdue;
    private double duration;
    private int staffId;

    private Discount discountType; //TODO  this will later be changed to the right discount data type instead of String


    public Task(Department department, double price) {
        this.department = department;

        this.status = "Ready to process";
        this.taskId = count++;
        this.isComplete=false;
        this.isOverdue = false;

        //TODO: if statements for discounts and to calculate price.
        this.price = price;
    }

    public void startTask(boolean dayShift, int id){
        this.status = "In Progress";
        this.startTime = new Timestamp(System.currentTimeMillis());
        this.dayShift = dayShift;
        this.staffId = id;

    }

    public void completeTask(){
        this.isComplete = true;
        this.status = "Complete";
        this.timeTaken= ((completeTime.getTime() - startTime.getTime())/1000)/60/60;
        if (timeTaken > duration){
            this.isOverdue = true;
        }
    }
    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public int getTaskId() {
        return taskId;
    }

    public Department getDepartment() {
        return department;
    }

    public Date getStartTime() {
        return startTime;
    }


    public double getTimeTaken() {
        return timeTaken;
    }

    public boolean isDayShift() {
        return dayShift;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    public boolean checkIfComplete(){
        return isComplete;
    }


}
