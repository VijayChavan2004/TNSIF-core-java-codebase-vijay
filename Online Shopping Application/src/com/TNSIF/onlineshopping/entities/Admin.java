package com.TNSIF.onlineshopping.entities;

public class Admin extends User {
    
    // Default constructor
    public Admin() {
        super();
    }
    
    // Parameterized constructor
    public Admin(int userId, String username, String email) {
        super(userId, username, email);
    }
    
    @Override
    public String toString() {
        return "Admin - User ID: " + userId + ", Username: " + username + ", Email: " + email;
    }
}