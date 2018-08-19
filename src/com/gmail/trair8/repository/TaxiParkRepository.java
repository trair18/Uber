package com.gmail.trair8.repository;

import com.gmail.trair8.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaxiParkRepository {

    private static final Logger LOGGER = LogManager.getLogger(TaxiParkRepository.class);

    public List<Car> findAll() throws FileNotFoundException, IOException {
        List<Car> cars = new ArrayList<Car>();
        FileInputStream fileInputStream = new FileInputStream(new File("cars.txt"));
        byte[] a = new byte[fileInputStream.available()];
        fileInputStream.read(a, 0, fileInputStream.available());
        String p = new String(a);
        String[] w = p.split("\n");
        for (String q : w) {
            String[] s = q.split("(, )|($)");
            cars.add(new Car(Integer.parseInt(s[0]), s[1], Integer.parseInt(s[2]), Integer.parseInt(s[3])));
        }
        fileInputStream.close();
        return cars;
    }

}
