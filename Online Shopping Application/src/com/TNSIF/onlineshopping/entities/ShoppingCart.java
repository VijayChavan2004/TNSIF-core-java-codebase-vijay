package com.TNSIF.onlineshopping.entities;


import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> items;
    
    // Default constructor
    public ShoppingCart() {
        this.items = new HashMap<>();
    }
    
    // Getter method
    public Map<Product, Integer> getItems() {
        return items;
    }
    
    // Setter method
    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }
    
    // Method to add product to cart
    public void addProduct(Product product, int quantity) {
        if (items.containsKey(product)) {
            items.put(product, items.get(product) + quantity);
        } else {
            items.put(product, quantity);
        }
    }
    
    // Method to remove product from cart
    public void removeProduct(Product product) {
        items.remove(product);
    }
    
    // Method to update quantity of a product
    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
        } else {
            items.put(product, quantity);
        }
    }
    
    // Method to get quantity of a specific product
    public int getQuantity(Product product) {
        return items.getOrDefault(product, 0);
    }
    
    // Method to calculate total price of cart
    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    
    // Method to clear cart
    public void clearCart() {
        items.clear();
    }
    
    // Method to check if cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    // Method to get total items count
    public int getTotalItems() {
        int total = 0;
        for (int quantity : items.values()) {
            total += quantity;
        }
        return total;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopping Cart:\n");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sb.append("Product: ").append(entry.getKey().getName())
              .append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        sb.append("Total Price: ").append(getTotalPrice());
        return sb.toString();
    }
}