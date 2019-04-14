package com.cracow.controllers;
import com.cracow.entities.User;
import com.cracow.services.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/getall")
    public Iterable<User> getAll()
    {
        return userService.getAllUsers();
    }

    @GetMapping
    public Optional<User> getSideById(@RequestParam String id)
    {
        return userService.getUserById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestParam String id)
    {
        userService.deleteById(id);
    }

    @PostMapping("/add")
    public User saveSite(@RequestBody User user)
    {
        return userService.saveUser(user);
    }

}

