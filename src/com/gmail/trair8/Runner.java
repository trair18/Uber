package com.gmail.trair8;

public class Runner {

    public static void main(String[] args) {

        Car car1 = new Car("audi");
        Car car2 = new Car("bmw");
        Car car3 = new Car("lada");
        TaxiService taxiService = new TaxiService();

        TaxiPark.getInstance().getCars().add(car1);
        TaxiPark.getInstance().getCars().add(car2);
        TaxiPark.getInstance().getCars().add(car3);
        System.out.println(TaxiPark.getInstance().getCars());

        Passenger passenger1 = new Passenger(2, 3);
        Passenger passenger2 = new Passenger(3, 4);
        Passenger passenger3 = new Passenger(4, 5);

        passenger1.sendRequest(taxiService);
        passenger2.sendRequest(taxiService);
        System.out.println(passenger1);
        System.out.println(passenger2);
    }
}
