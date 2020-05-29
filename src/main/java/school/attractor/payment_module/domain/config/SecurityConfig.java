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
                .loginPage ( "/user/login" )
                .failureUrl ( "/user/login?error=true" );


        httpSecurity.logout ()
                .logoutUrl ( "/user/logout" )
                .logoutSuccessUrl ( "/" )
                .clearAuthentication ( true )
                .invalidateHttpSession ( true );

        httpSecurity.authorizeRequests ()
                .anyRequest ()
                .permitAll ();
    }

    @Override
    protected void  configure (AuthenticationManagerBuilder authMB) throws Exception{
        String fetchUsersQuery = "select email, password, enabled"
                + " from users"
                + " where email = ?";;
        String fetchRolesQuery = "select email, role"
                + " from users"
                + " where email = ?";

        authMB.jdbcAuthentication ()
                .usersByUsernameQuery ( fetchUsersQuery )
                .authoritiesByUsernameQuery ( fetchRolesQuery )
                .dataSource ( dataSource );

    }
}
