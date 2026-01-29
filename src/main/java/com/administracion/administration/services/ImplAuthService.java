package com.administracion.administration.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.administracion.administration.DTOs.AuthDTO;
import com.administracion.administration.DTOs.RecibeEmail;
import com.administracion.administration.entities.Auth;
import com.administracion.administration.repositories.AuthRepository;

@Service
public class ImplAuthService implements IAuthService{

    private final AuthRepository AR;
    private final PasswordEncoder passwordEncoder;

    public ImplAuthService(AuthRepository ar, PasswordEncoder passwordEncoder){
        this.AR = ar;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Integer guardarAuth(AuthDTO adto) {
        Auth auth = new Auth();
        auth.setEmail(adto.getEmail());
        auth.setPassword(adto.getPassword());
    
        Auth savedAuth = AR.save(auth); // ← aquí se genera el PK
    
        return savedAuth.getId(); // o getPk_auth según tu entidad
    }


    @Override
    public Integer saveAuth(RecibeEmail dto) {
        if (AR.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya existe");
        }

        Auth auth = new Auth();
        auth.setEmail(dto.getEmail());
        auth.setPassword(passwordEncoder.encode("CCRAN-teatro"));

        Auth saved = AR.save(auth);
        return saved.getId();
    }

    @Override
    public Integer obtenerIdUsuarioXEmail(String email) {
        Integer idUsuario = AR.obtenerIdUsuarioPorEmail(email);
        if (idUsuario == null) {
            throw new RuntimeException("no existe");
        }
        return idUsuario;
    } 
    
}
