package school.attractor.payment_module.domain.util;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.attractor.payment_module.domain.commersant.Commersant;
import school.attractor.payment_module.domain.commersant.CommersantRepository;
import school.attractor.payment_module.domain.order.OrderRepository;
import school.attractor.payment_module.domain.shop.Shop;
import school.attractor.payment_module.domain.shop.ShopRepository;

@Configuration
public class FillData {

    @Bean
    public CommandLineRunner fill(PasswordEncoder encoder, CommersantRepository commersantRepository, ShopRepository shopRepository, OrderRepository orderRepository) {
        return (args) -> {
            Commersant commersant1 = Commersant.builder ( )
                    .email ( "commersant1@mail.ru" )
                    .password ( encoder.encode ( "12345678" ) )
                    .build ( );

            Commersant commersant2 = Commersant.builder ( )
                    .email ( "commersant2@mail.ru" )
                    .password ( encoder.encode ( "12345678" ) )
                    .build ( );
            commersantRepository.save (  commersant2);
            commersantRepository.save ( commersant1 );

            Shop sulpak = Shop.builder ( )
                    .siteName ( "Sulpak" )
                    .commersant ( commersant1 )
                    .build ( );
            Shop technodom = Shop.builder ( )
                    .siteName ( "Технодом" )
                    .commersant ( commersant1 )
                    .build ( );

            Shop aliexpress = Shop.builder ( )
                    .siteName ( "Aliexpress" )
                    .commersant ( commersant2 )
                    .build ( );
            Shop lamoda = Shop.builder ( )
                    .siteName ( "Lamoda" )
                    .commersant ( commersant2 )
                    .build ( );
            shopRepository.save(sulpak );
            shopRepository.save (  technodom);
            shopRepository.save(aliexpress);
            shopRepository.save (lamoda  );
            orderRepository.saveAll(GenerateData.addOrdersForCommersant1 (sulpak, technodom));
            orderRepository.saveAll ( GenerateData.addOrdersForCommersant2 ( aliexpress, lamoda ) );
        };
    }
}
