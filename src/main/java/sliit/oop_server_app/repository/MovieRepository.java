package sliit.oop_server_app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sliit.oop_server_app.entity.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String>{

    List<Movie> findByNameContainingIgnoreCase(String name);

    List<Movie> findAllByYear(String year);
    boolean existsByYear(String year);
}
