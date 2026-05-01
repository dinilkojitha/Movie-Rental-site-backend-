package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.ReviewService;
import sliit.oop_server_app.entity.Rating;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(produces = "application/json")
    public List<Rating> get() {
       return reviewService.getAll();
    }

    @PostMapping("/new")
    public Rating registerUsers(@RequestBody Rating reviews) {
       return reviewService.add(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public int deleteUser(@PathVariable int id) {
        return reviewService.delete(id);
    }


}









