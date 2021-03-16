package JobTasks;

public class ValuedCustomer extends CustomerAccount{
    private Discount discountPlan;

    public ValuedCustomer(String name, String phoneNumber, String email, Boolean valuedCustomer, Discount discountPlan) {
        super(name, phoneNumber, email, valuedCustomer);
        this.discountPlan = discountPlan;
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
    public double calculateCost(){return price;}
}
