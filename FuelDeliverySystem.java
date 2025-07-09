import java.util.*;
class Customer {
    String name;
    String phoneNumber;
    String location;
    String fuelType;
    double paymentAmount;
    public Customer(String name, String phoneNumber, String location, String fuelType, double paymentAmount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.fuelType = fuelType;
        this.paymentAmount = paymentAmount;
    }
}
class Driver {
    String name;
    boolean isAvailable;
    public Driver(String name) {
        this.name = name;
        this.isAvailable = true;
    }
    public void assignOrder(Customer customer) {
        this.isAvailable = false;
        System.out.println("Driver " + this.name + " has been assigned to deliver fuel to " + customer.name + " at " + customer.location);
    }
    public void completeDelivery() {
        this.isAvailable = true;
    }
}
public class FuelDeliverySystem {
    Map<String, Driver> driverMap = new HashMap<>();
    PriorityQueue<Customer> orderQueue = new PriorityQueue<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer c1, Customer c2) {
            return Double.compare(c2.paymentAmount, c1.paymentAmount); 
        }
    });
    public void addOrder(Customer customer) {
        orderQueue.add(customer);
        System.out.println("Customer " + customer.name + " has placed an order.");
    }
    public void addDriver(Driver driver) {
        driverMap.put(driver.name, driver);
    }
    public void assignDelivery() {
        while (!orderQueue.isEmpty()) {
            Driver assignedDriver = null;
            for (Driver driver : driverMap.values()) {
                if (driver.isAvailable) {
                    assignedDriver = driver;
                    break;
                }
            }
            if (assignedDriver != null) {
                Customer customer = orderQueue.poll(); 
                assignedDriver.assignOrder(customer);
                assignedDriver.completeDelivery();
                System.out.println("Fuel delivery for " + customer.name + " is completed.");
            } else {
                System.out.println("No available driver for delivery.");
                break;
            }
        }
    }
    public static void main(String[] args) {
        FuelDeliverySystem system = new FuelDeliverySystem();
        Scanner scanner = new Scanner(System.in);
        system.addDriver(new Driver("John"));
        system.addDriver(new Driver("Mike"));
        system.addDriver(new Driver("Jane"));
        while (true) {
            System.out.print("Enter customer name (or type 'exit' to stop): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            System.out.print("Enter fuel type (Petrol/Diesel/CNG): ");
            String fuelType = scanner.nextLine();
            System.out.print("Enter payment amount: ");
            double paymentAmount = scanner.nextDouble();
            scanner.nextLine(); 
            system.addOrder(new Customer(name, phoneNumber, location, fuelType, paymentAmount));
        }
        system.assignDelivery();
        scanner.close();
    }
}
