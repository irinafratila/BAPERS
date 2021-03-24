package JobTasks;


import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Muhammad Masum Miah
 */

public class Job {
    private int priority;
    private Timestamp deadlineTimeStamp;
    private String deadline;
    private String status;
    private Timestamp startTimeStamp;
    private String startTime;
    private double timeTaken;
    private int startedBy;
    private List<Task> tasks;
    private double price ;
    private int jobId;
    private boolean isJobComplete;
    private String specialInstructions;
    private Timestamp completeTimeStamp;
    private String completeTime;
    int customerId;
    int hours;
    int completedBy;
    private List<TasksJobs> tasksJobs;
    double vat = 20;
    double priorityRate;


    // Constructor for the Job class.

    public Job(int jobId, int account,int priority, String specialInstructions,String status,String start,String deadline,String completeTime,int hours,int startedBy, double price, int completedBy) {

        this.customerId = account;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.startTime = start;
        this.startedBy = startedBy;
        this.hours = hours;
        this.price = price;
        this.jobId = jobId;
        this.specialInstructions = specialInstructions;
        this.completeTime = completeTime;
        this.completedBy = completedBy;
    }

    public Job(int priority, String specialInstructions, List<Task> tasks) {
        this.priority = priority;
        this.specialInstructions = specialInstructions;
        startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.deadlineTimeStamp = setDeadline();
        this.deadline = deadlineTimeStamp.toString();
        this.status = "In progress";
        this.tasks = tasks;
        this.price = calculatePrice();
        this.isJobComplete = false;
        this.startTime =startTimeStamp.toString();
    }

    public double getPriorityRate() {
        return priorityRate;
    }

    public void setPriorityRate(double priorityRate) {
        this.priorityRate = priorityRate;
    }

    // Calculate the deadline based on the priority of the job. 5 as highest priority.
    public Timestamp setDeadline() {
        if(priority ==5){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(1, ChronoUnit.HOURS));
            priorityRate =100;
        }
        else if(priority ==4){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(2, ChronoUnit.HOURS));
            priorityRate = 75;
        }
        else if(priority ==3){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(3, ChronoUnit.HOURS));
            priorityRate = 50;
        }
        else if(priority ==2){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(6, ChronoUnit.HOURS));
            priorityRate = 25;
        }
        else if(priority ==1){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(24, ChronoUnit.HOURS));
            priorityRate = 0;
        }
        return deadlineTimeStamp;
    }

    //If the priority of the job changes.
    public void setPriority(int priority) {
        this.priority = priority;
        deadlineTimeStamp = setDeadline();
    }

    // Helper method Check to see if all tasks are complete.
    public boolean completeJobTasksCheck() {
        ListIterator<TasksJobs> check = tasksJobs.listIterator();
        while (check.hasNext()) {
            if (!check.next().getIsComplete().equals("yes")) {
                System.out.println("Task \"" + check.next().getTaskJobId() + "\" is not complete!");
                return false;
            }

        }return true;
    }
    // Helper method Check to see if all tasks are complete.
    public boolean completeJobCheck(List<Job> jobs) {
        ListIterator<Job> check = jobs.listIterator();
        while (check.hasNext()) {
            if (check.next().getCompleteTime().equalsIgnoreCase("null")) {
                System.out.println("Task \"" + check.next().getJobId() + "\" is not complete!");
                return false;
            }

        }return true;
    }

    //Complete the job.
    public void completeJob(int user){
        if(completeJobTasksCheck()){
            this.completeTimeStamp = new Timestamp(System.currentTimeMillis());
            this.completeTime  =completeTimeStamp.toString();
            this.isJobComplete = true;
            this.status = "Job Complete";
            this.completedBy = user;
            this.timeTaken = setTimeTaken();
        }
        else{
            System.out.println("Job cannot be completed!");
        }

       // TODO: add completed by.

    }
    // returns duration in hours.
    public double setTimeTaken() {

        timeTaken = ((completeTimeStamp.getTime() - startTimeStamp.getTime())/1000)/60/60;
        return timeTaken;

    }

    //Sets the current task in operation.
    public boolean setCurrentOperation(String dayOrNight, int id) {
        ListIterator<TasksJobs> tasksList = tasksJobs.listIterator();
        while (tasksList.hasNext()) {
            if (!tasksList.next().getIsComplete().equals(("yes"))) {
                tasksList.next().startTask(dayOrNight,id);
                return true;
            }
        }
        System.out.println("All jobs are complete!");
        return false;

    }

    // Manage tasks
    public void addTasks(Task t) {
        this.tasks.add(t);
    }

    public void removeTask(int taskId){
        ListIterator<Task> tasksList = tasks.listIterator();
        while( tasksList.hasNext()){
            if (tasksList.next().getTaskId() == taskId){
                tasksList.remove();
            }
        }
    }
    public Task retrieveTask(int taskID) {
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            if (tasksList.next().getTaskId() == taskID) {
                return tasksList.next();
            }
        } return null;
    }
    public String inspectTask(int taskId){
        ListIterator<TasksJobs> tasksList = tasksJobs.listIterator();
        while (tasksList.hasNext()) {
            if (tasksList.next().getTaskId() == taskId) {
                return tasksList.next().getStatus();
            }
        }
        System.out.println("Task not available!");
        return null;
    }
    public void updateTask(int taskId, String dayShift, int staffId) {
        ListIterator<TasksJobs> tasksList = tasksJobs.listIterator();
        while (tasksList.hasNext()) {
            if (tasksList.next().getTaskId() == taskId) {
                if(tasksList.next().getStatus().equals("In Progress") )
                    tasksList.next().completeTask();
                else if (tasksList.next().getStatus().equals("Ready to process")) {
                    tasksList.next().startTask(dayShift, staffId);

                }

            }
        }
    }

    public double calculatePrice(){
        ListIterator<Task> tasksList = this.tasks.listIterator();
        while (tasksList.hasNext()) {
            price += tasksList.next().getPrice();

     //TODO: Set price for valuable customers.
            }return price ;
    }
    public void alert(){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long leftToDeadline = ((deadlineTimeStamp.getTime() - current.getTime())/1000)/60/60;
        if (leftToDeadline < 50){
            //TODO create an alert
            System.out.println("deadline approaching" );
        }
    }


    //Getters and Setters


    public int getPriority() {
        return priority;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public Timestamp getDeadlineTimeStamp() {
        return deadlineTimeStamp;
    }

    public void setDeadlineTimeStamp(Timestamp deadlineTimeStamp) {
        this.deadlineTimeStamp = deadlineTimeStamp;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public int getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(int startedBy) {
        this.startedBy = startedBy;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public boolean isJobComplete() {
        return isJobComplete;
    }

    public void setJobComplete(boolean jobComplete) {
        isJobComplete = jobComplete;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Timestamp getCompleteTimeStamp() {
        return completeTimeStamp;
    }

    public void setCompleteTimeStamp(Timestamp completeTimeStamp) {
        this.completeTimeStamp = completeTimeStamp;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(int completedBy) {
        this.completedBy = completedBy;
    }


    public List<TasksJobs> getTasksJobs() {
        return tasksJobs;
    }

    public void setTasksJobs(List<TasksJobs> tasksJobs) {
        this.tasksJobs = tasksJobs;
    }

}
