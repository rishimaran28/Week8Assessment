import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Room class
abstract class Room {
    protected String type;
    protected int units;

    public Room(String type, int units) {
        this.type = type;
        this.units = units;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateBill();
}

// Single Room: Rs 8 per unit
class SingleRoom extends Room {
    public SingleRoom(int units) {
        super("SINGLE", units);
    }

    @Override
    public double calculateBill() {
        return units * 8.0;
    }
}

// Shared Room: Rs 6 per unit, divided equally by number of occupants
class SharedRoom extends Room {
    private int occupants;

    public SharedRoom(int units, int occupants) {
        super("SHARED", units);
        this.occupants = occupants;
    }

    @Override
    public double calculateBill() {
        return (units * 6.0) / occupants;
    }
}

// AC Room: Rs 10 per unit + fixed charge of Rs 200
class ACRoom extends Room {
    public ACRoom(int units) {
        super("AC", units);
    }

    @Override
    public double calculateBill() {
        return (units * 10.0) + 200.0;
    }
}

public class ElectricityBillCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            int units = scanner.nextInt();
            if ("SINGLE".equalsIgnoreCase(type)) {
                rooms.add(new SingleRoom(units));
            } else if ("SHARED".equalsIgnoreCase(type)) {
                int occupants = scanner.nextInt();
                rooms.add(new SharedRoom(units, occupants));
            } else if ("AC".equalsIgnoreCase(type)) {
                rooms.add(new ACRoom(units));
            }
        }

        double grandTotal = 0.0;
        for (Room r : rooms) {
            double bill = r.calculateBill();
            grandTotal += bill;
            System.out.printf("%s: %.2f\n", r.getType(), bill);
        }
        System.out.printf("Total: %.2f\n", grandTotal);

        scanner.close();
    }
}