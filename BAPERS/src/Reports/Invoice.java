package Reports;

import Database.DbDriver;

import java.util.List;

public class Invoice {
    int customerId;
    String name;
    String contact;
    String address;
    String phoneNumber;
    String startTime;
    int jobId;
    int taskId;
    String desc;
    double tPrice;
    double total;

    public Invoice(int customerId, String name, String contact, String address, String phoneNumber, String start, int jobId, int taskId, String desc, double tPrice, double total) {
        this.customerId = customerId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.startTime = start;
        this.jobId = jobId;
        this.taskId = taskId;
        this.desc = desc;
        this.tPrice = tPrice;
        this.total = total;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double gettPrice() {
        return tPrice;
    }

    public void settPrice(double tPrice) {
        this.tPrice = tPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
