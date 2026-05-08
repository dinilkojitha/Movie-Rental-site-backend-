package sliit.oop_server_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

//    List<Movie> findAllWithCategories();
    List<Movie> findByNameContainingIgnoreCase(String name);
//    List<Movie> findAll();
}
