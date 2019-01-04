package com.example.projekt.car.DTOs;

public class Fault {
    private int carID;
    private boolean isCritical;
    private String description;
    private long date;

    public Fault(int carID, boolean isCritical, String description, long date) {
        this.carID = carID;
        this.isCritical = isCritical;
        this.description = description;
        this.date = date;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
