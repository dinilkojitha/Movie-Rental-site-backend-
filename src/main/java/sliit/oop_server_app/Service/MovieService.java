package sliit.oop_server_app.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.DTO.MovieRequest;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.DTO.MovieUpdateRequest;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> {
                    MovieResponse res = new MovieResponse();
                    res.setName(movie.getName());
                    res.setLanguage(movie.getLanguage());
                    res.setCountry(movie.getCountry());
                    res.setShortDescription(movie.getShortDescription());
                    res.setDescription(movie.getDescription());
                    res.setImage(movie.getImage());
                    res.setLink(movie.getLink());
                    res.setTrailerLink(movie.getTrailerLink());
                    res.setCategoryId(movie.getCategoryId());
                    res.setPrice(movie.getPrice());
                    res.setImdb(movie.getImdb());
                    res.setTomato(movie.getTomato());
                    res.setViewcount(movie.getViewcount());
                    res.setDuration(movie.getDuration());
                    res.setActors(movie.getActors());
                    return res;
                })
                .toList();
    }

    public List<MovieResponse> searchMovies(String query) {
        return movieRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(movie -> {
                    MovieResponse res = new MovieResponse();
                    res.setName(movie.getName());
                    res.setPrice(movie.getPrice());
                    return res;
                })
                .toList();
    }

    public MovieResponse updateMovie(String id, MovieUpdateRequest request){

        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        existing.setName(request.getName());
        existing.setLanguage(request.getLanguage());
        existing.setCountry(request.getCountry());
        existing.setPrice(request.getPrice());
        existing.setDuration(request.getDuration());
        existing.setImdb(request.getImdb());
        existing.setTomato(request.getTomato());
        existing.setViewcount(request.getViewcount());
        existing.setDescription(request.getDescription());
        existing.setShortDescription(request.getShortDescription());
        existing.setImage(request.getImage());
        existing.setLink(request.getLink());
        existing.setTrailerLink(request.getTrailerLink());

        Movie updated = movieRepository.save(existing);

        MovieResponse response = new MovieResponse();
        
        response.setName(updated.getName());
        response.setLanguage(updated.getLanguage());
        response.setCountry(updated.getCountry());
        response.setPrice(updated.getPrice());
        response.setDuration(updated.getDuration());
        response.setImdb(updated.getImdb());
        response.setTomato(updated.getTomato());
        response.setViewcount(updated.getViewcount());
        response.setDescription(updated.getDescription());
        response.setShortDescription(updated.getShortDescription());
        response.setImage(updated.getImage());
        response.setLink(updated.getLink());
        response.setTrailerLink(updated.getTrailerLink());

        return response;
    }

    public MovieResponse createMovie(MovieRequest request){

        Movie movie = new Movie();                      //  Entity Object creat for inputs
        movie.setName(request.getName());
        movie.setLanguage(request.getLanguage());
        movie.setCountry(request.getCountry());
        movie.setPrice(request.getPrice());
        movie.setDuration(request.getDuration());
        movie.setCategoryId(request.getCategoryId());
        movie.setImdb(request.getImdb());
        movie.setTomato(request.getTomato());
        movie.setViewcount(request.getViewcount());
        movie.setDescription(request.getDescription());
        movie.setShortDescription(request.getShortDescription());
        movie.setImage(request.getImage());
        movie.setLink(request.getLink());
        movie.setTrailerLink(request.getTrailerLink());

        Movie saved = movieRepository.save(movie);

        MovieResponse response = new MovieResponse();   //  Response Object creat for outputs
        response.setName(saved.getName());
        response.setLanguage(saved.getLanguage());
        response.setCountry(saved.getCountry());
        response.setPrice(saved.getPrice());
        response.setDuration(saved.getDuration());
        response.setCategoryId(saved.getCategoryId());
        response.setImdb(saved.getImdb());
        response.setTomato(saved.getTomato());
        response.setViewcount(saved.getViewcount());
        response.setDescription(saved.getDescription());
        response.setShortDescription(saved.getShortDescription());
        response.setImage(saved.getImage());
        response.setLink(saved.getLink());
        response.setTrailerLink(saved.getTrailerLink());
        
        return response;
    }

    public void deleteMovie(String id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        movieRepository.deleteById(id);
    }
}