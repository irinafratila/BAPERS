package JobTasks;

import BapersControl.Main;

public class JobTaskDashboard {
    private Main m;

    public JobTaskDashboard(){
        this.m = new Main();
    }


    public void changeSceneHome() throws Exception{
        try {
            m.changeScene("/BapersControl/dashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneJob() throws Exception{
        try {
            m.changeScene("/JobTasks/jobDashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneTask() throws Exception{
        try {
            m.changeScene("/JobTasks/taskDashboard.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



}
