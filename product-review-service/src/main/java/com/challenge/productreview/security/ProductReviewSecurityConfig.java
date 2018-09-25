package com.challenge.productreview.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ProductReviewSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private ProductReviewEntryPoint authEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/review").authenticated()
                .antMatchers(HttpMethod.PUT,"/review").authenticated()
                .antMatchers(HttpMethod.DELETE,"/review/*").authenticated()
                .antMatchers(HttpMethod.GET,"/review/*").anonymous()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);

        // enable show h2 admin console
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("challenge")
                .password(passwordEncoder().encode("code"))
                .roles("PRODUCT_API");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
