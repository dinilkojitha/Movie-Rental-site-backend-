package sliit.oop_server_app.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import sliit.oop_server_app.entity.CategoryHasMovie;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.DTO.MovieRequest;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private Actors_has_moviesRepository actors_has_moviesRepository;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
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

    @Transactional(readOnly = true) // Crucial for your loop-and-fetch mechanism
    public List<MovieResponse> getAllMovies() {
        // 1. Use the standard findAll() method
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponse> movieResponseList = new ArrayList<>();

        movies.forEach(movie -> {
            MovieResponse movieResponse = new MovieResponse();

            mapMovieToResponse(movie);

            // 2. Your specific mechanism: Fetching via the Join Repository
            List<CategoryHasMovie> categoryHasMovies = categoryHasMovieRepository.findByMovies_id(movie.getId());
            List<Category> cat = new ArrayList<>();

            categoryHasMovies.forEach(categoryHasMovie -> {
                cat.add(categoryHasMovie.getCategory());
            });

            movieResponse.setCategoryId(cat);
            movieResponseList.add(movieResponse);
        });
        return movieResponseList;
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


    public MovieResponse updateMovie(Integer id, MovieRequest request){

        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        existing.setName(request.getName());
        existing.setLanguage(request.getLanguage());
        existing.setCountry(request.getCountry());
        existing.setPrice(request.getPrice());
        existing.setHours(request.getHours());
        existing.setImdb(request.getImdb());
        existing.setTomato(request.getTomato());
        existing.setViewcount(request.getViewcount());
        existing.setDescription(request.getDescription());
        existing.setShortdescription(request.getShortDescription());
        existing.setImage(request.getImage());
        existing.setLink(request.getLink());
        existing.setTrailerlink(request.getTrailerLink());

        Movie updated = movieRepository.save(existing);

//        MovieResponse response = new MovieResponse();
//
//        response.setName(updated.getName());
//        response.setLanguage(updated.getLanguage());
//        response.setCountry(updated.getCountry());
//        response.setPrice(updated.getPrice());
//        response.setHours(updated.getHours());
//        response.setImdb(updated.getImdb());
//        response.setTomato(updated.getTomato());
//        response.setViewcount(updated.getViewcount());
//        response.setDescription(updated.getDescription());
//        response.setShortDescription(updated.getShortdescription());
//        response.setImage(updated.getImage());
//        response.setLink(updated.getLink());
//        response.setTrailerLink(updated.getTrailerlink());

        return mapMovieToResponse(updated);
    }

    public MovieResponse createMovie(MovieRequest request, List<Integer> categoryIds){

        Movie movie = new Movie();                      //  Entity Object creat for inputs
        Category category = new Category();

        movie.setName(request.getName());
        movie.setLanguage(request.getLanguage());
        movie.setCountry(request.getCountry());
        movie.setPrice(request.getPrice());
        movie.setHours(request.getHours());
//        movie.setCategoryId(request.getCategoryId());
        movie.setImdb(request.getImdb());
        movie.setTomato(request.getTomato());
        movie.setViewcount(request.getViewcount());
        movie.setDescription(request.getDescription());
        movie.setShortdescription(request.getShortDescription());
        movie.setImage(request.getImage());
        movie.setLink(request.getLink());
        movie.setTrailerlink(request.getTrailerLink());

        Movie saved = movieRepository.save(movie);

//        MovieResponse response = new MovieResponse();   //  Response Object creat for outputs
//        response.setName(saved.getName());
//        response.setLanguage(saved.getLanguage());
//        response.setCountry(saved.getCountry());
//        response.setPrice(saved.getPrice());
//        response.setHours(saved.getHours());
//        response.setCategoryId(saved.getCategoryId());
//        response.setImdb(saved.getImdb());
//        response.setTomato(saved.getTomato());
//        response.setViewcount(saved.getViewcount());
//        response.setDescription(saved.getDescription());
//        response.setShortDescription(saved.getShortdescription());
//        response.setImage(saved.getImage());
//        response.setLink(saved.getLink());
//        response.setTrailerLink(saved.getTrailerlink());

        return mapMovieToResponse(saved);
    }


    // Helper method to convert Entity -> Response
    private MovieResponse mapMovieToResponse(Movie movie) {
        MovieResponse response = new MovieResponse();

        response.setName(movie.getName());
        response.setLanguage(movie.getLanguage());
        response.setCountry(movie.getCountry());
        response.setPrice(movie.getPrice());
        response.setHours(movie.getHours());
        response.setYear(movie.getYear());
//        response.setCategoryId(saved.getCategoryId());
        response.setImdb(movie.getImdb());
        response.setTomato(movie.getTomato());
        response.setViewcount(movie.getViewcount());
        response.setDescription(movie.getDescription());
        response.setShortDescription(movie.getShortdescription());
        response.setImage(movie.getImage());
        response.setLink(movie.getLink());
        response.setTrailerLink(movie.getTrailerlink());

        return response;
    }

    public String updateCount(Integer id) {
        movieRepository.findById(id).ifPresent(movie -> {
            // Handle null viewcount safety
            int currentCount = (movie.getViewcount() == null) ? 0 : movie.getViewcount();
            movie.setViewcount(currentCount + 1);

            movieRepository.save(movie);
        });
        return "success";
    }

    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                                     .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        List<CategoryHasMovie> mappings = categoryHasMovieRepository.findByMovies_id(id);

        categoryHasMovieRepository.deleteAll(mappings);
        movieRepository.delete(movie);
    }

    public List<MovieResponse> yearFilter(Integer year){
        List<Movie> movies = movieRepository.findByYearEquals(year);

        return movies.stream()
                .map(this::mapMovieToResponse)
                .toList();
    }

    public List<MovieResponse> imdbFilter(Double imdb){
        List<Movie> movies = movieRepository.findByImdbGreaterThanEqual(imdb);

        return movies.stream()
                .map(this::mapMovieToResponse)
                .toList();
    }
}