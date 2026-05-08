package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    private static final String AmaniFile = "src/main/resources/inventory.csv";
    private static final String DELIMITER = "\\|";;
    ;

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(AmaniFile))) {

            // Line 1: dealership info -> name|address|phone
            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("Inventory file is empty.");
                return null;
            }

            String[] headerParts = headerLine.split(DELIMITER);
            String name = headerParts[0];
            String address = headerParts[1];
            String phone = headerParts[2];
            dealership = new Dealership(name, address, phone);

            // Lines 2+: vehicles -> vin|year|make|model|type|color|odometer|price
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(DELIMITER);
                int vin           = Integer.parseInt(parts[0]);
                int year          = Integer.parseInt(parts[1]);
                String make       = parts[2];
                String model      = parts[3];
                String vehicleType = parts[4];
                String color      = parts[5];
                int odometer      = Integer.parseInt(parts[6]);
                double price      = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model,
                        vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}