package Payment;

public class Transaction {
    private final int jobID;
    private double amount;
    private final String typeOfPayment;


    public Transaction(int jobID,double amount, String typeOfPayment) {
        this.jobID = jobID;
        this.amount = amount;
        this.typeOfPayment = typeOfPayment;
    }


    protected double getAmount() {
        return amount;
    }

    protected void setAmount(double amount) {
        this.amount = amount;
    }


    protected String getTypeOfPayment() {
        return typeOfPayment;
    }

}
