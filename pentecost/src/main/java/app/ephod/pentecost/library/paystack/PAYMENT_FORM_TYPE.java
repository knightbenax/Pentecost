package app.ephod.pentecost.library.paystack;

public enum PAYMENT_FORM_TYPE {
    CARD(0),
    BANK(1);

    private final int value;

    PAYMENT_FORM_TYPE(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
