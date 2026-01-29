package com.administracion.administration.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.administracion.administration.auth.dto.LoginRequestDTO;
import com.administracion.administration.auth.exceptions.CredencialesInvalidasException;
import com.administracion.administration.entities.Auth;
import com.administracion.administration.repositories.AuthRepository;
import com.administracion.administration.security.jwt.JwtService;

@Service
public class AuthServiceImpl {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(AuthRepository authRepository,PasswordEncoder passwordEncoder, JwtService jwtService){
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login(LoginRequestDTO dto) {

    Auth auth = authRepository.findByEmail(dto.getEmail())
        .orElseThrow(CredencialesInvalidasException::new);

    if (!passwordEncoder.matches(dto.getPassword(), auth.getPassword())) {
        throw new CredencialesInvalidasException();
    }

    return jwtService.generarToken(auth);
}


}
