package com.oop_cw.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private static TicketPool instance;
    private volatile List<Ticket> tickets = Collections.synchronizedList(new ArrayList<Ticket>());

    private TicketPool() {

    }

    public static TicketPool getInstance() {
        if (instance == null) {
            instance = new TicketPool();
        }
        return instance;
    }
    // Logger instance for logging information
    Logger log = LoggerFactory.getLogger(TicketPool.class);

    /**
     * Method to add a ticket to the pool. 
     * If the pool is full, it logs a message and waits until space becomes available.
     */
    public synchronized void addTicket(Ticket ticket) {
        if (tickets.size() >= Configuration.getInstance().getMaxTicketCapacity()) {
            LogManager.getInstance().addLog(String.format("Ticket pool is full. Cannot add ticket %s. Vendor %d is waiting.", ticket.getId(), ticket.getVendorId()));
            log.info("Ticket pool is full. Cannot add ticket {}. Vendor {} is waiting.",ticket.getId(),ticket.getVendorId());
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
            LogManager.getInstance().addLog(String.format("Adding ticket: %d by vendor: %d", ticket.getId(), ticket.getVendorId()));
            log.info("Adding ticket: {} by vendor: {}", ticket.getId(), ticket.getVendorId());
            tickets.add(ticket);
            notifyAll(); //Notify other threads that a ticket has been added
        }
    }

    /**
     * Method to remove a ticket from the pool. 
     * If the pool is empty, it logs a message and waits until tickets are available.
     */
    public synchronized void removeTicket(int customerId) {
        while (tickets.isEmpty()) {
            LogManager.getInstance().addLog(String.format("Ticket pool is empty cannot remove tickets."));
            log.info("Ticket pool is empty cannot remove tickets.");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        tickets.remove(0);// Remove the first ticket from the list
        notifyAll();

    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

}
