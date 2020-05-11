package school.attractor.payment_module.domain.transaction;

public enum TransactionType {
    REFUND (0), PAYMENT (1), HOLD (2), AUTH(3);

    private final int code;

    TransactionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
