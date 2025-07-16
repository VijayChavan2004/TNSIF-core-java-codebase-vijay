package com.TNSIF.onlineshopping.application;

import com.TNSIF.onlineshopping.entities.*;
import com.TNSIF.onlineshopping.services.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProductService productService = new ProductService();
    private static OrderService orderService = new OrderService(productService);
    private static AdminService adminService = new AdminService(productService, orderService);
    private static CustomerService customerService = new CustomerService(productService, orderService);
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void displayMainMenu() {
        System.out.println("\n1. Admin Menu");
        System.out.println("2. Customer Menu");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }
    
    private static void adminMenu() {
        while (true) {
            displayAdminMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    viewProducts();
                    break;
                case 4:
                    createAdmin();
                    break;
                case 5:
                    viewAdmins();
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 7:
                    viewOrders();
                    break;
                case 8:
                    System.out.println("Exiting Admin...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void displayAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. View Products");
        System.out.println("4. Create Admin");
        System.out.println("5. View Admins");
        System.out.println("6. Update Order Status");
        System.out.println("7. View Orders");
        System.out.println("8. Return");
        System.out.print("Choose an option: ");
    }
    
    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        
        System.out.print("Enter Stock Quantity: ");
        int stockQuantity = scanner.nextInt();
        
        if (adminService.addProduct(productId, name, price, stockQuantity)) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add product. Product ID may already exist.");
        }
    }
    
    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int productId = scanner.nextInt();
        
        if (adminService.removeProduct(productId)) {
            System.out.println("Product removed successfully!");
        } else {
            System.out.println("Product not found.");
        }
    }
    
    private static void viewProducts() {
        adminService.viewProducts();
    }
    
    private static void createAdmin() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        if (adminService.createAdmin(userId, username, email)) {
            System.out.println("Admin created successfully!");
        } else {
            System.out.println("Failed to create admin. User ID may already exist.");
        }
    }
    
    private static void viewAdmins() {
        adminService.displayAdmins();
    }
    
    private static void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
        String status = scanner.nextLine();
        
        if (adminService.updateOrderStatus(orderId, status)) {
            System.out.println("Order status updated successfully!");
        } else {
            System.out.println("Order not found.");
        }
    }
    
    private static void viewOrders() {
        adminService.viewAllOrders();
    }
    
    private static void customerMenu() {
        while (true) {
            displayCustomerMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    placeOrder();
                    break;
                case 4:
                    viewCustomerOrders();
                    break;
                case 5:
                    viewProducts();
                    break;
                case 6:
                    System.out.println("Exiting Customer Menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void displayCustomerMenu() {
        System.out.println("\nCustomer Menu:");
        System.out.println("1. Create Customer");
        System.out.println("2. View Customers");
        System.out.println("3. Place Order");
        System.out.println("4. View Orders");
        System.out.println("5. View Products");
        System.out.println("6. Return");
        System.out.print("Choose an option: ");
    }
    
    private static void createCustomer() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        
        if (customerService.createCustomer(userId, username, email, address)) {
            System.out.println("Customer created successfully!");
        } else {
            System.out.println("Failed to create customer. User ID may already exist.");
        }
    }
    
    private static void viewCustomers() {
        customerService.displayCustomers();
    }
    
    private static void placeOrder() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }
        
        List<ProductQuantityPair> orderProducts = new ArrayList<>();
        
        while (true) {
            System.out.print("Enter Product ID to add to order (or -1 to complete): ");
            int productId = scanner.nextInt();
            
            if (productId == -1) {
                break;
            }
            
            Product product = productService.findProductById(productId);
            if (product == null) {
                System.out.println("Product not found.");
                continue;
            }
            
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            
            if (quantity <= 0) {
                System.out.println("Invalid quantity.");
                continue;
            }
            
            orderProducts.add(new ProductQuantityPair(product, quantity));
        }
        
        if (!orderProducts.isEmpty()) {
            if (customerService.placeOrder(customerId, orderProducts)) {
                System.out.println("Order placed successfully!");
            } else {
                System.out.println("Failed to place order. Check product availability.");
            }
        } else {
            System.out.println("No products added to order.");
        }
    }
    
    private static void viewCustomerOrders() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        
        customerService.viewCustomerOrders(customerId);
    }
}
