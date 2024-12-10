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

    Logger log = LoggerFactory.getLogger(TicketPool.class);

    public synchronized void addTicket(Ticket ticket) {
        if (tickets.size() >= Configuration.getInstance().getMaxTicketCapacity()) {
            System.out.printf("Ticket pool is full. Cannot add ticket %s. Vendor %d is waiting.%n", ticket.getId(), ticket.getVendorId());
            log.info("Ticket pool is full. Cannot add ticket {}. Vendor {} is waiting.", ticket.getId());
                ticket.getVendorId();
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
            System.out.printf("Adding ticket: %d by vendor: %d %n", ticket.getId(), ticket.getVendorId());
            log.info("Adding ticket: {} by vendor: {}", ticket.getId(), ticket.getVendorId());
            tickets.add(ticket);
            notifyAll();
        }
    }

    public synchronized void removeTicket(int customerId) {
        while (tickets.isEmpty()) {
            System.out.println("Ticket pool is empty cannot remove tickets.");
            log.info("Ticket pool is empty cannot remove tickets.");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        tickets.remove(0);
        notifyAll();

    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

}
