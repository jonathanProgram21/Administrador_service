package com.administracion.administration.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.auth.dto.LoginRequestDTO;
import com.administracion.administration.auth.dto.LoginResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {    

    private final AuthServiceImpl authServiceImpl;

    public AuthController(AuthServiceImpl ai){
        this.authServiceImpl = ai;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        String token = authServiceImpl.login(dto);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
