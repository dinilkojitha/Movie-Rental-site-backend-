package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Rating;
import sliit.oop_server_app.repository.RatingsRepository;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/ratings")
public class RatingsController {
    @Autowired
    private RatingsRepository ratingsRepository;




    @GetMapping(produces = "application/json")
    public List<Rating> get() {
        List<Rating> ratings = this.ratingsRepository.findAll();
        return ratings;
    }

    @PostMapping("/save")
    public List<Rating> saveUsers(@RequestBody List<Rating> ratings) {
        if (ratings.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.print(ratings);
        return ratingsRepository.saveAll(ratings);
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







