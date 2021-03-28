//package Payment;
//
//public class PaymentInterface{
//    Payment payment;
//    CardDetails cardDetails;
//
//    public PaymentInterface() {
//    }
//
//
//    public Payment makePayment(int jobID, double amount, String date) {
//        payment = new Payment(jobID, amount, "cash");
//
//        //Here, you'dd add it do the DB
//        return payment;
//    }
//
//    public Payment makeCardPayment(int jobID, double amount, String last4Digits, String cardType, String expiryDate, String date) {
//        payment = new Payment(jobID, amount, "cash");
//        cardDetails = new CardDetails(last4Digits, cardType, expiryDate);
//
//
//        return payment;
//    }
//
//    public void removePayment() {
//
//    }
//
//    public Payment getPayment() {
//        return payment;
//    }
//}
