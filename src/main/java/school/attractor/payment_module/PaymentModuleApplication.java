package school.attractor.payment_module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class PaymentModuleApplication {

    public static void main(String[] args) {
        log.info("log");
        SpringApplication.run(PaymentModuleApplication.class, args);
    }

}
