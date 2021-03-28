package Discount;

public class Discount {
    private int discountId;
    private String description;

    public Discount(int discountId, String description) {
        this.discountId = discountId;
        this.description = description;
    }

    public double calculatePrice() {
        return 0;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
