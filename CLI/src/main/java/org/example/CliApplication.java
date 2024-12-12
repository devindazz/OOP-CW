package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class CliApplication {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Ticket Booking System CLI ===");
        System.out.println("1. Add Vendor");
        System.out.println("2. Add Customer");
        System.out.println("3. Manage Configuration");
        System.out.println("4. Control Panel");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
        }
        return choice;
    }

    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                addVendor();
                break;
            case 2:
                addCustomer();
                break;
            case 3:
                ManageConfiguration();
                break;
            case 4:
                ControlPanel();
                break;
            case 5:
                System.out.println("Exiting the system. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    private static void addVendor() {
        System.out.println("\n=== Add Vendor ===");

        try {
            System.out.print("Enter Release Interval (in mili seconds): ");
            int releaseInterval = Integer.parseInt(scanner.nextLine());

            if (releaseInterval <= 0) {
                System.out.println("Release Interval must be a positive number. Try again.");
                return;
            }

            System.out.print("Enter Tickets Per Release: ");
            int ticketsPerRelease = Integer.parseInt(scanner.nextLine());

            if (ticketsPerRelease <= 0) {
                System.out.println("Tickets Per Release must be a positive number. Try again.");
                return;
            }

            Vendor vendor = new Vendor(releaseInterval, ticketsPerRelease);

            String response = sendVendorDataToBackend(vendor);
            System.out.println(response);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values only.");
        }
    }

    private static String sendVendorDataToBackend(Vendor vendor) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(Map.of("release_interval", vendor.getReleaseInterval(), "ticket_per_release",
                    vendor.getTicketPerRelease()));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/vendors"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static void addCustomer() {
        System.out.println("\n=== Add Customer ===");

        try {
            System.out.print("Enter Retrieval Interval: ");
            int retrievalInterval = Integer.parseInt(scanner.nextLine());

            if (retrievalInterval <= 0) {
                System.out.println("Retrievaal Interval must be a positive number. Try again.");
                return;
            }

            Customer customer = new Customer(retrievalInterval);

            String response = sendCustomerDataToBackend(customer);
            System.out.println(response);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values only.");
        }
    }

    private static String sendCustomerDataToBackend(Customer customer) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(Map.of("retrieval_interval", customer.getRetrievalInterval()));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/customers"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body().toString();
        } catch (Exception e) {
            System.out.println("Error connecting to backend: " + e.getMessage());
            return e.getMessage();
        }
    }

    private static void ManageConfiguration() {
        System.out.println("\n=== Manage Configuration ===");
    
        try {
            System.out.print("Enter total tickets: ");
            int totalTickets = Integer.parseInt(scanner.nextLine());
    
            if (totalTickets <= 0) {
                System.out.println("Total tickets must be a positive number. Try again.");
                return;
            }
    
            System.out.print("Enter ticket release rate: ");
            int ticketReleaseRate = Integer.parseInt(scanner.nextLine());
    
            if (ticketReleaseRate <= 0) {
                System.out.println("Ticket Release Rate must be a positive number. Try again.");
                return;
            }
    
            System.out.print("Enter customer retrieval rate: ");
            int customerRetrievalRate = Integer.parseInt(scanner.nextLine());
    
            if (customerRetrievalRate <= 0) {
                System.out.println("Customer Retrieval Rate must be a positive number. Try again.");
                return;
            }
    
            System.out.print("Enter Max ticket Capacity: ");
            int maxTicketCapacity = Integer.parseInt(scanner.nextLine());
    
            if (maxTicketCapacity <= 0) {
                System.out.println("Max ticket capacity must be a positive number. Try again.");
                return;
            }

            Configuration configuration = new Configuration();
            configuration.setTotalTickets(totalTickets);
            configuration.setTicketReleaseRate(ticketReleaseRate);
            configuration.setCustomerRetrievalRate(customerRetrievalRate);
            configuration.setMaxTicketCapacity(maxTicketCapacity);
    
            String response = sendConfigurationDataToBackend(configuration);
            System.out.println(response);
    
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values only.");
        }
    }
    
    private static String sendConfigurationDataToBackend(Configuration configuration) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(configuration);
    
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/configuration"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
    
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println("Error connecting to backend: " + e.getMessage());
            return e.getMessage();
        }
    }

    private static boolean isSystemRunning = false;

    private static void ControlPanel() {
        System.out.println("\n=== Control Panel ===");
        System.out.println("1. Start System");
        System.out.println("2. Stop System");
        System.out.println("3. Back to Main Menu");

        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                startSystem();
                break;
            case 2:
                stopSystem();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void startSystem() {
        if (isSystemRunning) {
            System.out.println("System is already running!");
            return;
        }

        System.out.println("Starting the system...");
        isSystemRunning = true;
        sendSystemStateToBackend("start");
    }

    private static void stopSystem() {
        if (!isSystemRunning) {
            System.out.println("System is already stopped!");
            return;
        }

        System.out.println("Stopping the system...");
        isSystemRunning = false;
        sendSystemStateToBackend("stop");
    }

    private static void sendSystemStateToBackend(String state) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/system/" + state)) 
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}