//package JobTasks;
//
//import Admin.User;
//
//import java.sql.Timestamp;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.ListIterator;
//
///**
// * @author Muhammad Masum Miah
// */
//
//public class Job {
//    private static int count;
//    private int priority;
//    private Timestamp deadline;
//    private String status;
//    private Timestamp startTime;
//    private float timeTaken;
//    private User completedBy;
//    private List<Task> tasks;
//    private  double price;
//    private int jobId;
//    private boolean isJobComplete;
//    private String specialInstructions;
//    private Timestamp completeTime;
//
//
//    public Job(int priority, String specialInstructions) {
//        this.priority = priority;
//        this.specialInstructions = specialInstructions;
//        this.jobId = count++;
//        startTime = new Timestamp(System.currentTimeMillis());
//        this.deadline = setDeadline();
//        this.status = "in progress";
//        this.price = calculatePrice();
//        this.isJobComplete = false;
//    }
//    // calculate the deadline based on the priority of the job. 5 as highest priority.
//    public Timestamp setDeadline() {
//        if(priority ==5){
//            deadline = Timestamp.from(startTime.toInstant().plus(1, ChronoUnit.HOURS));
//        }
//        else if(priority ==4){
//            deadline = Timestamp.from(startTime.toInstant().plus(2, ChronoUnit.HOURS));
//        }
//        else if(priority ==3){
//            deadline = Timestamp.from(startTime.toInstant().plus(3, ChronoUnit.HOURS));
//        }
//        else if(priority ==2){
//            deadline = Timestamp.from(startTime.toInstant().plus(6, ChronoUnit.HOURS));
//        }
//        else if(priority ==1){
//            deadline = Timestamp.from(startTime.toInstant().plus(24, ChronoUnit.HOURS));
//        }
//        return deadline;
//    }
//
//
//    public int getJobId() {
//        return jobId;
//    }
//    //If the priority of hte job changes.
//    public void setPriority(int priority) {
//        this.priority = priority;
//        deadline = setDeadline();
//    }
//
//    public void completeJob() {
//        this.completeTime = new Timestamp(System.currentTimeMillis());
//        this.isJobComplete = true;
//        this.status = "Job Complete";
//
//        // TODO: add completed by.
//
//    }
//    // returns duration in hours.
//    public float setTimeTaken() {
//        if(isJobComplete) {
//            timeTaken = ((completeTime.getTime() - startTime.getTime())/1000)/60/60;
//
//            return timeTaken;
//        }else
//            System.out.println("Job is not complete yet");
//        return -1;
//    }
//
//
//
//    public void addTasks(Task t) {
//        this.tasks.add(t);
//    }
//
//    public void removeTask(int taskId){
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while( tasksList.hasNext()){
//            if (tasksList.next().getTaskId() == taskId){
//                tasksList.remove();
//            }
//        }
//    }
//
//    public void updateTask(int taskId,boolean dayShift, int staffId) {
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while (tasksList.hasNext()) {
//            if (tasksList.next().getTaskId() == taskId) {
//                if(tasksList.next().getStatus() == "In Progress")
//                    tasksList.next().completeTask();
//                else if (tasksList.next().getStatus() == "Ready to process") {
//                    tasksList.next().startTask(dayShift, staffId);
//
//                }
//
//            }
//        }
//    }
//
//    public Task retrieveTask(int taskID) {
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while (tasksList.hasNext()) {
//            if (tasksList.next().getTaskId() == taskID) {
//                return tasksList.next();
//            }
//        } return null;
//    }
//
//    public Task setCurrentOperation() {
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while (tasksList.hasNext()) {
//            if (!tasksList.next().checkIfComplete()) {
//                return tasksList.next();
//            }
//        }
//        System.out.println("All jobs are complete!");
//        return null;
//    }
//
//    public String inspectTask(int taskId){
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while (tasksList.hasNext()) {
//            if (tasksList.next().getTaskId() == taskId) {
//                return tasksList.next().getStatus();
//            }
//        }
//        System.out.println("Task not available!");
//        return null;
//    }
//
//
//    //    public Timestamp operationDeadline(){
////
////    }
//    public String retrieveJobStatus(){
//        return status;
//    }
//
//    public double calculatePrice(){
//        ListIterator<Task> tasksList = tasks.listIterator();
//        while (tasksList.hasNext()) {
//            price += tasksList.next().getPrice();
//            //TODO: Set price for valuable customers.
//        }return price;
//
//
//    }
//    public double getPrice(){
//        return price;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public Timestamp getStartTime() {
//        return startTime;
//    }
//
//    public double getTimeTaken() {
//        return timeTaken;
//    }
//    public void setCompletedBy(User completedBy) {
//        this.completedBy = completedBy;
//    }
//
//    public User getCompletedBy() {
//        return completedBy;
//    }
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//
//
//    public Timestamp getCompleteTime() {
//        return completeTime;
//    }
//
//    public boolean isJobComplete() {
//        return isJobComplete;
//    }
//
//    public void setJobComplete(boolean jobComplete) {
//        isJobComplete = jobComplete;
//    }
//
//    public String getSpecialInstructions() {
//        return specialInstructions;
//    }
//
//    public void setSpecialInstructions(String specialInstructions) {
//        this.specialInstructions = specialInstructions;
//    }
//}