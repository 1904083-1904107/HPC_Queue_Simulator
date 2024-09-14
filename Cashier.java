public class Cashier extends Thread {
    private final GroceryQueue groceryQueue;
    private boolean isAvailable;

    public Cashier(GroceryQueue groceryQueue) {
        this.groceryQueue = groceryQueue;
        this.isAvailable = true;
    }

    @Override
    public void run() {
        while (true) {
            Customer customer = groceryQueue.serveCustomer();
            if (customer != null) {
                isAvailable = false;
                // Simulate serving time
                try {
                    Thread.sleep(customer.getServiceTime() * 1000L); // Convert seconds to milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customer.markServed();
                isAvailable = true;
            }
            // Simulate idle time
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
