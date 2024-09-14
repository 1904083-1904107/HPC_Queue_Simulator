# Concurrent Queue Simulation

This project implements two types of concurrent queue systems in Java: `BankQueue` and `GroceryQueues`. It simulates customer arrivals and service processes to determine which queue model is more efficient.

## Overview

The simulation includes the following components:

### Producer
- **Function**: Generates customers at random intervals between 20 and 60 seconds and pushes them into the queues.

### Consumer
- **Function**: Serves customers with service times ranging from 60 to 300 seconds.
  - **For `BankQueue`**: Served by `Teller`.
  - **For `GroceryQueues`**: Served by `Cashier`.

### Queues
- **`BankQueue`**: Contains a single queue. Customers depart immediately if the queue is full.
- **`GroceryQueues`**: Contains multiple queues (one for each cashier). If all queues are full, customers wait up to 10 seconds for a queue to become available before leaving.

### Customer
- **Tracks**:
  - Arrival time
  - Service time
  - Whether they departed without being served

## Parameters
- **Duration**: Length of the simulation in minutes.
- **Number of Tellers**: For the `BankQueue`.
- **Number of Cashiers**: For the `GroceryQueues`.
- **Capacity**:
  - `BankQueue`: Maximum length of the queue.
  - `GroceryQueues`: Maximum length per queue.

## Output
For each queue type, the simulation provides:
- Total number of customers arrived
- Total number of customers who departed without being served
- Total number of customers served
- Average time taken to serve each customer
