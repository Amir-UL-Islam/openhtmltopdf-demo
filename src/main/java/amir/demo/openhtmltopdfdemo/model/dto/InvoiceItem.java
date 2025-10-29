package amir.demo.openhtmltopdfdemo.model.dto;

public class InvoiceItem {
    private String description;
    private int quantity;
    private double price;
    
    // Constructors, getters, setters
    public double getTotal() {
        return quantity * price;
    }

    public InvoiceItem(String description, int quantity, double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
