import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Employee class
abstract class Employee {
    protected String name;
    protected double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateBonus();
}

// Full-time: 10% of monthly salary
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.10;
    }
}

// Part-time: 5% of monthly salary
class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return monthlySalary * 0.05;
    }
}

// Intern: Fixed bonus of Rs 2,000
class InternEmployee extends Employee {
    public InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        return 2000.0;
    }
}

public class FestivalBonusCalculator {
    public static Employee createEmployee(String type, String name, double salary) {
        if ("FULLTIME".equalsIgnoreCase(type)) {
            return new FullTimeEmployee(name, salary);
        } else if ("PARTTIME".equalsIgnoreCase(type)) {
            return new PartTimeEmployee(name, salary);
        } else if ("INTERN".equalsIgnoreCase(type)) {
            return new InternEmployee(name, salary);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();
            Employee emp = createEmployee(type, name, salary);
            if (emp != null) {
                employees.add(emp);
            }
        }

        double grandTotal = 0.0;
        for (Employee emp : employees) {
            double bonus = emp.calculateBonus();
            grandTotal += bonus;
            System.out.printf("%s: %.2f\n", emp.getName(), bonus);
        }
        System.out.printf("Total Bonus: %.2f\n", grandTotal);

        scanner.close();
    }
}