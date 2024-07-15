package com.travelbooking.service;

import com.travelbooking.dao.DestinationDAO;
import com.travelbooking.model.Destination;

import java.math.BigDecimal;
import java.util.Scanner;

public class DestinationService {
    DestinationDAO destinationDAO = new DestinationDAO();

    public void manageDestinations(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Destination");
            System.out.println("2. View Destination");
            System.out.println("3. Update Destination");
            System.out.println("4. Delete Destination");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDestination(scanner);
                    break;
                case 2:
                    viewDestination(scanner);
                    break;
                case 3:
                    updateDestination(scanner);
                    break;
                case 4:
                    deleteDestination(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addDestination(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter price per day: ");
        BigDecimal pricePerDay = scanner.nextBigDecimal();

        Destination destination = new Destination();
        destination.setName(name);
        destination.setLocation(location);
        destination.setDescription(description);
        destination.setPricePerDay(pricePerDay);

        destinationDAO.addDestination(destination);
        System.out.println("Destination added successfully!");
    }

    private void viewDestination(Scanner scanner) {
        System.out.print("Enter destination ID: ");
        int destinationId = scanner.nextInt();

        Destination destination = destinationDAO.getDestination(destinationId);
        if (destination != null) {
            System.out.println("Name: " + destination.getName());
            System.out.println("Location: " + destination.getLocation());
            System.out.println("Description: " + destination.getDescription());
            System.out.println("Price per day: " + destination.getPricePerDay());
        } else {
            System.out.println("Destination not found!");
        }
    }

    private void updateDestination(Scanner scanner) {
        System.out.print("Enter destination ID: ");
        int destinationId = scanner.nextInt();

        Destination destination = destinationDAO.getDestination(destinationId);
        if (destination != null) {
            System.out.print("Enter new name: ");
            String name = scanner.next();
            scanner.nextLine();
            System.out.print("Enter new location: ");
            String location = scanner.nextLine();
            System.out.print("Enter new description: ");
            String description = scanner.nextLine();
            System.out.print("Enter new price per day: ");
            BigDecimal pricePerDay = scanner.nextBigDecimal();

            destination.setName(name);
            destination.setLocation(location);
            destination.setDescription(description);
            destination.setPricePerDay(pricePerDay);

            destinationDAO.updateDestination(destination);
            System.out.println("Destination updated successfully!");
        } else {
            System.out.println("Destination not found!");
        }
    }

    private void deleteDestination(Scanner scanner) {
        System.out.print("Enter destination ID: ");
        int destinationId = scanner.nextInt();

        destinationDAO.deleteDestination(destinationId);
        System.out.println("Destination deleted successfully!");
    }
}
