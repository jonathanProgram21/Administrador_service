package com.administracion.administration.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrevoEmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public void enviarCorreo(String para, String asunto, String html) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

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
        """.formatted(para, asunto, html);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        restTemplate.postForEntity(
            "https://api.brevo.com/v3/smtp/email",
            request,
            String.class
        );
    }
}