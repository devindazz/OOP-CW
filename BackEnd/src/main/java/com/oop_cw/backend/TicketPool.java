package com.oop_cw.backend;

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

public synchronized void addTicket(Ticket ticket) {
    if (tickets.size() >= Configuration.getInstance().getMaxTicketCapacity()) {
        System.out.printf("Ticket pool is Full. Cannot add ticket %d %n", ticket.getId());
        try {
            wait();
        } catch(InterruptedException e) {
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
        System.out.printf("Adding ticket: %d by vendor: %d %n",ticket.getId(), ticket.getVendorId() );
        tickets.add(ticket);
        notifyAll();  
    }
}

public synchronized void removeTicket(int customerId) {
    while (tickets.isEmpty()) {
        System.out.println("Ticket pool is empty cannot remove tickets.");
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

}
