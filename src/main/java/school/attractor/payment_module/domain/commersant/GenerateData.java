package school.attractor.payment_module.domain.commersant;

import school.attractor.payment_module.domain.transaction.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateData {


    public static List<Transaction> addTransaction() {
        List<Transaction> transactions = new ArrayList<>();
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

        Transaction test1 = Transaction.builder()
                .amount("5000")
                .CARD("111-333-4444-3333-3121")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .date(new Date())
                .shopName("Технодом")
                .status("Подтвержден")
                .orderId("1234")
                .build();

        Transaction test2 = Transaction.builder()
                .amount("50000")
                .CARD("333-333-333-333-333")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .date(new Date())
                .shopName("Suplak")
                .status("Возврат полностью")
                .orderId("45421")
                .build();

        Transaction test3 = Transaction.builder()
                .amount("50000")
                .CARD("333-333-333-333-333")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .date(new Date())
                .shopName("Suplak")
                .status("Отказ")
                .orderId("45421")
                .build();

        Transaction test4 = Transaction.builder()
                .amount("380000")
                .CARD("333-333-333-333-333")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .date(new Date())
                .shopName("Pulser")
                .status("Подтвержден")
                .orderId("6666")
                .build();

        Transaction test5 = Transaction.builder()
                .amount("650000")
                .CARD("333-333-333-333-333")
                .cardHolderName("Test Name")
                .CVC2("333")
                .EXP("20/20")
                .email("test@test")
                .userName("test")
                .date(new Date())
                .shopName("Aliexpress")
                .status("Подтвержден")
                .orderId("81722")
                .build();

        transactions.add( test);
        transactions.add( test1);
        transactions.add( test2);
        transactions.add( test3);
        transactions.add( test4);
        transactions.add( test5);

        return transactions;

    }
}
