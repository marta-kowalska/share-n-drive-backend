package com.codecool.sharendrivebackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenServices jwtTokenServices;


    public SecurityConfig(JwtTokenServices jwtTokenServices) {
        this.jwtTokenServices = jwtTokenServices;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/css/**", "/js/**", "/favicon.ico").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/signin").permitAll()
            .antMatchers(HttpMethod.POST, "/share-n-drive/add-car").authenticated()
            .antMatchers(HttpMethod.GET, "/share-n-drive/bookings").authenticated()
            .antMatchers(HttpMethod.GET, "/share-n-drive/customer/*").authenticated()
            .antMatchers(HttpMethod.POST, "/share-n-drive/book-car").authenticated()
            .antMatchers(HttpMethod.DELETE, "/share-n-drive/remove-car/*").authenticated()
            .and()
            .addFilterBefore(new JwtTokenFilter(jwtTokenServices),
                UsernamePasswordAuthenticationFilter.class);

    }
}
