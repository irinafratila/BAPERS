package Alerts;

public class PaymentAlert extends  Thread {
    private int job_id =-1;
    private int account = -1;
    private String cname =null;

    @Override
    public void run() {
        boolean flag = true;

        while (flag) {

            System.out.println("job ID: " + job_id + " Customer ID: " + getAccount() + " Customer Name: " + getCname() + " has passed thier payment deadline.");
            flag = false;


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