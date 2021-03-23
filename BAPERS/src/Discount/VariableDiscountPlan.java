package Discount;

public class VariableDiscountPlan {
    private double price; //the price of each task

    public VariableDiscountPlan() {
    }

    public VariableDiscountPlan(double price) {
        this.price = price;
    }

    public double calculatePrice(double rate){
        return price*rate;
    }
}
