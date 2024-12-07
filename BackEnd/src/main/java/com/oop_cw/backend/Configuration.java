package com.oop_cw.backend;

public class Configuration {
    private static Configuration instance;
    private int totalTickets = 100;
    private int ticketReleaseRate = 2;
    private int customerRetrievalRate = 2;
    private int maxTicketCapacity = 10;
    private boolean isRunning = true;
    
    private Configuration() {}

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if (totalTickets > 0) {
            this.totalTickets = totalTickets;
        } else {
            throw new IllegalArgumentException("Total tickets must be greater than 0.");
        }
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        if (ticketReleaseRate > 0) {
            this.ticketReleaseRate = ticketReleaseRate;
        } else {
            throw new IllegalArgumentException("Ticket release rate must be greater than 0.");
        }
    }

    public int getCustomerRetievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        if (customerRetrievalRate > 0) {
            this.customerRetrievalRate = customerRetrievalRate;
        } else {
            throw new IllegalArgumentException("Customer retrieval rate must be greater than 0.");
        }
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        if (maxTicketCapacity > 0) {
            this.maxTicketCapacity = maxTicketCapacity;
        } else {
            throw new IllegalArgumentException("Max ticket capacity must be greater than 0.");
        }
    }


    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning){
        this.isRunning = isRunning;
    }
}

