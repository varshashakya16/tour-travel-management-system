package com.tour.main;

import com.tour.entity.Review;
import com.tour.entity.User;
import com.tour.entity.TourPackage;
import com.tour.dao.ReviewDAO;
import com.tour.dao.UserDao;
import com.tour.dao.TourPackageDAO;

import java.util.List;
import java.util.Scanner;

public class ReviewSubMenu {

    static ReviewDAO reviewDAO = new ReviewDAO();
    static UserDao userDao = new UserDao();
    static TourPackageDAO tourPackageDAO = new TourPackageDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void reviewSubMenu() {
        while (true) {
            System.out.println("\nReview Management");
            System.out.println("1. Create Review");
            System.out.println("2. View Review by ID");
            System.out.println("3. View All Reviews");
            System.out.println("4. Update Review");
            System.out.println("5. Delete Review");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createReview();
                    break;
                case 2:
                    viewReviewById();
                    break;
                case 3:
                    viewAllReviews();
                    break;
                case 4:
                    updateReview();
                    break;
                case 5:
                    deleteReview();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createReview() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter tour package ID: ");
        int packageId = scanner.nextInt();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter review text: ");
        String reviewText = scanner.nextLine();

        // Fetch the User and TourPackage objects
        User user = userDao.getUserById(userId);
        TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);

        if (user != null && tourPackage != null) {
            Review review = new Review();
            review.setUser(user);  // Set the User object
            review.setTourPackage(tourPackage);  // Set the TourPackage object
            review.setRating(rating);
            review.setComment(reviewText);

            reviewDAO.saveReview(review);
            System.out.println("Review created successfully.");
        } else {
            System.out.println("User or Tour Package not found.");
        }
    }

    private static void viewReviewById() {
        System.out.print("Enter review ID: ");
        int reviewId = scanner.nextInt();
        Review review = reviewDAO.getReviewById(reviewId);
        if (review != null) {
            System.out.println("Review ID: " + review.getReviewId());
            System.out.println("User: " + review.getUser().getName());  // Display user name instead of user ID
            System.out.println("Package: " + review.getTourPackage().getDestination());  // Display package destination
            System.out.println("Rating: " + review.getRating());
            System.out.println("Review Text: " + review.getComment());
        } else {
            System.out.println("Review not found.");
        }
    }

    private static void viewAllReviews() {
        List<Review> reviews = reviewDAO.getAllReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews found.");
        } else {
            reviews.forEach(review -> {
                System.out.println("Review ID: " + review.getReviewId());
                System.out.println("User: " + review.getUser().getName());  // Display user name
                System.out.println("Package: " + review.getTourPackage().getDestination());  // Display package destination
                System.out.println("Rating: " + review.getRating());
                System.out.println("Review Text: " + review.getComment());
                System.out.println();
            });
        }
    }

    private static void updateReview() {
        System.out.print("Enter review ID to update: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Review review = reviewDAO.getReviewById(reviewId);

        if (review != null) {
            System.out.print("Enter new rating (1-5): ");
            int rating = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new review text: ");
            String reviewText = scanner.nextLine();

            // Update the review
            review.setRating(rating);
            review.setComment(reviewText);

            reviewDAO.updateReview(review);
            System.out.println("Review updated successfully.");
        } else {
            System.out.println("Review not found.");
        }
    }

    private static void deleteReview() {
        System.out.print("Enter review ID to delete: ");
        int reviewId = scanner.nextInt();
        reviewDAO.deleteReview(reviewId);
        System.out.println("Review deleted successfully.");
    }
}
