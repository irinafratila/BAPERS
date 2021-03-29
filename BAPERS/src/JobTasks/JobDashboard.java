package JobTasks;

import BapersControl.Main;

public class JobDashboard {

    private BapersControl.Main m;

    public JobDashboard(){
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

    public void changeSceneCreateJob() throws Exception{
        try {
            m.changeScene("/JobTasks/searchCustomer.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneUpdateJob() throws Exception{
        try {
            m.changeScene("/JobTasks/updateJob.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void changeSceneDeleteJob() throws Exception{
        try {
            m.changeScene("/JobTasks/deleteJob.fxml");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}
