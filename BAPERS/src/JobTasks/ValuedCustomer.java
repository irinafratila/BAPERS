package JobTasks;


import Discount.*;

public class ValuedCustomer extends CustomerAccount{
    private Discount discountPlan;

    public ValuedCustomer(String name, String phoneNumber, String email) {
        super(name, phoneNumber, email);


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
