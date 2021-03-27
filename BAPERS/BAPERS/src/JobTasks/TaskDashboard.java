package JobTasks;

import BapersControl.Main;

public class TaskDashboard {


    private BapersControl.Main m;

    public TaskDashboard(){
        this.m = new Main();
    }

    public void changeSceneHome() throws Exception{
        try {
            m.changeScene("/JobTasks/jobTaskDashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void changeSceneCreateTask() throws Exception{
        try {
            m.changeScene("/JobTasks/createTask.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneUpdateTask() throws Exception{
        try {
            m.changeScene("/JobTasks/updateTask.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneDeleteTask() throws Exception{
        try {
            m.changeScene("/JobTasks/deleteTask.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
