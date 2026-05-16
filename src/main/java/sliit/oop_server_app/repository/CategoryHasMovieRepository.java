package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.CategoryHasMovie;

import java.util.List;

@Repository
public interface CategoryHasMovieRepository extends JpaRepository<CategoryHasMovie, Integer> {

    List<CategoryHasMovie> findByMovies_id(Integer moviesId);
    List<CategoryHasMovie> findByCategory_id(Integer categoryId);

    void deleteByMovies_id(Integer id);
}
