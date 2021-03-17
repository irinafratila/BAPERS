
package JobTasks;

public class TaskDescription {
    private int TaskID;
    private String location;
    private String description;
    private int duration;

    public TaskDescription(int taskID, String location, String description, int duration) {
        TaskID = taskID;
        this.location = location;
        this.description = description;
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
        return description;
    }

    protected void setDescriptin(String description) {
        this.description = description;
    }

    protected int getDuration() {
        return duration;
    }

    protected void setDuration(int duration) {
        this.duration = duration;
    }
}
