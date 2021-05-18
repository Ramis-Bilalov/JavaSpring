package com.bilalov.javaspringbootlessonfour.controllers;

import com.bilalov.javaspringbootlessonfour.entities.User;
import com.bilalov.javaspringbootlessonfour.repositories.RoleRepository;
import com.bilalov.javaspringbootlessonfour.services.UsersService;
import com.bilalov.javaspringbootlessonfour.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UsersService usersService;
    private RoleRepository roleRepository;

    @Autowired
    public void setService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String indexUserPage(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "user_views/user";
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", usersService.findById(id).orElseThrow(NotFoundException :: new));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_views/user_form";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute(new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "user_views/user_form";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        usersService.createOrUpdate(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id) {
        usersService.deleteById(id);
        return "redirect:/user";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("product_views/not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
