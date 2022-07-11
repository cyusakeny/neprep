package com.example.neprep.config;

import com.example.neprep.security.JwtAuthenticationFilter;
import com.example.neprep.security.JwtAuthorizationFilter;
import com.example.neprep.security.customUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private  customUserDetails userDetails;
@Bean
public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
@Override
    public  void  configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
            .authorizeRequests()
            .antMatchers(
                    "/","/car","/user/addUser","/signin"
            ).permitAll()
            .antMatchers( "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/v3/api-docs",
                    "/v3/api-docs/swagger-config",
                    "/configuration/ui",
                    "/swagger-ui/**",
                    "/webjars/**",
                    "/swagger-resources/**"	,
                    "/configuration/security"
                    ).permitAll()
        .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/homepage.html", true)
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout")
            .permitAll();
    }

}
