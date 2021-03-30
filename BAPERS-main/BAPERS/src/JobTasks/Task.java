
package JobTasks;

import Database.DbDriver;
import Discount.Discount;

import java.sql.Timestamp;

/**
 * @author Muhammad Masum Miah
 */


public class Task {
    private static int count;
    private int taskId;
    private String description;
    private double price;
    private int department;
    private double duration;

    public Task(int id, String description, int d, double price, int duration) {
        this.department = d;
        this.description = description;
        this.taskId = id;
        this.price = price;
        this.duration = duration;
        //TODO: if statements for discounts and to calculate price.
        this.price = price;
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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}