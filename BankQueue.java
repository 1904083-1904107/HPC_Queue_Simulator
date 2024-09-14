import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankQueue {
    private final Queue<Customer> queue;
    private final Lock lock;
    private final int maxQueueLength;
    private final List<Teller> tellers;

    public BankQueue(int numberOfTellers, int maxQueueLength) {
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.maxQueueLength = maxQueueLength;
        this.tellers = new ArrayList<>();

        // Create tellers
        for (int i = 0; i < numberOfTellers; i++) {
            Teller teller = new Teller(this);
            tellers.add(teller);
            teller.start();
        }
    }

    public boolean addCustomer(Customer customer) {
        lock.lock();
        try {
            if (queue.size() >= maxQueueLength) {
                return false; // Queue is full, customer leaves
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

    public void releaseTeller() {
        // Teller logic already managed within Teller class
    }
}
