package JobTasks;

public class TaskDescription {
    private int TaskID;
    private String location;
    private String descriptin;
    private int duration;

    public TaskDescription(int taskID, String location, String descriptin, int duration) {
        TaskID = taskID;
        this.location = location;
        this.descriptin = descriptin;
        this.duration = duration;
    }

    protected int getTaskID() {
        return TaskID;
    }

    protected void setTaskID(int taskID) {
        TaskID = taskID;
    }

    protected String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    protected String getDescriptin() {
        return descriptin;
    }

    protected void setDescriptin(String descriptin) {
        this.descriptin = descriptin;
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }
}
