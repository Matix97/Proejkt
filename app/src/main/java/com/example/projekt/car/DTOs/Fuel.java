package com.example.projekt.car.DTOs;

public class Fuel {
    private int carID;
    private double amount;
    private long date;

    public Fuel(int carID, double amount, long date) {
        this.carID = carID;
        this.amount = amount;
        this.date = date;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
