package com.administracion.administration.email.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BREVO_URL =
            "https://api.brevo.com/v3/smtp/email";

    // ==========================
    // EMAIL SIMPLE
    // ==========================
    public void sendEmail(String para, String asunto, String contenido) {

        String body = """
        {
          "sender": {
            "name": "Teatro CCRAN",
            "email": "teatroccran@gmail.com"
          },
          "to": [
            { "email": "%s" }
          ],
          "subject": "%s",
          "htmlContent": "%s"
        }
        """.formatted(para, asunto, contenido);

        enviar(body);
    }

    // ==========================
    // EMAIL CON ADJUNTO (PDF)
    // ==========================
    public void enviarCorreoConAdjunto(
            String para,
            String asunto,
            String contenido,
            byte[] pdf,
            String nombreArchivo
    ) {

        String base64 = java.util.Base64.getEncoder().encodeToString(pdf);

        String body = """
        {
          "sender": {
            "name": "Teatro CCRAN",
            "email": "teatroccran@gmail.com"
          },
          "to": [
            { "email": "%s" }
          ],
          "subject": "%s",
          "htmlContent": "%s",
          "attachment": [
            {
              "content": "%s",
              "name": "%s"
            }
          ]
        }
        """.formatted(para, asunto, contenido, base64, nombreArchivo);

        enviar(body);
    }

    // ==========================
    // MÉTODO COMÚN
    // ==========================
    private void enviar(String body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("api-key", apiKey);

            HttpEntity<String> request =
                    new HttpEntity<>(body, headers);

            restTemplate.postForEntity(
                    BREVO_URL,
                    request,
                    String.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar email con Brevo", e);
        }
    }
}
