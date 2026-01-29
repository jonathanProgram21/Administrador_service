package com.administracion.administration.email.resetPassword;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.administracion.administration.email.service.EmailService;
import com.administracion.administration.entities.Auth;
import com.administracion.administration.repositories.AuthRepository;

@Service
public class PasswordResetService {

    private final AuthRepository authRepository;
    private final CodigoResetStore codigoResetStore;
    private final EmailService emailService;  
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(
        AuthRepository ar,
        CodigoResetStore cr,
        EmailService es,
        PasswordEncoder pd
    ){
        this.authRepository = ar;
        this.codigoResetStore = cr;
        this.emailService = es;
        this.passwordEncoder = pd;

    }

    public void solicitarCodigo(String Email){
        Auth auth = authRepository.findByEmail(Email)
            .orElseThrow(() -> new RuntimeException("Correo no encontrado"));
        
        String codigo = String.valueOf((int)(Math.random()*900000)+100000);

        CodigoRecuperacion cr = new CodigoRecuperacion(codigo, LocalDateTime.now().plusMinutes(10));

        codigoResetStore.guardar(Email, cr);

        emailService.sendEmail(Email,"Codigo de recuperacion" , "Tu codigo es "+codigo+ "  ,Este codigo sera valor durante 10 minutos" );
    }


    public void cambiarPassword(String codigo, String nuevaPassword) {
        
        Optional<Map.Entry<String, CodigoRecuperacion>> match =
            codigoResetStore.buscarPorCodigo(codigo);

        
        if (match.isEmpty()) {
            throw new RuntimeException("Código inválido");
        }

        CodigoRecuperacion cr = match.get().getValue();
        String email = match.get().getKey();

        if (cr.getExpiracion().isBefore(LocalDateTime.now())) {
            codigoResetStore.eliminar(email);
            throw new RuntimeException("Código expirado");
        }

        Auth auth = authRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        auth.setPassword(passwordEncoder.encode(nuevaPassword));
        authRepository.save(auth);
        
        codigoResetStore.eliminar(email);
    }
}
