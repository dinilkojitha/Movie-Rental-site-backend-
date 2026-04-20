package sliit.oop_server_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.server.ResponseStatusException;
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

//    @GetMapping("/sort")
//    public List<Movies> sortMovies(@RequestParam String query){
//
//        String regex = "^" + query;
//        List<Movies> movies = this.moviesRepository.findByNameRegexIgnoreCase(regex);
//        return movies;
//    }


    //  Search Algorithm
    @GetMapping("/search/{query}")
    public List<Movies> searchMovies(@PathVariable String query) {

        String regex = "^" + query;   // search starting letters
        return moviesRepository.findByNameRegexIgnoreCase(regex);
    }

    //  Sort Algorithm
    @GetMapping("/sort/{id}")
    public List<Movies> getByCategory(@PathVariable String id){
        List<Movies> movies = this.moviesRepository.getMoviesByCategoryId(id);
        return movies;
    }

    @PostMapping("/add")
    public List<Movies> saveUsers(@RequestBody List<Movies> movies) {
        if (movies.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.print(movies);
        return moviesRepository.saveAll(movies);
    }

    //  Update movie
    @PutMapping("/update/{id}")
    public ResponseEntity<Movies> updateMovies(@PathVariable String id, @RequestBody Movies movies) {
        Movies update = moviesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        update.setName(movies.getName());
        update.setHours(movies.getHours());
        update.setCategoryId(movies.getCategoryId());
        update.setCountry(movies.getCountry());
        update.setLanguage(movies.getLanguage());
        update.setImdb(movies.getImdb());
        update.setActors(movies.getActors());
        update.setDescription(movies.getDescription());
        update.setPrice(movies.getPrice());
        update.setImage(movies.getImage());
        update.setLink(movies.getLink());
        update.setShortDescription(movies.getShortDescription());
        update.setTomato(movies.getTomato());
        update.setTrailerLink(movies.getTrailerLink());
        update.setViewcount(movies.getViewcount());

        Movies updatedMovie = moviesRepository.save(update);
        return ResponseEntity.ok(updatedMovie);
    }

    //  Delete movie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        if (!moviesRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        moviesRepository.deleteById(id);
        return ResponseEntity.ok("Movie deleted successfully");
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







