package school.attractor.payment_module.util;

import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.registries.Registries;
import school.attractor.payment_module.domain.transaction.Transaction;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.time.LocalDateTime;
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
                    .orderId (randomOrderId )
                    .shopName(shops.get(random.nextInt(shops.size())))
                    .userName(name)
                    .status(TransactionStatus.APPROVED)
                    .amount((randomAmount))
                    .residual(randomAmount)
                    .email("test@test")
                    .cardHolderName(name)
                    .card(1111111111111111L)
                    .exp(01)
                    .exp_year(20)
                    .cvc2(202)
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

//    static List<Registries> addRegistries() {
//
//        Random random = new Random();
//
//        List<Registries> registries = new ArrayList<>();
//
//                            //orders
//        for (int i = 0; i < 30; i++) {
//            Registries test = Registries.builder()
//                    .date(new Date())
//                    .build();
//        }
//
//        return registries;
//    }

    static List<Commersant> addCommersant() {

        Random random = new Random();

        List<String> commersantBik = Arrays.asList("ALFAKZKA", "KCJBKZKX", "NURSKZKX");
        List<String> commersantAccount = Arrays.asList("KZ949470398990260769", "KZ678560000005917533", "KZ3584902KZ004474351");
        List<String> commersantBin = Arrays.asList("10140005471", "21040005839", "90140003737");
        List<String> commersantName = Arrays.asList("Jeff", "Lester", "Franklin");

        List<Commersant> commersants = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int randomId = random.nextInt(10);
            Commersant test = Commersant.builder()
                    .id(randomId)
                    .bik(commersantBik.get(random.nextInt(commersantBik.size())))
                    .account(commersantAccount.get(random.nextInt(commersantAccount.size())))
                    .bin(commersantBin.get(random.nextInt(commersantBin.size())))
                    .bik(commersantName.get(random.nextInt(commersantName.size())))
                    .build();
            commersants.add(test);
        }

        return commersants;
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
