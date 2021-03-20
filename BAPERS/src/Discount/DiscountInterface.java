package Discount;

import java.util.List;

public class DiscountInterface {
    public void addFlexibleDiscountPlan() {

    }

    public void removeFlexibleDiscountPlan(){

    }

    public FlexibleDiscountPlan getFlexibleDiscountPlan(){
        return new FlexibleDiscountPlan();
    }

    public void addFixedDiscountPlan() {

    }

    public void removeFixedDiscountPlan(){

    }

    public FixedDiscountPlan getFixedDiscountPlan(){
        return new FixedDiscountPlan();
    }

    public void addVaribleDiscountPlan() {

    }

    public void removeVariableDiscountPlan(){

    }

    public VariableDiscountPlan getVariableDiscountPlan(){
        return new VariableDiscountPlan();
    }

    public void addTaskDiscount(List<String> details){

    }

    public void removeTaskDiscount() {

    }

    public TaskDiscount getTaskDiscount() {
        return new TaskDiscount(2);
    }

    public void addJobDiscount(List<String> details){

    }

    public void removeJobDiscount() {

    }

    public JobDiscount getJobDiscount() {
        return new JobDiscount(2);
    }


}
