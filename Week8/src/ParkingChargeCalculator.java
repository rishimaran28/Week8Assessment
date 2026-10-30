import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base vehicle class
abstract class Vehicle {
    protected String type;
    protected int hours;

    public Vehicle(String type, int hours) {
        this.type = type;
        this.hours = hours;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateCharge();
}

// Bike: Rs 10 per hour
class Bike extends Vehicle {
    public Bike(int hours) {
        super("BIKE", hours);
    }

    @Override
    public double calculateCharge() {
        return hours * 10.0;
    }
}

// Car: Rs 30 for first hour, plus Rs 20 for each additional hour
class Car extends Vehicle {
    public Car(int hours) {
        super("CAR", hours);
    }

    @Override
    public double calculateCharge() {
        if (hours <= 1) {
            return 30.0;
        }
        return 30.0 + (hours - 1) * 20.0;
    }
}

// Truck: Rs 50 per hour, minimum charge of Rs 100
class Truck extends Vehicle {
    public Truck(int hours) {
        super("TRUCK", hours);
    }

    @Override
    public double calculateCharge() {
        double charge = hours * 50.0;
        return Math.max(charge, 100.0);
    }
}

public class ParkingChargeCalculator {
    public static Vehicle createVehicle(String type, int hours) {
        if ("BIKE".equalsIgnoreCase(type)) {
            return new Bike(hours);
        } else if ("CAR".equalsIgnoreCase(type)) {
            return new Car(hours);
        } else if ("TRUCK".equalsIgnoreCase(type)) {
            return new Truck(hours);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int hours = scanner.nextInt();
            Vehicle v = createVehicle(type, hours);
            if (v != null) {
                vehicles.add(v);
            }
        }

        double grandTotal = 0.0;
        for (Vehicle v : vehicles) {
            double charge = v.calculateCharge();
            grandTotal += charge;
            System.out.printf("%s: %.2f\n", v.getType(), charge);
        }
        System.out.printf("Total: %.2f\n", grandTotal);

        scanner.close();
    }
}