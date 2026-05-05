package sliit.oop_server_app.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT DISTINCT m FROM Movie m " +
            "LEFT JOIN FETCH m.categoryHasMovies chm " +
            "LEFT JOIN FETCH chm.category")
    List<Movie> findAllWithCategories();
    List<Movie> findByNameContainingIgnoreCase(String name);
//
//    List<Movie> findAllByYear(String year);
//    boolean existsByYear(String year);
}
