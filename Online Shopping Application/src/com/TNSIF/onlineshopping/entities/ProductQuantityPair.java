package com.TNSIF.onlineshopping.entities;

public class ProductQuantityPair {
    private Product product;
    private int quantity;
    
    // Default constructor
    public ProductQuantityPair() {
    }
    
    // Parameterized constructor
    public ProductQuantityPair(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    // Getter methods
    public Product getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    // Setter methods
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Method to calculate total price for this item
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
    
    @Override
    public String toString() {
        return "Product: " + product.getName() + ", Quantity: " + quantity;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductQuantityPair that = (ProductQuantityPair) obj;
        return product.equals(that.product);
    }
    
    @Override
    public int hashCode() {
        return product.hashCode();
    }
}