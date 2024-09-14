import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GroceryQueue {
    private final Queue<Customer> queue;
    private final int maxQueueLength;
    private final Lock lock;

    public GroceryQueue(int maxQueueLength) {
        this.queue = new LinkedList<>();
        this.maxQueueLength = maxQueueLength;
        this.lock = new ReentrantLock();
    }

    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            if (queue.size() >= maxQueueLength) {
                return false; // Queue is full
            }
            queue.add(customer);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public Customer serveCustomer() {
        lock.lock();
        try {
            if (!queue.isEmpty()) {
                return queue.poll(); // Serve the first customer
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public int getQueueSize() {
        return queue.size();
    }
}
