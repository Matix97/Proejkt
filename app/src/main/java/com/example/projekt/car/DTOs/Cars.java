package com.example.projekt.car.DTOs;

import java.io.Serializable;

public class Cars implements Serializable {
    private String model;
    private int faultID;
    private String registrationNumber;
    private boolean isOk;
    private double fuelAmount;
    private boolean isTaken;
    private int id;

    public Cars(String model, int faultID, String registrationNumber, boolean isOk, double fuelAmount, boolean isTaken, int id) {
        this.model = model;
        this.faultID = faultID;
        this.registrationNumber = registrationNumber;
        this.isOk = isOk;
        this.fuelAmount = fuelAmount;
        this.isTaken = isTaken;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cars{" +
                "model='" + model + '\'' +
                ", faultID=" + faultID +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", isOk=" + isOk +
                ", fuelAmount=" + fuelAmount +
                ", isTaken=" + isTaken +
                ", id=" + id +
                '}';
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getFaultID() {
        return faultID;
    }

    public void setFaultID(int faultID) {
        this.faultID = faultID;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
