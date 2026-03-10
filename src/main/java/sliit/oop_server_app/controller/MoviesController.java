package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movies;
import sliit.oop_server_app.entity.Users;
import sliit.oop_server_app.repository.MoviesRepository;
import sliit.oop_server_app.repository.UsersRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/movies")
public class MoviesController {
    @Autowired
    private MoviesRepository moviesRepository;




    @GetMapping(produces = "application/json")
    public List<Movies> get() {
        List<Movies> movies = this.moviesRepository.findAll();
        return movies;
    }

    @PostMapping("/save")
    public List<Movies> saveUsers(@RequestBody List<Movies> movies) {
        if (movies.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.print(movies);
        return moviesRepository.saveAll(movies);
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







