package com.TNSIF.onlineshopping.services;

import com.TNSIF.onlineshopping.entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> products;
    
    // Constructor
    public ProductService() {
        this.products = new ArrayList<>();
    }
    
    // Method to add a product
    public boolean addProduct(Product product) {
        if (findProductById(product.getProductId()) == null) {
            products.add(product);
            return true;
        }
        return false; // Product with same ID already exists
    }
    
    // Method to remove a product by ID
    public boolean removeProduct(int productId) {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false; // Product not found
    }
    
    // Method to find a product by ID
    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
    
    // Method to get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    // Method to update product details
    public boolean updateProduct(int productId, String name, double price, int stockQuantity) {
        Product product = findProductById(productId);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setStockQuantity(stockQuantity);
            return true;
        }
        return false;
    }
    
    // Method to check if product is available in required quantity
    public boolean isProductAvailable(int productId, int requiredQuantity) {
        Product product = findProductById(productId);
        return product != null && product.getStockQuantity() >= requiredQuantity;
    }
    
    // Method to reduce stock quantity
    public boolean reduceStock(int productId, int quantity) {
        Product product = findProductById(productId);
        if (product != null && product.getStockQuantity() >= quantity) {
            product.reduceStock(quantity);
            return true;
        }
        return false;
    }
    
    // Method to increase stock quantity
    public boolean increaseStock(int productId, int quantity) {
        Product product = findProductById(productId);
        if (product != null) {
            product.increaseStock(quantity);
            return true;
        }
        return false;
    }
    
    // Method to display all products
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}