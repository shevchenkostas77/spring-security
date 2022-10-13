package com.shevchenkostas77.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource; // как подключится к базе данных информация содержится в поле dataSource

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Spring Security берет информацию о пользователях из базы данных
        auth.jdbcAuthentication().dataSource(dataSource);

//         дефолтное шифрование паролей (в памяти)
//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("stas")
//                        .password("stas")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder.username("elena")
//                        .password("elena")
//                        .roles("HR"))
//                .withUser(userBuilder.username("ivan")
//                        .password("ivan")
//                        .roles("MANAGER", "HR"));


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info/**").hasRole("MANAGER")
                .and().formLogin().permitAll();
    }
}
