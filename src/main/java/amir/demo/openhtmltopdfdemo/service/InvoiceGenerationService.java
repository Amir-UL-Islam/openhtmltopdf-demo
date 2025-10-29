package amir.demo.openhtmltopdfdemo.service;

import amir.demo.openhtmltopdfdemo.model.dto.Customer;
import amir.demo.openhtmltopdfdemo.model.dto.Invoice;
import amir.demo.openhtmltopdfdemo.model.dto.InvoiceItem;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceGenerationService {
    @Autowired
    private SpringTemplateEngine templateEngine;

    public byte[] generateInvoicePdf() {
        // 1. Generate Dummy Data for a multi-page document
        Invoice invoice = createDummyInvoice();

        // 2. Process Thymeleaf template to get final HTML string
        Context context = new Context();
        context.setVariable("invoice", invoice);
        String htmlContent = templateEngine.process("invoice", context);

        // 3. Use OpenHtmlToPdf to convert the HTML string to a PDF
        try (OutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
            return ((ByteArrayOutputStream) os).toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    private Invoice createDummyInvoice() {
        // Create an invoice with enough items to force multiple pages
        Customer customer = new Customer("John Doe", "123 Main St, Anytown, USA");
        List<InvoiceItem> items = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            items.add(new InvoiceItem("Item " + i + " Description", 1, 15.0 + i));
        }

        return new Invoice("ACME Solutions Inc.", LocalDate.now(), "INV-2025-001", customer, items);
    }
}