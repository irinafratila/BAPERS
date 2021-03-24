package Payment;

public class CardDetails {
    private final String last4Digits;
    private final String cardType;
    private final String expiryDate;

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
