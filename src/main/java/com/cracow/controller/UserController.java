package com.cracow.controller;

import com.cracow.dto.input.UserNewDto;
import com.cracow.service.UserService;
import com.cracow.service.security.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@Api(value = "Users API")
@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(
            HttpServletRequest request,
            @Valid @RequestBody UserNewDto userNewDto
    ) {

        String sessionId = request.getSession().getId();
        userService.registerNewUser(userNewDto);

        String result = securityService.autologin(sessionId, userNewDto.getEmail(), userNewDto.getPassword());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            HttpServletRequest request,
            @RequestParam @Email String email,
            @RequestParam String password
    ) {
        String sessionId = request.getSession().getId();
        String result = securityService.autologin(sessionId, email, password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("isFullyAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

