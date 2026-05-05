package sliit.oop_server_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.Service.MovieService;
import sliit.oop_server_app.entity.Movie;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<Movie> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search")
    public List<MovieResponse> search(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping("/create")
    public MovieResponse addMovie(@RequestBody Movie request, @RequestParam List<Integer> categoryIds) {
        return movieService.createMovie(request, categoryIds);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> update(
            @PathVariable int id,
            @RequestBody Movie request){

        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @PutMapping("/update/count/{id}")
    public String updateCount(@PathVariable int id){
        movieService.updatecount(id);
        return "success";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        movieService.deletemovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

//    @GetMapping("/years/{Year}")
//    public List<?> movielist (@PathVariable String Year){
//        return movieService.filterByYears(Year);
//    }
}