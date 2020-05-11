package school.attractor.payment_module.util;

import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.util.*;


class GenerateData {

    static List<Order> addOrders() {

        Random random = new Random();
        List<Order> orders = new ArrayList<>();
        List<String> shops = Arrays.asList("Sulpak", "Все для дома", "Хозяюшка","Технодом","Магнум","Aliexpress","Lamoda");
        List<String> userName = Arrays.asList("Artur","Бакытжан","Кирил","Вячеслав","Вор","Test");
        for (int i = 0; i < 30; i++) {
            int randomAmount = random.nextInt(80000);
            int randomId = random.nextInt(5000);
            int randomOrderId = random.nextInt ( 100000 ) + 999999;
            String name = userName.get(random.nextInt(userName.size()));

            Order test = Order.builder()
                    .id(randomId)
                    .orderId ( String.valueOf ( randomOrderId ) )
                    .shopName(shops.get(random.nextInt(shops.size())))
                    .userName(name)
                    .status(TransactionStatus.APPROVED)
                    .amount((randomAmount))
                    .residual(randomAmount)
                    .email("test@test")
                    .cardHolderName(name)
                    .CARD("1111 1111 1111 1111")
                    .EXP("01")
                    .EXP_YEAR("20")
                    .CVC2("202")
                    .date(new Date())
                    .build();
            orders.add(test);
            if (i % 6 == 0) {
                test.setStatus(TransactionStatus.REFUSED);
            }
            if (i % 3 == 0) {
                test.setStatus(TransactionStatus.RESERVED);
            }
            if (i % 4 == 0) {
                test.setStatus(TransactionStatus.PARTIAL_REFUND);
            }
            if (i % 5 == 0) {
                test.setStatus(TransactionStatus.TOTAL_REFUND);
            }
        }
        return orders;
    }

    static List<Transaction> addTransaction() {

        Random random = new Random();
        List<Transaction> transactions = new ArrayList<>();
        List<String> shops = Arrays.asList("Sulpak", "Все для дома", "Хозяюшка","Технодом","Магнум","Aliexpress","Lamoda");
        List<String> userName = Arrays.asList("Artur","Бакытжан","Кирил","Вячеслав","Вор","Test");
        for (int i = 0; i < 30; i++) {
            int randomAmount = random.nextInt(80000);
            int randomId = random.nextInt(5000);

            Transaction test = Transaction.builder()
                    .amount((randomAmount))
                    .date(new Date())
                    .status(TransactionStatus.APPROVED)
                    .build();
            transactions.add(test);
            if (i % 3 == 0) {
                test.setStatus(TransactionStatus.REFUSED);
            }
        }
        return transactions;
    }
}
