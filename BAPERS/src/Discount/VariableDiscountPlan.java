package Discount;

public class VariableDiscountPlan {

    private int varId;
    private int discountId;
    private int taskId;
    private double rate;


    public VariableDiscountPlan(int varId, int discountId, int taskId, double rate) {
        this.varId = varId;
        this.discountId = discountId;
        this.taskId = taskId;
        this.rate = rate;
    }

    public int getVarId() {
        return varId;
    }

    public void setVarId(int varId) {
        this.varId = varId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }


}
