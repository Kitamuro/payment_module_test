package school.attractor.payment_module.domain.util;

import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.order.Order;
import school.attractor.payment_module.domain.shop.Shop;
import school.attractor.payment_module.domain.transaction.TransactionStatus;

import java.util.*;


class GenerateData {


    static List<Order> addOrdersForCommersant1(Shop shop1, Shop shop2) {

        Random random = new Random();
        List<Order> orders = new ArrayList<>();
        List<String> userName = Arrays.asList("Artur","Бакытжан","Кирил","Вячеслав","Вор","Test");
        for (int i = 0; i < 30; i++) {
            int randomAmount = random.nextInt(80000);
            int randomId = random.nextInt(5000);
            int randomOrderId = random.nextInt ( 100000 ) + 999999;
            String name = userName.get(random.nextInt(userName.size()));

            Order test = Order.builder()
                    .id(randomId)
                    .orderId (randomOrderId )
                    .shop ( shop1 )
                    .shopName(shop1.getSiteName ())
                    .userName(name)
                    .status( TransactionStatus.APPROVED)
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
                test.setStatus( TransactionStatus.REFUSED);
            }
            if (i % 3 == 0) {
                test.setStatus( TransactionStatus.RESERVED);
                test.setShop ( shop2 );
                test.setShopName ( shop2.getSiteName () );
            }
            if (i % 4 == 0) {
                test.setStatus( TransactionStatus.PARTIAL_REFUND);
            }
            if (i % 5 == 0) {
                test.setStatus( TransactionStatus.TOTAL_REFUND);
            }
        }
        return orders;
    }

    static List<Order> addOrdersForCommersant2(Shop shop1, Shop shop2) {

        Random random = new Random();
        List<Order> orders = new ArrayList<>();
        List<String> userName = Arrays.asList("Artur","Бакытжан","Кирил","Вячеслав","Вор","Test");
        for (int i = 0; i < 30; i++) {
            int randomAmount = random.nextInt(80000);
            int randomId = random.nextInt(5000);
            int randomOrderId = random.nextInt ( 100000 ) + 999999;
            String name = userName.get(random.nextInt(userName.size()));

            Order test = Order.builder()
                    .id(randomId)
                    .orderId (randomOrderId )
                    .shop ( shop1 )
                    .shopName(shop1.getSiteName ())
                    .userName(name)
                    .status( TransactionStatus.APPROVED)
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
                test.setStatus( TransactionStatus.REFUSED);
            }
            if (i % 3 == 0) {
                test.setStatus( TransactionStatus.RESERVED);
                test.setShop ( shop2 );
                test.setShopName ( shop2.getSiteName () );
            }
            if (i % 4 == 0) {
                test.setStatus( TransactionStatus.PARTIAL_REFUND);
            }
            if (i % 5 == 0) {
                test.setStatus( TransactionStatus.TOTAL_REFUND);
            }
        }
        return orders;
    }

}
