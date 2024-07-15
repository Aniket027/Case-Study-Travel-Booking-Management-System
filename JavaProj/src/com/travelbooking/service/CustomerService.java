package com.travelbooking.service;

import com.travelbooking.dao.CustomerDAO;
import com.travelbooking.model.Customer;

import java.util.Scanner;

public class CustomerService {
    CustomerDAO customerDAO = new CustomerDAO();

    public void manageCustomers(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer(scanner);
                    break;
                case 2:
                    viewCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void addCustomer(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private void viewCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        Customer customer = customerDAO.getCustomer(customerId);
        if (customer != null) {
            System.out.println("Name: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Address: " + customer.getAddress());
        } else {
            System.out.println("Customer not found!");
        }
    }

    private void updateCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        Customer customer = customerDAO.getCustomer(customerId);
        scanner.nextLine();
        if (customer != null) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter new address: ");
            String address = scanner.nextLine();

            customer.setName(name);
            customer.setEmail(email);
            customer.setPhoneNumber(phoneNumber);
            customer.setAddress(address);

            customerDAO.updateCustomer(customer);
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer not found!");
        }
    }

    private void deleteCustomer(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();

        customerDAO.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully!");
    }
}