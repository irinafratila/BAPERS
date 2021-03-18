
package JobTasks;


import Discount.*;

import java.util.List;

public class ValuedCustomer extends CustomerAccount{
    private Discount discountPlan;

    public ValuedCustomer(String title, String firstName, String lastName, String address, String postcode, String city, String phoneNumber, String email,Discount d) {
        super(title, firstName, lastName, address, postcode, city, phoneNumber, email);
        discountPlan =d;
    }

    public void applyFixedDiscount(){
        discountPlan = new FixedDiscount();
    }
    public void applyVariableDiscount(){
        discountPlan = new VariableDiscount();
    }
    public void applyFlexiDiscount(){
        discountPlan = new FlexiDiscount();
    }
    public void downgradeCustomer(){}
//    public double calculateCost(){return price;}
}

