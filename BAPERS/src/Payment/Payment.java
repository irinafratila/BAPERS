package Payment;

public class Payment {
    private final int payment_ID;
    private final int jobID;
    private final int customer_ID;
    private String cashOrCard;
    private double amount;


    public Payment(int payment_ID, int jobID, int customer_ID, String cashOrCard, double amount) {
        this.payment_ID = payment_ID;
        this.jobID = jobID;
        this.customer_ID = customer_ID;
        this.cashOrCard = cashOrCard;
        this.amount = amount;
    }

    public int getPayment_ID() {
        return payment_ID;
    }

    public int getJobID() {
        return jobID;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public String getCashOrCard() {
        return cashOrCard;
    }

    public void setCashOrCard(String cashOrCard) {
        this.cashOrCard = cashOrCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
