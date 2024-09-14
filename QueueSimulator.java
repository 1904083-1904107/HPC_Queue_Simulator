import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueueSimulator {
    private final BankQueue bankQueue;
    private final GroceryQueues groceryQueues;
    private final long simulationTime;
    private final Random random = new Random();

    private final List<Customer> allCustomers;

    public QueueSimulator(long simulationTimeMinutes, int numberOfTellers, int numberOfCashiers, int bankMaxQueueLength,
            int groceryMaxQueueLength) {
        this.simulationTime = simulationTimeMinutes * 60;
        this.bankQueue = new BankQueue(numberOfTellers, bankMaxQueueLength);
        this.groceryQueues = new GroceryQueues(numberOfCashiers, groceryMaxQueueLength);
        this.allCustomers = new ArrayList<>();
    }

    public void simulate() {
        long clock = 0;
        while (clock < simulationTime) {
            clock++;
            // Add customers at random intervals (20-60 seconds)
            if (random.nextInt(40) + 20 == clock % 60) {
                Customer customer = new Customer(clock);
                allCustomers.add(customer);

                // Add customer to BankQueue
                if (!bankQueue.addCustomer(customer)) {
                    customer.markServed(); // Customer couldn't join the queue
                }

                // Add customer to GroceryQueues
                groceryQueues.addCustomer(customer);
            }

            // Increment time
            try {
                Thread.sleep(1000); // Simulating a 1-second tick
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        generateReport();
    }

    private void generateReport() {
        long served = allCustomers.stream().filter(Customer::wasServed).count();
        long left = allCustomers.size() - served;
        System.out.println("Total customers: " + allCustomers.size());
        System.out.println("Customers served: " + served);
        System.out.println("Customers left without service: " + left);
    }
}
