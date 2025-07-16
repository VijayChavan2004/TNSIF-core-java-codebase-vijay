package com.TNSIF.onlineshopping.entities;

public class Product 
{
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;
    
    // Default constructor
    public Product() {
    }
    
    // Parameterized constructor
    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    
    // Getter methods
    public int getProductId() {
        return productId;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    // Setter methods
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    // Method to reduce stock quantity
    public void reduceStock(int quantity) {
        if (this.stockQuantity >= quantity) {
            this.stockQuantity -= quantity;
        }
    }
    
    // Method to increase stock quantity
    public void increaseStock(int quantity) {
        this.stockQuantity += quantity;
    }
    
    @Override
    public String toString() {
        return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", stockQuantity=" + stockQuantity + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId == product.productId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(productId);
    }
}