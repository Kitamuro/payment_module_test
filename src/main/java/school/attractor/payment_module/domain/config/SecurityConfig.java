package school.attractor.payment_module.domain.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedOrigins(Arrays.asList("*"));
        cors.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        cors.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        cors.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf()
                .ignoringAntMatchers("/pay");

         httpSecurity.formLogin ()
                .loginPage ( "/login" )
                .failureUrl ( "/login?error=true" )
                .defaultSuccessUrl ( "/home" );

        httpSecurity.logout ()
                .logoutUrl ( "/logout" )
                .logoutSuccessUrl ( "/" )
                .clearAuthentication ( true )
                .invalidateHttpSession ( true );

        httpSecurity.authorizeRequests ()
                .antMatchers ( "/login" , "/pay", "/")
                .permitAll ();

        httpSecurity.authorizeRequests ()
                .antMatchers ( "/registration")
                .permitAll ();

        httpSecurity.authorizeRequests ()
                .antMatchers (  "/css/**", "/js/**", "/img/**", "/images/**", "/icon/**", "/vendor/**")
                .permitAll ();

        httpSecurity.authorizeRequests ()
                .anyRequest ()
                .fullyAuthenticated ();


    }

    @Override
    protected void  configure (AuthenticationManagerBuilder authMB) throws Exception{
        String fetchUsersQuery = "select email, password, enabled"
                + " from commersants"
                + " where email = ?";
        String fetchRolesQuery = "select email, role"
                + " from commersants"
                + " where email = ?";

        authMB.jdbcAuthentication ()
                .usersByUsernameQuery ( fetchUsersQuery )
                .authoritiesByUsernameQuery ( fetchRolesQuery )
                .dataSource ( dataSource );

    }

}
