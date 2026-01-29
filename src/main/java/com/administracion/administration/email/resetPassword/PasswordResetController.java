package com.administracion.administration.email.resetPassword;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    private final PasswordResetService service;

    public PasswordResetController(PasswordResetService prc){
        this.service = prc;
    }

    @PostMapping("/solicitar")
    public String solicitar(@RequestBody EmailDTO dto) {
        service.solicitarCodigo(dto.getEmail());
        return "Código enviado si el correo existe";
    }

    @PostMapping("/cambiar")
    public String cambiar(@RequestBody CambioPasswordDTO dto) {
        service.cambiarPassword(
            dto.getCodigo(),
            dto.getNuevaPassword()
        );
        return "Contraseña actualizada";
    }

    
}
