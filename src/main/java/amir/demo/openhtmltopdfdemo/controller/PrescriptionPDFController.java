package amir.demo.openhtmltopdfdemo.controller;

import amir.demo.openhtmltopdfdemo.service.PrescriptionGenerationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PrescriptionPDFController {

    @Autowired
    private PrescriptionGenerationService prescriptionGenerationService;

    @GetMapping("/generate/prescription")
    public void generatePrescriptionPdf(HttpServletResponse response) throws IOException {
        byte[] pdfBytes = prescriptionGenerationService.generatePrescriptionPdf();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=prescription.pdf");
        response.setContentLength(pdfBytes.length);
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }
}