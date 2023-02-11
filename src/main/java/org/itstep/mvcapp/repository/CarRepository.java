package org.itstep.mvcapp.repository;

import org.itstep.mvcapp.model.Car;
import org.itstep.mvcapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {



}
