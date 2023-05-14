package com.modu.ModuForm.app.web.security;

import com.modu.ModuForm.app.domain.user.common.Role;
import com.modu.ModuForm.app.web.config.OAuth.CustomOAuthSuccessHandler;
import com.modu.ModuForm.app.web.config.OAuth.CustomOAuthUserService;
import com.modu.ModuForm.app.web.security.authentication.FormAuthenticationProvider;
import com.modu.ModuForm.app.web.security.authentication.FormUserDetailService;
import com.modu.ModuForm.app.web.security.authentication.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final CustomOAuthUserService customUserTypesOAuth2UserService;
    private final CustomOAuthSuccessHandler customOAuthSuccessHandler;
    private final FormUserDetailService formUserDetailService;
    private final FormAuthenticationProvider formAuthenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                .userDetailsService(formUserDetailService)
                .authenticationProvider(formAuthenticationProvider)

                // URL 인가
                .authorizeRequests()
                .antMatchers("/**", "/users/login", "/users/register","/app/**",
                        "/css/**", "/images/**", "/js/**", "/h2-console/**", "/login/proc").permitAll()
                    .antMatchers("/api/**", "/answers/**", "/surveys/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()

                // form Login
                .formLogin()
                    .loginPage("/users/login")
                    .loginProcessingUrl("/login/proc")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/users/login?error=true")
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

        http
                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
