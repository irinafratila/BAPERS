//package JobTasks;
//
//import Discount.Discount;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.ListIterator;
//import java.util.Scanner;
//
//public class CustomerAccount {
//    private static int count;
//    private int customerId;
//    private String title;
//    private String firstName;
//    private String lastName;
//    private String address;
//    private String postcode;
//    private String city;
//    private String phoneNumber;
//    private String email;
//    private List<Job> jobs;
//    Scanner sc = new Scanner(System.in);
//
//    public void setCustomerId(int customerId) {
//        this.customerId = customerId;
//    }
//
//    public void setJobs(List<Job> jobs) {
//        this.jobs = jobs;
//    }
//
//    public CustomerAccount(String title, String firstName, String lastName, String address, String postcode, String city, String phoneNumber, String email) {
//        this.title = title;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.postcode = postcode;
//        this.city = city;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.jobs = null;
//        this.customerId = count++;
//    }
//
//    public void makePayment(int jobId, double amount) {
//        ListIterator<Job> jobList = jobs.listIterator();
//        while (jobList.hasNext()) {
//            if (jobList.next().getJobId() == jobId) {
//                if (jobList.next().getPrice() == amount) {
//                    System.out.println("Payment was succesful!");
//                } else if (jobList.next().getPrice() < amount) {
//                    System.out.println("You have overpaid, transaction unsuccessful");
//                } else System.out.println("You have underpaid, please pay the full price.");
//            }
//        }
//    }
//
//    public void createJob(int priority, String instructions){
//        Job newJob = new Job(priority, instructions);
//        //TODO: check how to add multiple tasks.
//        jobs.add(newJob);
//
//
//    }
//    public void addTask(int jobId, Task t) {
//        ListIterator<Job> jobList = jobs.listIterator();
//        while (jobList.hasNext()) {
//            if (jobList.next().getJobId() == jobId) {
//                jobList.next().addTasks(t);
//            }
//        }
//    }
//    public List<Task> taskList() {
//        List<Task> tasks = new LinkedList<>();
//        return tasks;
//    }
//    public List<Job> getJobs() {
//        return jobs;
//    }
//
//    public void upgradeCustomer( Discount d) {
//        ValuedCustomer upgrade = new ValuedCustomer(title,firstName,lastName,address,postcode,city,phoneNumber,email, JobTasks.Discount d);
//        upgrade.setJobs(jobs);
//        int id = getCustomerId();
//        //TODO: remove from database and then re-add the valued customer
//
//    }
//
//    public int getCustomerId() {
//        return customerId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getPostcode() {
//        return postcode;
//    }
//
//    public void setPostcode(String postcode) {
//        this.postcode = postcode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}