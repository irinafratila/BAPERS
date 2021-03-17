
package JobTasks;

import java.sql.Timestamp;
import java.util.Date;

public class Task {
    private String status;
    private int taskId;
    private String department;
    private Date startDate;
    private Timestamp startTime;
    private double timeTaken;
    private boolean dayShift;
    private double price;
    private String discountType = "none"; //TODO  this will later be changed to the right discount data type instead of String

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDepartment() {
        return department;
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

    public boolean isDayShift() {
        return dayShift;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setDayShift(boolean dayShift) {
        this.dayShift = dayShift;
    }
    public void start(){}
    public void complete(){}

    public Task(String status, int taskId, String department, boolean dayShift) {
        this.status = status;
        this.taskId = taskId;
        this.department = department;
        this.dayShift = dayShift;
    }
}

