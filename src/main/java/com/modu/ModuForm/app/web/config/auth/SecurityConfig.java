package com.modu.ModuForm.app.web.config.auth;

import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.web.config.auth.OAuth.CustomOAuthSuccessHandler;
import com.modu.ModuForm.app.web.config.auth.OAuth.CustomOAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuthUserService customUserTypesOAuth2UserService;
    private final CustomOAuthSuccessHandler customOAuthSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                // URL 인가
                .authorizeRequests()
                .antMatchers("/**", "/users/login", "/users/register","/app/**",
                        "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/**").hasRole(Role.USER.name())
                    .antMatchers("/answers/**").hasRole(Role.USER.name())
                    .antMatchers("/surveys/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()

                // form Login
                .formLogin()
                    .loginPage("/users/login")
                .successHandler(customOAuthSuccessHandler)
                .and()

                // Logout
                .logout()
                .logoutSuccessUrl("/users/login")
                .and()

                //
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customUserTypesOAuth2UserService);
        return http.build();
    }
}
