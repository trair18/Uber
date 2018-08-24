package com.gmail.trair8.repository;

import com.gmail.trair8.exception.RepositoryException;
import com.gmail.trair8.model.Car;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaxiParkRepository {

    public List<Car> findAll() throws RepositoryException {
        try {
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
        } catch (IOException e){
            throw new RepositoryException("Problem with repository!", e);
        }
    }

}
