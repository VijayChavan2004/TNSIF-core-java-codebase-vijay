package com.TNSIF.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;
    private List<ProductQuantityPair> products;
    private String status;
    
    // Default constructor
    public Order() {
        this.products = new ArrayList<>();
        this.status = "Pending";
    }
    
    // Parameterized constructor
    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }
    
    // Parameterized constructor with products
    public Order(int orderId, Customer customer, List<ProductQuantityPair> products) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products != null ? products : new ArrayList<>();
        this.status = "Pending";
    }
    
    // Getter methods
    public int getOrderId() {
        return orderId;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public List<ProductQuantityPair> getProducts() {
        return products;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setter methods
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void setProducts(List<ProductQuantityPair> products) {
        this.products = products;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // Method to add product to order
    public void addProduct(ProductQuantityPair productQuantityPair) {
        this.products.add(productQuantityPair);
    }
    
    // Method to remove product from order
    public void removeProduct(ProductQuantityPair productQuantityPair) {
        this.products.remove(productQuantityPair);
    }
    
    // Method to calculate total order amount
    public double getTotalAmount() {
        double total = 0.0;
        for (ProductQuantityPair pair : products) {
            total += pair.getTotalPrice();
        }
        return total;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append(", Customer: ").append(customer.getUsername())
          .append(", Status: ").append(status).append("\n");
        for (ProductQuantityPair pair : products) {
            sb.append(pair.toString()).append("\n");
        }
        return sb.toString().trim();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return orderId == order.orderId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(orderId);
    }
}