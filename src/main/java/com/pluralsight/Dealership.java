package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }



    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            boolean makeOk  = make  == null || make.isBlank()
                    || v.getMake().equalsIgnoreCase(make);
            boolean modelOk = model == null || model.isBlank()
                    || v.getModel().equalsIgnoreCase(model);
            if (makeOk && modelOk) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matches.add(v);
            }
        }
        return matches;
    }


    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {

    }

    // ----- Getters & Setters (no setter for inventory by design) -----

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}