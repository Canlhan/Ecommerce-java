package com.cancodevery.ecom.config;

import com.cancodevery.ecom.user.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig
{


    private final CustomUserDetailService customUserDetailService;


     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{


         http
                 .csrf().disable()

                 .authorizeRequests()
                 .antMatchers("/products/")

                 .authenticated()
                 .and()
                 .formLogin().disable()
                 .httpBasic();
         System.out.println("asd");





         return http.build();
     }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        System.out.println("managera girdi");
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        System.out.println("assw girdi");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        System.out.println("provider");
         DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
         provider.setUserDetailsService(this.customUserDetailService);
         provider.setPasswordEncoder(encoder());
         return provider;
    }





}
