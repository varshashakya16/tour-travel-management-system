package com.tour.main;

import com.tour.entity.Booking;
import com.tour.entity.User;
import com.tour.entity.TourPackage;
import com.tour.dao.BookingDAO;
import com.tour.dao.UserDao;
import com.tour.dao.TourPackageDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;

public class BookingSubMenu {

    static BookingDAO bookingDAO = new BookingDAO();
    static UserDao userDao = new UserDao();
    static TourPackageDAO tourPackageDAO = new TourPackageDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void bookingSubMenu() {
        while (true) {
            System.out.println("\nBooking Management");
            System.out.println("1. Create Booking");
            System.out.println("2. View Booking by ID");
            System.out.println("3. View All Bookings");
            System.out.println("4. Update Booking");
            System.out.println("5. Delete Booking");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createBooking();
                    break;
                case 2:
                    viewBookingById();
                    break;
                case 3:
                    viewAllBookings();
                    break;
                case 4:
                    updateBooking();
                    break;
                case 5:
                    deleteBooking();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createBooking() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter tour package ID: ");
        int packageId = scanner.nextInt();
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        String bookingDateString = scanner.next();
        System.out.print("Enter status: ");
        String status = scanner.next();

        // Convert booking date string to java.sql.Date
        Date bookingDate = convertStringToDate(bookingDateString);

        // Fetch the User and TourPackage objects
        User user = userDao.getUserById(userId);
        TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);

        if (user != null && tourPackage != null) {
            Booking booking = new Booking();
            booking.setUser(user);  // Set the User object
            booking.setTourPackage(tourPackage);  // Set the TourPackage object
            booking.setBookingDate(bookingDate);
            booking.setStatus(status);

            bookingDAO.saveBooking(booking);
            System.out.println("Booking created successfully.");
        } else {
            System.out.println("User or Tour Package not found.");
        }
    }

    private static Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = format.parse(dateString);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            return null;
        }
    }

    private static void viewBookingById() {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("User: " + booking.getUser().getName());  // Display user name
            System.out.println("Package: " + booking.getTourPackage().getDestination());  // Display package destination
            System.out.println("Booking Date: " + booking.getBookingDate());
            System.out.println("Status: " + booking.getStatus());
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void viewAllBookings() {
        List<Booking> bookings = bookingDAO.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            bookings.forEach(booking -> {
                System.out.println("Booking ID: " + booking.getBookingId());
                System.out.println("User: " + booking.getUser().getName());  // Display user name
                System.out.println("Package: " + booking.getTourPackage().getDestination());  // Display package destination
                System.out.println("Booking Date: " + booking.getBookingDate());
                System.out.println("Status: " + booking.getStatus());
                System.out.println();
            });
        }
    }

    private static void updateBooking() {
        System.out.print("Enter booking ID to update: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Booking booking = bookingDAO.getBookingById(bookingId);

        if (booking != null) {
            System.out.print("Enter new status: ");
            String status = scanner.nextLine();

            // Update the booking
            booking.setStatus(status);
            bookingDAO.updateBooking(booking);
            System.out.println("Booking updated successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    private static void deleteBooking() {
        System.out.print("Enter booking ID to delete: ");
        int bookingId = scanner.nextInt();
        bookingDAO.deleteBooking(bookingId);
        System.out.println("Booking deleted successfully.");
    }
}
