import java.util.*;

abstract class Vehicle {
    private String id;
    private boolean available = true;

    Vehicle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 50.0;
    }
}

class SUV extends Vehicle {
    SUV(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Truck extends Vehicle {
    Truck(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 120.0;
    }
}

class Customer {
    private String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private boolean active = true;

    Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public void returnVehicle() {
        if (!active) {
            System.out.println(vehicle.getId() + " has already been returned.");
            return;
        }

        active = false;
        vehicle.setAvailable(true);
        System.out.println(vehicle.getId() + " returned by " + customer.getName() + ".");
    }
}

class RentalService {
    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (days <= 0) {
            System.out.println("Rental duration must be at least one day.");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId() + " is currently unavailable.");
            return null;
        }

        vehicle.setAvailable(false);
        Rental rental = new Rental(customer, vehicle, days);
        System.out.println(vehicle.getId() + " rented successfully by " + customer.getName() + ".");
        System.out.printf("Rental charge: $%.2f.%n", vehicle.calculateCharge(days));
        return rental;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        RentalService service = new RentalService();

        Rental r1 = service.rentVehicle(c1, sedanA, 3);
        service.rentVehicle(c2, sedanA, 2);

        if (r1 != null) {
            r1.returnVehicle();
        }

        service.rentVehicle(c3, suvB, 5);
    }
}
