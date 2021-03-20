package JobTasks;

import Admin.User;

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
    private Timestamp deadline;
    private String status;
    private Timestamp startTime;
    private float timeTaken;
    private User completedBy;
    private List<Task> tasks;
    private double price;
    private int jobId;
    private boolean isJobComplete;
    private String specialInstructions;
    private Timestamp completeTime;

    // Constructor for the Job class.
    public Job(int priority, String specialInstructions, List<Task> tasks) {
        this.priority = priority;
        this.specialInstructions = specialInstructions;
        this.jobId = count++;
        startTime = new Timestamp(System.currentTimeMillis());
        this.deadline = setDeadline();
        this.status = "In progress";
        this.tasks = tasks;
        this.price = calculatePrice();
        this.isJobComplete = false;
    }

    // Calculate the deadline based on the priority of the job. 5 as highest priority.
    public Timestamp setDeadline() {
        if(priority ==5){
            deadline = Timestamp.from(startTime.toInstant().plus(1, ChronoUnit.HOURS));
        }
        else if(priority ==4){
            deadline = Timestamp.from(startTime.toInstant().plus(2, ChronoUnit.HOURS));
        }
        else if(priority ==3){
            deadline = Timestamp.from(startTime.toInstant().plus(3, ChronoUnit.HOURS));
        }
        else if(priority ==2){
            deadline = Timestamp.from(startTime.toInstant().plus(6, ChronoUnit.HOURS));
        }
        else if(priority ==1){
            deadline = Timestamp.from(startTime.toInstant().plus(24, ChronoUnit.HOURS));
        }
        return deadline;
    }

    //If the priority of the job changes.
    public void setPriority(int priority) {
        this.priority = priority;
        deadline = setDeadline();
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
    public void completeJob(User user){
        if(completeJobCheck()) {
            this.completeTime = new Timestamp(System.currentTimeMillis());
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

        timeTaken = ((completeTime.getTime() - startTime.getTime())/1000)/60/60;
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


    public double calculatePrice(){
        ListIterator<Task> tasksList = tasks.listIterator();
        while (tasksList.hasNext()) {
            price += tasksList.next().getPrice();
     //TODO: Set price for valuable customers.
            }return price;


    }


    //Getters and Setters


    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setTimeTaken(float timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setCompletedBy(User completedBy) {
        this.completedBy = completedBy;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setJobComplete(boolean jobComplete) {
        isJobComplete = jobComplete;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public void setCompleteTime(Timestamp completeTime) {
        this.completeTime = completeTime;
    }

    public int getPriority() {
        return priority;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public float getTimeTaken() {
        return timeTaken;
    }

    public User getCompletedBy() {
        return completedBy;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public double getPrice() {
        return price;
    }

    public int getJobId() {
        return jobId;
    }

    public boolean isJobComplete() {
        return isJobComplete;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public Timestamp getCompleteTime() {
        return completeTime;
    }
}
