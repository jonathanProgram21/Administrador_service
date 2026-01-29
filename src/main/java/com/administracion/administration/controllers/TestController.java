package com.administracion.administration.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    // 🔐 SOLO ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String soloAdmin() {
        return "Hola ADMIN, acceso concedido";
    }

    // 🔐 USUARIO y ADMIN
    @GetMapping("/usuario")
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMIN')")
    public String usuario() {
        return "Hola USUARIO, acceso concedido";
    }
}
