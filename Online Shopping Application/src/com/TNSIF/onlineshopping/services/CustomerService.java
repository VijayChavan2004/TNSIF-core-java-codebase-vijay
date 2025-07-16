package com.TNSIF.onlineshopping.services;

import com.TNSIF.onlineshopping.entities.Customer;
import com.TNSIF.onlineshopping.entities.Product;
import com.TNSIF.onlineshopping.entities.ProductQuantityPair;
import com.TNSIF.onlineshopping.entities.Order;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customers;
    private ProductService productService;
    private OrderService orderService;
    
    // Constructor
    public CustomerService(ProductService productService, OrderService orderService) {
        this.customers = new ArrayList<>();
        this.productService = productService;
        this.orderService = orderService;
    }
    
    // Method to create a new customer
    public boolean createCustomer(int userId, String username, String email, String address) {
        if (findCustomerById(userId) == null) {
            Customer customer = new Customer(userId, username, email, address);
            customers.add(customer);
            return true;
        }
        return false; // Customer with same ID already exists
    }
    
    // Method to find customer by ID
    public Customer findCustomerById(int userId) {
        for (Customer customer : customers) {
            if (customer.getUserId() == userId) {
                return customer;
            }
        }
        return null;
    }
    
    // Method to get all customers
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
    
    // Method to display all customers
    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("Customers:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
    
    // Method to view all products (delegates to ProductService)
    public void viewProducts() {
        productService.displayProducts();
    }
    
    // Method to add product to shopping cart
    public boolean addToCart(int customerId, int productId, int quantity) {
        Customer customer = findCustomerById(customerId);
        Product product = productService.findProductById(productId);
        
        if (customer != null && product != null && productService.isProductAvailable(productId, quantity)) {
            customer.getShoppingCart().addProduct(product, quantity);
            return true;
        }
        return false;
    }
    
    // Method to remove product from shopping cart
    public boolean removeFromCart(int customerId, int productId) {
        Customer customer = findCustomerById(customerId);
        Product product = productService.findProductById(productId);
        
        if (customer != null && product != null) {
            customer.getShoppingCart().removeProduct(product);
            return true;
        }
        return false;
    }
    
    // Method to place order from shopping cart
    public boolean placeOrderFromCart(int customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null && !customer.getShoppingCart().isEmpty()) {
            List<ProductQuantityPair> orderProducts = new ArrayList<>();
            
            for (Product product : customer.getShoppingCart().getItems().keySet()) {
                int quantity = customer.getShoppingCart().getQuantity(product);
                orderProducts.add(new ProductQuantityPair(product, quantity));
            }
            
            Order order = orderService.createOrder(customer, orderProducts);
            if (order != null) {
                customer.getShoppingCart().clearCart();
                return true;
            }
        }
        return false;
    }
    
    // Method to place order directly
    public boolean placeOrder(int customerId, List<ProductQuantityPair> products) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            Order order = orderService.createOrder(customer, products);
            return order != null;
        }
        return false;
    }
    
    // Method to view customer orders
    public void viewCustomerOrders(int customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer != null) {
            orderService.displayCustomerOrders(customer);
        } else {
            System.out.println("Customer not found.");
        }
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
