package amir.demo.openhtmltopdfdemo.model.dto;

import java.time.LocalDate;
import java.util.List;

public class Invoice {
    private String companyName;
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private Customer customer;
    private List<InvoiceItem> items;

    public Invoice(String companyName, LocalDate invoiceDate, String invoiceNumber, Customer customer, List<InvoiceItem> items) {
        this.companyName = companyName;
        this.invoiceDate = invoiceDate;
        this.invoiceNumber = invoiceNumber;
        this.customer = customer;
        this.items = items;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    // Constructors, getters, setters
}

