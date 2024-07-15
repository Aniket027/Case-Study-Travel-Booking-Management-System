import com.travelbooking.service.DestinationService;
import com.travelbooking.service.CustomerService;
import com.travelbooking.service.BookingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DestinationService destinationService = new DestinationService();
        CustomerService customerService = new CustomerService();
        BookingService bookingService = new BookingService();

        while (true) {
            System.out.println("1. Manage Destinations");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> destinationService.manageDestinations(scanner);
                case 2 -> customerService.manageCustomers(scanner);
                case 3 -> bookingService.manageBookings(scanner);
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
