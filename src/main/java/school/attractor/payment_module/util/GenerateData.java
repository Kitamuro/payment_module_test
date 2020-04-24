package school.attractor.payment_module.util;

import school.attractor.payment_module.domain.transaction.Transaction;

import java.util.*;


class GenerateData {

    static List<Transaction> addTransaction() {

        Random random = new Random();
        List<Transaction> transactions = new ArrayList<>();
        List<String> shops = Arrays.asList("Sulpak", "Все для дома", "Хозяюшка","Технодом","Магнум","Aliexpress","Lamoda");
        List<String> userName = Arrays.asList("Artur","Бакытжан","Кирил","Вячеслав","Вор","Test");
        for (int i = 0; i < 30; i++) {
            int randomAmount = random.nextInt(80000);
            int randomId = random.nextInt(5000);

            Transaction test = Transaction.builder()
                    .amount(String.valueOf(randomAmount))
                    .CARD("2214-3112-333-0987-5672")
                    .cardHolderName(userName.get(random.nextInt(userName.size())))
                    .CVC2("7800")
                    .EXP("20/20")
                    .email("test@test")
                    .userName(userName.get(random.nextInt(userName.size())))
                    .date(new Date())
                    .shopName(shops.get(random.nextInt(shops.size())))
                    .status("Подтвержден")
                    .orderId(String.valueOf(randomId))
                    .build();
            transactions.add(test);
            if (i % 3 == 0) {
                test.setStatus("Отклонен");
            }
        }
        return transactions;
    }
}
