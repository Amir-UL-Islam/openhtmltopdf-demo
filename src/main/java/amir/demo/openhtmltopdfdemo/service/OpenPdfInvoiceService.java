package amir.demo.openhtmltopdfdemo.service;

import amir.demo.openhtmltopdfdemo.model.dto.Customer;
import amir.demo.openhtmltopdfdemo.model.dto.Invoice;
import amir.demo.openhtmltopdfdemo.model.dto.InvoiceItem;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenPdfInvoiceService {

    // Color scheme
    private static final Color HEADER_COLOR = new Color(44, 90, 160); // #2c5aa0
    private static final Color TABLE_HEADER_BG = new Color(242, 242, 242);
    private static final Color TABLE_BORDER = new Color(221, 221, 221);

    public byte[] generateInvoicePdf() {
        try {
            // Create invoice with dummy data
            Invoice invoice = createDummyInvoice();

            // Create document
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4, 56, 56, 80, 80); // margins: left, right, top, bottom
            PdfWriter writer = PdfWriter.getInstance(document, baos);

            // Add header and footer event handler
            HeaderFooterPageEvent event = new HeaderFooterPageEvent(invoice);
            writer.setPageEvent(event);

            document.open();

            // Add Bill To section
            addBillToSection(document, invoice);

            // Add spacing
            document.add(new Paragraph(" "));

            // Add Invoice Items Table
            addInvoiceItemsTable(document, invoice);

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF with OpenPDF", e);
        }
    }

    private void addBillToSection(Document document, Invoice invoice) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, HEADER_COLOR);
        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);

        Paragraph billToTitle = new Paragraph("Bill To:", titleFont);
        billToTitle.setSpacingAfter(10);
        document.add(billToTitle);

        Paragraph customerName = new Paragraph(invoice.getCustomer().getName(), normalFont);
        document.add(customerName);

        Paragraph customerAddress = new Paragraph(invoice.getCustomer().getAddress(), normalFont);
        customerAddress.setSpacingAfter(15);
        document.add(customerAddress);
    }

    private void addInvoiceItemsTable(Document document, Invoice invoice) throws DocumentException {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, HEADER_COLOR);
        Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.BLACK);
        Font tableCellFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);

        Paragraph itemsTitle = new Paragraph("Invoice Items", titleFont);
        itemsTitle.setSpacingAfter(10);
        document.add(itemsTitle);

        // Create table with 4 columns
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{4f, 1.5f, 1.5f, 1.5f});
        table.setHeaderRows(1); // This makes the header row repeat on every page

        // Add header cells
        addTableHeader(table, "Description", tableHeaderFont);
        addTableHeader(table, "Quantity", tableHeaderFont);
        addTableHeader(table, "Price", tableHeaderFont);
        addTableHeader(table, "Total", tableHeaderFont);

        // Add data rows
        for (InvoiceItem item : invoice.getItems()) {
            addTableCell(table, item.getDescription(), tableCellFont, Element.ALIGN_LEFT);
            addTableCell(table, String.valueOf(item.getQuantity()), tableCellFont, Element.ALIGN_CENTER);
            addTableCell(table, String.format("%.2f", item.getPrice()), tableCellFont, Element.ALIGN_RIGHT);
            addTableCell(table, String.format("%.2f", item.getTotal()), tableCellFont, Element.ALIGN_RIGHT);
        }

        // Calculate total
        double grandTotal = invoice.getItems().stream()
                .mapToDouble(InvoiceItem::getTotal)
                .sum();

        // Add total row
        PdfPCell totalLabelCell = new PdfPCell(new Phrase("Grand Total:", tableHeaderFont));
        totalLabelCell.setColspan(3);
        totalLabelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalLabelCell.setPadding(8);
        totalLabelCell.setBackgroundColor(TABLE_HEADER_BG);
        totalLabelCell.setBorder(Rectangle.BOX);
        totalLabelCell.setBorderColor(TABLE_BORDER);
        table.addCell(totalLabelCell);

        PdfPCell totalValueCell = new PdfPCell(new Phrase(String.format("%.2f", grandTotal), tableHeaderFont));
        totalValueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalValueCell.setPadding(8);
        totalValueCell.setBackgroundColor(TABLE_HEADER_BG);
        totalValueCell.setBorder(Rectangle.BOX);
        totalValueCell.setBorderColor(TABLE_BORDER);
        table.addCell(totalValueCell);

        document.add(table);
    }

    private void addTableHeader(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(8);
        cell.setBackgroundColor(TABLE_HEADER_BG);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderColor(TABLE_BORDER);
        table.addCell(cell);
    }

    private void addTableCell(PdfPTable table, String text, Font font, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setPadding(8);
        cell.setBorder(Rectangle.BOX);
        cell.setBorderColor(TABLE_BORDER);
        table.addCell(cell);
    }

    private Invoice createDummyInvoice() {
        Customer customer = new Customer("John Doe", "123 Main St, Anytown, USA");
        List<InvoiceItem> items = new ArrayList<>();
        
        // Create 50 items to force multiple pages
        for (int i = 1; i <= 50; i++) {
            items.add(new InvoiceItem("Item " + i + " Description", 1, 15.0 + i));
        }

        return new Invoice("ACME Solutions Inc.", LocalDate.now(), "INV-2025-001", customer, items);
    }

    // Inner class for Header and Footer Page Event
    static class HeaderFooterPageEvent extends PdfPageEventHelper {
        private final Invoice invoice;
        private Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, HEADER_COLOR);
        private Font headerSmallFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.DARK_GRAY);
        private Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 10, Color.DARK_GRAY);

        public HeaderFooterPageEvent(Invoice invoice) {
            this.invoice = invoice;
        }

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            
            // Add Header
            addHeader(cb, document);
            
            // Add Footer
            addFooter(cb, document, writer);
        }

        private void addHeader(PdfContentByte cb, Document document) {
            try {
                // Create header table
                PdfPTable headerTable = new PdfPTable(1);
                headerTable.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
                headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                // Company Name
                PdfPCell companyCell = new PdfPCell(new Phrase(invoice.getCompanyName(), headerFont));
                companyCell.setBorder(Rectangle.NO_BORDER);
                companyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                companyCell.setPaddingBottom(5);
                headerTable.addCell(companyCell);

                // Invoice Number
                PdfPCell invoiceNumCell = new PdfPCell(new Phrase("Invoice #" + invoice.getInvoiceNumber(), headerSmallFont));
                invoiceNumCell.setBorder(Rectangle.NO_BORDER);
                invoiceNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                invoiceNumCell.setPaddingBottom(3);
                headerTable.addCell(invoiceNumCell);

                // Date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                PdfPCell dateCell = new PdfPCell(new Phrase("Date: " + invoice.getInvoiceDate().format(formatter), headerSmallFont));
                dateCell.setBorder(Rectangle.NO_BORDER);
                dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                dateCell.setPaddingBottom(10);
                headerTable.addCell(dateCell);

                // Border line
                PdfPCell borderCell = new PdfPCell();
                borderCell.setBorder(Rectangle.BOTTOM);
                borderCell.setBorderColor(HEADER_COLOR);
                borderCell.setBorderWidth(2);
                borderCell.setFixedHeight(2);
                headerTable.addCell(borderCell);

                // Position header at top
                headerTable.writeSelectedRows(0, -1,
                        document.leftMargin(),
                        document.getPageSize().getHeight() - 20,
                        cb);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void addFooter(PdfContentByte cb, Document document, PdfWriter writer) {
            try {
                // Create footer table
                PdfPTable footerTable = new PdfPTable(1);
                footerTable.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
                footerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                // Border line
                PdfPCell borderCell = new PdfPCell();
                borderCell.setBorder(Rectangle.TOP);
                borderCell.setBorderColor(HEADER_COLOR);
                borderCell.setBorderWidth(2);
                borderCell.setFixedHeight(2);
                borderCell.setPaddingBottom(5);
                footerTable.addCell(borderCell);

                // Thank you message
                PdfPCell thankYouCell = new PdfPCell(new Phrase("Thank you for your business!", footerFont));
                thankYouCell.setBorder(Rectangle.NO_BORDER);
                thankYouCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                thankYouCell.setPaddingTop(5);
                footerTable.addCell(thankYouCell);

                // Page number
                String pageText = "Page " + writer.getPageNumber();
                PdfPCell pageCell = new PdfPCell(new Phrase(pageText, footerFont));
                pageCell.setBorder(Rectangle.NO_BORDER);
                pageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pageCell.setPaddingTop(3);
                footerTable.addCell(pageCell);

                // Position footer at bottom
                footerTable.writeSelectedRows(0, -1,
                        document.leftMargin(),
                        document.bottomMargin() + 10,
                        cb);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}