package com.administracion.administration.email.orchestration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.administracion.administration.email.pdf.PdfGeneratorService;
import com.administracion.administration.email.service.EmailService;
import com.administracion.administration.repositories.RespuestasRepo;
import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;

@Service
public class EnvioDocumentosService {

    private final RespuestasRepo respuestasRepo;
    private final PdfGeneratorService pdfService;
    private final EmailService emailService;

    @Value("${correo.audio}")
    private String correoAudio;

    @Value("${correo.iluminacion}")
    private String correoIluminacion;

    @Value("${correo.estacionamiento}")
    private String correoEstacionamiento;

    @Value("${correo.auxiliar}")
    private String correoEscenario;

    public EnvioDocumentosService(
            RespuestasRepo respuestasRepo,
            PdfGeneratorService pdfService,
            EmailService emailService) {

        this.respuestasRepo = respuestasRepo;
        this.pdfService = pdfService;
        this.emailService = emailService;
    }

    public void enviarDocumentosPorEvento(Long idEvento) {

        List<PreguntaRespuestaDTO> lista =
                respuestasRepo.getPreguntasYRespuestasPorEvento(idEvento);

        Map<String, List<PreguntaRespuestaDTO>> agrupado =
                lista.stream()
                        .collect(Collectors.groupingBy(PreguntaRespuestaDTO::getArea));

        agrupado.forEach((area, datos) -> {

            byte[] pdf = pdfService.generarPdfPorArea(area, datos);
            String correo = obtenerCorreoPorArea(area);

            emailService.enviarCorreoConAdjunto(
                    correo,
                    "Documentación área " + area,
                    "Se adjunta la documentación correspondiente al área de " + area,
                    pdf,
                    area + ".pdf"
            );
        });
    }

    private String obtenerCorreoPorArea(String area) {
        return switch (area.toLowerCase()) {
            case "audio" -> correoAudio;
            case "iluminacion" -> correoIluminacion;
            case "estacionamiento" -> correoEstacionamiento;
            case "auxiliar" -> correoEscenario;
            default -> throw new RuntimeException("Área no configurada: " + area);
        };
    }
}
