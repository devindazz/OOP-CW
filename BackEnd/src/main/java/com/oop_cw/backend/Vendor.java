package com.oop_cw.backend;

import java.util.Random;

public class Vendor implements Runnable {
    private final int id;
    private int ticketPerRelease;
    private int releaseInterval;

    public Vendor(int ticketPerRelease, int releaseInterval) {
        this.id = new Random().nextInt(100000);
        this.ticketPerRelease = ticketPerRelease;
        this.releaseInterval = releaseInterval;
    }

    // The run method defines the behavior of the vendor thread.
    @Override
    public void run() {
        while (true) {
             // Check if the system is running; if not, skip releasing tickets
            if (!Configuration.getInstance().getIsRunning()) {
                continue;
            }
            // Release tickets according to the ticketPerRelease value
            for (int i = 0; i < ticketPerRelease; i++) {
                Ticket ticket = new Ticket(this.id);
                 // Add the ticket to the TicketPool (synchronized block in TicketPool handles concurrency)
                TicketPool.getInstance().addTicket(ticket);
            }
            try {
                Thread.sleep(releaseInterval);
            } catch (InterruptedException e) {
                // If the thread is interrupted, handle the exception and reset interrupt status
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }

        }
    }

    public int getId() {
        return id;
    }

    public int getTicketPerRelease() {
        return ticketPerRelease;
    }

    public void setTicketPerRelease(int ticketPerRelease) {
        this.ticketPerRelease = ticketPerRelease;
    }

    public int getReleaseInterval() {
        return releaseInterval;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

}
