package com.aderenchuk.brest.service.config;

import com.aderenchuk.brest.model.Permission;
import com.aderenchuk.brest.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/tours/**").hasAuthority(Permission.TOURS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/tours/**").hasAuthority(Permission.TOURS_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/tours/**").hasAuthority(Permission.TOURS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/tours#**").hasAuthority(Permission.TOURS_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/clients/**").hasAuthority(Permission.CLIENTS_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/client/**").hasAuthority(Permission.CLIENTS_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/client/**").hasAuthority(Permission.CLIENTS_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/client#**").hasAuthority(Permission.CLIENTS_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}
