package sliit.oop_server_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.DTO.MovieRequest;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.DTO.MovieUpdateRequest;
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
    public List<MovieResponse> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search")
    public List<MovieResponse> search(@RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping("/create")
    public ResponseEntity<MovieResponse> create(@Valid @RequestBody MovieRequest request){
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable String id,
            @RequestBody MovieUpdateRequest request){

        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}