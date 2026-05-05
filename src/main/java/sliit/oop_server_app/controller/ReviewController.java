package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.Service.ReviewService;
import sliit.oop_server_app.entity.Review;
import sliit.oop_server_app.repository.ReviewRepository;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping(produces = "application/json")
    public List<Review> get() {
       return reviewService.getAll();
    }

    @GetMapping("{id}")
    public List<Review> get(@PathVariable int id) {
        return reviewService.getbyMovieId(id);
    }

    @PostMapping("/save")
    public Review saveUsers(@RequestBody Review reviews) {
        return reviewService.add(reviews);
    }

    @PutMapping("/update")
    public Review updateUsers(@RequestBody Review reviews) {
        return reviewService.update(reviews , reviews.getId());
    }

    @PutMapping("/like/{id}")
    public Review like(@PathVariable int id) {
        return reviewService.like(id);
    }

    @PutMapping("/unlike/{id}")
    public Review unlike(@PathVariable int id) {
        return reviewService.dislike(id);
    }




//    @PutMapping("/update")
//    public ResponseEntity<List<FinalPage>> updateFinalPages(@RequestBody List<FinalPage> finals) {
//        for (FinalPage fine : finals) {
//            Optional<FinalPage> existing = finalPageRepository.findById(fine.getId());
//            if (existing.isPresent()) {
//                FinalPage updated = existing.get();
//                updated.setTitle(fine.getTitle());
//                updated.setDescription(fine.getDescription());
//                updated.setImage(fine.getImage());
//                updated.setAbout_detail(fine.getAbout_detail());
//                updated.setAbout_title(fine.getAbout_title());
//                // Add any other fields you want to update
//                finalPageRepository.save(updated);
//            }
//        }
//        // Return the full MainPage list (or filter as needed)
//        List<FinalPage> mainPages = finalPageRepository.findAll();
//        return ResponseEntity.ok(mainPages);
//    }



}







