package sliit.oop_server_app.Service;

import org.checkerframework.checker.units.qual.Acceleration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.DTO.MovieResponse;
import sliit.oop_server_app.entity.*;
import sliit.oop_server_app.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryHasMovieRepository categoryHasMovieRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Actors_has_moviesRepository actors_has_moviesRepository;


    public List<Category> get() {
        return categoryRepository.findAll();
    }


    public Category save( Category categories) {
        return categoryRepository.save(categories);
    }

    public String deleteCategory( int id) {
        if (categoryRepository.existsById(id)) {
            List<CategoryHasMovie> all = categoryHasMovieRepository.findByCategory_id(id);
            categoryHasMovieRepository.deleteAll(all);
            all.forEach(singleCategoryHasMovie -> {
                movieRepository.delete(singleCategoryHasMovie.getMovies());
            });
            categoryRepository.deleteById(id);
            return "Category deleted successfully!";
        } else {
            return "Category deleted failed!";
        }
    }

    public List<MovieResponse> getByCategoryFilterd(int id){
        List<CategoryHasMovie> categoryHasMovie = categoryHasMovieRepository.findByCategory_id(id);
        List<MovieResponse> movies = new ArrayList<>();
        categoryHasMovie.forEach(categoryHasMovie1 -> {
            var movie = categoryHasMovie1.getMovies();

            MovieResponse movieResponse = new MovieResponse();

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
            movies.add(movieResponse);
        });

        return movies;
    }

}
