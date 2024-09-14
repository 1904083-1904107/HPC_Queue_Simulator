import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroceryQueues {
    private final GroceryQueue[] queues;
    private final int maxQueueLength;
    private final List<Cashier> cashiers;

    public GroceryQueues(int numberOfQueues, int maxQueueLength) {
        this.queues = new GroceryQueue[numberOfQueues];
        this.maxQueueLength = maxQueueLength;
        this.cashiers = new ArrayList<>();

        // Create queues and cashiers
        for (int i = 0; i < numberOfQueues; i++) {
            GroceryQueue groceryQueue = new GroceryQueue(maxQueueLength);
            queues[i] = groceryQueue;

            Cashier cashier = new Cashier(groceryQueue);
            cashiers.add(cashier);
            cashier.start();
        }
    }

    public boolean addCustomer(Customer customer) {
        // Find the queue with the fewest customers
        int minQueueSize = maxQueueLength;
        int selectedQueueIndex = -1;
        for (int i = 0; i < queues.length; i++) {
            int queueSize = queues[i].getQueueSize();
            if (queueSize < minQueueSize) {
                minQueueSize = queueSize;
                selectedQueueIndex = i;
            }
        }

        // If all queues are full
        if (selectedQueueIndex == -1) {
            return false;
        }

        return queues[selectedQueueIndex].addCustomer(customer);
    }
}
