package com.cracow.controllers;
import com.cracow.dto.UserDto;
import com.cracow.entities.User;
import com.cracow.repositories.UserRepository;
import com.cracow.services.UserService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password, HttpServletRequest request, HttpSession session)
    {
        session.invalidate();
        if (userService.login(email, password)) {
            HttpSession newSession = request.getSession(); // create session
            return new ResponseEntity<String>(newSession.getId(),HttpStatus.OK);
        }
        else return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session)
    {
        System.out.println(session.getId());
        session.invalidate();
        return new ResponseEntity<Void>(HttpStatus.OK);
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

