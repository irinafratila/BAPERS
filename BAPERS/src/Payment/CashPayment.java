package Payment;

public class CashPayment extends Payment{
    public CashPayment(int payment_ID, int jobID, int customer_ID, String cashOrCard, double amount) {
        super(payment_ID, jobID, customer_ID, cashOrCard, amount);
    }
}
