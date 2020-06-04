package school.attractor.payment_module.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.attractor.payment_module.domain.order.OrderRepository;
import school.attractor.payment_module.domain.registries.CSVFile;


@Configuration
public class FillData {

    @Bean
    public CommandLineRunner fill(OrderRepository orderRepository) {
        return (args) -> {
            var csvFile = new CSVFile();
            csvFile.creatCSVFile();
            orderRepository.saveAll(GenerateData.addOrders());
        };
    }
}
