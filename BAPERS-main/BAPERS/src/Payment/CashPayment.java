package Payment;

import BapersControl.Main;
import Customer.CustomerAccount;
import Database.DbDriver;
import JobTasks.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CashPayment implements Initializable {

    private BapersControl.Main m;

    private int JobId;
    private Double Amount;
    private String CashOrCard;


    @FXML
    private  TextField jobID,amount;



    public CashPayment(){
        this.m = new Main();
        int id = BapersControl.tempJobSession.getId();
        Job searchedJob = DbDriver.searchJobs(id);
        this.JobId = searchedJob.getJobId();
        this.Amount =  searchedJob.getPrice();
        this.CashOrCard = "cash";


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobID.setText(String.valueOf(this.JobId));
        amount.setText(String.valueOf(this.Amount));

    }


    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Payment/paymentType.fxml");
    }


    public void MakePayment() throws IOException, SQLException {
        System.out.println("call mnake cash payment method ");

        this.JobId = Integer.parseInt(jobID.getText());
        this.Amount = Double.parseDouble(amount.getText());
        this.CashOrCard = "cash";
//        m.changeScene("/Payment/viewInvoice.fxml");

//       CustomerAccount.makeCardPayment(JobId,Amount,CashOrCard,CardType,Expiry,LastDigits);
        CustomerAccount.makeCashPayment(JobId,Amount);

        //make sure to update current status which is not curently done

        jobID.clear();
        amount.clear();

        System.out.println(JobId+" - "+Amount+" - "+CashOrCard);

    }


}
