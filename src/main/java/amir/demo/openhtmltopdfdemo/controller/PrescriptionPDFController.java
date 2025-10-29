package amir.demo.openhtmltopdfdemo.controller;

import amir.demo.openhtmltopdfdemo.Utils;
import amir.demo.openhtmltopdfdemo.service.PrescriptionGenerationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PrescriptionPDFController {

    AtomicLong counter = new AtomicLong();

    @Autowired
    private PrescriptionGenerationService prescriptionGenerationService;

    @GetMapping("/generate/prescription")
    public void generatePrescriptionPdf(HttpServletResponse response) throws IOException {
        byte[] pdfBytes = prescriptionGenerationService.generatePrescriptionPdf();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + Utils.getString(counter.getAndIncrement()) + "_prescription.pdf");
        response.setContentLength(pdfBytes.length);
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }

}