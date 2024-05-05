package com.example.gestionticket.conf;

import com.example.gestionticket.services.UserServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
class SecurityConfig {

   @Bean
    public ServletContextInitializer servletContextInitializer() {
    return servletContext -> {
        servletContext.getSessionCookieConfig().setHttpOnly(true);
        servletContext.getSessionCookieConfig().setSecure(true); // Ensure cookies are only sent over HTTPS
    };
}

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserServiceImpl userService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authz) -> authz
                .requestMatchers("/registration", "/index","/Evenement", "/static/css/**", "/static/images/**" ,"/static/js/**").permitAll() // Allow access to /registration without authentication
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll())

        ;
        http.logout(withDefaults());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


