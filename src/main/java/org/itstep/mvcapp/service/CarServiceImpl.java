package org.itstep.mvcapp.service;

import org.itstep.mvcapp.model.Car;
import org.itstep.mvcapp.model.User;
import org.itstep.mvcapp.repository.CarRepository;
import org.itstep.mvcapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public Car getCarById(long id) {

        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = null;
        if (optionalCar.isPresent())
        {
            car = optionalCar.get();
        }
        else {
            System.out.println("Car not found with id = " + id);
            throw new RuntimeException("Car not found with id = " + id);
        }

        return car;
    }

    @Override
    public void deleteCarById(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Page<Car> pagination(int page, int size, String sortByField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var ccc = carRepository.findAll(pageable);

        return ccc;

    }
}
