package com.example.tcs.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter {

//    protected MyConfig() {
//        super();
//    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return new CustomUserDtlsService();
    }

    @Bean
    public BCryptPasswordEncoder getPassword()
    {
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider daoProvider(){
        DaoAuthenticationProvider dao =new DaoAuthenticationProvider();
        dao.setUserDetailsService(getUserDetailsService());
        dao.setPasswordEncoder(getPassword());
        return dao;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoProvider());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN").antMatchers("/**").permitAll().and().formLogin()
                .loginPage("/adminLogin")
                .loginProcessingUrl("/doLogin").defaultSuccessUrl("/user/dashboard")
                .and().csrf().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
