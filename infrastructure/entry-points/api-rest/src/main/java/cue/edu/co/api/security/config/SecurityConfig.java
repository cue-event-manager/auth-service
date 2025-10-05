package cue.edu.co.api.security.config;

import cue.edu.co.api.auth.constants.AuthEndpoint;
import cue.edu.co.api.security.filter.InternalGatewayFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final InternalGatewayFilter internalGatewayFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(internalGatewayFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                AuthEndpoint.LOGIN_ENDPOINT,
                                AuthEndpoint.REFRESH_TOKEN_ENDPOINT,
                                AuthEndpoint.LOGOUT_ENDPOINT
                                ).permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}