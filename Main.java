public class Main {
    public static void main(String[] args) {
        long simulationTimeMinutes = 120; // Simulate for 2 hours
        int numberOfTellers = 3;
        int numberOfCashiers = 3;
        int bankMaxQueueLength = 5;
        int groceryMaxQueueLength = 2;

        QueueSimulator simulator = new QueueSimulator(simulationTimeMinutes, numberOfTellers, numberOfCashiers,
                bankMaxQueueLength, groceryMaxQueueLength);
        simulator.simulate();
    }
}
