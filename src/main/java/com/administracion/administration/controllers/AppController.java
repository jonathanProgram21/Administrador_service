package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.AuthDTO;
import com.administracion.administration.DTOs.AuthResponseDTO;
import com.administracion.administration.DTOs.RecibeEmail;
import com.administracion.administration.services.IAuthService;
import com.administracion.administration.services.usuarios.IUsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/auth")
public class AppController {

    private final IAuthService IAS;
    private final PasswordEncoder passwordEncoder;

    
    public AppController(IAuthService repoAuth,IUsuarioService iu,
        PasswordEncoder passwordEncoder){
        this.IAS = repoAuth;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void testEncoder() {
        String hash = passwordEncoder.encode("otro");
        System.out.println("este es el hash-------------"+hash);
    }

    @GetMapping("/uno")
    public String data (){
        testEncoder();
        return "si funciona";
    }

    @PostMapping("/guardar/auth")
    public ResponseEntity<AuthResponseDTO> saveAuth(@RequestBody AuthDTO entity) {
        Integer idGenerado = IAS.guardarAuth(entity);

        return ResponseEntity.ok(
            new AuthResponseDTO(idGenerado, "Auth creado correctamente")
        );
    }
    // Este metodo guarda el Auth de un usuario
    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> postMethodName(@RequestBody RecibeEmail email) {
    Integer idGenerado = IAS.saveAuth(email);
    return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(idGenerado, "contraseña registrada correctamente"));
    }  

    @GetMapping("/idUsuario/{email}")
    public Integer idUsuario(@PathVariable String email) {
        return IAS.obtenerIdUsuarioXEmail(email);
    }
    
}
