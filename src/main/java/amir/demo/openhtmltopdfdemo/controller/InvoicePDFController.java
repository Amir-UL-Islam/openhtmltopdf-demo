package amir.demo.openhtmltopdfdemo.controller;

import amir.demo.openhtmltopdfdemo.Utils;
import amir.demo.openhtmltopdfdemo.model.dto.Medicine;
import amir.demo.openhtmltopdfdemo.service.InvoiceGenerationService;
import amir.demo.openhtmltopdfdemo.service.OpenPdfInvoiceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
class InvoicePDFController {

    AtomicLong counter = new AtomicLong();

    @Autowired
    private OpenPdfInvoiceService openPdfInvoiceService;
    @Autowired
    private InvoiceGenerationService invoiceGenerationService;

    @GetMapping("/generate/invoice")
    public void generatePdf(HttpServletResponse response) throws IOException {
        // Sample data
        Map<String, Object> data = Map.of(
                "hospitalName", "City General Hospital",
                "patientName", "John Doe",
                "doctorName", "Dr. Smith",
                "medicines", List.of(
                        new Medicine("Amoxicillin", "500mg", "Take one pill every 8 hours"),
                        new Medicine("Paracetamol", "500mg", "Take as needed for pain")
                )
        );

        byte[] pdfBytes = invoiceGenerationService.generateInvoicePdf();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + Utils.getString(counter.getAndIncrement()) + "-invoice.pdf");
        response.getOutputStream().write(pdfBytes);
    }

    @GetMapping("/generate/invoice/openpdf")
    public void generateFromOpenPdf(HttpServletResponse response) throws IOException {
        // Sample data
        byte[] pdfBytes = openPdfInvoiceService.generateInvoicePdf();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + Utils.getString(counter.getAndIncrement()) + "-invoice.pdf");
        response.getOutputStream().write(pdfBytes);
    }
}

