package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkinglotManager;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

public class GotoShowParkinglotDetialController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;
    private ParkinglotManager parkinglotManager;

    public GotoShowParkinglotDetialController(Request request, Response response, ParkingBoy parkingBoy,ParkinglotManager parkinglotManager) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        this.parkinglotManager = parkinglotManager;
    }

    @Override
    public String process() {

        response.send(parkinglotManager.getParkingLotsInfo());
        return "forward:root";
    }
}
