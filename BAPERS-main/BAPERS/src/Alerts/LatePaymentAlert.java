package Alerts;


import java.sql.Timestamp;

import static java.sql.Date.valueOf;

public class LatePaymentAlert {

    int account;
    String name;
    int jobId;
    Timestamp complete;

    public LatePaymentAlert(int account, String name, int jobId, Timestamp complete) {
        this.account = account;
        this.name = name;
        this.jobId = jobId;
        this.complete = complete;

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

    public Timestamp getComplete() {
        return complete;
    }

    public void setComplete(Timestamp complete) {
        this.complete = complete;
    }
}