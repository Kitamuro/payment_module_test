package school.attractor.payment_module.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.attractor.payment_module.domain.transaction.TransactionRepository;


@Configuration
public class FillData {

    @Bean
    public CommandLineRunner fill(TransactionRepository transactionRepository) {
        return (args) -> {
            transactionRepository.saveAll(GenerateData.addTransaction());
        };

    }
}
