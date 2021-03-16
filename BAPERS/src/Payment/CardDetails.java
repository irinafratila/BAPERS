package Payment;

public class CardDetails {
    private String last4Digits;
    private String cardType;
    private String expiryDate;

    public CardDetails(String _last4Digits, String _cardType, String _expiryDate) {
        last4Digits = _last4Digits;
        cardType = _cardType;
        expiryDate = _expiryDate;

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
