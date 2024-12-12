package org.example;

import java.util.*;

public class Vendor {
    private final Integer id;
    private Integer ticketPerRelease;
    private Integer releaseInterval;

    public Vendor(Integer ticketPerRelease, Integer releaseInterval) {
        this.id = new Random().nextInt(100000);
        this.ticketPerRelease = ticketPerRelease;
        this.releaseInterval = releaseInterval;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTicketPerRelease() {
        return ticketPerRelease;
    }

    public void setTicketPerRelease(int ticketPerRelease) {
        this.ticketPerRelease = ticketPerRelease;
    }

    public Integer getReleaseInterval() {
        return releaseInterval;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

}
