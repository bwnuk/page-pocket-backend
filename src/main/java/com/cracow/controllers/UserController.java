package com.cracow.controllers;

import com.cracow.dto.UserDto;
import com.cracow.entities.User;
import com.cracow.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;


@RestController
@RequestMapping("/api/users")
@Api(value="users", description="operations pertaining to users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/getall")
    //@RequestMapping(value = "/getall", method= RequestMethod.GET)
    @ApiOperation(value="get all users", response=Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of users"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<Iterable<User>> getAll()
    {
        Iterable<User> result = userService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value="Log in", response=HttpSession.class)
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
    @ApiOperation(value="Log out")
    public ResponseEntity<Void> logout(HttpSession session)
    {
        System.out.println(session.getId());
        session.invalidate();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @PostMapping("/register")
    @ApiOperation(value="Register")
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

