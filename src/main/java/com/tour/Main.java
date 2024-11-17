package com.tour;

import com.tour.dao.UserDao;
import com.tour.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        
        // Create a new user
        User newUser = new User();
        newUser.setName("Raj");
        newUser.setEmail("Raj.doe@example.com");
        newUser.setPassword("Password123");  // Make sure this is set

        // Save user
        userDao.saveUser(newUser);
        
        // Fetch user by ID
        User fetchedUser = userDao.getUserById(newUser.getUserId());
        System.out.println(fetchedUser.getName() + " - " + fetchedUser.getEmail());
        
        // Update user
        fetchedUser.setEmail("new.email@example.com");
        userDao.updateUser(fetchedUser);
        
        // Delete user
        userDao.deleteUser(fetchedUser.getUserId());
    }
}
