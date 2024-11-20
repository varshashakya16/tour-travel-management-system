package com.tour.main;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Welcome to the Tour Management System");
                System.out.println("1. User Management");
                System.out.println("2. Tour Package Management");
                System.out.println("3. Booking Management");
                System.out.println("4. Payment Management");
                System.out.println("5. Review Management");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        UserSubMenu.userSubMenu();
                        break;
                    case 2:
                        TourPackageSubMenu.tourPackageSubMenu();
                        break;
                    case 3:
                        BookingSubMenu.bookingSubMenu();
                        break;
                    case 4:
                        PaymentSubMenu.paymentSubMenu();
                        break;
                    case 5:
                        ReviewSubMenu.reviewSubMenu();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return; // Exit the program
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } finally {
            scanner.close(); // Close the scanner to avoid resource leak
        }
    }
}
