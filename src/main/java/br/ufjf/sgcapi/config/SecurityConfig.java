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
        // Passando o funcionarioService diretamente no construtor da classe!
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
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/funcionarios/**").permitAll()
                        .requestMatchers("/api/v1/combustivel/**").permitAll()
                        .requestMatchers("/api/v1/cliente/**").permitAll()
                        .requestMatchers("/api/v1/cidade/**").permitAll()
                        .requestMatchers("/api/v1/carroceria/**").permitAll()
                        .requestMatchers("/api/v1/carroUsado/**").permitAll()
                        .requestMatchers("/api/v1/carroTestDrive/**").permitAll()
                        .requestMatchers("/api/v1/carroNovo/**").permitAll()
                        .requestMatchers("/api/v1/agenda/**").permitAll()
                        .requestMatchers("/api/v1/agencia/**").permitAll()
                        .requestMatchers("/api/v1/acessorio/**").permitAll()
                        .requestMatchers("/api/v1/vendedor/**").permitAll()
                        .requestMatchers("/api/v1/venda/**").permitAll()
                        .requestMatchers("/api/v1/testDrive/**").permitAll()
                        .requestMatchers("/api/v1/modelo/**").permitAll()
                        .requestMatchers("/api/v1/gerente/**").permitAll()
                        .requestMatchers("/api/v1/acessorio/**").permitAll()
                        .requestMatchers("/api/v1/formaDePagamento/**").permitAll()
                        .requestMatchers("/api/v1/estado/**").permitAll()
                        .requestMatchers("/api/v1/direcao/**").permitAll()

                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/v2/api-docs",
                "/v3/api-docs/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**"
        );
    }
}