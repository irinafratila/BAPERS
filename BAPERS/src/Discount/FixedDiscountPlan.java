package Discount;

public class FixedDiscountPlan extends Discount{
    private String setUpDate;
    private double discountRate; //the rate will be stored as a percentage (ie 20.0 for a 20% discount)
    private double price; //the price of a job

    public FixedDiscountPlan(int discountId, String description, String setUpDate, double discountRate) {
        super(discountId, description);
        this.setUpDate = setUpDate;
        this.discountRate = discountRate;
    }

    public double calculatePrice() {
        double discountAmount = price * (discountRate/100);
        double afterDiscount = price - discountAmount;
        return afterDiscount;
    }

    protected String getSetUpDate() {
        return setUpDate;
    }

    protected double getDiscountRate() {
        return discountRate;
    }

    protected void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
    }

    protected void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }


}
