package Customer;

import BapersControl.Main;
import Database.DBConnection;
import Database.DbDriver;
import Discount.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import jdk.internal.jimage.ImageStream;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UpgradeCustomer implements Initializable {


    private Main m;
    private DBConnection conn;
    private Connection connDB;
    private Map<Integer, Double> ranges;
    private Map<Integer, Double> taskDiscounts;
    private Discount d;
//    private String isValuable;
//    private int discountId;

    public UpgradeCustomer(){
        this.m = new Main();
        this.conn = new DBConnection();
        this.connDB = conn.getConnection();
        this.ranges = new HashMap<>();
        this.taskDiscounts = new HashMap<>();
        this.d = DbDriver.getLastDiscountFromDB();
//        this.isValuable = "";
//        this.discountId = 1;
    }

    @FXML
    private Button createAccountButton,deleteAccountButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label updateCustomerMessageLabel, rangeMessage;

    @FXML
    private TextField id,customerType,discountRate,discountRange,taskID;

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Customer/upgradeCustomer.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String custType =BapersControl.tempCustomerSession.getCustomerType();
//        String discount = BapersControl.tempCustomerSession.getDiscountID();
        // try and put discount rate here is any
        System.out.println("tesing upgrade " +custType );
        try {

            // fix this 
             this.customerType.setText("valuable");
//            if (discount != null) this.discountI.setText(discount);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void upgradeCallFixed() throws SQLException {
        String CustomerType = customerType.getText();
        updateCustomerType(CustomerType,"fixed");
    }
    public void upgradeCallFlexi() throws SQLException {
        String CustomerType = customerType.getText();
        updateCustomerType(CustomerType,"flexi");
    }
    public void upgradeCallVariable() throws SQLException {
        String CustomerType = customerType.getText();
        updateCustomerType(CustomerType,"variable");
    }

    public void downgradeCall() throws SQLException {
        String CustomerType = customerType.getText();
        updateCustomerType(CustomerType,"");
    }


    //Update the customer type to either normal or valuable adjusting the discounts alongside.
    public void updateCustomerType(String isValuable, String type) throws SQLException {

        int id = Integer.parseInt(BapersControl.tempCustomerSession.getId());
        int discountId = d.getDiscountId()+1;
        if (isValuable.equalsIgnoreCase("valuable")) {
            if (type.equalsIgnoreCase("flexi")) {
                DbDriver.insertDiscount(type);
                applyFlexiDiscount();
//                applyFlexiDiscount();
            } else if (type.equalsIgnoreCase("fixed")) {
                applyFixedDiscount(type);
            } else if (type.equalsIgnoreCase("variable")) {
                DbDriver.insertDiscount(type);
                applyVariableDiscount();
            } else {
                isValuable = "normal";
                discountId = 1;
            }
        }
        Discount d = DbDriver.getLastDiscountFromDB();
        System.out.println(isValuable +" - " + discountId +" - "+ id);
        DbDriver.updateCustomerType(isValuable, d.getDiscountId(), id);
    }

    public void applyFixedDiscount(String type) throws SQLException {
        try {

            int rate = Integer.parseInt(discountRate.getText());
            DbDriver.insertFixedDiscount(type, rate);

            updateCustomerMessageLabel.setText("User has a fixed discount of "+ rate+"%");
            customerType.setText("");
            discountRate.setText("");
        }catch (Exception e){
            e.printStackTrace();
            updateCustomerMessageLabel.setText("Fixed Discount failed to be applied");
        }
    }


    public void applyVariableDiscount() {
//        int id = Integer.parseInt(BapersControl.tempCustomerSession.getId());
//        String CustomerType = customerType.getText();

        if (taskDiscounts.isEmpty()){
            updateCustomerMessageLabel.setText("Variable Discount needs to be added");
        }else {
            try {
                updateCustomerMessageLabel.setText("Variable Discounts applied");
                discountRate.setText("");
                taskID.setText("");
                customerType.setText("");
                rangeMessage.setText("");

            }catch (Exception e){
                e.printStackTrace();
                updateCustomerMessageLabel.setText("Variable Discount failed to be applied");
            }
        }


    }
    public void addTaskDiscount(){
        int discountId = d.getDiscountId();
        int TaskId = Integer.parseInt(taskID.getText());
        double rate = Double.parseDouble(discountRate.getText());
        taskDiscounts.put(TaskId,rate);
         System.out.println(rate +" - "+discountId +" - " + TaskId);
        DbDriver.insertVariableDiscount( discountId,rate, TaskId);


        discountRate.setText("");
        taskID.setText("");
        rangeMessage.setText("");
    }

    public void confirmTaskDiscount(){

        String message = "";
        StringBuffer output = new StringBuffer(110);

        for (Map.Entry<Integer, Double> entry : taskDiscounts.entrySet()){
            message = "Task ID = '"+ entry.getKey() +"', Rate = '" + entry.getValue();
            output.append(message);
            output.append("\n");
            System.out.println("Task ID = " + entry.getKey() +
                    ", Rate = " + entry.getValue());
        }

        rangeMessage.setText(output.toString());

    }

    public void removeTaskDiscount(){
        int discountId = d.getDiscountId();
        int TaskId = Integer.parseInt(taskID.getText());
        double rate = Double.parseDouble(discountRate.getText());
        taskDiscounts.remove(TaskId,rate);
        DbDriver.removeVariableDiscount(rate, discountId, TaskId);
        discountRate.setText("");
        taskID.setText("");
        rangeMessage.setText("");
    }


//test variable discount


    public  void applyFlexiDiscount() {
//        int id = Integer.parseInt(BapersControl.tempCustomerSession.getId());
//        String CustomerType = customerType.getText();

        if (ranges.isEmpty()){
            updateCustomerMessageLabel.setText("Variable Discount needs to be added");

        }else {
            try {
                updateCustomerMessageLabel.setText("Flexi Discount applied");
                customerType.setText("");
                discountRate.setText("");
                discountRange.setText("");
            }catch (Exception e){
                e.printStackTrace();
                updateCustomerMessageLabel.setText("Flexi Discount failed to be applied");
            }

        }

    }


    public void addRange(){
        int discountId = d.getDiscountId();
        int range = Integer.parseInt(discountRange.getText());
        double rate = Double.parseDouble(discountRate.getText());
        ranges.put(range,rate);
       // System.out.println(rate +" - "+discountId +" - " + range);
        DbDriver.insertFlexibleDiscount(discountId,rate,  range);
        discountRate.setText("");
        discountRange.setText("");
    }

    public void confirmRange(){

        String message = "";
        StringBuffer output = new StringBuffer(110);

        for (Map.Entry<Integer, Double> entry : ranges.entrySet()){
            message = "Range End = '"+ entry.getKey() +"', Rate = '" + entry.getValue();
            output.append(message);
            output.append("\n");
            System.out.println("Range End = " + entry.getKey() +
                    ", Rate = " + entry.getValue());
        }

        rangeMessage.setText(output.toString());

    }

    public void removeRange(){
        int discountId = d.getDiscountId();
        int range = Integer.parseInt(discountRange.getText());
        double rate = Double.parseDouble(discountRate.getText());
        ranges.remove(range,rate);
        DbDriver.removeFlexibleDiscount(rate, discountId, range);
        discountRate.setText("");
        discountRange.setText("");
    }
}
