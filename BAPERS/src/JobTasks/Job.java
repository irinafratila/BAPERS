package JobTasks;


import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Muhammad Masum Miah
 */

public class Job {
    private static int count;
    private int priority;
    private Timestamp deadlineTimeStamp;
    private String deadline;
    private String status;
    private Timestamp startTimeStamp;
    private String startTime;
    private float timeTaken;
    private int startedBy;
    private List<Task> tasks;
    private float price;
    private int jobId;
    private boolean isJobComplete;
    private String specialInstructions;
    private Timestamp completeTimeStamp;
    private String completeTime;
    int customerId;
    int hours;
    int completedBy;

    // Constructor for the Job class.

    public Job(int jobId, int account,int priority, String specialInstructions,String status,String start,String deadline,String completeTime,int hours,int startedBy, float price, int completedBy) {

        this.customerId = account;
        this.priority = priority;
        this.deadline = deadline;
        this.status = status;
        this.startTime = start;
        this.timeTaken = timeTaken;
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
        this.jobId = count++;
        startTimeStamp = new Timestamp(System.currentTimeMillis());
        this.deadlineTimeStamp = setDeadline();
        this.deadline = deadlineTimeStamp.toString();
        this.status = "In progress";
        this.tasks = tasks;
        this.price = calculatePrice();
        this.isJobComplete = false;
        this.startTime =startTimeStamp.toString();
    }

    // Calculate the deadline based on the priority of the job. 5 as highest priority.
    public Timestamp setDeadline() {
        if(priority ==5){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(1, ChronoUnit.HOURS));
        }
        else if(priority ==4){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(2, ChronoUnit.HOURS));
        }
        else if(priority ==3){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(3, ChronoUnit.HOURS));
        }
        else if(priority ==2){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(6, ChronoUnit.HOURS));
        }
        else if(priority ==1){
            deadlineTimeStamp = Timestamp.from(startTimeStamp.toInstant().plus(24, ChronoUnit.HOURS));
        }
        return deadlineTimeStamp;
    }

    //If the priority of the job changes.
    public void setPriority(int priority) {
        this.priority = priority;
        deadlineTimeStamp = setDeadline();
    }

    // Check to see if all jobs are complete.
    public boolean completeJobCheck() {
        ListIterator<Task> check = tasks.listIterator();
        while (check.hasNext()) {
            if (!check.next().isComplete()) {
                System.out.println("Task \"" + check.next().getDescription() + "\" is not complete!");
                return false;
            }

        }return true;
    }

    //Complete the job.
    public void completeJob(int user){
        if(completeJobCheck()) {
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
    public float setTimeTaken() {

        timeTaken = ((completeTimeStamp.getTime() - startTimeStamp.getTime())/1000)/60/60;
        return timeTaken;

    }

    //Sets the current task in operation.
    public boolean setCurrentOperation(boolean dayOrNight, int id) {
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            if (!tasksList.next().isComplete()) {
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
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            if (tasksList.next().getTaskId() == taskId) {
                return tasksList.next().getStatus();
            }
        }
        System.out.println("Task not available!");
        return null;
    }
    public void updateTask(int taskId,boolean dayShift, int staffId) {
        ListIterator<Task> tasksList = tasks.listIterator();
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


//    public Timestamp operationDeadline(){
//
//    }


    public float calculatePrice(){
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            price += tasksList.next().getPrice();
     //TODO: Set price for valuable customers.
            }return price;


    }


    //Getters and Setters


    public int getPriority() {
        return priority;
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

    public float getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(float timeTaken) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
}
