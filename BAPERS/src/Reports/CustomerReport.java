package Reports;

import Database.DbDriver;
import JobTasks.Job;

import java.util.List;

public class CustomerReport {
    int customerId, jobId;
    String customer_name, contact, address, getPhoneNumber;

    public CustomerReport(int customerId, int jobId, String customer_name, String contact, String address, String getPhoneNumber) {
        this.customerId = customerId;
        this.jobId = jobId;
        this.customer_name = customer_name;
        this.contact = contact;
        this.address = address;
        this.getPhoneNumber = getPhoneNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public String getGetPhoneNumber() {
        return getPhoneNumber;
    }

    public void setGetPhoneNumber(String getPhoneNumber) {
        this.getPhoneNumber = getPhoneNumber;
    }

}
