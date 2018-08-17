package com.gmail.trair8.runner;

import com.gmail.trair8.model.Car;
import com.gmail.trair8.model.Passenger;
import com.gmail.trair8.model.TaxiPark;
import com.gmail.trair8.service.TaxiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        Car car1 = new Car("audi", 20, 20);
        Car car2 = new Car("bmw", 2, 4);
        Car car3 = new Car("lada", 7, 7);
        TaxiPark.getInstance().getCars().add(car1);
        TaxiPark.getInstance().getCars().add(car2);
        TaxiPark.getInstance().getCars().add(car3);
        LOGGER.info(TaxiPark.getInstance().getCars());

        TaxiService taxiService = new TaxiService();
        Passenger passenger1 = new Passenger("a", 2, 3, 34, 34, taxiService);
        Passenger passenger2 = new Passenger("b",19, 19, 23, 23, taxiService);
        Passenger passenger3 = new Passenger("c",4, 5, 14, 15, taxiService);
        Passenger passenger4 = new Passenger("d",4, 5, 14, 15, taxiService);

        new Thread(passenger1).start();
        new Thread(passenger2).start();
        new Thread(passenger3).start();
        new Thread(passenger4).start();



    }
}
