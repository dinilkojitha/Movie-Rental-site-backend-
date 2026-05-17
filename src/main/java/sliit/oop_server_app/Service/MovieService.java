package sliit.oop_server_app.Service;

import org.springframework.http.RequestEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import sliit.oop_server_app.entity.*;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.DTO.MovieRequest;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private RentalsRepository rentalsRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReplyRepository replyRepository;

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

        // Mapping basic fields
        movieResponse.setId(movie.getId());
        movieResponse.setName(movie.getName());
        movieResponse.setLanguage(movie.getLanguage());
        movieResponse.setCountry(movie.getCountry());
        movieResponse.setHours(movie.getHours());
        movieResponse.setShortDescription(movie.getShortdescription());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setImage(movie.getImage());
        movieResponse.setLink(movie.getLink());
        movieResponse.setTrailerLink(movie.getTrailerlink());
        movieResponse.setImdb(movie.getImdb());
        movieResponse.setTomato(movie.getTomato());
        movieResponse.setViewcount(movie.getViewcount());
        movieResponse.setYear(movie.getYear());
        movieResponse.setPrice(movie.getPrice());
        movieResponse.setRatings(movie.getRatings());

        List<Actor> act = new ArrayList<>();
        List<ActorsHasMovie> actorsHasMovies = actors_has_moviesRepository.findByMovies_Id(movie.getId());
        actorsHasMovies.forEach(actorsHasMovie -> {
            act.add(actorsHasMovie.getActors());
        });
        movieResponse.setActorsId(act);

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


    @Transactional(readOnly = true) // Crucial for your loop-and-fetch mechanism
    public List<MovieResponse> getAllMoviesByRatings() {
        // 1. Use the standard findAll() method
        List<Movie> movies = movieRepository.findByOrderByViewcountDesc();
        List<MovieResponse> movieResponseList = new ArrayList<>();

        movies.forEach(movie -> {
            MovieResponse movieResponse = new MovieResponse();

            // Mapping basic fields
            movieResponse.setId(movie.getId());
            movieResponse.setName(movie.getName());
            movieResponse.setLanguage(movie.getLanguage());
            movieResponse.setCountry(movie.getCountry());
            movieResponse.setHours(movie.getHours());
            movieResponse.setShortDescription(movie.getShortdescription());
            movieResponse.setDescription(movie.getDescription());
            movieResponse.setImage(movie.getImage());
            movieResponse.setLink(movie.getLink());
            movieResponse.setTrailerLink(movie.getTrailerlink());
            movieResponse.setImdb(movie.getImdb());
            movieResponse.setTomato(movie.getTomato());
            movieResponse.setViewcount(movie.getViewcount());
            movieResponse.setYear(movie.getYear());
            movieResponse.setPrice(movie.getPrice());
            movieResponse.setRatings(movie.getRatings());

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


    public String updateMovie(Integer id,MovieRequest request) {

        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        existing.setName(request.getName());
        existing.setCountry(request.getCountry());
        existing.setHours(request.getHours());
        existing.setYear(request.getYear());
        existing.setDescription(request.getDescription());
        existing.setImage(request.getImage());
        existing.setLink(request.getLink());
        existing.setImdb(request.getImdb());
        existing.setTrailerlink(request.getTrailerlink());
        existing.setTomato(request.getTomato());
        existing.setViewcount(request.getViewcount());
        existing.setPrice(request.getPrice());
        existing.setRatings(request.getRatings());

        categoryHasMovieRepository.deleteByMovies_id(id);
        Movie updatedMovie = movieRepository.save(existing);



        System.out.println("Movie Updated: " + updatedMovie.getId());
        //    Map categories
        List<Integer> catIds = request.getCategoryId();



        if (catIds != null && !catIds.isEmpty()) {
            List<CategoryHasMovie> relations = catIds.stream().map(categoryId -> {
                Category cat = categoryRepository.findCategoryById(categoryId);
                if (cat == null) {
                    throw new RuntimeException("Category not found: " + categoryId);
                }

                CategoryHasMovie joinTable = new CategoryHasMovie();
                joinTable.setCategory(cat);
                joinTable.setMovies(updatedMovie);
                return joinTable;
            }).collect(Collectors.toList());

            //  Save all relations at once instead of in a loop
            categoryHasMovieRepository.saveAll(relations);
        }
        return "Update Successful";
    }


    public String createMovie(MovieRequest request) {

        Movie movie = new Movie();
        movie.setName(request.getName());
        movie.setLanguage(request.getLanguage());
        movie.setCountry(request.getCountry());
        movie.setHours(request.getHours());
        movie.setYear(request.getYear());
        movie.setDescription(request.getDescription());
        movie.setImage(request.getImage());
        movie.setLink(request.getLink());
        movie.setTrailerlink(request.getTrailerlink());
        movie.setImdb(request.getImdb());
        movie.setTomato(request.getTomato());
        movie.setViewcount(request.getViewcount());
        movie.setPrice(request.getPrice());
        movie.setRatings(request.getRatings());
        Movie savedMovie = movieRepository.save(movie);

        System.out.println("Movie saved: " + savedMovie.getId());

        List<Integer> actorsId = request.getActorsId();
        if (actorsId != null && !actorsId.isEmpty()) {
           actorsId.forEach(actorId -> {
               ActorsHasMovie actorsHasMovie = new ActorsHasMovie();
               Optional<Actor> actor = actorsRepository.findById(actorId);
               actor.ifPresent(eachactor -> {
                   actorsHasMovie.setActors(eachactor);
                   actorsHasMovie.setMovies(savedMovie);
                   actors_has_moviesRepository.save(actorsHasMovie);
               });
           });
        }

        //    Map categories
        List<Integer> catIds = request.getCategoryId();

        if (catIds != null && !catIds.isEmpty()) {
            List<CategoryHasMovie> relations = catIds.stream().map(categoryId -> {
                Category cat = categoryRepository.findCategoryById(categoryId);
                if (cat == null) {
                    throw new RuntimeException("Category not found: " + categoryId);
                }

                CategoryHasMovie joinTable = new CategoryHasMovie();
                joinTable.setCategory(cat);
                joinTable.setMovies(savedMovie);
                return joinTable;
            }).collect(Collectors.toList());

            //  Save all relations at once instead of in a loop
            categoryHasMovieRepository.saveAll(relations);
        }
        return "Create Successful";
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

    @Transactional
    public void deleteMovie(Integer id) {
        movieRepository.findById(id).ifPresent(movie -> {

            // 1. Delete Category link associations
            List<CategoryHasMovie> cats = categoryHasMovieRepository.findByMovies_id(id);
            categoryHasMovieRepository.deleteAll(cats);

            // 2. Clear transactional history logs
            rentalsRepository.deleteByMovies_Id(id);

            // 3. Find all reviews connected to this movie
            List<Review> reviews = reviewRepository.findByMovies_id(id);

            // 4. Cascade delete all nested child replies linked to those reviews first
            reviews.forEach(review -> {
                replyRepository.deleteByReview_Movies_Id(review.getId());
            });

            // 5. Safely drop the orphan review records now that child constraints are cleared
            reviewRepository.deleteAll(reviews);

            // 6. Finally, drop the primary movie node completely
            movieRepository.delete(movie);
        });
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