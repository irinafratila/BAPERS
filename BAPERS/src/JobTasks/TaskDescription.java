
package JobTasks;

public class TaskDescription {
    private static int count;
    private int taskID;
    private String location;
    private String description;
    private int duration;

    public TaskDescription(int taskID, String location, String description, int duration) {
        this.taskID = taskID;
        this.location = location;
        this.description = description;
        this.duration = duration;
    }

    protected int getTaskID() {
        return taskID;
    }

    protected void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    protected String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }
}