package Discount;

public class /**/FixedDiscountPlan{
    private String setUpDate;
    private double discountRate; //the rate will be stored as a percentage (ie 20.0 for a 20% discount)
    private int discountId;
    private int fixedId;


    public FixedDiscountPlan(double discountRate, int discountId, int fixedId) {
        this.discountRate = discountRate;
        this.discountId = discountId;
        this.fixedId = fixedId;
    }


    protected String getSetUpDate() {
        return setUpDate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    protected void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
    }

    protected void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getFixedId() {
        return fixedId;
    }

    public void setFixedId(int fixedId) {
        this.fixedId = fixedId;
    }

}
