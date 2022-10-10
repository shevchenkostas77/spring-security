package com.shevchenkostas77.spring.security.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = User.withDefaultPasswordEncoder(); // дефолтное шифрование паролей (в памяти)

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("stas")
                        .password("stas")
                        .roles("EMPLOYEE"))
                .withUser(userBuilder.username("elena")
                        .password("elena")
                        .roles("HR"))
                .withUser(userBuilder.username("ivan")
                        .password("ivan")
                        .roles("MANAGER", "HR"));
    }
}
