package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.AuthDTO;
import com.administracion.administration.DTOs.AuthResponseDTO;
import com.administracion.administration.DTOs.RecibeEmail;
import com.administracion.administration.services.IAuthService;
import com.administracion.administration.services.usuarios.IUsuarioService;
import com.administracion.administration.email.service.EmailService;

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
    private final EmailService emailService;

    
    public AppController(IAuthService repoAuth,IUsuarioService iu,
        PasswordEncoder passwordEncoder,
        EmailService emailService){
        this.IAS = repoAuth;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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
    public ResponseEntity<?> postMethodName(@RequestBody RecibeEmail email) {
        String titulo = "Usuario Registrado en el sistema del teatro Auditorio Acolmizcli Nezahualcoyotl";
        String contenido = "Usted ha sido resgistrado dentro de nuestra base de datos como usuario del sistema de tareas del"+
                            "Teatro del Complejo Cultural y Recreativo Acolmiztli Nezahualcoyotl por lo que su informacion "+
                            "sera debidamente usada para razones relacionadas al teatro";
        try {
            emailService.sendEmail(email.getEmail(), titulo, contenido);
            Integer idGenerado = IAS.saveAuth(email);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(idGenerado, "contraseña registrada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.ok().body("Verifiacr que el correo ingresado este bien");
        }

    
    }  

    @GetMapping("/idUsuario/{email}")
    public Integer idUsuario(@PathVariable String email) {
        return IAS.obtenerIdUsuarioXEmail(email);
    }
    
}
