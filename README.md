# Online Fuel Delivery System
This Java application simulates a backend system for managing fuel delivery operations. It models the interaction between customers and drivers, providing a simple but effective structure for handling fuel orders in real time.

# Design Overview
The system is developed using object-oriented programming principles to ensure modularity, clarity, and scalability.

Customer Class: Represents a fuel order request. Each customer object stores information such as the customer's name, phone number, delivery location, fuel type, and payment amount.

Driver Class: Represents a delivery driver. Each driver maintains their name and availability status. Drivers can be assigned to customers and marked as busy or available based on the delivery status.

FuelDeliverySystem Class:

Maintains a map of driver names to driver objects for quick access.

Uses a priority queue to manage customer orders, allowing higher-priority customers to be served first.

Assigns available drivers to pending customer requests.

Updates driver availability once the delivery is completed.

# Core Programming Concepts
Encapsulation: Each class defines its own data and methods, keeping functionality organized.

Abstraction: Implementation details are hidden behind clearly defined methods like assignOrder() and completeDelivery().

Data Structures: Utilizes HashMap for driver management and PriorityQueue for efficient customer handling.

# Summary
This project demonstrates how Java can be used to simulate a task allocation system in logistics. It emphasizes real-time request processing, driver availability tracking, and priority-based order handling. The system is extendable and can serve as a foundation for a more complex application with database integration or user interfaces.

