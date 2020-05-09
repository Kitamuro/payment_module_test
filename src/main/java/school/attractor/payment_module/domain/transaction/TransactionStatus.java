package school.attractor.payment_module.domain.transaction;

public enum  TransactionStatus {
    NULL(0),
    NEW(1),
    RESERVED(2),
    APPROVED(3),
    REFUSED(4),
    PARTIAL_REFUSED(5);


    private final int code;

    TransactionStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

}
