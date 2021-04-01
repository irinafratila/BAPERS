package Alerts;

import Payment.Payment;

import java.util.List;

public class PaymentAlert extends  Thread {
    private int job_id =-1;
    private int account = -1;
    private String cname =null;
//    private List<String>

//    public PaymentAlert(List<String> late){
//
//    }

    @Override
    public void run() {
        boolean flag = true;



        while (true) {

            int i = 0;

            String x = "job ID: " + job_id + " Customer ID: " + getAccount() + " Customer Name: " + getCname() + " has passed thier payment deadline.";
            System.out.println("job ID: " + job_id + " Customer ID: " + getAccount() + " Customer Name: " + getCname() + " has passed thier payment deadline.");
            new AlertSession(x);

//            Thread.sleep();
            try {
                //Will run checks again after 15 minutes.
                Thread.sleep(900000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            int fakeJobid = job_id;

//            if (job_id == fakeJobid){
//                i++;
//            }
//            if (i > 1){
//                flag = false;
//            }

           // flag = false;
        }


    }


    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }



}