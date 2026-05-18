package sliit.oop_server_app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sliit.oop_server_app.entity.Actor;
import sliit.oop_server_app.entity.Category;
import sliit.oop_server_app.entity.CategoryHasMovie;
import sliit.oop_server_app.entity.Movie;
import sliit.oop_server_app.repository.ActorsRepository;
import sliit.oop_server_app.repository.CategoryHasMovieRepository;
import sliit.oop_server_app.repository.CategoryRepository;
import sliit.oop_server_app.repository.MovieRepository;

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


    public List<Category> get() {
        return categoryRepository.findAll();
    }


    public Category save( Category categories) {
        return categoryRepository.save(categories);
    }

    public String deleteCategory( int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category deleted successfully!";
        } else {
            return "Category deleted failed!";
        }
    }

    public List<Movie> getByCategoryFilterd(int id){
        List<CategoryHasMovie> categoryHasMovie = categoryHasMovieRepository.findByCategory_id(id);
        List<Movie> movies = new ArrayList<>();
        categoryHasMovie.forEach(categoryHasMovie1 -> {
            movies.add(categoryHasMovie1.getMovies());
        });

        return movies;
    }

}
