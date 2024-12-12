package org.example;

public class TicketPool {
    private static TicketPool instance;
    
    private TicketPool() {}

    public static TicketPool getInstance() {
        if (instance == null) {
            instance = new TicketPool();
        }
        return instance;
    }


}
