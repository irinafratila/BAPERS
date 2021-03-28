package Reports;

public class IndividualPerformanceReport {
    private int staff_id;
    private String name;
    private String role;
    private int taskId;
    private String location;
    private String startTime;
    private String complete;
    private double timeTaken;


    public IndividualPerformanceReport(int staff_id, String name, String role, int taskId, String location, String startTime, String complete, double timeTaken) {
        this.staff_id = staff_id;
        this.name = name;
        this.role = role;
        this.taskId = taskId;
        this.location = location;
        this.startTime = startTime;
        this.complete = complete;
        this.timeTaken = timeTaken;

    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }
}
