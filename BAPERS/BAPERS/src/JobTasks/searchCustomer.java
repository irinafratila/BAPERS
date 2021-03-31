package JobTasks;


import BapersControl.tempCustomerSession;
import Database.DbDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class searchCustomer {

    private BapersControl.Main m;

    @FXML
    private TextField id;

    @FXML private Label searchCustomerMessageLabel;

    public String idData = "";

    public searchCustomer(){
        this.m = new BapersControl.Main();
    }

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/JobTasks/jobDashboard.fxml");
    }


    public void searchCustomer(ActionEvent event) throws IOException {
        String ID = id.getText();
        this.idData = ID;

        try {

            Boolean result;
            result = DbDriver.searchCustomerAccount(ID);
            // System.out.println("serchuser" + id + result);
            if (result== true){
                searchCustomerMessageLabel.setText("Customer found");
//                BapersControl.Main m= new BapersControl.Main();
                new tempCustomerSession(this.idData);
//                m.changeScene("/Customer/createJob.fxml");
                m.changeScene("/JobTasks/createJob.fxml");
            }else{
                searchCustomerMessageLabel.setText("Customer not found in db ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
