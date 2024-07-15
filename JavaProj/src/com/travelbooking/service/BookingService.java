package com.travelbooking.service;

import com.travelbooking.dao.BookingDAO;
import com.travelbooking.dao.DestinationDAO;
import com.travelbooking.model.Booking;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Scanner;

public class BookingService {
    BookingDAO bookingDAO = new BookingDAO();

    public void manageBookings(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Booking");
            System.out.println("2. View Booking");
            System.out.println("3. Update Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBooking(scanner);
                    break;
                case 2:
                    viewBooking(scanner);
                    break;
                case 3:
                    updateBooking(scanner);
                    break;
                case 4:
                    cancelBooking(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addBooking(Scanner scanner) {
        System.out.print("Enter destination ID: ");
        int destinationId = scanner.nextInt();
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter booking date (YYYY-MM-DD): ");
        Date bookingDate = Date.valueOf(scanner.next());
        System.out.print("Enter start date (YYYY-MM-DD): ");
        Date startDate = Date.valueOf(scanner.next());
        System.out.print("Enter end date (YYYY-MM-DD): ");
        Date endDate = Date.valueOf(scanner.next());
        BigDecimal totalCost = calculateCost(startDate, endDate, destinationId);

        Booking booking = new Booking();
        booking.setDestinationId(destinationId);
        booking.setCustomerId(customerId);
        booking.setBookingDate(bookingDate);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setTotalCost(totalCost);
        booking.setStatus("confirmed");

        bookingDAO.addBooking(booking);
        System.out.println("Booking added successfully!");
    }

    private void viewBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();

        Booking booking = bookingDAO.getBooking(bookingId);
        if (booking != null) {
            System.out.println("Destination ID: " + booking.getDestinationId());
            System.out.println("Customer ID: " + booking.getCustomerId());
            System.out.println("Booking Date: " + booking.getBookingDate());
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());
            System.out.println("Total Cost: " + booking.getTotalCost());
            System.out.println("Status: " + booking.getStatus());
        } else {
            System.out.println("Booking not found!");
        }
    }

    private void updateBooking(Scanner scanner) {
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();

        Booking booking = bookingDAO.getBooking(bookingId);
        if (booking != null) {
            System.out.print("Enter new destination ID: ");
            int destinationId = scanner.nextInt();
            System.out.print("Enter new customer ID: ");
            int customerId = scanner.nextInt();
            System.out.print("Enter new booking date (YYYY-MM-DD): ");
            Date bookingDate = Date.valueOf(scanner.next());
            System.out.print("Enter new start date (YYYY-MM-DD): ");
            Date startDate = Date.valueOf(scanner.next());
            System.out.print("Enter new end date (YYYY-MM-DD): ");
            Date endDate = Date.valueOf(scanner.next());
            BigDecimal totalCost = calculateCost(startDate, endDate, destinationId);

            booking.setDestinationId(destinationId);
            booking.setCustomerId(customerId);
            booking.setBookingDate(bookingDate);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setTotalCost(totalCost);
            booking.setStatus("confirmed");

            bookingDAO.updateBooking(booking);
            System.out.println("Booking updated successfully!");
        } else {
            System.out.println("Booking not found!");
        }
    }

    private void cancelBooking(Scanner scanner){
        System.out.print("Enter booking ID: ");
        int bookingId = scanner.nextInt();
        bookingDAO.cancelBooking(bookingId);
        System.out.println("Booking cancelled successfully.");
    }

    private long calculateNoOfDays(Date startDate, Date endDate){
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    private BigDecimal calculateCost(Date startDate, Date endDate, int destinationId){
        long noOfDays = calculateNoOfDays(startDate, endDate);
        DestinationDAO destinationDAO = new DestinationDAO();
        BigDecimal pricePerDay = destinationDAO.getPricePerDay(destinationId);
        BigDecimal totalCost = pricePerDay.multiply(new BigDecimal(noOfDays));
        return totalCost;
    }

}