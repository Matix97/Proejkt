package com.example.projekt.car.DTOs;

public class Fuel {
    private int carID;
    private double amount;
    private long timestamp;

    public Fuel(int carID, double amount, long timestamp) {
        this.carID = carID;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
