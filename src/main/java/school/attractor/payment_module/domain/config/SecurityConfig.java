package school.attractor.payment_module.domain.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin ()
                .loginPage ( "/login" )
                .failureUrl ( "/login?error=true" )
                .defaultSuccessUrl ( "/" );

        httpSecurity.logout ()
                .logoutUrl ( "/logout" )
                .logoutSuccessUrl ( "/login" )
                .clearAuthentication ( true )
                .invalidateHttpSession ( true );

        httpSecurity.authorizeRequests ()
                .antMatchers ( "/login")
                .permitAll ();

        httpSecurity.authorizeRequests ()
                .antMatchers ( "/register")
                .permitAll ();

        httpSecurity.authorizeRequests ()
                .antMatchers ( "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/icon/**")
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
