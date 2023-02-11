package org.itstep.mvcapp.controller;

import org.itstep.mvcapp.model.Car;
import org.itstep.mvcapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("cars/add")
    public String showNewCarForm(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);

        return "new_car";

    }

    @PostMapping("cars/save")
    public String saveCarForm(@ModelAttribute("car") Car car) {

        carService.saveCar(car);

        return "redirect:/cars";
    }

    @GetMapping("cars/update/{id}")
    public String showUpdateCarForm(@PathVariable(value = "id") long id, Model model) {

        Car car = carService.getCarById(id);
        model.addAttribute("car", car);

        return "update_car";
    }

    @GetMapping("cars/delete/{id}")
    public String deleteCarForm(@PathVariable(value = "id") long id) {

        carService.deleteCarById(id);

        return "redirect:/cars";
    }

    @GetMapping("/cars/page/{pageNo}")
    public String pagination(@PathVariable (value = "pageNo") int pageNo,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir,
                             Model model) {
        int pageSize = 5;

        Page<Car> page = carService.pagination(pageNo, pageSize, sortField, sortDir);
        List<Car> listCars = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCars", listCars);
        return "cars";
    }

    @GetMapping("/cars")
    public String showAllCars(Model model)
    {


        return pagination(1, "CarName", "asc", model);
    }
}
