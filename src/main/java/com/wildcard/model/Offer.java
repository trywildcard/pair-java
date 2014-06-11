package com.wildcard.model;

public class Offer {
    // required fields
    private final float price;

    // optional fields

    public Offer(float price) {
        this.price = price;
    }
    
    public float getPrice(){
        return price;
    }
}
