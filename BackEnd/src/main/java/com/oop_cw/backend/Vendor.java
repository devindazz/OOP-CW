package com.oop_cw.backend;

import java.util.Random;

public class Vendor implements Runnable {
    private int id;
    private int ticketPerRelease;
    private int releaseInterval;

    public Vendor(int releaseInterval) {
        this.id = new Random().nextInt(100000);
        this.ticketPerRelease = Configuration.getInstance().getTicketReleaseRate();
        this.releaseInterval = releaseInterval;
    }

    @Override
    public void run() {
        while (true) {
            if (!Configuration.getInstance().getIsRunning()) {
                continue;
            }
            for (int i = 0; i < ticketPerRelease; i++) {
                Ticket ticket = new Ticket(this.id);
                TicketPool.getInstance().addTicket(ticket);
            }
            try {
                Thread.sleep(releaseInterval);
            } 
            catch (InterruptedException e) {
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
