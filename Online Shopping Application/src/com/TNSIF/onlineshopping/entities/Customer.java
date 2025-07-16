package com.TNSIF.onlineshopping.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    
    // Default constructor
    public Customer() {
        super();
        this.orders = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }
    
    // Parameterized constructor
    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.address = address;
        this.orders = new ArrayList<>();
        this.shoppingCart = new ShoppingCart();
    }
    
    // Getter methods
    public String getAddress() {
        return address;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    // Setter methods
    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    // Method to add order
    public void addOrder(Order order) {
        this.orders.add(order);
    }
    
    // Method to remove order
    public void removeOrder(Order order) {
        this.orders.remove(order);
    }
    
    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Email: " + email + ", Address: " + address;
    }
}
