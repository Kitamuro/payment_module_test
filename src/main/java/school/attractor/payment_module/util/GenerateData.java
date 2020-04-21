package school.attractor.payment_module.util;

import school.attractor.payment_module.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class GenerateData {

    static List<Transaction> addTransaction() {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Transaction test = Transaction.builder()
                    .amount("100")
                    .CARD("2214-3112-333-0987-5672")
                    .cardHolderName("Test Name")
                    .CVC2("7800")
                    .EXP("20/20")
                    .email("test@test")
                    .userName("test")
                    .date(new Date())
                    .shopName("Все для дома")
                    .status("Подтвержден")
                    .orderId("1277")
                    .build();
            transactions.add(test);
        }

        return transactions;

    }
}
