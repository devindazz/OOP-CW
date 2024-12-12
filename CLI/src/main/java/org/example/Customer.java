package org.example;

import java.util.*;

public class Customer {
    private Integer id;
    private Integer retrievalInterval;

    public Customer(Integer retrievalInterval) {
        this.id = new Random().nextInt(100000);
        setRetrievalInterval(retrievalInterval);
        ;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRetrievalInterval() {
        return retrievalInterval;
    }

    public void setRetrievalInterval(int retrievalInterval) {
        this.retrievalInterval = retrievalInterval;
    }
}
