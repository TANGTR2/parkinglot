package com.thoughtworks.tdd.parklinglot.core;

import java.util.List;

public class ParkinglotManager {
    private List<ParkingLot> parkingLots;
    private ParkingBoy parkBoy;
    public String str;

    public ParkinglotManager(ParkingBoy parkBoy, List<ParkingLot> parkingLots) {
        this.parkBoy=parkBoy;
        this.parkingLots=parkingLots;
    }

    public String getParkingLotsInfo() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("|停车场ID|名称|车位|已停车辆|剩余车位|\n");
        buffer.append("======================================\n");
        parkingLots.forEach(parkingLot -> {
            String id=parkingLot.getParklotId();
            String name=parkingLot.getParkinglotName();
            int size=parkingLot.getSize();
            int hadParkCarNum=parkingLot.getHadParkCarNum();
            int restSites=size-hadParkCarNum;
            String item=String.format("|%s|%s|%d(个)|%d(辆)|%d(个)|\n",id,name,size,hadParkCarNum,restSites);
            buffer.append(item);
        });
        buffer.append("\n");
        buffer.append(String.format("总车位：%d(个)\n",totalParkingSpace()));
        buffer.append(String.format("停车总量：%d（辆）\n",totalParking()));
        buffer.append(String.format("总剩余车位：%d（个）",totalParkingSpace()-totalParking()));

        return buffer.toString();
    }

    private int totalParkingSpace() {
        return parkingLots.stream().map(parkingLot->parkingLot.getSize()).reduce(0,(total,current)->total+current);
    }
    private int totalParking() {
        return parkingLots.stream().map(parkingLot->parkingLot.getHadParkCarNum()).reduce(0,(total,current)->total+current);
    }

    public int getParkingLotNum(){
        return parkingLots.size();
    }

    public void addParkingLot(String name, int size) {
        ParkingLot parkingLot = new ParkingLot(size);
        parkingLot.setParkinglotName(name);
        parkingLots.add(parkingLot);
    }
}

