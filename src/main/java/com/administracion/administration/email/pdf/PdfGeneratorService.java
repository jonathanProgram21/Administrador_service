package com.administracion.administration.email.pdf;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.administracion.administration.services.DTOs_de_respuestas.PreguntaRespuestaDTO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

@Service
public class PdfGeneratorService {
    public byte[] generarPdfPorArea(String area, List<PreguntaRespuestaDTO> datos) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Título
        document.add(new Paragraph("Área: " + area.toUpperCase())
                .setBold()
                .setFontSize(14));

        // Tabla (2 columnas)
        Table table = new Table(2);
        table.addCell("Descripción");
        table.addCell("Respuesta");

        for (PreguntaRespuestaDTO dto : datos) {
            table.addCell(dto.getDescripcion());
            table.addCell(dto.getRespuesta());
        }

        document.add(table);
        document.close();

        return outputStream.toByteArray();
    }
}
