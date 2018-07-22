package com.thoughtworks.tdd.parklinglot.core;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int hadParkCarNum;
    private String parkinglotName;
    private String parklotId;
    private int size;
    private Map<Receipt, Car> parkedCars = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
        if (parkedCars.size() == this.size) {
            throw new ParkingLotFullException();
        }

        Receipt key = new Receipt();
        this.parkedCars.put(key, car);
        return key;
    }

    public boolean containCar(Receipt parkingTicket) {
        return this.parkedCars.containsKey(parkingTicket);
    }


    public Car unPark(Receipt receipt) {
        return this.parkedCars.remove(receipt);
    }

    public boolean isFull() {
        return this.parkedCars.size() == size;
    }

    public String getParklotId() {
        return parklotId;
    }
    public void setParklotId(String parklotId) {
        this.parklotId = parklotId;
    }

    public String getParkinglotName() {
        return parkinglotName;
    }
    public void setParkinglotName(String parkinglotName) {
        this.parkinglotName = parkinglotName;
    }

    public int getHadParkCarNum() {
        return hadParkCarNum;
    }
    public void setHadParkCarNum(int hadParkCarNum) {
        this.hadParkCarNum = hadParkCarNum;
    }

    public void setParkedCars(Map<Receipt, Car> parkedCars) {
        this.parkedCars = parkedCars;
    }
    public Map<Receipt, Car> getParkedCars() {
        return parkedCars;
    }
}
