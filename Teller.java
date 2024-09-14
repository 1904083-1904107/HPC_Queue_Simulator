public class Teller extends Thread {
    private final BankQueue bankQueue;
    private boolean isAvailable;

    public Teller(BankQueue bankQueue) {
        this.bankQueue = bankQueue;
        this.isAvailable = true;
    }

    @Override
    public void run() {
        while (true) {
            Customer customer = bankQueue.serveCustomer();
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
                bankQueue.releaseTeller(); // Release teller when done
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
