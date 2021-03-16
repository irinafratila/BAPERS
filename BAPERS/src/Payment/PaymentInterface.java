package Payment;

public class PaymentInterface{
    Transaction payment;
    CardDetails cardDetails;

    public PaymentInterface() {
    }


    public Transaction makePayment(int jobID, double amount, String date) {
        payment = new Transaction(jobID, amount, "cash");

        //Here, you'dd add it do the DB
        return payment;
    }

    public Transaction makeCardPayment(int jobID, double amount, String last4Digits, String cardType, String expiryDate, String date) {
        payment = new Transaction(jobID, amount, "cash");
        cardDetails = new CardDetails(last4Digits, cardType, expiryDate);


        return payment;
    }

    public void removePayment() {

    }

    public Transaction getPayment() {
        return payment;
    }
}
