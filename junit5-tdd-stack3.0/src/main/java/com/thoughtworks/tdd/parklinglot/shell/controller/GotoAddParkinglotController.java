package com.thoughtworks.tdd.parklinglot.shell.controller;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

public class GotoAddParkinglotController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoAddParkinglotController(Request request, Response response, ParkingBoy parkingBoy) {

        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String process() {
        response.send("请输入你要添加的停车场信息（格式为：名称，车位）：");
        return "";
    }
}
