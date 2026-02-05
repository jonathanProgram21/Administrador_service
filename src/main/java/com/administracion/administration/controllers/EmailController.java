package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.devolucionCuestionario;
import com.administracion.administration.DTOs.notificaciones;
import com.administracion.administration.email.DTO_emails.emailTo;
import com.administracion.administration.email.orchestration.EnvioDocumentosService;
import com.administracion.administration.email.service.EmailService;
import com.administracion.administration.repositories.AuthRepository;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    
    private final EmailService emailService;
    private final EnvioDocumentosService envioDocumentosService; 
    private final AuthRepository authRepository; 

    public EmailController(EmailService eservice, EnvioDocumentosService eds,AuthRepository authRepository){
        this.emailService = eservice;
        this.envioDocumentosService = eds;
        this.authRepository = authRepository;
    }

    @PostMapping("/enviar/encuesta")
    public String enviarEncuesta(@RequestBody emailTo dto) {
        String titulo = "Contestar Encuesta para su evento";
        String cont = "Por este medio te envio el link para que realices la encuesta para tu evento: https://front-cuestionario.vercel.app/";
        emailService.sendEmail(dto.getPara(), titulo, cont);
        return "Email enviado";
    }

    @PostMapping("/devolver/encuesta")
    public String devolverEncuesta(@RequestBody devolucionCuestionario dto) {
        String titulo = "Devolucion de la encuesta realizada para su evento";
        String cont = dto.getComentario()+". Por lo anterior le vuelvo a enviar la encuesta para su evento: https://front-cuestionario.vercel.app/";
        emailService.sendEmail(dto.getCorreo(), titulo, cont);
        return "Email enviado";
    }
    
    @PostMapping("/inperfectos")
    public String enviarReporte(@RequestBody devolucionCuestionario dto) {
        String titulo = "Desperfectos tras su evento en el teatro del CCRAN";
        String cont = dto.getComentario();
        emailService.sendEmail(dto.getCorreo(), titulo, cont);
        return "Email enviado correctamente";
    }

    @PostMapping("/notificar/actualizacion")
    public String notificarActualizaciones(@RequestBody notificaciones dto) {
        String titulo = "Actualización del sistema de tareas del teatro";
        String cont = dto.getMensaje();
        List<String> correos = authRepository.findAllEmails();
        try {
            
            for (String i : correos) {
                System.out.println(i);
                emailService.sendEmail(i, titulo, cont);
            }
            return "Email enviado correctamente";
        } catch (Exception e) {
            return "Un correo no existe";
            
        }
    }
    
    @PostMapping("/notificar/funciones")
    public String notificarFunciones(@RequestBody notificaciones dto) {
        String titulo = "Resumen de las nuevas funciones del sistema";
        String cont = dto.getMensaje();
        List<String> correos = authRepository.findAllEmails();
        try {    
            for (String i : correos) {
                emailService.sendEmail(i, titulo, cont);
            }
            return "Email enviado correctamente";
        } catch (Exception e) {
            return "Un correo no existe";
            
        }
    }

    @PostMapping("/enviar-documentos/{idEvento}")
    public String enviarDocumentos(@PathVariable Long idEvento) {
        envioDocumentosService.enviarDocumentosPorEvento(idEvento);
        return "Documentos enviados correctamente";
    }
    

}
