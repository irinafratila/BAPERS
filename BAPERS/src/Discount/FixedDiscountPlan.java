package Discount;

public class FixedDiscountPlan extends Discount{
    private String setUpDate;
    private double discountRate; //the rate will be stored as a percentage (ie 20.0 for a 20% discount)
    private double price; //the price of a job

    public FixedDiscountPlan(double rate, String setUpDate){
        discountRate = rate;
        this.setUpDate = setUpDate;
    }

    public double calculatePrice() {
        return price * (discountRate / 100);
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
