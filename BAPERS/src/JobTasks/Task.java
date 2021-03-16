package JobTasks;

public class Task {
    private String status = "in progress";
    private int TaskID;
    private String startTime;
    private String startDate;
    private int timeTaken = 0;
    private boolean dayShift = false;
    private boolean nightShift = false;
    private String discountType = "none"; //TODO  this will later be changed to the right discount data type instead of String

    public Task(int taskID, String startTime, String startDate, String discountType) {
        TaskID = taskID;
        this.startTime = startTime;
        this.startDate = startDate;
        this.discountType = discountType;
    }
}
