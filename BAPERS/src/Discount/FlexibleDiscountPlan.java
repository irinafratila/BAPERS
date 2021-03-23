package Discount;

public class FlexibleDiscountPlan extends Discount{
    private double price; //the price of a job or a task

    public FlexibleDiscountPlan(int discountId, String description) {
        super(discountId, description);
    }

    public double calculatePrice(double rate) {
        return price * (rate / 100);
    }
}
