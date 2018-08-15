package com.gmail.trair8;

import java.util.ArrayList;

public class TaxiPark {

    private volatile static TaxiPark instance;
    private ArrayList<Car> cars = new ArrayList<Car>();

    private TaxiPark() {}

    public ArrayList<Car> getCars() {
        return cars;
    }

    public static TaxiPark getInstance() {
        if (instance == null) {
            synchronized (TaxiPark.class) {
                if (instance == null) {
                    instance = new TaxiPark();
                }
            }
        }
        return instance;
    }
}