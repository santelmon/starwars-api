package com.starwars.auth;

import com.starwars.model.Account;
import com.starwars.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Service
public class FakeAuthenticationProvider implements AuthenticationProvider {

    private AccountRepository accountRepository;

    public FakeAuthenticationProvider(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Account account = this.accountRepository.findByUsername( name );

        /* Autenticación en memoria.
        if( name.equals(password) ) {
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>() );
        }
        */

        if( account != null && password.equals(account.getPassword()) ) {
            // Usuario correcto.
            return new UsernamePasswordAuthenticationToken(name, password, getGrantedAuthorities(name));
        }

        throw new BadCredentialsException("Incorrect Password!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(String username) {
        Collection<? extends GrantedAuthority> authorities;

        if( "admin".equals(username) ) {
            authorities = Arrays.asList(() -> "ADMIN", () -> "BASIC");
        }
        else {
            authorities = Arrays.asList(() -> "BASIC");
        }

        return authorities;
    }
}
