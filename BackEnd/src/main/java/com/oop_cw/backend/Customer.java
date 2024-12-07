package com.oop_cw.backend;

import java.util.Random;

public class Customer implements Runnable {
    private int id;
    private int retrievalInterval;

    public Customer(int retrievalInterval) {
        this.id = new Random().nextInt(100000);
        setRetrievalInterval(retrievalInterval);;
    }

    @Override
    public void run() {
        while (true) {
            if (!Configuration.getInstance().getIsRunning()) {
                continue;
            }
            try {
                System.out.printf("Buying ticket from the TicketPool by customer %d%n", this.id);
                TicketPool.getInstance().removeTicket(this.getId());
                Thread.sleep(retrievalInterval);
            } catch (InterruptedException e) {
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
