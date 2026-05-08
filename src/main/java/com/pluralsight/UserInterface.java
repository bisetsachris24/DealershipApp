package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final Scanner thescanner = new Scanner(System.in);

    public UserInterface() {
        // Empty constructor; init() handles loading the dealership.
    }


    // Public entry point


    public void display() {
        init();

        boolean Apprunning = true;
        while (Apprunning) {
            displayMenu();
            String command = thescanner.nextLine().trim();

            switch (command) {
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "99":
                    Apprunning = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
                    break;
            }
        }
    }

    // ---------------------------------------------------------------
    // Private helpers
    // ---------------------------------------------------------------

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("=========== " + dealership.getName() + " ===========");
        System.out.println("  1 - Find vehicles within a price range");
        System.out.println("  2 - Find vehicles by make / model");
        System.out.println("  3 - Find vehicles by year range");
        System.out.println("  4 - Find vehicles by color");
        System.out.println("  5 - Find vehicles by mileage range");
        System.out.println("  6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("  7 - List ALL vehicles");
        System.out.println("  8 - Add a vehicle");
        System.out.println("  9 - Remove a vehicle");
        System.out.println(" 99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles to display.");
            return;
        }

        System.out.printf("%-8s %-6s %-12s %-12s %-8s %-10s %-10s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("--------------------------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            System.out.printf("%-8d %-6d %-12s %-12s %-8s %-10s %-10d $%-10.2f%n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    // ---------------------------------------------------------------
    // Process methods (only #7 is wired up this phase)
    // ---------------------------------------------------------------

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {
        // Phase 5
    }

    public void processGetByYearRequest() {
        // Phase 5
    }

    public void processGetByColorRequest() {
        // Phase 5
    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {
        // Phase 5
    }

    public void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        // Phase 5
    }

    public void processRemoveVehicleRequest() {
        // Phase 5
    }
}