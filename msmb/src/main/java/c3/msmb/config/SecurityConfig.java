/* package c3.msmb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF si usas Postman
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST).permitAll() // Permite el registro sin autenticación
                .anyRequest().authenticated()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.PATCH).permitAll() // Permite el registro sin autenticación
                .anyRequest().authenticated()
            )
            .httpBasic(); // Habilita Basic Auth
        return http.build();
    }
} */