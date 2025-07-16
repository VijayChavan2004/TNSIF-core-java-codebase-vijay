package com.TNSIF.onlineshopping.entities;
public class User {
    protected int userId;
    protected String username;
    protected String email;
    
    // Default constructor
    public User() {
    }
    
    // Parameterized constructor
    public User(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
    
    // Getter methods
    public int getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    // Setter methods
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Email: " + email;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return userId == user.userId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}
