package com.administracion.administration.email;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-email")
public class pruebaController {
    private final BrevoEmailService emailService;

    public pruebaController(BrevoEmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public String enviarPrueba() {
        emailService.enviarCorreo(
            "135joni246@gmail.com",
            "Prueba Brevo + Render 🚀",
            "<h2>Funciona</h2><p>Email enviado correctamente</p>"
        );
        return "Email enviado";
    }
}
