package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final Scanner thescanner = new Scanner(System.in);

    public UserInterface() {
        // Empty constructor; init() handles loading the dealership.
    }
    private String ask(String message) {
        System.out.print(message);
        return thescanner.nextLine().trim();
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


    // Process methods


    public void processGetByPriceRequest() {
        try {
            double min = Double.parseDouble(ask("Minimum price: "));
            double max = Double.parseDouble(ask("Maximum price: "));
            displayVehicles(dealership.getVehiclesByPrice(min, max));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning to menu.");
        }
    }

    public void processGetByMakeModelRequest() {
        String make  = ask("Make (leave blank for any): ");
        String model = ask("Model (leave blank for any): ");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));

    }

    public void processGetByYearRequest() {
        try {
            int min = Integer.parseInt(ask("Minimum year: "));
            int max = Integer.parseInt(ask("Maximum year: "));
        displayVehicles(dealership.getVehiclesByYear(min, max));
    } catch (NumberFormatException e) {
        System.out.println("Invalid year. Returning to menu.");
    }
    }

    public void processGetByColorRequest() {
        String color = ask("Color: ");
        displayVehicles(dealership.getVehiclesByColor(color));


    }

    public void processGetByMileageRequest() {
        try {
            int min = Integer.parseInt(ask("Minimum mileage: "));
            int max = Integer.parseInt(ask("Maximum mileage: "));
            displayVehicles(dealership.getVehiclesByMileage(min, max));

        }
        catch (NumberFormatException e) {
        System.out.println("Invalid mileage. Returning to menu.");
    }
    }

    public void processGetByVehicleTypeRequest() {
        String type = ask("Vehicle type (car, truck, SUV, van): ");
        displayVehicles(dealership.getVehiclesByType(type));

    }

    public void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }
    private void saveDealership() {
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
    }
    public void processAddVehicleRequest() {
        try {
            int vin = Integer.parseInt(ask("VIN: "));
            int year = Integer.parseInt(ask("Year: "));
            String make = ask("Make: ");
            String model = ask("Model: ");
            String vehicleType = ask("Type (car, truck, SUV, van): ");
            String color = ask("Color: ");
            int odometer = Integer.parseInt(ask("Odometer: "));
            double price = Double.parseDouble(ask("Price: "));

            Vehicle vehicle = new Vehicle(vin, year, make, model,
                    vehicleType, color, odometer, price);
            dealership.addVehicle(vehicle);
            saveDealership();
            System.out.println("Vehicle added.");
        }catch (NumberFormatException e) {
        System.out.println("Invalid input. Vehicle not added.");
    }
    }

    public void processRemoveVehicleRequest() {

    }
}