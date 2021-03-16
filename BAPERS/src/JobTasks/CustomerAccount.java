package JobTasks;

import java.util.List;
import java.util.ListIterator;

public class CustomerAccount {
    private String name;
    private String phoneNumber;
    private String email;
    private Boolean valuedCustomer;
    private List<Job> jobs;

    public CustomerAccount(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.valuedCustomer = false;
    }
    public void makePayment(int jobId, double amount){
        ListIterator<Job> jobList = jobs.listIterator();
        while (jobList.hasNext()){
            if (jobList.next().getJobId() == jobId){
                if(jobList.next().getPrice() == amount) {
                    System.out.println("Payment was succesful!");
                }
                else if (jobList.next().getPrice()< amount) {
                    System.out.println("You have overpaid, transaction unsuccessful");
                }
                else System.out.println("You have underpaid, please pay the full price.");
                }
                }
            }

    public void createJob(){
        Job newJob = new Job();

        newJob.setJobId();

        jobs.add(newJob);

        //complete the rest

    }
    public void addTask(int jobId, Task t){
        ListIterator<Job> jobList = jobs.listIterator();
        while (jobList.hasNext()){
            if (jobList.next().getJobId() == jobId){
                jobList.next().addTask(t);
            }
        }
    }
    public List<Job> getJobs(){
        return jobs;
    }
    public void upgradeCustomer(){
        if(!valuedCustomer){
            valuedCustomer = true;
            ValuedCustomer upgrade = new ValuedCustomer(name,phoneNumber,email);
        }
    }

}
