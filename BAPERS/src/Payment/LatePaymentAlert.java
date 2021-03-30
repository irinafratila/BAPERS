package Payment;

import Alerts.DeadlineAlert;
import Customer.CustomerAccount;
import Database.DbDriver;
import JobTasks.Job;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static java.sql.Date.valueOf;

public class LatePaymentAlert {

    int account;
    String name;
    int jobId;

    public LatePaymentAlert(int account, String name, int jobId) {
        this.account = account;
        this.name = name;
        this.jobId = jobId;

    }

    Timestamp current;
    long currentDate;


    Date date = new Date(2021,2,30);

    public void generateAlert(Date date) {
       Thread alert = new DeadlineAlert();
        List<LatePaymentAlert> LPA = DbDriver.latePaymentAlert();
        List<CustomerAccount> customers = DbDriver.queryCustomers();
        List<Job> jobs = DbDriver.searchAllJobs();
        for(LatePaymentAlert l: LPA){
            int cust = l.account;
            CustomerAccount searchedCUStomer = DbDriver.searchCustomer(cust);
            if(searchedCUStomer.getCustomerType().equalsIgnoreCase("normal")){
                alert.start();
            }else{
                //if date is passed the 10th, then alert manager
               current = new Timestamp(System.currentTimeMillis());
                Date d = new Date(current.getTime());
                if(d.after(date)){
                   alert.start();
                }

            }
        }




    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
