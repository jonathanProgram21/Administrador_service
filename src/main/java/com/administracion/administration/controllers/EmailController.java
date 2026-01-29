package com.administracion.administration.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.administracion.administration.DTOs.devolucionCuestionario;
import com.administracion.administration.email.DTO_emails.emailTo;
import com.administracion.administration.email.orchestration.EnvioDocumentosService;
import com.administracion.administration.email.service.EmailService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;
    private final EnvioDocumentosService envioDocumentosService; 

    public EmailController(EmailService eservice, EnvioDocumentosService eds){
        this.emailService = eservice;
        this.envioDocumentosService = eds;
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

    @PostMapping("/enviar-documentos/{idEvento}")
    public String enviarDocumentos(@PathVariable Long idEvento) {
        envioDocumentosService.enviarDocumentosPorEvento(idEvento);
        return "Documentos enviados correctamente";
    }

}
