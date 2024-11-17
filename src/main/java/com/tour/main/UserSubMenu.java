package com.tour.main;

import com.tour.entity.User;
import com.tour.dao.UserDao;
import java.util.Scanner;
import java.util.List;
public class UserSubMenu {

    static UserDao userDao = new UserDao();
    static Scanner scanner = new Scanner(System.in);

    public static void userSubMenu() {
        while (true) {
            System.out.println("\nUser Management");
            System.out.println("1. Create User");
            System.out.println("2. View User by ID");
            System.out.println("3. View All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    viewUserById();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setAddress(address);
        userDao.saveUser(user);
        System.out.println("User created successfully.");
    }

    private static void viewUserById() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        User user = userDao.getUserById(userId);
        if (user != null) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhone());
            System.out.println("Address: " + user.getAddress());
        } else {
            System.out.println("User not found.");
        }
    }

    private static void viewAllUsers() {
        List<User> users = userDao.getAllUsers();  // Explicitly declare List<User>
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {  // For-each loop
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone: " + user.getPhone());
                System.out.println("Address: " + user.getAddress());
                System.out.println();
            }
        }
    }


    private static void updateUser() {
        System.out.print("Enter user ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        User user = userDao.getUserById(userId);

        if (user != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter new address: ");
            String address = scanner.nextLine();

            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);

            userDao.updateUser(user);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int userId = scanner.nextInt();
        userDao.deleteUser(userId);
    }
}
