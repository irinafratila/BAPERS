
package Customer;

import Discount.Discount;
import Discount.FixedDiscount;
import Discount.FlexiDiscount;
import Discount.VariableDiscount;
import JobTasks.Job;
import JobTasks.Task;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class CustomerAccount {
    private static int count;
    private int customerId;
    private String customer_name;
    private String title;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private String city;
    private String phoneNumber;
    private String email;
    private List<Job> jobs;
    private Boolean isValuable;
    private Discount discountPlan;

    //experimenting adding tasks with the help of task ids.
//    Scanner sc = new Scanner(System.in);

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public CustomerAccount(int id,String customerName, String title, String firstName, String lastName, String address, String postcode, String city, String phoneNumber, String email, Boolean v) {
        this.customer_name = customerName;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.jobs = null;
        this.customerId = id;
        this.isValuable = v;
//        this.discountPlan =d;
        this.jobs = new LinkedList<>();
    }

    public void makePayment(int jobId, double amount) {
        ListIterator<Job> jobList = jobs.listIterator();
        while (jobList.hasNext()) {
            if (jobList.next().getJobId() == jobId) {
                if (jobList.next().getPrice() == amount) {
                    System.out.println("Payment was succesful!");
                } else if (jobList.next().getPrice() < amount) {
                    System.out.println("You have overpaid, transaction unsuccessful");
                } else System.out.println("You have underpaid, please pay the full price.");
            }
        }
    }

        public void createJob(Job job){
            jobs.add(job);
        }



        //TODO: check how to add multiple tasks.



    public void addTask(int jobId, Task t) {
        ListIterator<Job> jobList = jobs.listIterator();
        while (jobList.hasNext()) {
            if (jobList.next().getJobId() == jobId) {
                jobList.next().addTasks(t);
            }
        }
    }
    public List<Task> taskList() {
        List<Task> tasks = new LinkedList<>();
        return tasks;
    }
    public List<Job> getJobs() {
        return jobs;
    }

    public void upgradeCustomer( Discount d) {
       this.isValuable = true;
       this.discountPlan = d;

    }

    public void applyFixedDiscount(){
        discountPlan = new FixedDiscount();
    }
    public void applyVariableDiscount(){
        discountPlan = new VariableDiscount();
    }
    public void applyFlexiDiscount(){
        discountPlan = new FlexiDiscount();
    }
    public void downgradeCustomer(){}

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getValuable() {
        return isValuable;
    }

    public void setValuable(Boolean valuable) {
        isValuable = valuable;
    }

//    public Discount.Discount getDiscountPlan() {
//        return discountPlan;
//    }
//
//    public void setDiscountPlan(Discount.Discount discountPlan) {
//        this.discountPlan = discountPlan;
//    }
}


