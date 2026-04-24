package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.UserService;
import sliit.oop_server_app.entity.Users;

import java.util.List;

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


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody Users user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/sort")
    public List<Users> sort() {
        return userService.sortUsers();
    }

    @GetMapping("/search/{gmail}")
    public ResponseEntity<?> search(@PathVariable String gmail) {
        return userService.searchUserByGmail(gmail);
    }
}