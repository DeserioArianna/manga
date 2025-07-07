package org.lessons.java.manga.manga;

import org.lessons.java.manga.manga.security.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 👈 Disattivo il controllo CSRF per API REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/manga/**", "/api/manga", "/api/manga/**").permitAll()
                        .requestMatchers("/create", "/edit/**", "/delete/**", "/autore/**", "/genere/**")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .defaultSuccessUrl("/manga", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/manga")
                        .permitAll())
                .cors(withDefaults -> {}); // 👈 Abilito Cors con configurazione di default

        return http.build();
    }
}
