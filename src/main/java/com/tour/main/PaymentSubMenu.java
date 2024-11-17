package com.tour.main;

import com.tour.entity.Payment;
import com.tour.entity.Booking;
import com.tour.dao.PaymentDAO;
import com.tour.dao.BookingDAO;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class PaymentSubMenu {

    static PaymentDAO paymentDAO = new PaymentDAO();
    static BookingDAO bookingDAO = new BookingDAO(); // DAO for fetching Booking details
    static Scanner scanner = new Scanner(System.in);

    public static void paymentSubMenu() {
        while (true) {
            System.out.println("\nPayment Management");
            System.out.println("1. Create Payment");
            System.out.println("2. View Payment by ID");
            System.out.println("3. View All Payments");
            System.out.println("4. Update Payment");
            System.out.println("5. Delete Payment");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createPayment();
                    break;
                case 2:
                    viewPaymentById();
                    break;
                case 3:
                    viewAllPayments();
                    break;
                case 4:
                    updatePayment();
                    break;
                case 5:
                    deletePayment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createPayment() {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking ID. Please try again.");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter payment date (yyyy-mm-dd): ");
        Date paymentDate = Date.valueOf(scanner.nextLine());

        System.out.print("Enter payment status (Paid/Unpaid): ");
        String paymentStatus = scanner.nextLine();

        System.out.print("Enter payment method (e.g., Credit Card, Cash): ");
        String paymentMethod = scanner.nextLine();

        System.out.print("Enter transaction ID: ");
        String transactionId = scanner.nextLine();

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setPaymentDate(paymentDate);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentMethod(paymentMethod);
        payment.setTransactionId(transactionId);

        paymentDAO.savePayment(payment);
        System.out.println("Payment created successfully.");
    }

    private static void viewPaymentById() {
        System.out.print("Enter payment ID: ");
        int paymentId = scanner.nextInt();
        Payment payment = paymentDAO.getPaymentById(paymentId);
        if (payment != null) {
            System.out.println("Payment ID: " + payment.getPaymentId());
            System.out.println("Booking ID: " + payment.getBooking().getBookingId());
            System.out.println("Amount: " + payment.getAmount());
            System.out.println("Payment Date: " + payment.getPaymentDate());
            System.out.println("Payment Status: " + payment.getPaymentStatus());
            System.out.println("Payment Method: " + payment.getPaymentMethod());
            System.out.println("Transaction ID: " + payment.getTransactionId());
        } else {
            System.out.println("Payment not found.");
        }
    }

    private static void viewAllPayments() {
        List<Payment> payments = paymentDAO.getAllPayments();
        if (payments == null || payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            for (Payment payment : payments) {
                System.out.println("Payment ID: " + payment.getPaymentId());
                System.out.println("Booking ID: " + payment.getBooking().getBookingId());
                System.out.println("Amount: " + payment.getAmount());
                System.out.println("Payment Date: " + payment.getPaymentDate());
                System.out.println("Payment Status: " + payment.getPaymentStatus());
                System.out.println("Payment Method: " + payment.getPaymentMethod());
                System.out.println("Transaction ID: " + payment.getTransactionId());
                System.out.println();
            }
        }
    }

    private static void updatePayment() {
        System.out.print("Enter payment ID to update: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Payment payment = paymentDAO.getPaymentById(paymentId);

        if (payment != null) {
            System.out.print("Enter new payment status (Paid/Unpaid): ");
            String paymentStatus = scanner.nextLine();

            System.out.print("Enter new payment method (e.g., Credit Card, Cash): ");
            String paymentMethod = scanner.nextLine();

            System.out.print("Enter new transaction ID: ");
            String transactionId = scanner.nextLine();

            payment.setPaymentStatus(paymentStatus);
            payment.setPaymentMethod(paymentMethod);
            payment.setTransactionId(transactionId);

            paymentDAO.updatePayment(payment);
            System.out.println("Payment updated successfully.");
        } else {
            System.out.println("Payment not found.");
        }
    }

    private static void deletePayment() {
        System.out.print("Enter payment ID to delete: ");
        int paymentId = scanner.nextInt();
        paymentDAO.deletePayment(paymentId);
        System.out.println("Payment deleted successfully.");
    }
}
