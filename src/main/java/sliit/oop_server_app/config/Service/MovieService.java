package sliit.oop_server_app.config.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.CategoryHasMovie;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private Actors_has_moviesRepository actors_has_moviesRepository;
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private CategoryHasMovieRepository categoryHasMovieRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAllWithCategories();

        // Populate the transient categories field
        for (Movie movie : movies) {
            if (movie.getCategoryHasMovies() != null) {
                movie.setCategories(
                        movie.getCategoryHasMovies().stream()
                                .map(CategoryHasMovie::getCategory)
                                .toList()
                );
            }
        }

        return movies;
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


        // 3. Save and return the updated entity
        return movieRepository.save(data);
    }

    public MovieResponse createMovie(Movie request, List<Integer> categoryIds) {
        // 1. Map and Save the new Movie
        Movie movie = new Movie();
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
        movie.setViewcount(0);

        // Save movie first to generate the ID
        Movie savedMovie = movieRepository.save(movie);

        // 2. Loop through the List of Category IDs
        if (categoryIds != null && !categoryIds.isEmpty()) {
            for (Integer catId : categoryIds) {
                // Find category by ID (safe check)
                categoryRepository.findById(catId).ifPresent(category -> {
                    // Create the join entity entry
                    CategoryHasMovie mapping = new CategoryHasMovie();
                    mapping.setMovies(savedMovie);
                    mapping.setCategory(category);

                    // Save the mapping to the join table repository
                    categoryHasMovieRepository.save(mapping);
                });
            }
        }

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


    public void updatecount(Integer id) {
        movieRepository.findById(id).ifPresent(movie -> {
            // Handle null viewcount safety
            int currentCount = (movie.getViewcount() == null) ? 0 : movie.getViewcount();
            movie.setViewcount(currentCount + 1);

            movieRepository.save(movie);
        });
    }

    @Transactional
    public void deletemovie(Integer id) {
        // 1. Delete all associations in the join table first
        categoryHasMovieRepository.deleteByMovies_id(id);

        reviewRepository.deleteByMovies_id(id);
        // 2. If you have an actors join table, delete that too
        // actorHasMovieRepository.deleteByMovies_id(id);

        // 3. Now it's safe to delete the movie
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