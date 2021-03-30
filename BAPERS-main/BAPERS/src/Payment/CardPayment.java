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

public class CardPayment implements Initializable {

    private BapersControl.Main m;

    private int JobId;
    private float Amount;
    private String CashOrCard, CardType, Expiry, LastDigits;


    @FXML
    private  TextField jobID,amount,cardType,last4Digits;

    @FXML
    private DatePicker expiry;


    public CardPayment(){
        this.m = new Main();
        int id = BapersControl.tempJobSession.getId();
        Job searchedJob = DbDriver.searchJobs(id);
        this.JobId = searchedJob.getJobId();
        this.Amount = (float) searchedJob.getPrice();
        this.CashOrCard = "card";
        this.CardType = "";
        this.Expiry = "";
        this.LastDigits= "0";

        System.out.println(id + "and " + searchedJob.getJobId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jobID.setText(String.valueOf(this.JobId));
        amount.setText(String.valueOf(this.Amount));
        cardType.setText("card");

    }


    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Payment/paymentType.fxml");
    }


    public void MakePayment() throws SQLException {
        System.out.println("call make card payment method ");

        this.JobId = Integer.parseInt(jobID.getText());
        this.Amount = Float.parseFloat(amount.getText());
        this.CashOrCard = "card";
        this.CardType = cardType.getText();
        this.Expiry = ((TextField)expiry.getEditor()).getText();
        this.LastDigits= last4Digits.getText();

        CustomerAccount.makeCardPayment(JobId,Amount,CashOrCard,CardType,Expiry,LastDigits);
        //make sure to update current status which is not curently done

        jobID.clear();
        amount.clear();
        cardType.clear();
        expiry.setValue(null);
        last4Digits.clear();
        System.out.println(JobId+" - "+Amount+" - "+CashOrCard+" - "+Expiry+" - "+LastDigits);

    }


}
