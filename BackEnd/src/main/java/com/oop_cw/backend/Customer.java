package com.oop_cw.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Customer implements Runnable {
    private int id;
    private int retrievalInterval;

    public Customer(int retrievalInterval) {
        this.id = new Random().nextInt(100000);
        setRetrievalInterval(retrievalInterval);
        ;
    }

    Logger log = LoggerFactory.getLogger(Customer.class);
    
     /**
     * The run method defines the behavior of the customer thread.
     * The customer tries to buy a ticket at regular intervals if the system is running.
     */
    @Override
    public void run() {
        while (true) {
             // Check if the system is running; if not, skip ticket retrieval
            if (!Configuration.getInstance().getIsRunning()) {
                continue;
            }
            try {
                LogManager.getInstance().addLog(String.format("Buying ticket from the TicketPool %d", this.id));
                log.info("Buying ticket from the TicketPool by customer {}", this.id);
                TicketPool.getInstance().removeTicket(this.getId());

                 // Sleep for the retrieval interval before trying again
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
                // If the thread is interrupted, handle it by resetting the interrupt status
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getId() {
        return id;
    }

    public int getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        if (retrievalInterval > 0) {
            this.retrievalInterval = retrievalInterval;
        } else {
            throw new IllegalArgumentException("Retrieval interval must be greater than 0.");
        }
    }
}
