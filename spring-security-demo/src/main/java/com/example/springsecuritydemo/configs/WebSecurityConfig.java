package com.example.springsecuritydemo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   //overriding Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // inMemory authorization logic for 2 users
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("user")
                .roles("USER");
    }

    //spring will be using this bean for password encoding as the return type is PasswordEncoder
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") // /** can be used for all uri pattern
                .antMatchers("/user").hasAnyRole("USER","ADMIN")
                .antMatchers("/greeting").permitAll()
                .and()
                .formLogin();
    }

}
