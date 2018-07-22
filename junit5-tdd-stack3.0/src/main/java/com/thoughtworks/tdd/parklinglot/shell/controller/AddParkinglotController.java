package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkinglotManager;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

import static java.lang.Integer.parseInt;

public class AddParkinglotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;
    private ParkinglotManager parkinglotManager;

    public AddParkinglotController(Request request, Response response, ParkingBoy parkingBoy,ParkinglotManager parkinglotManager) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        this.parkinglotManager = parkinglotManager;
    }

    public String[] handleInformation(String command) {
        String[] div = command.split(",");
        return div;
    }

    @Override
    public String process() {
        String[] div = handleInformation(request.getCommand());
        parkinglotManager.addParkingLot(div[0],parseInt(div[1]));
        response.send("停车场添加成功！");
        return "forward:root";
    }
}

