package com.oop_cw.backend;

import java.util.Random;

public class Ticket {
    private final int id;
    private final int vendorId;

    public Ticket(int vendorId) {
        this.id = new Random().nextInt(100_000);
        this.vendorId = vendorId;
    }

    public int getId() {
        return id;
    }

    public int getVendorId() {
        return vendorId;
    }

}
