package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.UserService;
import sliit.oop_server_app.entity.Users;
import sliit.oop_server_app.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UsersController {

    @Autowired
    private UserService userService;

    

    @GetMapping(produces = "application/json")
    public List<Users> get() {
       return userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsers(@RequestBody Users user) {
       return userService.login(user);
    }

    @PostMapping("/register")
    public List<Users> registerUsers(@RequestBody List<Users> users) {
       return userService.register(users);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.delete(id);
    }


}









