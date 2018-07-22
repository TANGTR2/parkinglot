package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.core.ParkinglotManager;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

public class RemoveParkinglotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;
    private ParkinglotManager parkinglotManager;

    public RemoveParkinglotController(Request request, Response response, ParkingBoy parkingBoy,ParkinglotManager parkinglotManag) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        this.parkinglotManager = parkinglotManager;
    }

    @Override
    public String process() {
        boolean isExist=false;
        for (ParkingLot parkingLot:parkingBoy.getParkingLots()){
            if(parkingLot.getParklotId().equals(request.getCommand()) && parkingLot.getParkedCars().size()==0){
                isExist = true;
                parkingBoy.removeParkingLot(parkingLot);
                response.send("停车场删除成功！");
                break;
            }else if (parkingLot.getParklotId().equals(request.getCommand())){
                isExist = true;
                response.send("停车场删除失败，原因：此停车场中，依然停有汽车，无法删除！");
                break;
            }
        }
        if(!isExist){
            response.send("停车场删除失败，原因：此停车场不存在！");
        }
        return "forward:root";
    }
}
