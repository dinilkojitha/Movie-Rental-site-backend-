package sliit.oop_server_app.controller;

import sliit.oop_server_app.Service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.DTO.MovieRequest;

import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController( MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    public List<MovieResponse> getAll() {
        return movieService.getAllMovies();
    }

    @GetMapping("/search")
    public List<MovieResponse> search( @RequestParam String query) {
        return movieService.searchMovies(query);
    }

    @PostMapping("/create")
    public String addMovie( @RequestBody MovieRequest request) {
        return movieService.createMovie(request);
    }

    @PutMapping("/update/{id}")
    public String update( @PathVariable Integer id,
                          @RequestBody MovieRequest request){
        return movieService.updateMovie(id, request);
    }

    @PutMapping("/update/count/{id}")
    public String updateCount( @PathVariable int id){
        return movieService.updateCount(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete( @PathVariable Integer id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @GetMapping("/filter/imdb/{imdb}")
    public ResponseEntity<List<MovieResponse>> filterByImdb( @PathVariable Double imdb){
        return ResponseEntity.ok(movieService.imdbFilter(imdb));
    }

    @GetMapping("/filter/year/{year}")
    public ResponseEntity<List<MovieResponse>> filterByYear( @PathVariable Integer year){
        return ResponseEntity.ok(movieService.yearFilter(year));
    }
}