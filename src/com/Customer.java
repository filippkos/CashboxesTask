package com;


public class Customer {
    int timeAtTheCheckout;
    public Customer() {
        this.timeAtTheCheckout = (int) Math.round(Math.random() * 10);
    }

    public int getTimeAtTheCheckout() {
        return timeAtTheCheckout;
    }
}
