import java.util.Random;

public class Customer {
    private final long arrivalTime;
    private final int serviceTime; // in seconds
    private boolean served;

    public Customer(long arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = new Random().nextInt(241) + 60; // 60 to 300 seconds
        this.served = false;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void markServed() {
        this.served = true;
    }

    public boolean wasServed() {
        return served;
    }
}
