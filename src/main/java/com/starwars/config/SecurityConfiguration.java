package com.starwars.config;

import com.starwars.auth.FakeAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    private FakeAuthenticationProvider fakeAuthenticationProvider;

    public SecurityConfiguration(FakeAuthenticationProvider fakeAuthenticationProvider) {
        this.fakeAuthenticationProvider = fakeAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("ADMIN");

        auth.authenticationProvider( fakeAuthenticationProvider );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/films/**").permitAll()
                .antMatchers("/planets/**").hasRole("ADMIN")
                .antMatchers("/peoples/**").authenticated();
    }
}
