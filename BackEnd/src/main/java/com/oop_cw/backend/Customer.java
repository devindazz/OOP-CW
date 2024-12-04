package com.oop_cw.backend;

import java.util.Random;

public class Customer implements Runnable {
    private int id;
    private int retrievalInterval;

    public Customer(int retrievalInterval) {
        this.id = new Random().nextInt(100000);
        this.retrievalInterval = retrievalInterval;
    }

    @Override
    public void run() {
        while (true) {
            if (!Configuration.getInstance().getIsRunning()) {
                continue;
            }
            try {
                System.out.printf("Removing ticket from the TicketPool by customer %d %n කැරි පකයා!!!", this.id);
                System.out.println("සුබ මංගලම්! ❤️🕊️");
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
        this.retrievalInterval = retrievalInterval;
    }
}
