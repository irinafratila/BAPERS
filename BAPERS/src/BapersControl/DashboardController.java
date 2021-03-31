package BapersControl;

//import Customer.CustomerAccount;
import Database.DBConnection;

import Database.DbDriver;
import JobTasks.Job;
import JobTasks.JobTable;
import Payment.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    @FXML
    private Button button;

    @FXML
    private Button logout;

    @FXML
    private Label welcomeLabel,roleLabel,noAccessLabel,priceLabel,noOfJobsLabel;

    @FXML
    private TableView <JobTable>tableView;

    @FXML
    private TableColumn <JobTable, Integer> colJobID;

    @FXML
    private TableColumn <JobTable, Integer> colStaffID;
    @FXML
    private TableColumn <JobTable, Integer> colPriority;
    @FXML
    private TableColumn <Job, String> colStatus;
    @FXML
    private TableColumn <Job, String> colStart;
    @FXML
    private TableColumn <Job, String> colEnd;
    @FXML
    private TableColumn <Job, Float> colPrice;
    @FXML
    private TableColumn <Job, String> colInstruction;

    private Main m;

    private int totalPrice,noOfJobs;

    public DashboardController(){

        this.m = new Main();
        this.totalPrice = 0;
        this.noOfJobs = 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setText("");
        String username = currentLoginSession.getUsername();
        String role = currentLoginSession.getRole();
        welcomeLabel.setText("Welcome "+ username+",");
        roleLabel.setText("Role: "+ role+"");

        colJobID.setCellValueFactory(new PropertyValueFactory<>("JobID"));
        colStaffID.setCellValueFactory(new PropertyValueFactory<>("StaffID"));
        colPriority.setCellValueFactory(new PropertyValueFactory<>("Priority"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colInstruction.setCellValueFactory(new PropertyValueFactory<>("Instruction"));
        tableView.setItems(observableList  );
        test();
        calulateDetails();

    }


    ObservableList<JobTable> observableList = FXCollections.observableArrayList(
//            new JobTable(12,1,5,"in progress","today","tomorrow", (double) 99.60,"N/A")
    );

    public void test(){
        List<Job> jobs =DbDriver.queryJobs();

        for (Job j : jobs){
            JobTable jt = new JobTable(j.getJobId(),j.getCustomerId(),j.getPriority(),j.getStatus(),j.getStartTime(),j.getDeadlineString(),j.getPrice(),j.getSpecialInstructions());
            tableView.getItems().add(jt);
        }
//        JobTable jt = new JobTable(9,7,1,"in progress","today","tomorrow", (double) 49.60,"N/A");
//        tableView.getItems().add(jt);
    }
    public void show(){
//        List<Job> jobs =DbDriver.queryJobs();
        JobTable jt = new JobTable();
        List <List<String>> arrList = new ArrayList<>();

        for (int i = 0; i <tableView.getItems().size(); ++i){
            jt = tableView.getItems().get(i);
            arrList.add(new ArrayList<>());
            arrList.get(i).add(String.valueOf(jt.getJobID()));
            arrList.get(i).add(""+jt.getStaffID());
            arrList.get(i).add(""+jt.getPriority());
            arrList.get(i).add(""+jt.getStatus());
            arrList.get(i).add(""+jt.getStart());
            arrList.get(i).add(""+jt.getEnd());
            arrList.get(i).add(""+jt.getPrice());
            arrList.get(i).add(""+jt.getInstruction());

        }
        for (int i = 0; i < arrList.size(); ++i){
            for (int j = 0; j < arrList.get(i).size(); ++j){
                System.out.println(arrList.get(i).get(j));

            }

        }

    }

    public void remove(){
        ObservableList<JobTable> allJobs,singleJob;
        allJobs = tableView.getItems();
        singleJob = tableView.getSelectionModel().getSelectedItems();
        singleJob.forEach(allJobs::remove);
    }



    // change scene to login page
    public void userLogOut(ActionEvent event) throws IOException {
//        Main m = new Main();
        m.changeScene("Login.fxml");

    }


    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneUser() throws  IOException{
//        Main m = new Main();
        if(currentLoginSession.getRole().equalsIgnoreCase("Office manager") ){
            m.changeScene("/Admin/user.fxml");

        }else {
            m.changeScene("/BapersControl/noAccess.fxml");
        }
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneCustomer() throws  IOException{
//        Main m = new Main();
        if (currentLoginSession.getRole().equalsIgnoreCase("Office manager") || currentLoginSession.getRole().equalsIgnoreCase("Shift manager") || currentLoginSession.getRole().equalsIgnoreCase( "Receptionist") ){
            m.changeScene("/Customer/customer.fxml");
        }else {
            m.changeScene("/BapersControl/noAccess.fxml");

        }
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneJobs() throws  IOException{
//        Main m = new Main();
        if (currentLoginSession.getRole().equalsIgnoreCase("Office manager") || currentLoginSession.getRole().equalsIgnoreCase("Shift manager") || currentLoginSession.getRole().equalsIgnoreCase("Receptionist") || currentLoginSession.getRole().equalsIgnoreCase("Technician") ){
            m.changeScene("/JobTasks/jobTaskDashboard.fxml");
        }else {
            m.changeScene("/BapersControl/noAccess.fxml");

        }
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeScenePayment() throws  IOException{
//        Main m = new Main();
        if (currentLoginSession.getRole().equalsIgnoreCase("Office manager") || currentLoginSession.getRole().equalsIgnoreCase("Shift manager")|| currentLoginSession.getRole().equalsIgnoreCase( "Receptionist")){
            m.changeScene("/Payment/paymentType.fxml");
        }else {
            m.changeScene("/BapersControl/noAccess.fxml");

        }
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneBackup() throws  IOException{
//        Main m = new Main();
        if (currentLoginSession.getRole().equalsIgnoreCase("Office manager")){
            m.changeScene("BackupDB.fxml");
        }else {
            m.changeScene("/BapersControl/noAccess.fxml");

        }
    }
    //change scene from dashboard to user page where you can create or delete user
    public void changeSceneReport() throws  IOException{
//        Main m = new Main();
        if (currentLoginSession.getRole().equalsIgnoreCase("Office manager")){
            m.changeScene("/Reports/generateReport.fxml");
        }else {
            m.changeScene("/BapersControl/noAccess.fxml");

        }
    }


    public void calulateDetails(){
        List<Job> Jobs = DbDriver.searchAllJobs();
        for(Job j : Jobs){
           totalPrice += j.getPrice();
           noOfJobs++;
        }

        priceLabel.setText(String.valueOf("£"+totalPrice));
        noOfJobsLabel.setText(String.valueOf(noOfJobs));
    }

}




//ADDDDD EMAIL
//    ADDDDD EMAIL
//    ADDDDD EMAIL
//            look at list of colums to add to the db and inout their fake data
//backup db should be in database package