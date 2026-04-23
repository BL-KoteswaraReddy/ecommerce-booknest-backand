package com.ecommerce.security.config;

import com.ecommerce.security.filters.JwtAuthFilter;
//import com.ecommerce.security.handler.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

//    @Autowired
//    private OAuth2SuccessHandler oAuth2SuccessHandler;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()        // ✅ REST endpoints
                        .requestMatchers("/login/**", "/oauth2/**").permitAll() // ✅ OAuth2
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()
                )
                // ✅ Keep OAuth2 but separate from REST
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/oauth2/authorization/google")     // ✅ Explicit login page
//                        .successHandler(oAuth2SuccessHandler)
//                )
                // ✅ Disable form login completely
                .formLogin(form -> form.disable())
                // ✅ Disable httpBasic
                .httpBasic(basic -> basic.disable())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}