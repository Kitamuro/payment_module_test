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
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1); // number represents number of days
            Date yesterday = cal.getTime();
            cal.add(Calendar.DATE, -2);
            Date dayBeforeYesterday = cal.getTime ();
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
                    .date( today)
                    .build();
            orders.add(test);
            if(i%2==0){
                test.setShop ( shop2 );
                test.setShopName ( shop2.getSiteName () );
            }
            if (i % 6 == 0) {
                test.setStatus( TransactionStatus.REFUSED);
                test.setDate ( dayBeforeYesterday );
            }
            if (i % 3 == 0) {
                test.setStatus( TransactionStatus.RESERVED);
                test.setDate ( yesterday );
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

    static List<Commersant> addCommersants(){
        Random random = new Random();
        List<Commersant> commersants = new ArrayList<>();
        List<String> commersantBik = Arrays.asList("ALFAKZKA", "KCJBKZKX", "NURSKZKX");
        List<String> commersantAccount = Arrays.asList("KZ949470398990260769", "KZ678560000005917533", "KZ3584902KZ004474351");
        List<String> commersantBin = Arrays.asList("10140005471", "21040005839", "90140003737");
        List<String> commersantName = Arrays.asList("Jeff", "Sheldon", "Franklin");
        for (int i = 0; i < 10; i++) {
            int randomId = random.nextInt(10);
            Commersant test = Commersant.builder()
                    .id(randomId)
                    .bik(commersantBik.get(random.nextInt(commersantBik.size())))
                    .account(commersantAccount.get(random.nextInt(commersantAccount.size())))
                    .bin(commersantBin.get(random.nextInt(commersantBin.size())))
                    .name(commersantName.get(random.nextInt(commersantName.size())))
                    .build();
            commersants.add(test);
        }
        return commersants;
    }

}
