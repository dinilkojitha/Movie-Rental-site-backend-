package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.ReviewService;
import sliit.oop_server_app.Service.UserService;
import sliit.oop_server_app.entity.Review;
import sliit.oop_server_app.entity.Users;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(produces = "application/json")
    public List<Review> get() {
       return reviewService.getAll();
    }

    @PostMapping("/new")
    public Review registerUsers(@RequestBody Review reviews) {
       return reviewService.add(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return reviewService.delete(id);
    }


}









