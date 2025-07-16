package com.TNSIF.onlineshopping.services;

import com.TNSIF.onlineshopping.entities.Admin;
import com.TNSIF.onlineshopping.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Admin> admins;
    private ProductService productService;
    private OrderService orderService;
    
    // Constructor
    public AdminService(ProductService productService, OrderService orderService) {
        this.admins = new ArrayList<>();
        this.productService = productService;
        this.orderService = orderService;
    }
    
    // Method to create a new admin
    public boolean createAdmin(int userId, String username, String email) {
        if (findAdminById(userId) == null) {
            Admin admin = new Admin(userId, username, email);
            admins.add(admin);
            return true;
        }
        return false; // Admin with same ID already exists
    }
    
    // Method to find admin by ID
    public Admin findAdminById(int userId) {
        for (Admin admin : admins) {
            if (admin.getUserId() == userId) {
                return admin;
            }
        }
        return null;
    }
    
    // Method to get all admins
    public List<Admin> getAllAdmins() {
        return new ArrayList<>(admins);
    }
    
    // Method to display all admins
    public void displayAdmins() {
        if (admins.isEmpty()) {
            System.out.println("No admins available.");
        } else {
            System.out.println("Admins:");
            for (Admin admin : admins) {
                System.out.println(admin);
            }
        }
    }
    
    // Method to add product (delegates to ProductService)
    public boolean addProduct(int productId, String name, double price, int stockQuantity) {
        Product product = new Product(productId, name, price, stockQuantity);
        return productService.addProduct(product);
    }
    
    // Method to remove product (delegates to ProductService)
    public boolean removeProduct(int productId) {
        return productService.removeProduct(productId);
    }
    
    // Method to view all products (delegates to ProductService)
    public void viewProducts() {
        productService.displayProducts();
    }
    
    // Method to update order status (delegates to OrderService)
    public boolean updateOrderStatus(int orderId, String newStatus) {
        return orderService.updateOrderStatus(orderId, newStatus);
    }
    
    // Method to view all orders (delegates to OrderService)
    public void viewAllOrders() {
        orderService.displayAllOrders();
    }
    
    // Method to get product service
    public ProductService getProductService() {
        return productService;
    }
    
    // Method to get order service
    public OrderService getOrderService() {
        return orderService;
    }
}