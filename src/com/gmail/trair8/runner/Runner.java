package com.gmail.trair8.runner;

import com.gmail.trair8.model.Passenger;
import com.gmail.trair8.model.TaxiPark;
import com.gmail.trair8.repository.TaxiParkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Runner {

    private final static Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        try {
            TaxiPark.setCars(new TaxiParkRepository().findAll());
            System.out.println(TaxiPark.getCars());
        } catch (FileNotFoundException ex) {
            LOGGER.error("File not Found", ex);
            LOGGER.info("FileNotFoundException!");
        } catch (IOException ex) {
            LOGGER.error("IOException", ex);
            LOGGER.info("IOException!");
        }
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            new Thread(new Passenger(i + "", random.nextInt(50), random.nextInt(50),
                    random.nextInt(50), random.nextInt(50), TaxiPark.getInstance())).start();
        }

    }
}
