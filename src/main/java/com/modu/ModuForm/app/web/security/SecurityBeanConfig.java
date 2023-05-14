package com.modu.ModuForm.app.web.security;

import com.modu.ModuForm.app.web.security.authentication.jwt.CustomJwtProvider;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtAuthenticationFilter;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtExtractor;
import com.modu.ModuForm.app.web.security.authentication.jwt.JwtInjector;
import com.modu.ModuForm.app.web.security.properties.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtInjector jwtInjector(JwtProperties jwtProperties) {
        return new JwtInjector(jwtProperties);
    }

    @Bean
    public JwtExtractor jwtExtractor(JwtProperties jwtProperties) {
        return new JwtExtractor(jwtProperties);
    }

    @Bean
    public CustomJwtProvider customJwtProvider(JwtExtractor jwtExtractor, JwtInjector jwtInjector) {
        return new CustomJwtProvider(jwtExtractor, jwtInjector);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(CustomJwtProvider customJwtProvider) {
        return new JwtAuthenticationFilter(customJwtProvider);
    }
}
