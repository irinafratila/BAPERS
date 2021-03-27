package Customer;

import BapersControl.Main;
import Database.DbDriver;
import Discount.Discount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DowngradeCustomer implements Initializable {

    private Main m;
    private Discount d;

    @FXML
    private TextField customerType;

    @FXML
    private Label updateCustomerMessageLabel;

    public DowngradeCustomer(){
        this.m = new Main();
        this.d = DbDriver.getLastDiscountFromDB();
    }

    public void cancelRegister(ActionEvent event) throws IOException {
        m.changeScene("/Customer/upgradeCustomer.fxml");
    }

    public void downgradeCustomer(){

        try {
            int id = Integer.parseInt(BapersControl.tempCustomerSession.getId());
            //int discountId = d.getDiscountId()+1;
            String isValuable = customerType.getText();
            int discountId = 1;
            DbDriver.updateCustomerType(isValuable, discountId, id);

            customerType.setText("");
            updateCustomerMessageLabel.setText("Customer with id #"+id+" has been downgraded to status "+ isValuable);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerType.setText("normal");
    }
}
