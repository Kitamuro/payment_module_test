package school.attractor.payment_module.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.attractor.payment_module.domain.order.OrderRepository;


@Configuration
public class FillData {

    @Bean
    public CommandLineRunner fill(OrderRepository orderRepository) {
        return (args) -> {
            orderRepository.saveAll(GenerateData.addOrders());
        };
    }
}
