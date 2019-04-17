package com.cracow.controllers;
import com.cracow.entities.UserData;
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
    public Iterable<UserData> getAll()
    {
        return userService.getAllUsers();
    }

    @GetMapping
    public Optional<UserData> getSideById(@RequestParam String id)
    {
        return userService.getUserById(id);
    }

    @DeleteMapping
    public void deleteById(@RequestParam String id)
    {
        userService.deleteById(id);
    }

    @PostMapping("/add")
    public UserData saveSite(@RequestBody UserData userData)
    {
        return userService.saveUser(userData);
    }

}

