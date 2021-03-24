package Discount;

public class FlexibleDiscountPlan {
    private double price; //the price of a job or a task
    private int range;
    private double rate;
    private int flexiId;
    private int discountId;
    public FlexibleDiscountPlan(int discountId,  int range, double rate, int flexiId) {

        this.range = range;
        this.rate = rate;
        this.flexiId = flexiId;
        this.discountId=discountId;
    }

    public double calculatePrice(double rate) {
        return price * (rate / 100);
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getFlexiId() {
        return flexiId;
    }

    public void setFlexiId(int flexiId) {
        this.flexiId = flexiId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }
}
