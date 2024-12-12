package org.example;

import java.util.*;
public class Ticket {
    private final Integer id;
    private final Integer vendorId;

    public Ticket(Integer vendorId) {
        this.id = new Random().nextInt(100_000);
        this.vendorId = vendorId;
    }
    public Integer getId() {
        return id;
    }
    public Integer getVendorId() {
        return vendorId;
    }
}
