package com.example.PexChat.Config;


import com.example.PexChat.SideModel.CustomDetailService;
import com.example.PexChat.SideModel.CustomPassEncode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    

    CustomDetailService uService;

    public SecurityConfiguration(CustomDetailService uService){
        this.uService = uService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
            // .inMemoryAuthentication()
            // .withUser("admin123").password(passwordEncoder().encode("admin123")).roles("Admin")
            // .and()
            // .withUser("user").password(passwordEncoder().encode("user2")).roles("USER");
            .authenticationProvider(authenticationProvider());
        
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable()
            .authorizeRequests()
            
            // .anyRequest().permitAll().anyRequest().permitAll()
            // .antMatchers("/Admin/**").hasRole("Admin")
            .antMatchers("/Account/**").authenticated()
            // .antMatchers("/Cart/**").authenticated()

            .anyRequest().permitAll()
            
            // .antMatchers("/signAndReg")
            
            
            .and()
            // .httpBasic();
            .formLogin()
            .loginPage("/login")  
            .loginProcessingUrl("/log")
            .successForwardUrl("/LogSuccess")
            .failureForwardUrl("/FailureLog")
            .and()
            .logout()
            .logoutUrl("/Logout")
            .logoutSuccessUrl("/")
            .deleteCookies("JSONSESSION","JSESSIONID")
            .and()
            .rememberMe().userDetailsService(this.uService)
            ;
        
    }
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.uService);

        return daoAuthenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new CustomPassEncode();
    }
    
}
