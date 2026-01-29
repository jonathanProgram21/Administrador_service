package com.administracion.administration.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.administracion.administration.entities.Auth;
import com.administracion.administration.repositories.AuthRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AuthRepository authRepository;
    
    public UserDetailsServiceImpl (AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Auth auth = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(auth.getEmail(), auth.getPassword(), List.of(
            new SimpleGrantedAuthority(
                                "ROLE_" + auth.getUsuario().getRol().name()
                        )
        ));
    }    
}
