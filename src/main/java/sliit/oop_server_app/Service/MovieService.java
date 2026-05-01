package sliit.oop_server_app.Service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.CategoryRepository;
import sliit.oop_server_app.repository.MovieRepository;

import java.util.Date;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> {
                    MovieResponse res = new MovieResponse();
                    res.setId(movie.getId());
                    res.setName(movie.getName());
                    res.setLanguage(movie.getLanguage());
                    res.setCountry(movie.getCountry());
                    res.setShortDescription(movie.getShortdescription());
                    res.setDescription(movie.getDescription());
                    res.setImage(movie.getImage());
                    res.setLink(movie.getLink());
                    res.setTrailerLink(movie.getTrailerlink());
                    res.setCategoryId(movie.getCategory().getId());
                    res.setPrice(movie.getPrice());
                    res.setImdb(movie.getImdb());
                    res.setTomato(movie.getTomato());
                    res.setViewcount(movie.getViewcount());
                    res.setHours(movie.getHours());
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

    public Movie updateMovie(Integer id, Movie request) {
        // 1. Fetch the existing movie using Integer id
        Movie data = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        // 2. Update fields manually to ensure data integrity
        data.setName(request.getName());
        data.setLanguage(request.getLanguage());
        data.setCountry(request.getCountry());
        data.setHours(request.getHours());
        data.setShortdescription(request.getShortdescription());
        data.setDescription(request.getDescription());
        data.setImage(request.getImage());
        data.setLink(request.getLink());
        data.setTrailerlink(request.getTrailerlink());
        data.setImdb(request.getImdb());
        data.setTomato(request.getTomato());
        data.setViewcount(request.getViewcount());
        data.setPrice(request.getPrice());

        // Handle the Category association if it's being updated
        if (request.getCategory() != null) {
            data.setCategory(request.getCategory());
        }

        // 3. Save and return the updated entity
        return movieRepository.save(data);
    }

    public MovieResponse createMovie(Movie request) {
        // 1. Create a new Movie entity instance
        Movie movie = new Movie();

        // 2. Map Request data to the Entity
        movie.setName(request.getName());
        movie.setLanguage(request.getLanguage());
        movie.setCountry(request.getCountry());
        movie.setHours(request.getHours());
        movie.setShortdescription(request.getShortdescription());
        movie.setDescription(request.getDescription());
        movie.setImage(request.getImage());
        movie.setLink(request.getLink());
        movie.setTrailerlink(request.getTrailerlink());
        movie.setImdb(request.getImdb());
        movie.setTomato(request.getTomato());
        movie.setPrice(request.getPrice());
        movie.setViewcount(0); // Initialize view count for new movies

        // 3. Handle Category (Fetch from DB to ensure it exists)
        Category category = categoryRepository.findById(request.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        movie.setCategory(category);

        // 4. Save the entity
        Movie savedMovie = movieRepository.save(movie);


        return mapToResponse(savedMovie);
    }

    // Helper method to convert Entity -> Response
    private MovieResponse mapToResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setName(movie.getName());
        // Add other fields needed for the UI...
        return response;
    }

    public void deleteMovie(int id) {
        if (!movieRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        movieRepository.deleteById(id);
    }


//    public List<Movie> filterByYears(String year) {
//        // Check if the year exists
//        if (movieRepository.existsByYear(year)) {
//            // Just call the method directly; no need to cast or redeclare the type
//            return movieRepository.findAllByYear(year);
//        }
//        // Returning an empty list is safer than returning null
//        return Collections.emptyList();
//    }
}