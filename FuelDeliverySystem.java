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
    // DSA Concept: HashMap - Hash map to store drivers, where the key is the driver's name
    Map<String, Driver> driverMap = new HashMap<>();
    
    // DSA Concept: PriorityQueue (Heap) - We use a priority queue to store customers, sorted by their payment amount in descending order
    PriorityQueue<Customer> orderQueue = new PriorityQueue<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer c1, Customer c2) {
            // Sorting by payment amount (highest payment first)
            return Double.compare(c2.paymentAmount, c1.paymentAmount); // Sorting in descending order of payment
        }
    });
    
    public void addOrder(Customer customer) {
        // DSA Concept: PriorityQueue - We add customer orders to the priority queue. Priority is based on payment amount.
        orderQueue.add(customer);
        System.out.println("Customer " + customer.name + " has placed an order.");
    }
    
    public void addDriver(Driver driver) {
        // DSA Concept: HashMap - We store drivers in a hash map with their names as keys for O(1) lookups.
        driverMap.put(driver.name, driver);
    }
    
    public void assignDelivery() {
        // DSA Concept: Poll (PriorityQueue) - Retrieve the customer with the highest payment (highest priority) from the queue
        while (!orderQueue.isEmpty()) {
            // DSA Concept: Iteration over HashMap - Check the driver availability using the HashMap (O(1) access)
            Driver assignedDriver = null;
            for (Driver driver : driverMap.values()) {
                if (driver.isAvailable) {
                    assignedDriver = driver;
                    break;
                }
            }

            if (assignedDriver != null) {
                // DSA Concept: Poll - We retrieve and remove the customer with the highest payment from the priority queue.
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
        
        // Adding drivers
        system.addDriver(new Driver("John"));
        system.addDriver(new Driver("Mike"));
        system.addDriver(new Driver("Jane"));
        
        // Custom input for customers
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
            scanner.nextLine(); // consume newline
            
            // Create a new customer and add to the order queue
            system.addOrder(new Customer(name, phoneNumber, location, fuelType, paymentAmount));
        }
        
        // Assign deliveries to drivers
        system.assignDelivery();
        
        scanner.close();
    }
}
