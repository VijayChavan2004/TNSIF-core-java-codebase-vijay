package com.TNSIF.onlineshopping.services;

import com.TNSIF.onlineshopping.entities.Order;
import com.TNSIF.onlineshopping.entities.Customer;
import com.TNSIF.onlineshopping.entities.Product;
import com.TNSIF.onlineshopping.entities.ProductQuantityPair;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;
    private int nextOrderId;
    private ProductService productService;
    
    // Constructor
    public OrderService(ProductService productService) {
        this.orders = new ArrayList<>();
        this.nextOrderId = 1;
        this.productService = productService;
    }
    
    // Method to create a new order
    public Order createOrder(Customer customer, List<ProductQuantityPair> products) {
        // Validate stock availability for all products
        for (ProductQuantityPair pair : products) {
            if (!productService.isProductAvailable(pair.getProduct().getProductId(), pair.getQuantity())) {
                System.out.println("Insufficient stock for product: " + pair.getProduct().getName());
                return null;
            }
        }
        
        // Create order
        Order order = new Order(nextOrderId++, customer, products);
        orders.add(order);
        customer.addOrder(order);
        
        // Reduce stock for all products
        for (ProductQuantityPair pair : products) {
            productService.reduceStock(pair.getProduct().getProductId(), pair.getQuantity());
        }
        
        return order;
    }
    
    // Method to find order by ID
    public Order findOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }
    
    // Method to get all orders
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }
    
    // Method to get orders by customer
    public List<Order> getOrdersByCustomer(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().getUserId() == customer.getUserId()) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
    
    // Method to update order status
    public boolean updateOrderStatus(int orderId, String newStatus) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            return true;
        }
        return false;
    }
    
    // Method to cancel order
    public boolean cancelOrder(int orderId) {
        Order order = findOrderById(orderId);
        if (order != null && order.getStatus().equals("Pending")) {
            // Restore stock
            for (ProductQuantityPair pair : order.getProducts()) {
                productService.increaseStock(pair.getProduct().getProductId(), pair.getQuantity());
            }
            order.setStatus("Cancelled");
            return true;
        }
        return false;
    }
    
    // Method to display orders for a customer
    public void displayCustomerOrders(Customer customer) {
        List<Order> customerOrders = getOrdersByCustomer(customer);
        if (customerOrders.isEmpty()) {
            System.out.println("No orders found for customer: " + customer.getUsername());
        } else {
            System.out.println("Orders:");
            for (Order order : customerOrders) {
                System.out.println("Order ID: " + order.getOrderId() + ", Status: " + order.getStatus());
                for (ProductQuantityPair pair : order.getProducts()) {
                    System.out.println(pair.toString());
                }
            }
        }
    }
    
    // Method to display all orders
    public void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomer().getUsername() + ", Status: " + order.getStatus());
                for (ProductQuantityPair pair : order.getProducts()) {
                    System.out.println(pair.toString());
                }
            }
        }
    }
    
    // Method to get next order ID
    public int getNextOrderId() {
        return nextOrderId;
    }
}
