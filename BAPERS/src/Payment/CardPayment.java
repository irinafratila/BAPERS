package Payment;

public class CardPayment extends Payment {
    private final String cardType;
    private final String expiryDate;
    private final String last4Digits;

    public CardPayment(int payment_ID, int jobID, int customer_ID, String cashOrCard, double amount, String cardType, String expiryDate, String last4Digits) {
        super(payment_ID, jobID, customer_ID, cashOrCard, amount);
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.last4Digits = last4Digits;
    }




    protected String getLast4Digits() {
        return last4Digits;
    }

    protected String getCardType() {
        return cardType;
    }

    protected String getExpiryDate() {
        return expiryDate;
    }
}
