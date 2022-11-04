package com.modu.ModuForm.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final CustomOAuthUserService customUserTypesOAuth2UserService;
    private final CustomOAuthSuccessHandler customOAuthSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/**", "/users/login", "/users/register",
                            "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//                    .antMatchers("/api/**").hasRole(Role.USER.name())
//                    .antMatchers("/answers/**").hasRole(Role.USER.name())
//                    .antMatchers("/surveys/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
//                    .loginPage("/users/login")
                    .successHandler(customOAuthSuccessHandler)
                .and()
                    .logout()
                    .logoutSuccessUrl("/users/login")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customUserTypesOAuth2UserService);
    }
}
