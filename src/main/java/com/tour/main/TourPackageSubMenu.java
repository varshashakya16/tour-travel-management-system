package com.tour.main;

import com.tour.entity.TourPackage;
import com.tour.dao.TourPackageDAO;
import java.util.Scanner;
import java.util.List;
public class TourPackageSubMenu {

    static TourPackageDAO tourPackageDAO = new TourPackageDAO();
    static Scanner scanner = new Scanner(System.in);

    public static void tourPackageSubMenu() {
        while (true) {
            System.out.println("\nTour Package Management");
            System.out.println("1. Create Tour Package");
            System.out.println("2. View Tour Package by ID");
            System.out.println("3. View All Tour Packages");
            System.out.println("4. Update Tour Package");
            System.out.println("5. Delete Tour Package");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createTourPackage();
                    break;
                case 2:
                    viewTourPackageById();
                    break;
                case 3:
                    viewAllTourPackages();
                    break;
                case 4:
                    updateTourPackage();
                    break;
                case 5:
                    deleteTourPackage();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createTourPackage() {
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter duration (days): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter itinerary: ");
        String itinerary = scanner.nextLine();
        System.out.print("Enter availability (true/false): ");
        boolean availability = scanner.nextBoolean();

        TourPackage tourPackage = new TourPackage();
        tourPackage.setDestination(destination);
        tourPackage.setPrice(price);
        tourPackage.setDuration(duration);
        tourPackage.setItinerary(itinerary);
        tourPackage.setAvailability(availability);
        tourPackageDAO.saveTourPackage(tourPackage);
        System.out.println("Tour Package created successfully.");
    }

    private static void viewTourPackageById() {
        System.out.print("Enter tour package ID: ");
        int packageId = scanner.nextInt();
        TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);
        if (tourPackage != null) {
            System.out.println("Package ID: " + tourPackage.getPackageId());
            System.out.println("Destination: " + tourPackage.getDestination());
            System.out.println("Price: " + tourPackage.getPrice());
            System.out.println("Duration: " + tourPackage.getDuration());
            System.out.println("Itinerary: " + tourPackage.getItinerary());
            System.out.println("Availability: " + tourPackage.isAvailability());
        } else {
            System.out.println("Tour Package not found.");
        }
    }

    private static void viewAllTourPackages() {
    	List<TourPackage> packages = tourPackageDAO.getAllTourPackages();
        if (packages.isEmpty()) {
            System.out.println("No tour packages found.");
        } else {
            packages.forEach(tourPackage -> {
                System.out.println("Package ID: " + tourPackage.getPackageId());
                System.out.println("Destination: " + tourPackage.getDestination());
                System.out.println("Price: " + tourPackage.getPrice());
                System.out.println("Duration: " + tourPackage.getDuration());
                System.out.println("Itinerary: " + tourPackage.getItinerary());
                System.out.println("Availability: " + tourPackage.isAvailability());
                System.out.println();
            });
        }
    }

    private static void updateTourPackage() {
        System.out.print("Enter tour package ID to update: ");
        int packageId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        TourPackage tourPackage = tourPackageDAO.getTourPackageById(packageId);

        if (tourPackage != null) {
            System.out.print("Enter new destination: ");
            String destination = scanner.nextLine();
            System.out.print("Enter new price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter new duration (days): ");
            int duration = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.print("Enter new itinerary: ");
            String itinerary = scanner.nextLine();
            System.out.print("Enter new availability (true/false): ");
            boolean availability = scanner.nextBoolean();

            tourPackage.setDestination(destination);
            tourPackage.setPrice(price);
            tourPackage.setDuration(duration);
            tourPackage.setItinerary(itinerary);
            tourPackage.setAvailability(availability);

            tourPackageDAO.updateTourPackage(tourPackage);
            System.out.println("Tour Package updated successfully.");
        } else {
            System.out.println("Tour Package not found.");
        }
    }

    private static void deleteTourPackage() {
        System.out.print("Enter tour package ID to delete: ");
        int packageId = scanner.nextInt();
        tourPackageDAO.deleteTourPackage(packageId);
    }
}
