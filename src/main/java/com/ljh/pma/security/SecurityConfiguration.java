package com.ljh.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()     // in memory authentication
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .withDefaultSchema()      // using default schema
//            .withUser("myuser")
//                .password("pass")
//                .roles("USER")
//            .and()
//            .withUser("jh")
//                .password("pass2")
//                .roles("USER")
//            .and()
//            .withUser("manager")
//                .password("pass3")
//                .roles("ADMIN");

        // using custom user table
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("SELECT username, password, enabled " +
                        "FROM user_accounts WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username, role " +
                        "FROM user_accounts WHERE username = ?")
//            .passwordEncoder(getPasswordEncoder())    // using default
            .passwordEncoder(bCryptPasswordEncoder);

    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        // default encoder without encryption
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")     // need ROLE_ prefix
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/projects/update").hasRole("ADMIN")
                .antMatchers("/projects/delete").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")     // don't need ROLE_ prefix
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/employees/update").hasRole("ADMIN")
                .antMatchers("/employees/delete").hasRole("ADMIN")

//                .antMatchers("/employees/new").hasAnyAuthority("ADMIN")     // don't need ROLE_ prefix
//                .antMatchers("/employees/save").hasAnyAuthority("ADMIN")
//                .antMatchers("h2_console/**").permitAll()
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin(); // default form login
//                .loginPage("/login-page");       // using custom login-page

        // for postman testing
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }
}
