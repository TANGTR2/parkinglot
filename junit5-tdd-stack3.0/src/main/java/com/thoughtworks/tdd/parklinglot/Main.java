package com.thoughtworks.tdd.parklinglot;

import com.thoughtworks.tdd.parklinglot.core.ParkingBoy;
import com.thoughtworks.tdd.parklinglot.core.ParkingLot;
import com.thoughtworks.tdd.parklinglot.core.ParkinglotManager;
import com.thoughtworks.tdd.parklinglot.shell.controller.*;
import com.thoughtworks.tdd.parklinglot.shell.Router;
import com.thoughtworks.tdd.parklinglot.shell.io.Request;
import com.thoughtworks.tdd.parklinglot.shell.io.Response;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner cliInput = new Scanner(System.in);

    public static void main(String[] args) {

        String initRootPath = "root";
        Request request = new Request();
        Router router = initRouter(initRootPath, request);
        router.launch();

        try {
            while (true) {
                String command = cliInput.nextLine();
                request.setCommand(command);
                router.processRequest(request);
            }
        } catch (Exception ex) {
            System.out.println("\n App Exist, cause from route not exist!");
        } finally {
            cliInput.close();
        }
    }

    private static Router initRouter(String currentPage, Request request) {
        Response response = new Response();
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(20);
        parkingLot1.setParklotId("001");
        parkingLot1.setParkinglotName("南方停车场");
        parkingLot1.setHadParkCarNum(5);
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLot2.setParklotId("002");
        parkingLot2.setParkinglotName("北方停车场");
        parkingLot2.setHadParkCarNum(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy boy = new ParkingBoy(parkingLots);
        ParkinglotManager manager = new ParkinglotManager(boy,parkingLots);

        Router router = new Router(currentPage);
        router.registerRoute("root", new RootController(request, response, boy));
        router.registerRoute("root/1", new MainController(request, response, boy));
        router.registerRoute("root/1/1", new GotoParkingController(request, response, boy));
        router.registerRoute("root/1/2", new GoToPickUpController(request, response, boy));
        router.registerRoute("root/1/1/*", new ParkingController(request, response, boy));
        router.registerRoute("root/1/2/*", new PickUpController(request, response, boy));
        router.registerRoute("root/2", new ParkinglotController(request, response, boy));
        router.registerRoute("root/2/1", new GotoShowParkinglotDetialController(request, response, boy,manager));
        router.registerRoute("root/2/2", new GotoAddParkinglotController(request, response, boy));
        router.registerRoute("root/2/2/*", new AddParkinglotController(request, response, boy,manager));
        router.registerRoute("root/2/3", new GotoRemoveParkinglotController(request, response, boy));
        router.registerRoute("root/2/3/*", new RemoveParkinglotController(request, response, boy,manager));

        return router;
    }

}
