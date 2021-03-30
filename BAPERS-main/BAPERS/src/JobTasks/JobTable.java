package JobTasks;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class JobTable {

    private SimpleIntegerProperty jobID;
    private SimpleIntegerProperty staffID;
    private SimpleIntegerProperty priority;
    private SimpleStringProperty status;
    private SimpleStringProperty start;
    private SimpleStringProperty end;
    private SimpleDoubleProperty price;
    private SimpleStringProperty instruction;

    public JobTable(){

    }

    public JobTable(int jobID, int staffID, int priority,String status, String start,String end,Double price,String instruction){
        this.jobID = new SimpleIntegerProperty(jobID);
        this.staffID = new SimpleIntegerProperty(staffID);
        this.priority = new SimpleIntegerProperty(priority);
        this.status = new SimpleStringProperty(status);
        this.start = new SimpleStringProperty(start);
        this.end = new SimpleStringProperty(end);
        this.price = new SimpleDoubleProperty(price);
        this.instruction = new SimpleStringProperty(instruction);
    }

    public void setJobID(int jobID) {
        this.jobID.set(jobID);
    }

    public int getJobID() {
        return jobID.get();
    }

    public void setStaffID(int staffID) {
        this.staffID.set(staffID);
    }

    public int getStaffID() {
        return staffID.get();
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
    }

    public int getPriority() {
        return priority.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getStart() {
        return start.get();
    }

    public void setEnd(String end) {
        this.end.set(end);
    }

    public String getEnd() {
        return end.get();
    }

    public void setPrice(float price) {
        this.price.set(price);
    }

    public double getPrice() {
        return price.get();
    }

    public void setInstruction(String instruction) {
        this.instruction.set(instruction);
    }

    public String getInstruction() {
        return instruction.get();
    }
}
