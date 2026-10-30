import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base Subscription Plan class
abstract class SubscriptionPlan {
    protected String subscriberName;
    protected LocalDate startDate;

    public SubscriptionPlan(String subscriberName, LocalDate startDate) {
        this.subscriberName = subscriberName;
        this.startDate = startDate;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public abstract LocalDate calculateRenewalDate();
}

// Basic: 30 days
class BasicPlan extends SubscriptionPlan {
    public BasicPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(30);
    }
}

// Standard: 90 days
class StandardPlan extends SubscriptionPlan {
    public StandardPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(90);
    }
}

// Premium: 365 days
class PremiumPlan extends SubscriptionPlan {
    public PremiumPlan(String subscriberName, LocalDate startDate) {
        super(subscriberName, startDate);
    }

    @Override
    public LocalDate calculateRenewalDate() {
        return startDate.plusDays(365);
    }
}

public class StreamingRenewalReminder {
    public static SubscriptionPlan createPlan(String planType, String name, LocalDate startDate) {
        if ("BASIC".equalsIgnoreCase(planType)) {
            return new BasicPlan(name, startDate);
        } else if ("STANDARD".equalsIgnoreCase(planType)) {
            return new StandardPlan(name, startDate);
        } else if ("PREMIUM".equalsIgnoreCase(planType)) {
            return new PremiumPlan(name, startDate);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<SubscriptionPlan> plans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String planType = scanner.next();
            String name = scanner.next();
            String dateStr = scanner.next();
            LocalDate startDate = LocalDate.parse(dateStr);
            SubscriptionPlan plan = createPlan(planType, name, startDate);
            if (plan != null) {
                plans.add(plan);
            }
        }

        for (SubscriptionPlan plan : plans) {
            System.out.println(plan.getSubscriberName() + ": " + plan.calculateRenewalDate());
        }

        scanner.close();
    }
}