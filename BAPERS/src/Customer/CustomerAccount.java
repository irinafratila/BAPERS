
package Customer;

import Database.DbDriver;
import Discount.Discount;
import JobTasks.Job;
import JobTasks.Task;
import Discount.*;

import java.sql.SQLException;
import java.util.*;

import static Database.DbDriver.searchTask;

/**
 * @author Muhammad Masum Miah
 */

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
    private String customerType;
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
        if(isValuable){
            customerType = "valauble";
        }else {
            customerType = "normal";
        }
        this.discountId = discountId;
        this.jobs = new LinkedList<>();
    }

    //Record payment data into the database, once the right amount is paid.
    public void makePayment(int jobId, float amount, String cashOrCard, String cardType, String expiry, int lastDigits) throws SQLException {
        Job searchedJob = DbDriver.searchJobs(jobId);
        if (searchedJob.getPrice() == amount) {
            System.out.println("Payment was succesful!");
            DbDriver.insertPaymentHistory(searchedJob.getJobId(), searchedJob.getCustomerId(), cashOrCard, cardType, expiry, lastDigits, amount);
        } else if (searchedJob.getPrice() < amount) {
            System.out.println("You have overpaid, transaction unsuccessful");
        } else System.out.println("You have underpaid, please pay the full price.");
    }

    //After searching a customer, they are able to create jobs. This will also be stored into the database.
    public void createJob(int staffId, int priority, String specialInstructions, List<Task> newTasks) throws SQLException {

        Job job = new Job(priority, specialInstructions, newTasks);
        double newPrice =0;
        DbDriver.insertJob(getCustomer_name(),getTitle(),getFirstName(),getLastName(),getAddress(),getCity(),getPostcode(),getEmail(),getPhoneNumber(),getCustomerType(),getCustomerId(), job.getPriority(), job.getSpecialInstructions(), job.getStartTime(), job.getDeadline(), staffId, job.getPrice());
        Job searchedJob = DbDriver.searchJobJustCreated();// Will return  current job created from database.
        Discount d = DbDriver.getDiscount(getDiscountId());
        double rate;
        //Validate the customer discount price in order to apply correct price with discount.
        if(d!=null) {
            if (d.getDescription().equalsIgnoreCase("Flexi")) {
                LinkedList<Double> rates = new LinkedList<>();
                List<FlexibleDiscountPlan> flexi = DbDriver.queryFlexiDiscounts();
                for (FlexibleDiscountPlan f : flexi) {
                    if (f.getDiscountId() == getDiscountId()) {
                        if (job.getPrice() < f.getRange()) {// This will give the applicable discount rate depending on the price.
                            double getRate = f.getRate();
                            rates.add(getRate);
                        }
                    }
                }
                if (rates.size() > 0) {//Get the last rate.
                    rate = rates.get(rates.size() - 1);
                    newPrice = job.getPrice() - (job.getPrice() * rate / 100);
                    newPrice *= (1 + (job.getVat() / 100));//apply VAT on the new price.
                    newPrice *= (1 + (job.getPriorityRate() / 100)); //Adjust the price according to the priority.
                    job.setPrice(newPrice);
                    DbDriver.updateJobPrice(newPrice, searchedJob.getJobId());
                }
                //TODO update on database

            } else if (d.getDescription().equalsIgnoreCase("Fixed")) {
                List<FixedDiscountPlan> fixed = DbDriver.queryFixedDiscounts();
                List<Double> rates = new LinkedList<>();
                for (FixedDiscountPlan f : fixed) {
                    if (f.getDiscountId() == getDiscountId()) {
                        rate = f.getDiscountRate();

                        rates.add(rate);
                    }
                }
                if (rates.size() > 0) {
                    rate = rates.get(rates.size() - 1);
                    newPrice = job.getPrice() - (job.getPrice() * (rate / 100));
                    newPrice *= (1 + (job.getVat() / 100));
                    newPrice *= (1 + (job.getPriorityRate() / 100)); //Adjust the price according to the priority.
                    job.setPrice(newPrice);
                    DbDriver.updateJobPrice(newPrice, searchedJob.getJobId());
                }

            } else if (d.getDescription().equalsIgnoreCase("Variable")) {
                List<VariableDiscountPlan> variable = DbDriver.queryVariableDiscounts();
                Map<Integer, Double> rates = new HashMap<>();
                for (VariableDiscountPlan f : variable) {
                    for (Task t : job.getTasks()) {
                        if (f.getDiscountId() == getDiscountId() && f.getTaskId() == t.getTaskId()) {
                            int tId = t.getTaskId();
                            rate = f.getRate();
                            rates.put(tId, rate);
                        }
                    }
                }
                if (rates.size() > 0) {
                    for (Task t : job.getTasks())
                        if (rates.containsKey(t.getTaskId())) {
                            double taskPrice = t.getPrice();
                            rate = rates.get(t.getTaskId());
                            double discount = (taskPrice * (rate / 100));
                            newPrice = newPrice + (taskPrice - discount);
                        }
                    newPrice *= (1 + (job.getVat() / 100));
                    newPrice *= (1 + (job.getPriorityRate() / 100)); //Adjust the price according to the priority.
                    DbDriver.updateJobPrice(newPrice, searchedJob.getJobId());
                }
            }
        }
            else {
                newPrice = job.getPrice()*(1+(job.getVat()/100));
                newPrice *= (1+(job.getPriorityRate()/100)); //Adjust the price according to the priority.
                DbDriver.updateJobPrice(newPrice, searchedJob.getJobId());
            }


                for (Task t : job.getTasks())// Add the requested tasks onto the database.
                    DbDriver.insertTasksAvailableJobs(t.getTaskId(), searchedJob.getJobId());
                jobs.add(job);
                DbDriver.generateInvoice();
            }


    public void deleteJob(int id) {
        DbDriver.removeJob(id);
        DbDriver.removeTasksByJob(id);
    }


    // Add extra tasks after completing initial order.
    public void addTask(int jobId, int taskId) throws SQLException {
        Job searchedJob = DbDriver.searchJobs(jobId);
        Task searchedTask = searchTask(taskId);
        DbDriver.insertTasksAvailableJobs(searchedTask.getTaskId(), searchedJob.getJobId());
    }

    //Remove tasks from the job.
    public void removeTask(int id) {
        DbDriver.removeTasks(id);
    }


    //Update the customer type to either normal or valuable adjusting the discounts alongside.
    public void updateCustomerType(String isValuable, String type) throws SQLException {

        Discount d = DbDriver.searchLastDiscountId();
        int discountId = d.getDiscountId()+1;
        if (isValuable.equalsIgnoreCase("valuable")) {
            this.isValuable = true;
            if (type.equalsIgnoreCase("flexi")) {

                applyFlexiDiscount();
            } else if (type.equalsIgnoreCase("fixed")) {

                applyFixedDiscount();
            } else if (type.equalsIgnoreCase("variable")) {

                applyVariableDiscount();
            } else {
                this.isValuable = false;
                this.discountId = 1;
            }
        }
        DbDriver.updateCustomerType(isValuable, discountId, getCustomerId());
    }


    public void applyFixedDiscount() throws SQLException {
        System.out.println("Please type in the rate");
        rate = sc.nextDouble();
        Discount d = DbDriver.searchLastDiscountId();
        int discountId = d.getDiscountId();
        DbDriver.insertFixed(d.getDescription(),rate, discountId);
    }
    //need to do a null pointer chaeck.
    public void applyVariableDiscount() throws SQLException {
        int taskId;
        Discount d = DbDriver.searchLastDiscountId();
        int discountId = d.getDiscountId();
        while (true) {
            System.out.println("Please type in the task id");
            taskId = sc.nextInt();
            if(taskId == 0) {
                break;
            }
            System.out.println("Please type in the rate");
            rate = sc.nextDouble();
            DbDriver.insertVariable(d.getDescription(),rate,  taskId);
        }
    }
    public void applyFlexiDiscount() throws SQLException {
        Map<Integer, Double> ranges = new HashMap<>();
        int range;
        Discount d = DbDriver.searchLastDiscountId();
        int discountId = d.getDiscountId();
        while (true) {
            System.out.println("Please type in end of range");
            range = sc.nextInt();
            if(range == 0){
                break;
            }
            System.out.println("Please type in the rate");
            rate = sc.nextDouble();
            ranges.put(range, rate);
            DbDriver.insertFlexible(d.getDescription(),rate,  range);
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
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}