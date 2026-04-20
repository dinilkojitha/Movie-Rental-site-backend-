package sliit.oop_server_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // API වලට CSRF ඕනේ නැහැ
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // දැනට හැම Request එකකටම (Movies, Users ඔක්කොටම) අවසර දෙනවා
                );
        return http.build();
    }
}