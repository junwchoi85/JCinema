package com.jcinema.securityserver.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import com.jcinema.securityserver.excptionhandling.CustomBasicAuthenticationEntryPoint;

@Configuration
@Profile("prod")
public class ProjectSecurityConfigProd {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(
                smc->smc.invalidSessionUrl("/invalidSession")
                        .maximumSessions(1) // Only 1 session per user
                        .maxSessionsPreventsLogin(true) // Prevents login if user already has a session
                        // .expiredUrl("/expiredSession") // Redirect to this URL if session is expired
                )
            .requiresChannel((requiresChannel) -> requiresChannel.anyRequest().requiresSecure()) // enable https
            .csrf(csrfConfig -> csrfConfig.disable())
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/hi", "/", "/myAccount").authenticated()
                .requestMatchers("/welcome", "/register", "/invalidSession").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(httpBasicConfig -> httpBasicConfig.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService(DataSource dataSource) {
    //     return new JdbcUserDetailsManager(dataSource);
    // }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
