package com.cracow.controllers;
import com.cracow.dto.UserDto;
import com.cracow.entities.User;
import com.cracow.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/getall")
    public ResponseEntity<Iterable<User>> getAll()
    {
        Iterable<User> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
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

    @PostMapping("/register")
    public ResponseEntity<Void> saveUser(@RequestBody UserDto user)
    {
        try {
            User userE = convertToEntity(user);
            if (userService.saveUser(userE))
            return new ResponseEntity<Void>(HttpStatus.CREATED);
            else
                return new ResponseEntity<Void>(HttpStatus.CONFLICT);


        }
        catch (Exception e)
        {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

    }


    private UserDto userConvertToDTO(User user)
    {
        UserDto newUser = modelMapper.map(user, UserDto.class);
        return newUser;
    }

    private User convertToEntity(UserDto userDto) throws ParseException {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

}

