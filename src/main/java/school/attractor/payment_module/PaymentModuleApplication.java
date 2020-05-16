package school.attractor.payment_module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.attractor.payment_module.domain.commersant.CommersantController;

@SpringBootApplication
public class PaymentModuleApplication {

    private static Logger logger = LoggerFactory.getLogger(CommersantController.class);

    public static void main(String[] args) {
        logger.info("log");
        SpringApplication.run(PaymentModuleApplication.class, args);
    }

}
