package org.itstep.mvcapp.controller;

import org.itstep.mvcapp.model.Car;
import org.itstep.mvcapp.model.User;
import org.itstep.mvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/is-alive")
    public String isAlive(Model model) {
        Date date = new Date();
        System.out.println("server time is " + date);
        model.addAttribute("server_date", date);
        return "is_alive";
    }

    @GetMapping("users/add")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";

    }

    @PostMapping("users/save")
    public String saveUserForm(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("users/update/{id}")
    public String showUpdateUserForm(@PathVariable(value = "id") long id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "update_user";
    }

    @GetMapping("users/delete/{id}")
    public String deleteUserForm(@PathVariable(value = "id") long id) {

        userService.deleteUserById(id);

        return "redirect:/users";
    }

    @GetMapping("/users/page/{pageNo}")
    public String pagination(@PathVariable (value = "pageNo") int pageNo,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir,
                             Model model) {
        int pageSize = 5;

        Page<User> page = userService.pagination(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model)
    {


        return pagination(1, "userName", "ASC", model);
    }




//    @GetMapping("/cars")
//    public String showAllCars(Model model) {
//        Car car1 = new Car((long)1, "Honda", "Red");
//        Car car2 = new Car((long)2, "BMW", "Blue");
//        List<Car> cars = Arrays.asList(car1, car2);
//
//        model.addAttribute("cars", cars);
//
//        return "cars";
//    }
}
