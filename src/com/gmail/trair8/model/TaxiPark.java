package com.gmail.trair8.model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiPark {

    private static AtomicReference<TaxiPark> instance;
    private static ArrayList<Car> cars = new ArrayList<Car>();

    private static Lock lock = new ReentrantLock();
    private static Condition isFree = lock.newCondition();

    private TaxiPark() {}

    public ArrayList<Car> getCars() {
        return cars;
    }

    public static TaxiPark getInstance() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new AtomicReference<>(new TaxiPark());
            }
        } finally {
            lock.unlock();
        }
        return instance.get();
    }
}