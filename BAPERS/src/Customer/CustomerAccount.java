
package Customer;

import Database.DbDriver;
import Discount.Discount;
import JobTasks.Job;
import JobTasks.Task;
import JobTasks.TasksJobs;

import java.util.*;
/**
 * @author Muhammad Masum Miah
 */


import static JobTasks.Main.searchTask;

public class CustomerAccount {
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
    private int discountId;
    Scanner sc = new Scanner(System.in);
    double rate;


    public CustomerAccount(int id, String customerName, String title, String firstName, String lastName, String address, String postcode, String city, String phoneNumber, String email, Boolean v, int discountId) {
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
        this.discountId = discountId;
        this.jobs = new LinkedList<>();
    }

    //experimenting adding tasks with the help of task ids.
//    Scanner sc = new Scanner(System.in);

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }


    //Record payment data into the database, once the right amount is paid.
    public void makePayment(int jobId, float amount, String cashOrCard, String cardType, String expiry, int lastDigits) {
        Job searchedJob = searchJob(jobId);
        if (searchedJob.getPrice() == amount) {
            System.out.println("Payment was succesful!");
            DbDriver.insertPaymentHistory(searchedJob.getJobId(), searchedJob.getCustomerId(), cashOrCard, cardType, expiry, lastDigits, amount);
        } else if (searchedJob.getPrice() < amount) {
            System.out.println("You have overpaid, transaction unsuccessful");
        } else System.out.println("You have underpaid, please pay the full price.");
    }

    //Method to insert tasks for the job, as job_id is stored in the database,
    public static Job searchJobCreate() {
        List<Job> jobs = DbDriver.queryJobs();

        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        }
        return jobs.get(jobs.size() - 1);
    }

    // method to search the jobs table in the database.
    public static Job searchJob(int jobId) {
        List<Job> jobs = DbDriver.queryJobs();

        if (jobs == null) {
            System.out.println("No Jobs");
            return null;
        } else {
            for (Job j : jobs) {
                if (j.getJobId() == jobId) {
                    return j;
                }
            }
            return null;
        }

    }


    //After searching a customer, they are able to create jobs. This will also be stored into the database.
    public void createJob(int staffId, int priority, String specialInstructions, List<Task> newTasks) {
        Job job = new Job(priority, specialInstructions, newTasks);
        DbDriver.insertJob(getCustomerId(), job.getPriority(), job.getSpecialInstructions(), job.getStartTime(), job.getDeadline(), staffId, job.getPrice());
        Job searchedJob = searchJobCreate();
        System.out.println(job.getJobId());
        for (Task t : job.getTasks())
            DbDriver.insertTasksAvailableJobs(t.getTaskId(), searchedJob.getJobId());
        jobs.add(job);
    }

    public void deleteJob(int id) {
        DbDriver.removeJob(id);
        DbDriver.removeTasksByJob(id);
    }


    //Search through job tasks from the database.
    public static TasksJobs searchTasksJobs(int id) {
        List<TasksJobs> jobs = DbDriver.queryTasksJobs();

        if (jobs == null) {
            System.out.println("No Tasks in this job");
            return null;
        } else {
            for (TasksJobs j : jobs) {
                if (j.getJobId() == id) {
                    return j;
                }
            }
            return null;
        }

    }


    // Add extra tasks after completing initial order.
    public void addTask(int jobId, int taskId) {
        Job searchedJob = searchJob(jobId);
        Task searchedTask = searchTask(taskId);
        DbDriver.insertTasksAvailableJobs(searchedTask.getTaskId(), searchedJob.getJobId());
    }

    //Remove tasks from the job.
    public void removeTask(int id) {
        DbDriver.removeTasks(id);
    }


    //Update the customer type to either normal or valuable adjusting the discounts alongside.
    public void updateCustomerType(String isValuable, String type) {

        Discount d = searchDiscount();
        int discountId = d.getDiscountId()+1;
        double rate;
        if (isValuable.toLowerCase().equals("valuable")) {
            this.isValuable = true;
            if (type.toLowerCase().equals("flexi")) {
                applyFlexiDiscount();
            } else if (type.toLowerCase().equals("fixed")) {
                applyFixedDiscount();
            } else if (type.toLowerCase().equals("variable")) {
                applyVariableDiscount();
            } else {
                this.isValuable = false;
                this.discountId = 0;
            }
        }
        DbDriver.insertDiscount("type");
        DbDriver.updateCustomerType(isValuable, discountId, getCustomerId());
    }

    public static Discount searchDiscount() {
        List<Discount> discounts = DbDriver.queryDiscounts();

        if (discounts == null) {
            System.out.println("No Jobs");
            return null;
        }
        return discounts.get(discounts.size()-1);
    }

    public void applyFixedDiscount() {
        System.out.println("Please type in the rate");
        rate = sc.nextDouble();
        DbDriver.insertDiscount("Fixed");
        //TODO search discount for the last dId.
        DbDriver.insertFixedDiscount(rate, discountId);
    }

    public void applyVariableDiscount() {
        int taskId;
        DbDriver.insertDiscount("Variable");
        //TODO search discount for the last dId.
        while (true) {
            System.out.println("Please type in the task id");
            taskId = sc.nextInt();
            System.out.println("Please type in the rate");
            rate = sc.nextDouble();
            DbDriver.insertVariableDiscount(rate, discountId, taskId);
        }
    }
    public void applyFlexiDiscount() {
        Map<Integer, Double> ranges = new HashMap<>();
        int range;
        DbDriver.insertDiscount("Flexi");
        while (true) {

            System.out.println("Please type in end of range");
            range = sc.nextInt();
            if(range == 0){
                break;
            }
            System.out.println("Please type in the rate");
            rate = sc.nextDouble();
            ranges.put(range, rate);

            //TODO search discount for the last dId.
            DbDriver.insertFlexibleDiscount(rate, discountId, range);
        }
    }

    //Getters and Setters

    public List<Task> taskList() {
        List<Task> tasks = new LinkedList<>();
        return tasks;
    }

    public List<Job> getJobs() {
        return jobs;
    }

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

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}