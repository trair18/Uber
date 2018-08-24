package com.gmail.trair8.runner;

import com.gmail.trair8.model.Passenger;
import com.gmail.trair8.model.TaxiPark;

import java.util.Random;

public class Runner {

    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            new Thread(new Passenger(i + "", random.nextInt(50), random.nextInt(50),
                    random.nextInt(50), random.nextInt(50), TaxiPark.getInstance())).start();
        }

    }
}
