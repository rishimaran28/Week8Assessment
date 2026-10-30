import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class for Customer
abstract class Customer {
    protected String type;
    protected double billAmount;

    public Customer(String type, double billAmount) {
        this.type = type;
        this.billAmount = billAmount;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateFinalAmount();
}

// Student: 10% discount
class StudentCustomer extends Customer {
    public StudentCustomer(double billAmount) {
        super("STUDENT", billAmount);
    }

    @Override
    public double calculateFinalAmount() {
        return billAmount * 0.90;
    }
}

// Staff: 5% discount
class StaffCustomer extends Customer {
    public StaffCustomer(double billAmount) {
        super("STAFF", billAmount);
    }

    @Override
    public double calculateFinalAmount() {
        return billAmount * 0.95;
    }
}

// Guest: Full amount + Rs 10 service charge
class GuestCustomer extends Customer {
    public GuestCustomer(double billAmount) {
        super("GUEST", billAmount);
    }

    @Override
    public double calculateFinalAmount() {
        return billAmount + 10.0;
    }
}

public class CanteenBilling {
    public static Customer createCustomer(String type, double amount) {
        if ("STUDENT".equalsIgnoreCase(type)) {
            return new StudentCustomer(amount);
        } else if ("STAFF".equalsIgnoreCase(type)) {
            return new StaffCustomer(amount);
        } else if ("GUEST".equalsIgnoreCase(type)) {
            return new GuestCustomer(amount);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();
            Customer c = createCustomer(type, amount);
            if (c != null) {
                customers.add(c);
            }
        }

        double grandTotal = 0.0;
        for (Customer c : customers) {
            double finalAmount = c.calculateFinalAmount();
            grandTotal += finalAmount;
            System.out.printf("%s: %.2f\n", c.getType(), finalAmount);
        }
        System.out.printf("Total: %.2f\n", grandTotal);

        scanner.close();
    }
}