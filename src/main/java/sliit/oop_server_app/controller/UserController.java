package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.UserService;
import sliit.oop_server_app.entity.User;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public List<User> get() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public List<User> registerUser(@RequestBody List<User> User) {
        return userService.register(User);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.delete(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/sort")
    public List<User> sort() {
        return userService.sortUser();
    }

    @GetMapping("/search/{gmail}")
    public ResponseEntity<?> search(@PathVariable String gmail) {
        return userService.searchUserByGmail(gmail);
    }
}