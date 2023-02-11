package org.itstep.mvcapp.service;

import org.itstep.mvcapp.model.Car;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();
    void saveCar(Car car);
    Car getCarById(long id);
    void deleteCarById(long id);

    Page<Car> pagination(int page, int size, String sortByField, String sortDir);



}
