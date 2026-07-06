package br.ufjf.sgcapi.config;

import br.ufjf.sgcapi.security.JwtAuthFilter;
import br.ufjf.sgcapi.security.JwtService;
import br.ufjf.sgcapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final FuncionarioService funcionarioService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder; // Injetado automaticamente do PasswordConfig

    @Bean
    public JwtAuthFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, funcionarioService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider((UserDetailsService) funcionarioService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(java.util.List.of("*"));
                    corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                    corsConfiguration.setAllowedHeaders(java.util.List.of("Authorization", "Cache-Control", "Content-Type"));
                    return corsConfiguration;
                }))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        .requestMatchers("/api/v1/funcionarios/auth").permitAll()
                        .requestMatchers("/api/v1/funcionarios/**").authenticated()

                        .requestMatchers("/api/v1/combustivel/**").authenticated()
                        .requestMatchers("/api/v1/cliente/**").authenticated()
                        .requestMatchers("/api/v1/cidade/**").authenticated()
                        .requestMatchers("/api/v1/carroceria/**").authenticated()
                        .requestMatchers("/api/v1/carroUsado/**").authenticated()
                        .requestMatchers("/api/v1/carroTestDrive/**").authenticated()
                        .requestMatchers("/api/v1/carroNovo/**").authenticated()
                        .requestMatchers("/api/v1/agenda/**").authenticated()
                        .requestMatchers("/api/v1/agencia/**").authenticated()
                        .requestMatchers("/api/v1/acessorio/**").authenticated()
                        .requestMatchers("/api/v1/vendedor/**").authenticated()
                        .requestMatchers("/api/v1/venda/**").authenticated()
                        .requestMatchers("/api/v1/testDrive/**").authenticated()
                        .requestMatchers("/api/v1/modelo/**").authenticated()
                        .requestMatchers("/api/v1/gerente/**").authenticated()
                        .requestMatchers("/api/v1/formaDePagamento/**").authenticated()
                        .requestMatchers("/api/v1/estado/**").authenticated()
                        .requestMatchers("/api/v1/direcao/**").authenticated()

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/favicon.ico");
    }
}