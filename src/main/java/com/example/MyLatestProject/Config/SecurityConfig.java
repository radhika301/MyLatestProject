package com.example.MyLatestProject.Config;

import com.example.MyLatestProject.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Spring Security rules:
 *
 *  GET    /api/customers/**        → ADMIN + AGENT
 *  POST   /api/customers/register  → ADMIN + AGENT
 *  PUT    /api/customers/**        → ADMIN only
 *  PATCH  /api/customers/**        → ADMIN only
 *  DELETE /api/customers/**        → ADMIN only
 *  GET    /api/customers/me        → any logged-in user
 *  /h2-console/**                  → ADMIN only
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider(userDetailsService);
       // p.setUserDetailsService(userDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/calculator/**"))
            .headers(h -> h.frameOptions(f -> f.sameOrigin()))
            .authorizeHttpRequests(auth -> auth
            		
                
                .requestMatchers(HttpMethod.GET,    "/calculator/add").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET,    "/calculator/mul").hasAnyRole("AGENT")
                .requestMatchers(HttpMethod.POST,   "/calculator/sub").hasAnyRole("ADMIN", "AGENT")
                .requestMatchers(HttpMethod.PUT,    "/calculator/mul").hasRole("AGENT")
                .requestMatchers(HttpMethod.PATCH,  "/calculator/div").hasRole("AGENT")
                .requestMatchers(HttpMethod.DELETE, "/calculator/add").hasRole("ADMIN")
             
                .requestMatchers("/h2-console/**").hasRole("ADMIN")
                .requestMatchers("/calculator/**").authenticated()
                .anyRequest().authenticated()
            )
           // .httpBasic(basic -> {})
            
         // Custom entry point
            .httpBasic(basic -> {
                BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
                entryPoint.setRealmName("T-Mobile App");  // ← must set this
                basic.authenticationEntryPoint(entryPoint);
            })
  
            .authenticationProvider(authProvider());

        return http.build();
    }
}
